/**  
 * Project Name:platform_utils  
 * File Name:XlsUtil.java  
 * Package Name:com.zwsafety.platform.utils  
 * Date:2015-7-8上午11:33:12  
 * Copyright (c) 2015, peij@zwsafety.com All Rights Reserved.   
 */

package com.zwsafety.platform.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.zwsafety.platform.utils.entity.ValidationBean;

/**
 * @ClassName:XlsUtil
 * @Description: 读取.xls文件
 * @date: 2015-7-8 上午11:33:12
 * @author: luyao
 * @version:
 * @since: JDK 1.6
 */

public final class ExcelUtil {
    /**
     * excel的最大行数
     */
    private static final int EXCELMAXROW = 65530;
    
    private static int firstRowNum = 2;
    
	public static int getFirstRowNum() {
		return firstRowNum;
	}

	public static void setFirstRowNum(int firstRowNum) {
		ExcelUtil.firstRowNum = firstRowNum;
	}

	/**
     * logger
     */
    private static final Logger LOG = Logger.getLogger(ExcelUtil.class);
    
    /**
     * 读取excel中的文本，替换成存入数据库中的值
     */
    private static Map<String,String> textValueMap = new HashMap<String, String>();
    
    static{
    	textValueMap.put("男","0");
    	textValueMap.put("女","1");
        
    	textValueMap.put("正常","0");
        textValueMap.put("暂停","1");
        textValueMap.put("过期","2");
    }
    
    /**
     * Creates a new instance of XlsUtil.
     */
    private ExcelUtil() {
    }

    /**
     * 
     * @Title:读取excel文件
     * @date: 2015-7-8 下午3:01:35
     * @author: luyao
     * @param path
     *            文件全路径
     * @param items
     *            导出的字段，对应表格字段
     * @param clazz
     *            实体类
     * @return List<Object>
     * @throws Exception
     *             异常
     */
    public static List<Object> read(String path, String[] items, Class<?> clazz)
            throws Exception {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<Object> list = new ArrayList<Object>();
        Map<String, Object> map = null;
        // Sheet1
        HSSFSheet hssfSheet = hssfWorkbook.getSheet("Sheet1");
        // 循环行Row
        for (int rowNum = firstRowNum; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            HSSFRow hssfRow = hssfSheet.getRow(rowNum);
            if (hssfRow != null) {
                map = new HashMap<String, Object>();
                for (int i = 0; i < items.length; i++) {
                    map.put(items[i], getValue(hssfRow.getCell(i)));
                }
                Object obj = clazz.newInstance();
//                Object object = BeanUtil.toObject(map, clazz);
                BeanUtils.populate(obj,map);
                list.add(obj);
            }
        }
        hssfWorkbook.close();
        return list;
    }
    /**
    * @email:335892083@qq.com 
    * @Title:export
    * @Description TODO(导入到excel表格 并插入图片). 
    * @date  2016年6月15日 上午9:27:43 
    * @author lzqiangPC  
    * @param title
    * @param parameters
    * @param list
    * @param request
    * @param response
     */
    public static void export(String title, String[][] parameters,
            List<Map<String, Object>> list,HttpServletRequest request,HttpServletResponse response) {
    	export(title, parameters, list, request, response,null);
    }
    /**
    * @email:335892083@qq.com 
    * @Title:export
    * @Description TODO(导入到excel表格). 
    * @date  2016年6月15日 上午9:27:29 
    * @author lzqiangPC  
    * @param title
    * @param parameters
    * @param list
    * @param request
    * @param response
    * @param picturePath
     */
    public static void export(String title, String[][] parameters,
            List<Map<String, Object>> list,HttpServletRequest request,HttpServletResponse response,byte[] picturePath) {
        String downloadFilename;//下载后的文件名称
        String filename = "";
        OutputStream out = null;
        int count = (int) Math.ceil((double)list.size() / EXCELMAXROW);// 产生excel的个数
        if(count>1){
            downloadFilename =  DateUtil.formatDate(new Date(),"yyyyMMddHHmmss")+".zip";
        }else{
            downloadFilename =  DateUtil.formatDate(new Date(),"yyyyMMddHHmmss")+".xls";
        }
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", "attachment;filename=\""
                +downloadFilename + "\"");
        
        if(count>1){//进行切分导出
            File[] files = new File[count];
            try {
                String filePath = ResourceUtil.getUploadRootPath(request)
                        + "ExcelFolderTemp";
                File dirPath = new File(filePath);
                if (!dirPath.exists()) {
                    dirPath.mkdirs();
                }
                
                for (int i = 0; i < count; i++) {
                    filename = filePath + "/excel" + i + ".xls";
                    files[i] = new File(filename);
                    out = new FileOutputStream(new File(filename));
                    if(i==(count-1)){
                        baseexport(title, parameters,
                                list.subList(i * EXCELMAXROW,list.size()),
                                out,picturePath);
                    }else{
                        baseexport(title, parameters,
                                list.subList(i * EXCELMAXROW, (i + 1) * EXCELMAXROW),
                                out,picturePath); 
                    }
                }
                zipDownLoad(files,response);
                FileUtil.deleteDirs(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
            	 if(null!=out){
            		 try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();  
					}
            	 }
            }
        }else{
            try {
                baseexport(title, parameters, list,response.getOutputStream(),picturePath);
            } catch (Exception e) {
                e.printStackTrace();  
            }
        }
    }

    /**
     * @Title:导出Excel.
     * @date: 2015-7-10 下午12:43:35
     * @author: luyao
     * @param title
     *            表格标题(可为空)
     * @param parameters
     *            需要导出的字段及其文本含意
     * @param list
     *            数据集合
     * @param out
     *            文件输出流
     * @return
     * @throws Exception
     *             异常
     */
    private static void baseexport(String title, String[][] parameters,
            List<Map<String, Object>> list, OutputStream out,byte[] pictureBytes) throws Exception {
        // 工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        // 工作表
        HSSFSheet sheet = wb.createSheet("Sheet1");

        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
        // 生成标题样式
        HSSFCellStyle style1 = wb.createCellStyle();
        // 设置这些样式
        style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style1.setFillForegroundColor(HSSFColor.WHITE.index);
        style1.setWrapText(true);
        // 生成一个字体
        HSSFFont font1 = wb.createFont();
        font1.setFontHeightInPoints((short) 12);
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style1.setFont(font1);

        // 生成数据样式
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setWrapText(true);
        // 生成另一个字体
        HSSFFont font2 = wb.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);
        
        // 设置表格标题行
        int index = 0;
        // 产生表格列标行
        HSSFRow row = sheet.createRow(index);
        if (title != null && !title.equals("")) {
            // 合并单元格
            CellRangeAddress cra = new CellRangeAddress(0, 0, 0,
                    parameters.length - 1);
            // 在sheet里增加合并单元格
            sheet.addMergedRegion(cra);
            Cell cell1 = row.createCell(0);
            cell1.setCellStyle(style1);
            cell1.setCellValue(title);
            index++;
        }
        row = sheet.createRow(index);
        for (int i = 0; i < parameters.length; i++) {
        	Cell cell = row.createCell(i);
            cell.setCellStyle(style1);
            HSSFRichTextString text = new HSSFRichTextString(parameters[i][1]);
            cell.setCellValue(text);
        }

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(++index);
            Map<String, Object> map = list.get(i);
            for (int j = 0; j < parameters.length; j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellStyle(style2);
                if (map.containsKey(parameters[j][0])) {
                    cell.setCellValue(String.valueOf(map.get(parameters[j][0])==null?"":map.get(parameters[j][0])));
                }
            }
        }
        
        if(null!=pictureBytes && pictureBytes.length>0){
	        // 插入 PNG 图片至 Excel
	        int pictureIdx = wb.addPicture(pictureBytes, Workbook.PICTURE_TYPE_PNG);
	
	        CreationHelper helper = wb.getCreationHelper();
	        Drawing drawing = sheet.createDrawingPatriarch();
	        ClientAnchor anchor = helper.createClientAnchor();
	
	        // 图片插入坐标
	        anchor.setCol1(0);
	        anchor.setRow1(list.size()+2);
	        // 插入图片
	        Picture pict = drawing.createPicture(anchor, pictureIdx);
	        pict.resize();
        }

        wb.write(out);
        wb.close();
    }

    /**
     * @Title:getValue(转换数据类型).
     * @date: 2015-7-2 上午11:45:16
     * @author: luyao
     * @param cell
     *            HSSFCell
     * @return Object
     */
    public static Object getValue(HSSFCell cell) {
        String returnValue = "";
        SimpleDateFormat dateFormat = null;
        if (null != cell) {
            switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC: // 数字
            	if (HSSFDateUtil.isCellDateFormatted(cell)) {
            		Date date = cell.getDateCellValue();
            		return date;
            	}else{
	                Double doubleValue = cell.getNumericCellValue();
	                String str = doubleValue.toString();
	                str= new BigDecimal(str).toPlainString(); 
	                if (str.contains(".0")) {
	                    str = str.replace(".0", "");
	                }
	                return str;
            	}
            case HSSFCell.CELL_TYPE_STRING: // 字符串
                returnValue = cell.getStringCellValue();
                //将excel中的性别等 转成0,1存入数据库
                returnValue = textValueMap.get(returnValue)==null?returnValue:textValueMap.get(returnValue);
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false);
                try {
                    Date date = dateFormat.parse(returnValue);
                    return date;
                } catch (Exception e) {
                	try{
	                	 Integer value = Integer.parseInt(returnValue);
	                	 return value;
                    }catch(Exception e1){
                    	return returnValue;
                    }
                }
            case HSSFCell.CELL_TYPE_BOOLEAN: // 布尔
                Boolean booleanValue = cell.getBooleanCellValue();
                returnValue = booleanValue.toString();
                break;
            case HSSFCell.CELL_TYPE_BLANK: // 空值
                returnValue = null;
                break;
            case HSSFCell.CELL_TYPE_FORMULA: // 公式
                returnValue = cell.getCellFormula();
                break;
            case HSSFCell.CELL_TYPE_ERROR: // 故障
                returnValue = "";
                break;
            default:
                LOG.info("未知类型!");
                break;
            }
        }
        return returnValue;
    }

    /**
     * @Title:zipDownLoad
     * @Description TODO(zip打包下载).
     * @date 2015年8月21日
     * @author lzqiangPC
     * @param files
     * @param out
     * @throws Exception
     */
    public static void zipDownLoad(File[] files,HttpServletResponse response)
            throws Exception {
        OutputStream out = response.getOutputStream();
        ZipOutputStream zos = new ZipOutputStream(out);
        try{
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                zos.putNextEntry(new ZipEntry(f.getName()));
                FileInputStream fis = new FileInputStream(f);
                byte[] buffer = new byte[1024];
                int r = 0;
                while ((r = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, r);
                }
                fis.close();
            }
        }catch(Exception e){
            LOG.error(e);
        }finally{
            zos.flush();
            zos.close();
            out.close();
        }
    }
/*********************excel表格读取和校验开始************************************************************************/    
    /**
     * 读取excel表格内容,将内容放入list中
     * @param path excel上传的服务器后的路径
     * @param items excel表格的对应的子都数组
     * @return
     * @throws Exception
     */
    public static List<Map<String,Object>> read(String path, String[] items)
            throws Exception {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = null;
        // Sheet1
        HSSFSheet hssfSheet = hssfWorkbook.getSheet("Sheet1");
        // 循环行Row
        for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            HSSFRow hssfRow = hssfSheet.getRow(rowNum);
            if (hssfRow != null) {
                map = new HashMap<String,Object>();
                for (int i = 0; i < items.length; i++) {
                    map.put(items[i],ExcelUtil.getValue(hssfRow.getCell(i)));
                }
                list.add(map);
            }
        }
        hssfWorkbook.close();
        return list;
    }
    
    /**
     * 校验excel并将结果封装到bean中放入到list中 并校验数据合法性
     * @param list 从excel表格中读取的list
     * @param clazz 保存的实体类
     * @param controllerId 所在的控制器类的id 使用注解 默认值时类的首字母小写
     * @param validationstr 校验用的json字符串
     * @return Object  如果object为String类型则是返回的错误信息，如果为list则是返回的 Bean的list集合
     * @throws Exception
     */
	public static Object validation(List<Map<String,Object>> list,Class<?> clazz,String controllerId,String validationstr) throws Exception{
    	List<Object> listbean = new ArrayList<Object>();
        Map<String, Object> params = null;
    	ValidationBean[] validation;
    	String type = "";
    	try{//解析json字符串
    		validation = JSONUtil.json2obj(validationstr,ValidationBean[].class);
    	}catch(Exception e){
    		e.printStackTrace();
    		throw new RuntimeException("校验json解析错误,查看json是否正确");
    	}
    	for(int i=0;i<list.size();i++){
    		params = new HashMap<String, Object>();
			for(ValidationBean obj:validation){
				if(obj.fieldname == null){
					throw new RuntimeException("校验json错误,fieldname字段不可为空");
				}
				Object fieldvalue= list.get(i).get(obj.fieldname);
				if(obj.type == null){
					throw new RuntimeException("校验json错误,type字段不可为空");
				}
				
				if(obj.type == null){
					type = "java.lang.String";
				}else{
					type = obj.type;
				}
				if(fieldvalue!=null && !type.equals("java.lang.String") && !fieldvalue.getClass().getName().toLowerCase().equals(type.toLowerCase())){//类型是否正确
					return fieldvalue+"格式错误";
				}
				
				if(obj.notempty && obj.remote==null){//类型正确,判断是否可以为空
					//不可为空
					if(fieldvalue == null || StringUtil.isBlank(fieldvalue.toString())){
						return obj.message;
					}
				}
				//类型正确,判断长度是否正确,时间类型不校验
				if(fieldvalue!=null && !(fieldvalue instanceof Date) && !(fieldvalue instanceof Number) &&(fieldvalue.toString().length()<obj.length.min || fieldvalue.toString().length()>obj.length.max)){
					return obj.message;
				}
				
				if(obj.remote!=null){
					Map<String, String> invokeMap = invokeMethod(obj.remote.method, obj.remote.paramnames,list.get(i),controllerId);
					if(invokeMap!=null && invokeMap.get("errormsg")!=null){//有错误信息
						return invokeMap.get("errormsg");
					}else if(invokeMap!=null && invokeMap.get("idvalue")!=null){//有要存入数据库中的返回值
						fieldvalue = invokeMap.get("idvalue");
					}
				}
				//将字段和值添加到params中，然后进行封装到Bean中
				params.put(obj.fieldname,fieldvalue);
			}
			Object clazzs = clazz.newInstance();
			BeanUtils.populate(clazzs,params);
			listbean.add(clazzs);
    	}
    	return listbean;
    }
	
	/**
     * 利用反射执行方法
     * @param methods
     * @param params
     * @param map
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map<String,String> invokeMethod(String methods,String params,Map<String,Object> map,String controllerId){
    	if(!StringUtil.isBlank(methods) && !StringUtil.isBlank(params)){
	    	String [] paramArray = params.split(",");
			Class[] clazz = new Class[paramArray.length];
	    	Object[] obj = new Object[paramArray.length];
	    	for(int i=0;i<paramArray.length;i++){
	    		clazz[i] = String.class;
	    		obj[i] = map.get(paramArray[i]);
	    	}
	    	try {
	    		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
	    		Object controller = wac.getBean(controllerId);
				Method method = controller.getClass().getMethod(methods,clazz);
				return (Map<String, String>) method.invoke(controller,obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
		return null;
    }
/***************************excel表格读取和校验结束******************************************************************/        
    public static void main(String[] args) throws Exception {
        String[][] parameters = { { "detid", "探测器编号" }, { "detname", "探测器名称" },
                { "unitname", "单位" } };
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        HashMap<String, Object> map = null;
        for (int i = 0; i <= 200; i++) {
            map = new HashMap<String, Object>();
            map.put("detid", i);
            map.put("detname", "探测器名称探测器名称探测器名称探测器名称" + i);
            map.put("unitname", "单位" + i);
            list.add(map);
        }
        OutputStream out = new FileOutputStream(new File("d:/excel.xls"));
//        FileInputStream fis = new FileInputStream("C:/Users/刘赵强/Desktop/刘赵强.gif");
//        baseexport(null, parameters, list, out,IOUtils.toByteArray(fis));
        baseexport("hehehe", parameters, list, out,null);
    }
}

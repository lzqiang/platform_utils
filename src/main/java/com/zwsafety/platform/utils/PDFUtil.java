package com.zwsafety.platform.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PushbuttonField;

/**
 * PDF工具类
 * 
 * @author xufeng
 * @date:2015年6月13日
 */
public final class PDFUtil {
    /**
    * Creates a new instance of PDFUtil.
     */
    private PDFUtil() {
        // TODO Auto-generated constructor stub  
    }

    /**
    * @Title:exportByTemplate
    * @Description TODO(根据模板导出pdf). 
    * @date  2015年7月29日 
    * @author luyao  
    * @param templatePath 模板文件目录
    * @param data 数据集
    * @param attachPaths 图片
    * @param request HttpServletRequest
    * @param response HttpServletResponse
     */
    public static void exportByTemplate(String templatePath,
            Map<String, String> data, Map<String, byte[]> attachPaths,
            HttpServletRequest request, HttpServletResponse response) {
        OutputStream baos = null;
        PdfStamper stamp = null;
        PdfReader reader = null;
        try {
            response.setContentType("application/pdf;charset=utf-8");
            // response.setContentType("application/x-msdownload;charset=GBK");
            /* 如果想出来让IE提示你是打开还是保存的对话框，加上下面这句就可以了 */
            String finalFileName = URLEncoder.encode(
                    "用户确认单" + DateUtil.formatDate(new Date(), "yyyy-MM-dd")
                            + ".pdf", "UTF8");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + finalFileName);
            // 读取pdf模板
            reader = new PdfReader(templatePath);
            baos = response.getOutputStream();
            stamp = new PdfStamper(reader, baos);
            AcroFields form = stamp.getAcroFields();
            form.addSubstitutionFont(BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED)); // 简宋体
            // 文本信息
            for (String key : data.keySet()) {
                // 替换模板内容
                form.setField(key, data.get(key));
            }

            // 插入图片
            for (String attachPath : attachPaths.keySet()) {
                Image image = Image.getInstance(attachPaths.get(attachPath));
                PushbuttonField bt = form.getNewPushbuttonFromField(attachPath);
                bt.setLayout(PushbuttonField.LAYOUT_ICON_ONLY);
                bt.setProportionalIcon(false);
                bt.setImage(image);
                form.replacePushbuttonField(attachPath, bt.getField());
            }

            stamp.setFormFlattening(true);
            stamp.close();
            // response.setContentLength(10000);
            /*
             * Document doc = new Document(); PdfCopy pdfCopy = new PdfCopy(doc,
             * output); doc.open();
             * 
             * PdfImportedPage impPage = pdfCopy.getImportedPage(new
             * PdfReader(baos.toByteArray()), 1); pdfCopy.addPage(impPage);
             * doc.close(); //当文件拷贝 记得关闭doc
             */
            try {
                reader.close();
                baos.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}

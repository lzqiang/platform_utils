package com.zwsafety.platform.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName:FileUtil
 * @Description:TODO(文件上传工具类)
 * @date:2015年7月28日
 * @author luyao
 * @version 1.0
 * @since JDK 1.7
 */
public final class FileUtil {

    /**
     * 读取文本文件(.txt)内容
     * 
     * @param filePathAndName
     *            带有完整绝对路径的文件名
     * @param encoding
     *            文本文件打开的编码方式
     * @return 返回文本文件的内容
     * @exception IOException
     *                io异常
     */
    public static String readTxt(String filePathAndName, String encoding)
            throws IOException {
        encoding = encoding.trim();
        StringBuffer str = new StringBuffer("");
        String st = "";
        BufferedReader br = null;
        try {
            FileInputStream fs = new FileInputStream(filePathAndName);
            InputStreamReader isr;
            if (encoding.equals("")) {
                isr = new InputStreamReader(fs);
            } else {
                isr = new InputStreamReader(fs, encoding);
            }
            br = new BufferedReader(isr);
            try {
                String data = "";
                while ((data = br.readLine()) != null) {
                    str.append(data + " ");
                }
            } catch (Exception e) {
                str.append(e.toString());
            }
            st = str.toString();
        } catch (IOException es) {
            st = "";
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return st;
    }

    /**
     * @Title:getUploadPath
     * @Description TODO(获得上传文件路径).
     * @date 2015年7月28日
     * @author luyao
     * @param secondPath
     *            自定义目录
     * @param file
     *            文件
     * @return String
     * @throws Exception
     *             异常
     */
    public static String getUploadPath(String secondPath, MultipartFile file)
            throws Exception {
        String filename = file.getOriginalFilename();
        String[] names = filename.split("\\.");
        if (names.length > 1) {
            // 添加时间后缀
            filename = names[0] + "_" + new Date().getTime() + "."
                    + names[names.length - 1];
        }
        return secondPath + filename;
    }

    /**
     * @Title:upload
     * @Description TODO(文件本地保存).
     * @date 2015年7月28日
     * @author luyao
     * @param request
     *            HttpServletRequest
     * @param path
     *            自定义目录
     * @param file
     *            MultipartFile
     * @throws Exception
     *             异常
     */
    public static void upload(HttpServletRequest request, String path,
            MultipartFile file) throws Exception {
        String filePath = ResourceUtil.getUploadRootPath(request) + path;
        File dirPath = new File(filePath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        file.transferTo(new File(filePath));
    }

    /**
     * @Title:delete
     * @Description TODO(删除文件).
     * @date 2015年7月28日
     * @author luyao
     * @param request
     *            HttpServletRequest
     * @param filePath
     *            文件路径
     * @return boolean
     */
    public static boolean delete(HttpServletRequest request, String filePath) {
        String path = ResourceUtil.getUploadRootPath(request) + filePath;
        File delFile = new File(path);
        if (delFile.isFile() && delFile.exists()) {
            try {
                delFile.delete();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * @Title:checkFileExist
     * @Description TODO(检查文件是否已经存在 若存在，则返回true 若不存在，则返回false).
     * @date 2015年7月23日
     * @author luyao
     * @param fileString
     *            String
     * @param dirString
     *            String
     * @return boolean
     */
    public boolean checkFileExist(String fileString, String dirString) {
        String[] dirStrings = new File(dirString).list();
        boolean temp = false;
        for (int i = 0, len = dirStrings.length; i < len; i++) {
            if (fileString.equals(dirStrings[i])) {
                temp = true;
                break;
            }
        }
        return temp;
    }

    /**
     * @Title:copyDirectiory
     * @Description TODO(利用java本地拷贝文件及文件夹).
     * @date 2015年7月28日
     * @author luyao
     * @param objDir
     *            目标文件夹
     * @param srcDir
     *            源的文件夹
     * @throws IOException
     *             io异常
     */
    public static void copyDirectiory(String objDir, String srcDir)
            throws IOException {
        (new File(objDir)).mkdirs();
        File[] file = (new File(srcDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                FileInputStream input = new FileInputStream(file[i]);
                FileOutputStream output = new FileOutputStream(objDir + "/"
                        + file[i].getName());
                byte[] b = new byte[1024 * 5];
                int len;
                while ((len = input.read(b)) != -1) {
                    output.write(b, 0, len);
                }
                output.flush();
                output.close();
                input.close();
            }
            if (file[i].isDirectory()) {
                copyDirectiory(objDir + "/" + file[i].getName(), srcDir + "/"
                        + file[i].getName());
            }
        }
    }

    /**
     * @Title:stringToFile
     * @Description TODO(将String写入指定文件中(会覆盖原文件数据)).
     * @date 2015年7月28日
     * @author luyao
     * @param text
     *            源String
     * @param fileName
     *            目标文件路径
     * @throws IOException
     *             io异常
     */
    public static void stringToFile(String text, String fileName)
            throws IOException {
        BufferedWriter os = new BufferedWriter(new FileWriter(fileName));
        os.write(text);
        os.flush();
        os.close();
    }

    /**
     * @Title:fileToBytes
     * @Description TODO(获取文件filePath的字节编码byte[]).
     * @date 2015年7月28日
     * @author luyao
     * @param filePath
     *            文件全路径
     * @return 文件内容的字节编码
     */
    public static byte[] fileToBytes(String filePath) {
        if (filePath == null) {
            return null;
        }
        File tmpFile = new File(filePath);
        byte[] retBuffer = new byte[(int) tmpFile.length()];
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            fis.read(retBuffer);
            fis.close();
            return retBuffer;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * @Title:bytesToFile
     * @Description TODO(将byte[]转化成文件fullFilePath).
     * @date 2015年7月28日
     * @author luyao
     * @param fullFilePath
     *            文件全路径
     * @param content
     *            源byte[]
     */
    public static void bytesToFile(String fullFilePath, byte[] content) {
        if (fullFilePath == null || content == null) {
            return;
        }
        // 创建相应的目录
        File f = new File(getDir(fullFilePath));
        if (f == null || !f.exists()) {
            f.mkdirs();
        }
        try {
            FileOutputStream fos = new FileOutputStream(fullFilePath);
            fos.write(content);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Title:getDir
     * @Description TODO(根据传入的文件全路径，返回文件所在路径).
     * @date 2015年7月28日
     * @author luyao
     * @param fullPath
     *            文件全路径
     * @return 文件所在路径
     */
    public static String getDir(String fullPath) {
        int iPos1 = fullPath.lastIndexOf("/");
        int iPos2 = fullPath.lastIndexOf("\\");
        iPos1 = (iPos1 > iPos2 ? iPos1 : iPos2);
        return fullPath.substring(0, iPos1 + 1);
    }

    /**
     * @Title:getFileName
     * @Description TODO(根据传入的文件全路径，返回文件全名（包括后缀名）).
     * @date 2015年7月28日
     * @author luyao
     * @param fullPath
     *            文件全路径
     * @return 文件全名（包括后缀名）
     */
    public static String getFileName(String fullPath) {
        int iPos1 = fullPath.lastIndexOf("/");
        int iPos2 = fullPath.lastIndexOf("\\");
        iPos1 = (iPos1 > iPos2 ? iPos1 : iPos2);
        return fullPath.substring(iPos1 + 1);
    }

    /**
     * @Title:getFileSuffix
     * @Description TODO(获得文件名fileName中的后缀名).
     * @date 2015年7月28日
     * @author luyao
     * @param fileName
     *            源文件名
     * @return String 后缀名
     */
    public static String getFileSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1,
                fileName.length());
    }
    
    /**
     * @Title:replaceFileSuffix
     * @Description TODO(获得文件名fileName中的后缀名).
     * @date 2015年7月28日
     * @author luyao
     * @param fileName
     *            源文件名
     * @return String 后缀名
     */
    public static String replaceFileSuffix(String fileName,String newFileSuffix) {
        return fileName.replace(getFileSuffix(fileName),newFileSuffix);
    }


    /**
     * @Title:wrapFilePath
     * @Description TODO(转换文件路径中的\\为/).
     * @date 2015年7月28日
     * @author luyao
     * @param filePath
     *            要转换的文件路径
     * @return String
     */
    public static String wrapFilePath(String filePath) {
        filePath.replace('\\', '/');
        if (filePath.charAt(filePath.length() - 1) != '/') {
            filePath += "/";
        }
        return filePath;
    }

    /**
     * @Title:deleteDirs
     * @Description TODO(删除整个目录path,包括该目录下所有的子目录和文件).
     * @date 2015年7月28日
     * @author luyao
     * @param path
     *            文件全路径
     */
    public static void deleteDirs(String path) {
        if (path == null) {
            return;
        }
        File rootFile = new File(path);
        File[] files = rootFile.listFiles();
        if (files == null) {
            return;
        }
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isDirectory()) {
                deleteDirs(file.getPath());
            } else {
                file.delete();
            }
        }
        rootFile.delete();
    }
    /**
     * 附件下载
     * @param filename 文件名
     * @param path 文件路径
     * @param response 
     */
    public static void download(String filename,String path,HttpServletResponse response){
		InputStream in = null;
		OutputStream os = null;
		try{
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;filename="+
			new String(filename.getBytes("utf-8"),"ISO-8859-1"));
			File file = new File(path); 
			if (file.exists()) {  
				in = new FileInputStream(path);
				os = response.getOutputStream();
				byte[] b = new byte[1024 * 1024];
				int length;
				while ((length = in.read(b)) > 0) {
					os.write(b, 0, length);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(in!=null){
					in.close();
				}
				if(os!=null){
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    /**
     * 打开pdf文件
     * @param filename 文件名
     * @param path 文件路径
     * @param response 
     */
    public static void openPdf(String path,HttpServletResponse response){ 
    	try {
    		response.setContentType("application/pdf");
		    FileInputStream in;
			in = new FileInputStream(new File(path));
		    OutputStream out = response.getOutputStream();
		    byte[] b = new byte[512];
		    while ((in.read(b)) != -1) {
		        out.write(b);
		    }
		    out.flush();
		    in.close();
		    out.close();
	    } catch (Exception e) {
			e.printStackTrace();
		}
    }
    /**
     * 判断文件是否为图片
     * @param name
     * @return
     */
    public static boolean isImage(String name){
    	try {  
    	   BufferedImage image=ImageIO.read(new File(name));  
    	    if (image == null) {  
    	        return false;
    	    } 
    	} catch(IOException ex) {  
    	   return false;
    	} 
    	return true;
    }
}

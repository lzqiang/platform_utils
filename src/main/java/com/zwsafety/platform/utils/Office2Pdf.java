package com.zwsafety.platform.utils;

import java.io.File;



import org.apache.log4j.Logger;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;



/**
 * 需要安装openoffice软件
 * OpenOffice_HOME为openoffice软件的安装目录
 * OpenOffice_IP为安装地址 
 * OpenOffice_PORT：8100端口号
 * @author lzqiangPC
 *
 */
public class Office2Pdf {
	 /**
     * Logger
     */
    private static Logger logger = Logger.getLogger(JSONUtil.class);
    
	private static final String  PDF = "pdf";
	
	private static String OpenOffice_HOME="";
	private static String OpenOffice_IP="";
	private static String OpenOffice_PORT="";
	
	static{
		OpenOffice_HOME = ResourceUtil.getConfigByName("OpenOffice_HOME");
		OpenOffice_IP = ResourceUtil.getConfigByName("OpenOffice_IP");
		OpenOffice_PORT = ResourceUtil.getConfigByName("OpenOffice_PORT");
	}
	
	public void docToPdf(File inputFile, File outputFile){
		//启动服务
		if(OpenOffice_HOME.charAt(OpenOffice_HOME.length()-1)!='/'){
			OpenOffice_HOME+="/";
		}
		Process pro = null;
		OpenOfficeConnection connection = null;
		 // 启动OpenOffice的服务   
        String command = OpenOffice_HOME + "program/soffice.exe -headless -accept=\"socket,host="+OpenOffice_IP+",port="+OpenOffice_PORT+";urp;\"";
		 // connect to an OpenOffice.org instance running on port 8100
        DocumentConverter converter =null;
	    try{
	    	pro = Runtime.getRuntime().exec(command);
	    	connection = new SocketOpenOfficeConnection(8100);
	    	connection.connect();
	    	
	    	 // convert
	    	converter = new StreamOpenOfficeDocumentConverter(connection);
	    	logger.info(inputFile+"="+outputFile);
		    converter.convert(inputFile, outputFile);
		    logger.info("生成"+outputFile.getName());
	    }catch(Exception ex){
	    	logger.info("程序出错了");
	    	ex.printStackTrace();
	    	
	    }finally{
		    // close the connection
	    	if(connection!=null){
	    		connection.disconnect();
	    		connection = null;
	    	}
	    	pro.destroy();
	    	converter = null;
	    }
	}
	
	public static void office2pdf(String filename){
		if(FileUtil.getFileSuffix(filename).toLowerCase().equals(PDF ) || FileUtil.isImage(filename)){
			return ;
		}
		TestThread t1 = new Office2Pdf.TestThread();
		// 输入文件名
		t1.setInputFile(new File(filename));
		// 获得"."前面的文件名并将其输入为PDF
		t1.setOutputFile(new File(filename.substring(0,
				filename.lastIndexOf("."))
				+ ".pdf"));
		t1.start();
	}
	
	//生产pdf线程
	static class TestThread extends java.lang.Thread{
		private File inputFile;
		private File outputFile;
		
		public void run(){
			Office2Pdf t = new Office2Pdf();
			t.docToPdf(inputFile, outputFile);
		}

		public void setInputFile(File inputFile) {
			this.inputFile = inputFile;
		}

		public void setOutputFile(File outputFile) {
			this.outputFile = outputFile;
		}
	}
}

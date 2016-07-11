/**  
 * Project Name:platform_utils  
 * File Name:Base64Util.java  
 * Package Name:com.zwsafety.platform.utils  
 * Date:2016年6月15日
 * Copyright (c) 2016,zwsafety All Rights Reserved.   
 */

package com.zwsafety.platform.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * @ClassName:Base64Util
 * @Description:TODO(图片转Base64和Base64转图片)
 * @date:2016年6月15日
 * @author lzqiangPC
 * @version 1.0
 * @since: JDK 1.7
 */
public class Base64Util {
	private static Logger logger = Logger.getLogger(JSONUtil.class);
	/**
	 * 将本地图片进行Base64位编码
	 * 
	 * @param imgUrl
	 *            图片的url路径，如http://.....xx.jpg
	 * @return
	 */
	public static String encodeImgageToBase64(File imageFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		ByteArrayOutputStream outputStream = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(imageFile);
			outputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", outputStream);
			// 对字节数组Base64编码
			Base64 encoder = new Base64();
			byte[] strdeocde = encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
			String base64Str=new String(strdeocde);
			return base64Str;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	/**
	 * 将Base64位编码的图片进行解码，并保存到指定目录
	 * 
	 * @param src
	 *            base64编码的图片信息
	 * @return
	 * @throws DecoderException
	 */
	public static void decodeBase64ToImage(String Base64src,String path,String imgName) throws Exception{
		Base64 decoder = new Base64();
		FileOutputStream write = null;
		try {
			write = new FileOutputStream(new File(path+"/"+imgName));
			byte[] decoderBytes = decoder.decode(Base64src.getBytes());
			write.write(decoderBytes);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}finally{
			try {
				if(null!=write){
					write.close();
				}
			} catch (IOException e) {
				e.printStackTrace();  
			}
		}
	}
	
	public static void main(String[] args) {
		String src = encodeImgageToBase64(new File("D:/1.png"));
		try {
			decodeBase64ToImage(src, "D:/img", "hehe.jpg");
		} catch (Exception e) {
			System.out.println("jinglaiba");
			e.printStackTrace();  
		}
	}
}

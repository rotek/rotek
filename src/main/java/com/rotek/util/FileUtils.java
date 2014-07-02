/**
* @FileName: FileUtils.java
* @Package com.rotek.util
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-18 上午08:55:44
* @version V1.0
*/
package com.rotek.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: FileUtils
 * @Description: 文件的工具类
 * @author chenwenpeng
 * @date 2013-7-18 上午08:55:44
 *
 */
public class FileUtils {

	/**
	* @Title: savePic
	* @Description: 保存图片(必须先保证根文件路径是存在的，最后一级目录如果不存在可以自动创建)
	* @param pic
	* @param pic_location
	* @param rest_maxPic
	* @return
	* @throws IllegalStateException
	* @throws IOException
	* @return String
	* @throws
	*/
	public static String savePic(MultipartFile pic,String pic_location,long rest_maxPic) throws IllegalStateException, IOException{
		long pic_size = pic.getSize();
		if(pic_size>rest_maxPic){
			return null;
		}
		File pic_folder = new File(pic_location);
		if(!pic_folder.exists()){
			pic_folder.mkdir();
		}
		String fileileName = SysUtils.getRandom(4) + pic.getOriginalFilename();
		File pic_file = new File(pic_folder+"/" + fileileName);
		pic.transferTo(pic_file);
		return fileileName;
	}

	/**
	* @Title: clearPic
	* @Description: 清除图片
	* @param pic_location
	* @param pic_name
	* @return void
	* @throws
	*/
	public static boolean clearPic(String pic_location,String pic_name){
		File pic_folder = new File(pic_location);
		if(!pic_folder.exists()){
			return true;
		}
		File pic_file = new File(pic_folder+"/"+pic_name);
		if(null != pic_file && pic_file.exists()){
			return pic_file.delete();
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		String path = "E:\\restaurant_pic\\111111";

		System.out.println(new File(path).mkdir());
	}
}

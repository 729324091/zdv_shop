package com.zdv.shop.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 文件Utils类
 * 
 * @author LBY
 * @date: 2018年11月28日
 */
public class FileUtil {

	private FileUtil() { }

	/**
	 * 文件上传内存中存储最大值，默认1M
	 */
	public static int fileUploadSizeThreshol = 1048576;
	/**
	 * 文件上传最大大小，默认5M
	 */
	public static int fileUploadFileSizeMax = 5242880;
	
	public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();
	
	static {
		getAllFileType(); // 初始化文件类型信息
	}

	/**
	 * 处理文件上传
	 * 
	 * @author LBY 2018年11月28日
	 * @param fileName
	 *            文件名称
	 * @param inputStream
	 *            文件输入流
	 * @param path
	 *            上传路径
	 * @return
	 */
	public static boolean upload(String fileName, InputStream inputStream,
			String path) {
		if (inputStream == null || fileName == null || path == null)
			return false;
		// 创建文件上传工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存中存储文件的最大值
		factory.setSizeThreshold(fileUploadSizeThreshol);
		// 创建一个新的文件上传处理程序（处理文件上传核心类）
		ServletFileUpload upload = new ServletFileUpload();
		// 设置文件上传编码
		upload.setHeaderEncoding("UTF-8");
		// 设置最大上传文件大小
		upload.setFileSizeMax(fileUploadFileSizeMax);
		// 文件输出流
		FileOutputStream outputStream = null;
		// 创建上传路径File对象
		File filePath = new File(path);
		try {
			// 如果文件路径不存在，则创建
			if (!filePath.exists())
				filePath.mkdirs();
			outputStream = new FileOutputStream(path + File.separatorChar
					+ fileName);
			byte pool[] = new byte[1024];
			int len = -1;
			// 每次读取1024字节
			while ((len = inputStream.read(pool, 0, pool.length)) != -1) {
				// 写入文件
				outputStream.write(pool, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (outputStream != null) {
				try {
					outputStream.flush();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	/**
	 * 删除单个文件
	 * 
	 * @author LBY 2018年11月28日
	 * @param path
	 * @return
	 */
	public static boolean delFile(String path) {
		File file = new File(path);
		return file.exists() && file.isFile() ? file.delete() : false;
	}

	/**
	 * getAllFileType,常见文件头信息
	 */
	private static void getAllFileType() {
		FILE_TYPE_MAP.put("ffd8ffe000104a464946", "jpg"); // JPEG (jpg)
		FILE_TYPE_MAP.put("89504e470d0a1a0a0000", "png"); // PNG (png)
		FILE_TYPE_MAP.put("47494638396126026f01", "gif"); // GIF (gif)
		FILE_TYPE_MAP.put("49492a00227105008037", "tif"); // TIFF (tif)
		FILE_TYPE_MAP.put("424d228c010000000000", "bmp"); // 16色位图(bmp)
		FILE_TYPE_MAP.put("424d8240090000000000", "bmp"); // 24位位图(bmp)
		FILE_TYPE_MAP.put("424d8e1b030000000000", "bmp"); // 256色位图(bmp)
		FILE_TYPE_MAP.put("41433130313500000000", "dwg"); // CAD (dwg)
		FILE_TYPE_MAP.put("3c21444f435459504520", "html"); // HTML (html)
		FILE_TYPE_MAP.put("3c21646f637479706520", "htm"); // HTM (htm)
		FILE_TYPE_MAP.put("48544d4c207b0d0a0942", "css"); // css
		FILE_TYPE_MAP.put("696b2e71623d696b2e71", "js"); // js
		FILE_TYPE_MAP.put("7b5c727466315c616e73", "rtf"); // Rich Text Format (rtf)
		FILE_TYPE_MAP.put("38425053000100000000", "psd"); // Photoshop (psd)
		FILE_TYPE_MAP.put("46726f6d3a203d3f6762", "eml"); // Email [Outlook Express 6] (eml)
		FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "doc"); // MS Excel 注意：word、msi 和 excel的文件头一样
		FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "vsd"); // Visio 绘图
		FILE_TYPE_MAP.put("5374616E64617264204A", "mdb"); // MS Access (mdb)
		FILE_TYPE_MAP.put("252150532D41646F6265", "ps");
		FILE_TYPE_MAP.put("255044462d312e350d0a", "pdf"); // Adobe Acrobat (pdf)
		FILE_TYPE_MAP.put("2e524d46000000120001", "rmvb"); // rmvb/rm相同
		FILE_TYPE_MAP.put("464c5601050000000900", "flv"); // flv与f4v相同
		FILE_TYPE_MAP.put("00000020667479706d70", "mp4");
		FILE_TYPE_MAP.put("49443303000000002176", "mp3");
		FILE_TYPE_MAP.put("000001ba210001000180", "mpg"); //
		FILE_TYPE_MAP.put("3026b2758e66cf11a6d9", "wmv"); // wmv与asf相同
		FILE_TYPE_MAP.put("52494646e27807005741", "wav"); // Wave (wav)
		FILE_TYPE_MAP.put("52494646d07d60074156", "avi");
		FILE_TYPE_MAP.put("4d546864000000060001", "mid"); // MIDI (mid)
		FILE_TYPE_MAP.put("504b0304140000000800", "zip");
		FILE_TYPE_MAP.put("526172211a0700cf9073", "rar");
		FILE_TYPE_MAP.put("235468697320636f6e66", "ini");
		FILE_TYPE_MAP.put("504b03040a0000000000", "jar");
		FILE_TYPE_MAP.put("4d5a9000030000000400", "exe");// 可执行文件
		FILE_TYPE_MAP.put("3c25402070616765206c", "jsp");// jsp文件
		FILE_TYPE_MAP.put("4d616e69666573742d56", "mf");// MF文件
		FILE_TYPE_MAP.put("3c3f786d6c2076657273", "xml");// xml文件
		FILE_TYPE_MAP.put("494e5345525420494e54", "sql");// xml文件
		FILE_TYPE_MAP.put("7061636b616765207765", "java");// java文件
		FILE_TYPE_MAP.put("406563686f206f66660d", "bat");// bat文件
		FILE_TYPE_MAP.put("1f8b0800000000000000", "gz");// gz文件
		FILE_TYPE_MAP.put("6c6f67346a2e726f6f74", "properties");// bat文件
		FILE_TYPE_MAP.put("cafebabe0000002e0041", "class");// bat文件
		FILE_TYPE_MAP.put("49545346030000006000", "chm");// bat文件
		FILE_TYPE_MAP.put("04000000010000001300", "mxp");// bat文件
		FILE_TYPE_MAP.put("504b0304140006000800", "docx");// docx文件
		FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "wps");// WPS文字wps、表格et、演示dps都是一样的
		FILE_TYPE_MAP.put("6431303a637265617465", "torrent");

		FILE_TYPE_MAP.put("6D6F6F76", "mov"); // Quicktime (mov)
		FILE_TYPE_MAP.put("FF575043", "wpd"); // WordPerfect (wpd)
		FILE_TYPE_MAP.put("CFAD12FEC5FD746F", "dbx"); // Outlook Express (dbx)
		FILE_TYPE_MAP.put("2142444E", "pst"); // Outlook (pst)
		FILE_TYPE_MAP.put("AC9EBD8F", "qdf"); // Quicken (qdf)
		FILE_TYPE_MAP.put("E3828596", "pwl"); // Windows Password (pwl)
		FILE_TYPE_MAP.put("2E7261FD", "ram"); // Real Audio (ram)
	}

	/**
	 * 得到上传文件的文件头
	 * @param src
	 * @return
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 根据制定文件的文件头判断其文件类型
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileType(String filePath) {
		String res = null;
		try {
			res = getFileType(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 根据制定文件输入流判断其文件类型
	 * @param is
	 * @return
	 */
	public static String getFileType(InputStream is) {
		String res = null;
		try {
			byte[] b = new byte[10];
			is.read(b, 0, b.length);
			String fileCode = bytesToHexString(b);

			Iterator<String> keyIter = FILE_TYPE_MAP.keySet().iterator();
			while (keyIter.hasNext()) {
				String key = keyIter.next();
				if (key.toLowerCase().startsWith(fileCode.toLowerCase())
						|| fileCode.toLowerCase().startsWith(key.toLowerCase())) {
					res = FILE_TYPE_MAP.get(key);
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 判断文件类型
	 * @author LBY
	 * @date 2018年12月7日
	 * @param is
	 * @param suffixs 文件后缀，如png，jpg等
	 * @return
	 */
	public static boolean checkFileType(InputStream is, String[] suffixs) {
		String type = FileUtil.getFileType(is);
		if (type != null) {
			for (String suffix : suffixs) {
				if (suffix.equalsIgnoreCase(type))
					return true;
			}
		}
		return false;
	}
}

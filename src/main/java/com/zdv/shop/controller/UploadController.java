package com.zdv.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.utils.JsonConvertUtil;
import com.zdv.shop.common.utils.UploadUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/uploads/")
public class UploadController {
	/**访问地址*/
	@Value("${global.upload.path}")
	private String path;
	/**存放地址*/
	@Value("${global.upload.docBase}")
	private String docBase;
	@Autowired
	private UploadUtil uploadUtil;

	/**
	 * 上传图片
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "uploadImg.do")
	@ResponseBody
	public AjaxResult uploadImg(HttpServletRequest request) {
		final Map<String, Object> result = new HashMap<String, Object>();
		
		String filepath = uploadUtil.upload(request, docBase, path);
		result.put("filepath", filepath);
		return new AjaxResult(1, "上传成功", result);
	}

	/**
	 * 上传文件(注意:前端需要传默认参数:name="files",或者加注解@RequestParam("files")指定参数名)
	 * @param request
	 * @param type
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "uploadFile.do")
	@ResponseBody
	public AjaxResult uploadFile(MultipartFile[] files, HttpServletRequest request) {
		final Map<String, Object> result = new HashMap<String, Object>();
		// 判断file数组不能为空并且长度大于0
		if (files != null && files.length > 0) {
			// 循环获取file数组中得文件
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				// 判断文件是否为空
				if (!file.isEmpty()) {
					String fileName = file.getOriginalFilename();
					// 文件后缀
					String suffixes = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					// 重命名文件
					String nowName = uploadUtil.getNewName(suffixes);

					final ByteArrayOutputStream fileStream = new ByteArrayOutputStream();
					// 存放位置
					String savepath = uploadUtil.getFilePath(docBase, nowName);
					System.out.println(savepath);
					try {
						// 复制文件内容
						IOUtils.copy(file.getInputStream(), fileStream);
						// 复制文件
						FileUtils.copyInputStreamToFile(new ByteArrayInputStream(fileStream.toByteArray()), new File(savepath));
					} catch (Exception e) {
						return new AjaxResult(0, "上传失败", null);
					}
					result.put("nowname", nowName);
					result.put("filetype", suffixes);
					result.put("filesize", file.getSize());
					result.put("savepath", savepath);
					result.put("url", uploadUtil.getFilePath(path, nowName));
				} else {
					return new AjaxResult(0, "请选择文件上传", result);
				}
			}
		}
		return new AjaxResult(1, "上传成功", result);
	}

	/**
	 * 百度编辑器加载配置表/上传图片
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("ueditoUpload")
	public void ueditoUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setHeader("Content-Type", "text/html");
		String action = request.getParameter("action");
		String jsonStr = null;
		if ("config".equals(action)) {
			InputStream jsonInStream = JsonConvertUtil.class.getClassLoader().getResourceAsStream("config.json");
			jsonStr = JsonConvertUtil.convertStream2Json(jsonInStream);
		} else if ("uploadimage".equals(action)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("state", "SUCCESS");
			String url = uploadUtil.upload(request, docBase, path);
			map.put("url", url);
			map.put("title", "");
			map.put("original", "");
			jsonStr = new ObjectMapper().writeValueAsString(map);
		}
		response.getWriter().write(jsonStr);
	}

	@RequestMapping(value = "uploadImgBase64")
	@ResponseBody
	public AjaxResult uploadImgBase64(HttpServletRequest request,String img) {
		final Map<String, Object> result = new HashMap<String, Object>();
		MultipartFile multipartFile = base64ToMultipart(img);

		String filepath = uploadUtil.upload(multipartFile, docBase, path);
		result.put("filepath", filepath);
		return new AjaxResult(1, "上传成功", result);
	}



	public static MultipartFile base64ToMultipart(String base64) { try { String[] baseStrs = base64.split(",");

		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = new byte[0];
		b = decoder.decodeBuffer(baseStrs[1]);

		for(int i = 0; i < b.length; ++i) { if (b[i] < 0) { b[i] += 256;
		} } return new BASE64DecodedMultipartFile(b, baseStrs[0]);
	} catch (IOException e) { e.printStackTrace();
		return null;
	} }

}

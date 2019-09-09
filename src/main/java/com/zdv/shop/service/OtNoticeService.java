package com.zdv.shop.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zdv.shop.common.utils.AppUtil;
import com.zdv.shop.common.utils.FileUtil;
import com.zdv.shop.common.utils.KeyId;
import com.zdv.shop.controller.BaseController;
import com.zdv.shop.mapper.OtNoticeMapper;
import com.zdv.shop.model.OtNotice;

/**
 * 通知公告信息
 * @author LBY
 * @date: 2018年12月7日
 */
@Service
public class OtNoticeService extends AbstratService<OtNotice> {

	@Autowired
	OtNoticeMapper mapper;
	
	@Autowired
	HttpSession session;
	
	/**
	 * 封面图限制图片后缀
	 */
	private final String[] limitSuffixs = {"png", "jpg", "gif"};
	
	private final String uploadDirectory = "/upload/noticePic";
	
	/**
	 * 添加公告或修改
	 * @author LBY
	 * @date 2018年12月7日
	 * @param notice
	 * @param homepic
	 * @return
	 * @throws IOException
	 */
	public boolean addOrAlter(OtNotice notice, MultipartFile homepic) throws IOException {
		String picPath = null;
		if (homepic != null) {
			InputStream is = homepic.getInputStream();
			if (FileUtil.checkFileType(is, limitSuffixs)) {
				String path = getUploadPath();
				String fileName = AppUtil.getUuid() + ".png";
				if (FileUtil.upload(fileName, is, path))
					picPath = path + File.separatorChar + fileName;
				else
					return false;
			}
		}
		if(notice.getUnoticeid() != null) {
			OtNotice target = mapper.selectByPrimaryKey(notice.getUnoticeid());
			if(target.getUhomepic() != null)
				FileUtil.delFile(target.getUhomepic());
			notice.setUhomepic(picPath);
			return mapper.updateByPrimaryKey(notice) > 0;
		} else {
			notice.setUnoticeid(new KeyId().nextId());
			notice.setUcreatedate(BaseController.timeStamp());
			return mapper.insert(notice) > 0;
		}
	}
	
	/**
	 * 封面图文件上传路径
	 * @author LBY
	 * @date 2018年12月7日
	 * @return
	 */
	private String getUploadPath() {
		return session.getServletContext().getRealPath(this.uploadDirectory);
	}
}

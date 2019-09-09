package com.zdv.shop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.common.pojo.AjaxAppResult;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.common.utils.AjaxAppUtil;
import com.zdv.shop.common.utils.AppUtil;
import com.zdv.shop.common.utils.KeyId;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.mapper.CtCompRoleMapper;
import com.zdv.shop.mapper.CtCompMapper;
import com.zdv.shop.mapper.CtOpUserToCompMapper;
import com.zdv.shop.mapper.OpUsersMapper;
import com.zdv.shop.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 销售商信息 Service 层
 * @author LBY
 * @date: 2018年12月17日
 */
@Service
@Transactional
public class CtCompService extends AbstratService<CtComp> {

	@Autowired
	CtCompMapper mapper;
	@Autowired
	OpUsersService opUsersService;
	@Autowired
    MessageSource messageSource;
	@Autowired
	CtOpUserToCompService ctOpUserToCompService;
	@Autowired
	CtCompRoleService ctCompRoleService;
	@Autowired
	CtRoleOperationService ctRoleOperationService;
	@Autowired
	OpOpuserMCompService opuserMCompService;
	@Autowired
	OpUsersMapper usersMapper;
	@Autowired
	CtOpUserToCompMapper opUserToCompMapper;
	@Autowired
	CtCompRoleMapper compRoleMapper;
	@Autowired
	DtDistributorToCompService dtDistributorToCompanyService;

	/**
	 * APP添加销售商
	 * @author LBY
	 * @data 2019年1月10日
	 * @param opUsers
	 * @param comp
	 * @param lang
	 * @return
	 * @throws Exception
	 */
	public AjaxAppResult appAddCtComp(OpUsers opUsers, CtComp comp, String udistributorid, String lang) throws Exception {
	
		String now = opUsers.getUregdate();	// 当前时间
		CtComp sCompanyArg = new CtComp();
		sCompanyArg.setUcompname(comp.getUcompname());
		sCompanyArg.setUtel(comp.getUtel());
		List<CtComp> companys = mapper.queryList(sCompanyArg);
		sCompanyArg = null;
		if (opUsers.getUpassword() == null) {	// 不为空则判断新增，反之确认新增
			if (companys == null || companys.size() <= 0)
				return AjaxAppUtil.returnObj("150",
						messageSource.getMessage("global.system.nodata", null, StringUtils.dolanguge(lang)));
			comp = companys.get(0);
			opUsers = opUsersService.queryByMobile(opUsers.getUmobile());
			if (opUsers == null)
				return AjaxAppUtil.returnObj("150",
						messageSource.getMessage("global.system.nodata", null, StringUtils.dolanguge(lang)));
		} else {
			// 判断销售商或用户是否已存在
			if ((companys != null && companys.size() > 0) || opUsersService.queryByMobile(opUsers.getUmobile()) != null)
				return AjaxAppUtil.returnObj("151",
						messageSource.getMessage("global.system.nodata", null, StringUtils.dolanguge(lang)));
			if (opUsersService.insert(opUsers) > 0)		// 保存管理员信息
				mapper.insert(comp);				// 添加销售商
		}
		
		// 添加角色表
		CtCompRole ctCompRole = new CtCompRole();
		ctCompRole.setUcompid(comp.getUcompid());
		ctCompRole.setUtypes('2');
		ctCompRole = ctCompRoleService.queryOne(ctCompRole);
		if (ctCompRole == null) {
			ctCompRole = new CtCompRole();
			ctCompRole.setUroleid(KeyId.nextId());
			ctCompRole.setUlevel("01");
			ctCompRole.setUrolename("店长");
			ctCompRole.setUcompid(comp.getUcompid());
			ctCompRole.setUtypes('2');
			ctCompRole.setUlevelcolor("3881EC");
			ctCompRoleService.insert(ctCompRole);
		}
		
		// 判断添加管理员与销售商中间表
		CtOpUserToComp utcArg = new CtOpUserToComp();
		utcArg.setUcompid(comp.getUcompid());
		utcArg.setUopuserid(opUsers.getUopuserid());
		if (ctOpUserToCompService.queryCount(utcArg) <= 0) {
			utcArg.setUroleid(ctCompRole.getUroleid());
			utcArg.setUstatus('0');
			utcArg.setUcreatedate(now);
			ctOpUserToCompService.insert(utcArg);
		}
		
		// 添加到权限表
		/*CtRoleOperation ctRoleOperation = new CtRoleOperation();
		ctRoleOperation.setUroleid(ctCompRole.getUroleid());
		ctRoleOperation.setUcompid(comp.getUcompid());
		if (ctRoleOperationService.queryCount(ctRoleOperation) <= 0) {
			ctRoleOperationService.insert(ctRoleOperation);
		}*/
		
		// 添加业务员管理的销售商中间表
		OpOpuserMComp opuserMComp = new OpOpuserMComp();
		opuserMComp.setUcompid(comp.getUcompid());
		opuserMComp.setUopuserid(opUsers.getUopuserid());
		if (opuserMCompService.queryCount(opuserMComp) <= 0) {
			opuserMComp.setUstatus("0");
			opuserMComp.setUcreatedate(now);
			opuserMCompService.insert(opuserMComp);
		}
		
		// 添加到经销商管理的销售商中间表
		DtDistributorTComp distributorTComp = new DtDistributorTComp();
		distributorTComp.setUcompid(comp.getUcompid());
		distributorTComp.setUdistributorid(udistributorid);
		if (dtDistributorToCompanyService.queryCount(distributorTComp) <= 0) {
			distributorTComp.setUstatus('0');
			distributorTComp.setUcreatedate(now);
			dtDistributorToCompanyService.insert(distributorTComp);
		}

		return AjaxAppUtil.returnObj("000",
				messageSource.getMessage("global.system.nodata", null, StringUtils.dolanguge(lang)));
	}
	
	/**
	 * 根据管理员ID查询所管理的销售商信息
	 * @author LBY
	 * @date 2018年12月17日
	 * @param distributorid 经销商ID
	 * @param keywords 搜索关键字，根据零售名称、联系人搜索
	 * @param areaid 区域ID
	 * @return
	 */
	public List<Map<String, Object>> queryCompanyByDistributorid(Integer pageNo, Integer pageSize,
		String distributorid, String keywords, String areaid) {
		if (pageNo != null && pageSize != null)
			PageHelper.startPage(pageNo, pageSize);
		return mapper.queryCompanyByDistributorid(distributorid, keywords, areaid);
	}
	
	/**
	 * 根据管理员ID查询所管理的销售商信息
	 * @author LBY
	 * @date 2018年12月17日
	 * @param page 分页信息
	 * @param userid 管理员ID
	 * @param keyword 搜索关键字，根据零售名称、联系人搜索
	 * @param areaid 区域ID
	 * @return
	 */
	public PageAjax<Map<String, Object>> queryCompanyPageByUserid(PageAjax<CtComp> page, String userid, String keyword, String areaid) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		return AppUtil.returnPage(mapper.queryCompanyByDistributorid(userid, keyword, areaid));
	}
	
	/**
	 * 查询管理员所管理的销售商区域信息
	 * @author LBY
	 * @date 2018年12月17日
	 * @param distributorid 
	 * @return
	 */
	public List<PtArea> queryPossessAreaByDistributorid(String distributorid) { 
		return mapper.queryPossessAreaByDistributorid(distributorid);
	}


	@ServiceLog("查询销售商列表列表")
	public PageAjax<CtComp> queryCompanyPage(PageAjax<CtComp> page, CtComp clcompany) {
		String ids = mapper.getChildFromCompany(clcompany.getUcompid());
		clcompany.setUcompid(ids);
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<CtComp> list = mapper.childrenList(clcompany);
		return AppUtil.returnPage(list);
	}
	
	
	@ServiceLog("添加查询是否包含")
	public AjaxResult queryByNameOrAddress(CtComp clcompany){
		 List<CtComp> queryAllClcorpinfo = mapper.ContainuNameOradrs(clcompany);
		 String result = null;
		String ucompid = clcompany.getUcompid();
		List<String> list = new ArrayList<>();
		 if(queryAllClcorpinfo.size() ==0){
			 int row = mapper.insertSelectives(clcompany);
			 if(row<=0){
				 result = "添加失败";
			 }else{
			 	//生成根角色
				 CtCompRole ctCompRole = new CtCompRole();
				 String uroleid = new KeyId().nextId();
				ctCompRole.setUroleid(uroleid);
				ctCompRole.setUrolename("roleroot");
				/*ctCompRole.setUtypes("00");*/
				ctCompRole.setUcompid(ucompid);
				//获取所有权限 '类型 0系统 1经销商（供应商） 2销售商',
				 PtOperation ptOperation = new PtOperation();
				 ptOperation.setUoptype("0");
				/* List<PtOperation> select = ptOperationMapper.select(ptOperation);
				 ptOperation.setUoptype("1");
				 List<PtOperation> select1 = ptOperationMapper.select(ptOperation);
				 for (PtOperation operation : select1) {
					 select.add(operation);
				 }*/
				 //加入权限中间表
				 /*CtRoleOperation ctRoleOperation = new CtRoleOperation();
				 for (PtOperation operation : select) {
					 ctRoleOperation.setUroleid(uroleid);
					 ctRoleOperation.setUopid(operation.getUopid());
					 ctRoleOperationMapper.insert(ctRoleOperation);
				 }*/

				 /*ctCompRole.setOperations(select);*/
				 /*int i = ctCompRoleMapper.insertRole(ctCompRole);*/
				 if(row<=0) {
					 result = "添加失败";
				 }
				 list.add(ucompid);
				 list.add(uroleid);
			 }

		 }else{
			 result = "单位名已存在";
			 ucompid = "";
		 }
		 return AppUtil.returnObj(result,list);
	}
	
	@ServiceLog("更新数据")
	public AjaxResult updcl(CtComp clcompany){
		List<CtComp> queryCtComp = mapper.queryList(clcompany);
		 String result = null;
		 if(queryCtComp.size() ==0){
			 int row = mapper.updateCompanyByIds(clcompany);
			 if(row<=0){
				 result = "修改失败";
			 };
		 }else{
			 result = "单位名重复";
		 }
		 return AppUtil.returnObj(result);
	}
	
	@ServiceLog("删除数据")
    public AjaxResult deleByID(String ucompid) {
		int ret = mapper.deleteByPrimaryKey(ucompid);
        String result = null;
        if(ret <= 0){
        	result = "删除失败";
        }
    	return AppUtil.returnObj(result);
    }
	
	@ServiceLog("通过Id查询数据")
	public CtComp selectCompById(String ucompid){
		return mapper.selectByPrimaryKey(ucompid);
	}

	@ServiceLog("创建销售商及其相关")
	public AjaxResult addCompany(CtComp company, OpUsers users, CtCompRole ctCompRole) {
		String result = null;
		boolean a = usersMapper.insert(users) > 0;
		boolean b = mapper.insert(company) > 0;
		//将用户与销售商关联
		CtOpUserToComp ctOpUserToComp = new CtOpUserToComp();
		ctOpUserToComp.setUcompid(company.getUcompid());
		ctOpUserToComp.setUopuserid(users.getUopuserid());
		ctOpUserToComp.setUroleid(ctCompRole.getUroleid());
		ctOpUserToComp.setUcreatedate(System.currentTimeMillis()/1000+"");
		ctOpUserToComp.setUstatus('1');
		opUserToCompMapper.insert(ctOpUserToComp);
		compRoleMapper.insert(ctCompRole);
		if(!(a&&b)){
			result = "创建失败";
		}

		return new AjaxResult(1, "创建成功");


	}

    public PageAjax<CtComp> querypageByUTC(PageAjax<CtComp> page, CtOpUserToComp opUserToComp) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<CtComp> list = mapper.querypageByUTC(opUserToComp);
		return new PageAjax<CtComp>(list);
    }

    @ServiceLog("判断销售商名称是否存在")
	public AjaxResult checkByUcompname(String ucompname) {
		String result = null;
		Example example = new Example(CtComp.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andLike("ucompname", ucompname);
		List<CtComp> companyList = mapper.selectByExample(example);
		//判断 是否存在改名称的经销商
		if(companyList.size()==0){

		}else{
			result = "该名称已存在";
			return new AjaxResult(result);
		}

		return new AjaxResult(1,result);
	}

	public AjaxResult updateCompany(CtComp company, OpUsers users) {
		String result = null;
		try {
			boolean a = mapper.updateCompanyByIds(company) > 0;
			boolean b = usersMapper.updateUsers(users) > 0;

			result = "更新成功";
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult(result);
		}
		return new AjaxResult(1,result);
	}

	public AjaxResult delAllById(String ucompid) {
		String result = null;
		try {
			delAll(ucompid);
			result = "操作成功";
		}catch (Exception e){
			e.printStackTrace();
			result = "删除出错";
			return new AjaxResult(result);
		}
		return new AjaxResult(1, result);
		//int i = mapper.delAllById(udistributorid);
	}

	@ServiceLog("删除")
	public void delAll(String ucompid) {
		try {
			List<CtComp> list = mapper.queryChildrenIdList(ucompid);
			mapper.delAllById(ucompid);
			if (list.size() > 0) {
				for (CtComp comp : list) {
					delAll(comp.getUcompid());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public AjaxResult queryNameList(CtComp company) {
		Example example = new Example(CtComp.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andLike("ucompname", "%"+company.getUfullname()+"%");
		List<CtComp> list = mapper.selectByExample(example);
//		List<ClCompany> list = cMapper.queryNameList(company.getUfullname());
		String result = null;

		return AppUtil.returnObj(result,list);

	}
    public Integer getSysSN(Integer flag) {

        return mapper.getSysSN(flag);
     }

	public int updateBalanceByUcompid(CtComp comp) {
		return mapper.updateBalanceByUcompid(comp);
	}
}

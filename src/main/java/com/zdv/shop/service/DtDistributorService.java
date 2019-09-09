package com.zdv.shop.service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.common.utils.StringUtils;
import com.zdv.shop.mapper.*;
import com.zdv.shop.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 经销商（供应商）表Service
 * @author LBY
 * @date: 2018年12月17日
 */
@Service
public class DtDistributorService extends AbstratService<DtDistributor> {

	@Autowired
	DtDistributorMapper mapper;
	@Autowired
	OpUsersMapper usersMapper;
	@Autowired
	DtDistributorRoleMapper distributorRoleMapper;
	@Autowired
	DtOpUserToDistributorMapper opUserToDistributorMapper;
	@Autowired
	DtDistributorToCompMapper distributorToCompMapper;
	@Autowired
	CtCompMapper compMapper;


	/**
	 * 根据管理员ID查询所管理的经销商信息
	 * @author LBY
	 * @date 2018年12月17日
	 * @param userid 管理员id
	 * @param keywords 搜索关键字，根据经销商名称、联系人搜索
	 * @param areaid 区域id
	 * @return
	 */
	public List<Map<String, Object>> queryDistributorByUserid(Integer pageNo, Integer pageSize, String userid, String keywords, String areaid) {
		if (pageNo != null && pageSize != null)
			PageMethod.startPage(pageNo, pageSize);
		return mapper.queryDistributorByUserid(userid, keywords, areaid);
	}

	/**
	 * 查询管理员所管理的经销商区域信息
	 * @author LBY
	 * @date 2018年12月17日
	 * @param userid
	 * @return
	 */
	public List<PtArea> queryPossessAreaByUserid(String userid) {
		return mapper.queryPossessAreaByUserid(userid);
	}
	
	/**
	 * 查询指定区域内可向其下单的经销商
	 * @author LBY
	 * @data 2019年2月20日
	 * @param uproductid
	 * @param uareaid
	 * @param ustock
	 * @return 经销商ID
	 */
	public DtDistributor queryOrdersDistributor(String uproductid, String uareaid, Integer ustock) {
		return mapper.selectOrdersDistributor(uproductid, uareaid, ustock);
	}
	
	@ServiceLog("通过Id查询数据")
	public DtDistributor selectDistributorById(String udistributorid){
		return mapper.selectDistributorById(udistributorid);
	}
	@ServiceLog("通过Id查询数据")
	public DtDistributor selectParentDistributorById(String udistributorid){
		return mapper.selectParentDistributorById(udistributorid);
	}
	@ServiceLog("判断一个经销商是否有子经销商 ")
	public int getdistributorchildById(String udistributorid) {
		return mapper.getdistributorchildById(udistributorid);
	}

	@ServiceLog("保存经销商及其根用户")
	public AjaxResult saveDistributor(DtDistributor distributor, OpUsers users, DtDistributorRole distributorRole) {
		String result = null;
		boolean a = usersMapper.insert(users) > 0;
		boolean b = mapper.insert(distributor) > 0;
		distributorRoleMapper.insert(distributorRole);

		DtOpUserToDistributor opUserToDistributor = new DtOpUserToDistributor();
		opUserToDistributor.setUdistributorid(distributor.getUdistributorid());
		opUserToDistributor.setUopuserid(users.getUopuserid());
		opUserToDistributor.setUcreatedate(System.currentTimeMillis()/1000+"");
		opUserToDistributor.setUroleid(distributorRole.getUroleid());
		opUserToDistributor.setUstatus('1');
		opUserToDistributorMapper.insert(opUserToDistributor);
		if(!(a&&b)){
			result = "创建失败";
		}

		return new AjaxResult(1, "创建成功");

	}
	@ServiceLog("检测名称是否已存在")
	public AjaxResult checkByUname(String udname) {
		String result = null;
		Example example = new Example(DtDistributor.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andLike("udname", udname);
		List<DtDistributor> dtDistributors = mapper.selectByExample(example);
		//判断 是否存在改名称的经销商
		if(dtDistributors.size()==0){

		}else{
			result = "该名称已存在";
			return new AjaxResult(result);
		}

		return new AjaxResult(1,result);


	}

	@ServiceLog("分页查询")
	public PageAjax<DtDistributor> queryPage(PageAjax<DtDistributor> page, DtDistributor entity){
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<DtDistributor> list = mapper.queryPage(entity);
		return new PageAjax<DtDistributor>(list);
	}

	public AjaxResult delAllById(String udistributorid) {
		String result = null;
		try {
			delAll(udistributorid);
			result = "操作成功";
		}catch (Exception e){
			e.printStackTrace();
			result = "删除出错";
			return new AjaxResult(result);
		}
		return new AjaxResult(1, result);
		//int i = mapper.delAllById(udistributorid);

	}

	public void delAll(String udistributorid) {
		try {
			List<DtDistributor> list = mapper.queryChildrenIdList(udistributorid);
			mapper.delAllById(udistributorid);
			if (list.size() > 0) {
				for (DtDistributor dtDistributor : list) {
					delAll(dtDistributor.getUdistributorid());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@ServiceLog("通过OpUserToDistributor查询经销商列表")
	public PageAjax<DtDistributor> querypageByUTD(PageAjax<DtDistributor> page, DtOpUserToDistributor opUserToDistributor) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<DtDistributor> list = mapper.querypageByUTD(opUserToDistributor);
		return new PageAjax<DtDistributor>(list);
	}

	@ServiceLog("更新")
	public AjaxResult updateDistributor(DtDistributor distributor, OpUsers users) {
		String result = null;
		try {
			boolean a = mapper.updateByPrimaryKeySelective(distributor) > 0;
			boolean b = usersMapper.updateByPrimaryKeySelective(users) > 0;
			result = "更新成功";
		} catch (Exception e) {
			e.printStackTrace();
			result = "更新出错";
			return new AjaxResult(result);
		}
		return new AjaxResult(1, result);
	}

	public PageAjax<DtDistributor> queryDistributorPage(PageAjax<DtDistributor> page, DtDistributor distributor) {
		String ids = mapper.childrenListIdFromDistributor(distributor.getUdistributorid());
		List<DtDistributor> list = new ArrayList<DtDistributor>();
        distributor.setUdistributorid(ids);
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<DtDistributor> distributorList = mapper.childrenList(distributor);
      /*  try {
			childrenList(distributor, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
        PageAjax<DtDistributor> pageAjax = new PageAjax<>(distributorList);

        return pageAjax;
	}

	public void childrenList(DtDistributor distributor,List<DtDistributor> all) {
		try {
			List<DtDistributor> list = mapper.childrenList(distributor);
			all.addAll(list);
			if (list.size() > 0) {
				for (DtDistributor dtDistributor : list) {
                    distributor.setUdistributorid(dtDistributor.getUdistributorid());
                    childrenList(distributor,all);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public PageAjax<DtDistributor> queryDistributor2CompanyPage(PageAjax<DtDistributor> page, CtComp company) {
		String ids = mapper.childrenListIdFromDistributor(company.getUcompid());


		return new PageAjax<>();
	}

    public PageAjax<CtComp> querydistributor2CompPage(PageAjax<CtComp> page, CtComp company) {
		//获取所有下级经销商id
		String ids = mapper.childrenListIdFromDistributor(company.getUcompid());
		company.setUcompid(null);
		PageMethod.startPage(page.getPageNo(), page.getPageSize());

		List<CtComp> ctComps = compMapper.queryDistributor2CompList(company, ids);

		return new PageAjax<>(ctComps);
    }

    public AjaxResult distribtorAddComp(CtComp comp, String udistributorid) {
		//根据名字查询出销售商
		CtComp ctComp = compMapper.selectByUcompname(comp.getUcompname());

		//如果销售商不为空
		if (ctComp != null) {

			DtDistributorTComp dtDistributorTComp = new DtDistributorTComp();
			dtDistributorTComp.setUcompid(ctComp.getUcompid());
			dtDistributorTComp.setUdistributorid(udistributorid);
			//根据信息查询出经销商管理销售商中间表是否已经存在
			//List<DtDistributorTComp> select = distributorToCompMapper.queryDistributorToComp(dtDistributorTComp);
			//如果不存在则创建待审核的中间表，存在则返回已存在关系
			if (distributorToCompMapper.selectCount(dtDistributorTComp) <= 0 ) {
				dtDistributorTComp.setUstatus('0');
				distributorToCompMapper.addDistribtorAddComp(dtDistributorTComp);

				return new AjaxResult(1, "申请成功");
			}else{
				return new AjaxResult(0, "该销售商已存在关系中");
			}
		}

		return new AjaxResult();
	}

    public int insertDistributor(DtDistributor dtDistributor) {
		return mapper.insertDistributor(dtDistributor);
    }
}

package com.zdv.shop.service;

import com.github.pagehelper.page.PageMethod;
import com.zdv.shop.common.annotation.ServiceLog;
import com.zdv.shop.common.pojo.AjaxResult;
import com.zdv.shop.common.pojo.PageAjax;
import com.zdv.shop.common.utils.AppUtil;
import com.zdv.shop.mapper.CtCompRoleMapper;
import com.zdv.shop.mapper.CtRoleOperationMapper;
import com.zdv.shop.mapper.PtOperationMapper;
import com.zdv.shop.model.AuthRole;
import com.zdv.shop.model.CtCompRole;
import com.zdv.shop.model.CtRoleOperation;
import com.zdv.shop.model.PtOperation;

import groovy.beans.ListenerListASTTransformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.ArrayList;
import java.util.List;
//单位角色
@Service
public class CtRoleService extends AbstratService<CtCompRole>{
    @Autowired
    private CtCompRoleMapper roleMapper;
    @Autowired
    private CtRoleOperationMapper roleOperMapper;
    @Autowired
    private PtOperationMapper authOperationMapper;
    /*public CtCompRole queryRoleById(String uroleid){
        return roleMapper.queryCompRoleById(uroleid);
    }*/

    /*public List<CtCompRole> queryRoleByUcompId(String ucompid){
        return roleMapper.queryRoleByUcompId(ucompid);
    }*/
    @ServiceLog("分页查询")
    public PageAjax<CtCompRole> queryPage(PageAjax<CtCompRole> page, CtCompRole entity){
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<CtCompRole> list = queryList(entity);
        List<CtCompRole> allList = new ArrayList<CtCompRole>();
        for (CtCompRole ctCompRole : list) {
            String uparentid = ctCompRole.getUparentid();
            if(uparentid!=null || "" !=uparentid){
                /*ctCompRole.setFrole(roleMapper.queryCompRoleById(uparentid));*/
                allList.add(ctCompRole);
            }
        }
        return new PageAjax<CtCompRole>(allList);
    }
    @ServiceLog("新增角色")
    public AjaxResult saveRole(CtCompRole role) {
        String result = null;

        CtCompRole $role = new CtCompRole();
        if(role.getUcompid()==null||role.getUcompid().equals(""))
            $role =  null;/*roleMapper.queryByRolename(role.getUrolename());*/
        else
            $role = null;/*roleMapper.queryByRolename1(role.getUrolename(),role.getUcompid());*/

        if (null == $role) {
            if(role.getUparentid().equals("null")) role.setUparentid("0");
            save(role);
            //"0" Uifoper类型 0系统 1经销商（供应商） 2销售商'
            //roleMapper.insertCt_role_operation(role.getUroleid(), "1");
        } else {
            result = "角色名已存在";
        }
        return AppUtil.returnObj(result);
    }


    public List<CtCompRole> rootRolelist(String ucompid){
        return roleMapper.rootRolelist(ucompid);
    }
    //所有子级
    public List<CtCompRole> childRolelist(String uroleid,String ucompid){
       /* return roleMapper.childRolelist(uroleid, ucompid);*/
    	return null;
    }
    public List<CtCompRole> dotree(List<CtCompRole> list,String uroleid,String ucompid,int i){
        List<CtCompRole> listc = childRolelist(uroleid, ucompid);

        if(listc!=null) {
            if(listc.size()>0) {
                for(CtCompRole voc:listc) {
                    String s="";
                    for(int n = 0;n<i;n++)
                        s = s+"&emsp;";
                    voc.setUrolename(s+"∟"+voc.getUrolename());
                    list.add(voc);
                    dotree(list,voc.getUroleid(),ucompid,i+1);
                }
            }
        }
        return null;
    }


    @ServiceLog("更新角色")
    public AjaxResult updateRole(CtCompRole role) {
        String result = null;
        /*AuthRole $role = new AuthRole();
        if(role.getCustomerid()==null||role.getCustomerid().equals(""))
            $role = roleMapper.queryByRolename(role.getRolename());
        else
            $role = roleMapper.queryByRolename1(role.getRolename(),role.getCustomerid());*/
        if (null != role && role.getUrolename() != role.getUrolename()) {
            result = "角色名已存在";
        } else {
            updateByID(role);
        }
        return AppUtil.returnObj(result);
    }

    public List<CtCompRole> queryNotAdmin(String ucompid) {
        Example example = new Example(AuthRole.class);
        Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("urolename", "admin");
        criteria.andEqualTo("ucompid",ucompid);
        return roleMapper.selectByExample(example);
    }

    @ServiceLog("绑定角色权限")
    public AjaxResult bindOpers(String roleid, int[] opids) {
        List<CtRoleOperation> list = new ArrayList<CtRoleOperation>();
        CtRoleOperation roleOperation = null;
        for(int opid: opids){
            roleOperation = new CtRoleOperation();
            roleOperation.setUroleid(roleid);
            //roleOperation.setUopid(opid+"");
            list.add(roleOperation);
        }
        //通用mapper的批量插入竟然不行
//		roleOperMapper.insertList(list);
        roleOperMapper.batchInsert(list);
        return AppUtil.returnObj(null);
    }

    @ServiceLog("解除角色权限")
    public AjaxResult unbindOpers(String roleid, int[] opids){
        List<CtRoleOperation> list = new ArrayList<CtRoleOperation>();
        CtRoleOperation roleOperation = null;
        for(int opid: opids){
            roleOperation = new CtRoleOperation();
            roleOperation.setUroleid(roleid);
            //roleOperation.setUopid(opid +"");
            list.add(roleOperation);
        }
        roleOperMapper.delRoleOpers(list);
        return AppUtil.returnObj(null);
    }
    /*//所有父级
    public List<AuthRole> rootRolelist(String customerid){
        return roleMapper.rootRolelist(customerid);
    }
    //所有子级
    public List<AuthRole> childRolelist(String roleid,String customerid){
        return roleMapper.childRolelist(roleid, customerid);
    }
    public List<AuthRole> dotree(List<AuthRole> list,String roleid,String customerid,int i){
        List<AuthRole> listc = childRolelist(roleid, customerid);

        if(listc!=null) {
            if(listc.size()>0) {
                for(AuthRole voc:listc) {
                    String s="";
                    for(int n = 0;n<i;n++)
                        s = s+"&emsp;";
                    voc.setCname(s+"∟"+voc.getCname());
                    list.add(voc);
                    dotree(list,voc.getRoleid(),customerid,i+1);
                }
            }
        }
        return null;
    }
*/
    @ServiceLog("opcode绑定角色权限")
    public AjaxResult bindOpersByOpcode(String roleid, String[] opcodes,String ucompid) {
        try {
            List<CtRoleOperation> list = new ArrayList<CtRoleOperation>();
            List<CtRoleOperation> delList = new ArrayList<CtRoleOperation>();
            CtRoleOperation roleOperation = null;

            //先解除改角色所有权限
            CtRoleOperation ctRoleOperation = new CtRoleOperation();
            ctRoleOperation.setUroleid(roleid);
            ctRoleOperation.setUcompid(ucompid);
            roleOperMapper.delete(ctRoleOperation);
            List<String> codelist = new ArrayList<String>();
            for (int i=0; i<opcodes.length; i++) {
                if(!codelist.contains(opcodes[i])) {
                    codelist.add(opcodes[i]);
                }
            }

            //获取每个opcode
            for(String  opcode: codelist){
                //通过opcode 获得authOperations对象集合
                //List<PtOperation> authOperations = authOperationMapper.queryOperation(opcode, "");
                List<PtOperation> authOperations = authOperationMapper.queryOperationByCodeAndType(opcode,null);

                //遍历获得每个authOperation
                for (PtOperation authOperation : authOperations) {
                    ctRoleOperation.setUopid(authOperation.getUopid());
                    roleOperMapper.insert(ctRoleOperation);
                }

            }
                return new AjaxResult(1, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AppUtil.returnObj(null);

        }


    }

}

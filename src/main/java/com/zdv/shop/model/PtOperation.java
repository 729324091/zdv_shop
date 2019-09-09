package com.zdv.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;
//权限功能
@Table(name = "pt_operation")
public class PtOperation {
    @Id
    private String uopid;      //主键

    private String uopname;     //权限名称
    private String uoptype;       //操作类型 0无需排后，1新增，2修改，3删除

    private String uopcode;     //权限值

    private String uismenu;     //是否菜单 1是菜单，0非菜单

    private String uophref;     //权限操作链接

    private Integer uopseq;     //显示顺序

    private String uifoper;      //类型 0系统 1经销商（供应商） 2销售商


    public String getUismenu() {
        return uismenu;
    }

    public void setUismenu(String uismenu) {
        this.uismenu = uismenu;
    }

    public String getUifoper() {
        return uifoper;
    }

    public void setUifoper(String uifoper) {
        this.uifoper = uifoper;
    }

    public String getUopid() {
        return uopid;
    }

    public void setUopid(String uopid) {
        this.uopid = uopid;
    }

    public String getUopcode() {
        return uopcode;
    }

    public void setUopcode(String uopcode) {
        this.uopcode = uopcode == null ? null : uopcode.trim();
    }

    public String getUopname() {
        return uopname;
    }

    public void setUopname(String uopname) {
        this.uopname = uopname == null ? null : uopname.trim();
    }

    public String getUophref() {
        return uophref;
    }

    public void setUophref(String uophref) {
        this.uophref = uophref == null ? null : uophref.trim();
    }

    public Integer getUopseq() {
        return uopseq;
    }

    public void setUopseq(Integer uopseq) {
        this.uopseq = uopseq;
    }


    public String getUoptype() {
        return uoptype;
    }

    public void setUoptype(String uoptype) {
        this.uoptype = uoptype == null ? null : uoptype.trim();
    }
}
package com.zdv.shop.weixinh5.controller;

import com.zdv.shop.weixinh5.model.Refund;
import com.zdv.shop.weixinh5.model.RefundResult;

import java.util.Map;

public abstract class SignBuilder {

    public abstract RefundResult refundResultBuild(Map<String,String> map);
    public abstract Map<String, String> getParams(Refund vo);
}

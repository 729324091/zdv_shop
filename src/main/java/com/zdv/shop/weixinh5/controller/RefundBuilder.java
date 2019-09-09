package com.zdv.shop.weixinh5.controller;

import cn.jiguang.common.utils.StringUtils;
import com.zdv.shop.weixinh5.model.Refund;
import com.zdv.shop.weixinh5.model.RefundResult;

import java.util.HashMap;
import java.util.Map;

public class RefundBuilder extends SignBuilder {

    @Override
    public RefundResult refundResultBuild(Map<String, String> map) {
        RefundResult refundResult = new RefundResult();
        refundResult.setReturnCode(map.get("return_code"));
        refundResult.setReturnMsg(map.get("return_msg"));
        refundResult.setResultCode(map.get("result_code"));
        refundResult.setErrCode(map.get("err_code"));
        refundResult.setErrCodeDes(map.get("err_code_des"));
        refundResult.setAppid(map.get("appid"));
        refundResult.setMchId(map.get("mch_id"));
        refundResult.setNonceStr(map.get("nonce_str"));
        refundResult.setSign(map.get("sign"));
        refundResult.setTransactionId(map.get("transaction_id"));
        refundResult.setOutTradeNo(map.get("out_trade_no"));
        refundResult.setOutRefundNo(map.get("out_refund_no"));
        refundResult.setRefundId(map.get("refund_id"));
        refundResult.setRefundFee(Integer.parseInt(map.get("refund_fee")));

//        refundResult.setSettlementRefundFee(Integer.parseInt(map.get("settlement_refund_fee")));
        refundResult.setTotalFee(Integer.parseInt(map.get("total_fee")));
//        refundResult.setSettlementTotalFee(Integer.parseInt(map.get("settlement_total_fee")));
//        refundResult.setFeeType(map.get("fee_type"));
        refundResult.setCashFee(Integer.parseInt(map.get("cash_fee")));
        refundResult.setCashRefundFee(Integer.parseInt(map.get("cash_refund_fee")));


        return refundResult;
    }

    @Override
        public Map<String, String> getParams(Refund vo) {

            Map<String,String> params = new HashMap<String, String>();
            if(StringUtils.isNotEmpty(vo.getAppid())){
                params.put("appid",vo.getAppid());
            }
            if (StringUtils.isNotEmpty(vo.getMchId())) {
                params.put("mch_id", vo.getMchId());
            }
            if (StringUtils.isNotEmpty(vo.getDeviceInfo())) {
                params.put("device_info", vo.getDeviceInfo());
            }
            if(StringUtils.isNotEmpty(vo.getNonceStr())){
                params.put("nonce_str",vo.getNonceStr());
            }
            if (StringUtils.isNotEmpty(vo.getSign())) {
                params.put("sign", vo.getSign());
            }
            if (StringUtils.isNotEmpty(vo.getSignType())) {
                params.put("sign_type", vo.getSignType());
            }
            if (StringUtils.isNotEmpty(vo.getTransactionId())) {
                params.put("transaction_id", vo.getTransactionId());
            }
            if (StringUtils.isNotEmpty(vo.getOutTradeNo())) {
                params.put("out_trade_no", vo.getOutTradeNo());
            }
            if (StringUtils.isNotEmpty(vo.getOutRefundNo())) {
                params.put("out_refund_no", vo.getOutRefundNo());
            }
            if (StringUtils.isNotEmpty(vo.getTotalFee()+"")) {
                params.put("total_fee",Integer.toString(vo.getTotalFee()));
            }
            if (StringUtils.isNotEmpty(vo.getRefundFee()+"")) {
                params.put("refund_fee", Integer.toString(vo.getRefundFee()));
            }
            if (StringUtils.isNotEmpty(vo.getRefundFeeType())) {
                params.put("refund_fee_type",vo.getRefundFeeType());
            }
            if (StringUtils.isNotEmpty(vo.getOpUserId())) {
                params.put("op_user_id",vo.getOpUserId());
            }
            if (StringUtils.isNotEmpty(vo.getRefundAccount())) {
                params.put("refund_account",vo.getRefundAccount());
            }
            return params;
        }

    }
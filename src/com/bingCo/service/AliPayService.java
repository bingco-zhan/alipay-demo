package com.bingCo.service;

import com.alipay.api.*;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.bingCo.config.AlipayConfig;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AliPayService {

    /**
     * 支付宝交易支付接口
     * @param out_trade_no 商户订单号 商户网站订单系统中唯一订单号 必填
     * @param subject 订单名称 必填
     * @param total_amount 付款金额 必填
     * @param body 商品描述 可空
     * @param product_code 销售产品码 可空
     */
    public String pay(String out_trade_no, String subject, String total_amount, String body, String product_code) throws AlipayApiException {
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY,
                AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);

        AlipayRequest request = new AlipayTradeWapPayRequest();

        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel(); // 封装请求支付信息

        model.setOutTradeNo(out_trade_no);
        model.setSubject(subject);
        model.setTotalAmount(total_amount);
        model.setBody(body);
        model.setTimeoutExpress("2m");
        model.setProductCode(product_code);
        request.setBizModel(model);
        request.setNotifyUrl(AlipayConfig.notify_url); // 设置异步通知地址
        request.setReturnUrl(AlipayConfig.return_url); // 设置同步地址

        return client.pageExecute(request).getBody(); // 调用SDK生成表单
    }

    /**
     * 支付管道响应信息验证
     * @param parameterMap 支付宝返回的表单信息
     */
    public Boolean signature(Map<String, String[]> parameterMap) throws AlipayApiException {
        Map<String,String> params = new HashMap<>();
        for (Iterator iterator = parameterMap.keySet().iterator(); iterator.hasNext();) {
            String name = (String) iterator.next();
            String[] values = parameterMap.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);
    }

    /**
     * 订单查询
     * @param out_trade_no 商户订单号 商户网站订单系统中唯一订单号 必填
     * @param trade_no 支付宝交易号
     */
    public Map<String, String> query(String out_trade_no, String trade_no) throws AlipayApiException {

        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY,
                AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        AlipayRequest request = new AlipayTradeQueryRequest();

        AlipayTradeQueryModel model=new AlipayTradeQueryModel();
        model.setOutTradeNo(out_trade_no);
        model.setTradeNo(trade_no);
        request.setBizModel(model);

        AlipayResponse response =client.execute(request);
        Map map = new Gson().fromJson(response.getBody(), Map.class);
        return (Map) map.get("alipay_trade_query_response");
    }

    /**
     * 订单退款
     * @param out_trade_no 商户订单号 和支付宝交易号二选一
     * @param trade_no 支付宝交易号 和商户订单号二选一
     * @param refund_amount 退款金额 不能大于订单总金额
     * @param refund_reason 退款的原因说明
     * @param out_request_no 标识一次退款请求 同一笔交易多次退款需要保证唯一 如需部分退款 则此参数必传
     */
    public Map<String, String> refund(String out_trade_no, String trade_no, String refund_amount, String refund_reason, String out_request_no) throws AlipayApiException {

        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY,
                AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

        AlipayTradeRefundModel model=new AlipayTradeRefundModel();
        model.setOutTradeNo(out_trade_no);
        model.setTradeNo(trade_no);
        model.setRefundAmount(refund_amount);
        model.setRefundReason(refund_reason);
        model.setOutRequestNo(out_request_no);
        request.setBizModel(model);

        AlipayTradeRefundResponse response =client.execute(request);
        Map map = new Gson().fromJson(response.getBody(), Map.class);
        return (Map) map.get("alipay_trade_refund_response");
    }

    /**
     * 退款订单查询
     * @param out_trade_no 商户订单号 和支付宝交易号二选一
     * @param trade_no 支付宝交易号 和商户订单号二选一
     * @param out_request_no 请求退款接口时 传入的退款请求号 如果在退款请求时未传入 则该值为创建交易时的外部交易号
     */
    public Map<String, String> refundQuery(String out_trade_no, String trade_no, String out_request_no) throws AlipayApiException {

            AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY,
                    AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
            AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();

            AlipayTradeFastpayRefundQueryModel model=new AlipayTradeFastpayRefundQueryModel();
            model.setOutTradeNo(out_trade_no);
            model.setTradeNo(trade_no);
            model.setOutRequestNo(out_request_no);
            request.setBizModel(model);

            AlipayTradeFastpayRefundQueryResponse response=client.execute(request);
        Map map = new Gson().fromJson(response.getBody(), Map.class);
        return (Map) map.get("alipay_trade_fastpay_refund_query_response");
    }
}

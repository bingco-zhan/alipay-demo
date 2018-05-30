package com.bingCo.servlet;

import com.alipay.api.AlipayApiException;
import com.bingCo.config.AlipayConfig;
import com.bingCo.service.AliPayService;
import com.bingCo.util.Util;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class AliPayServlet extends BaseServlet {

    private final AliPayService service = new AliPayService();

    public void aliPay(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=" + AlipayConfig.CHARSET);

        try {
            String body = service.pay(new String(req.getParameter("WIDout_trade_no")),
                    new String(req.getParameter("WIDsubject")),
                    new String(req.getParameter("WIDtotal_amount")),
                    new String(req.getParameter("WIDbody")), "QUICK_WAP_WAY");
            resp.getWriter().write(body);//直接将完整的表单html输出到页面
            resp.getWriter().flush();
            resp.getWriter().close();
        }

        catch (AlipayApiException e) {
            e.printStackTrace();
            resp.getWriter().println("支付失败");
        }
    }

    @SuppressWarnings("deprecation")
    public void aliPaySignature (HttpServletRequest req, HttpServletResponse resp) throws AlipayApiException, IOException {

        if(service.signature(req.getParameterMap())){ // 验证成功
            resp.sendRedirect(new String("/return_url.html#验证成功".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            Util.write(req.getParameter("trade_no"), req.getRealPath("trade_no"));
        }else{
            resp.sendRedirect(new String("/return_url.html#验证失败".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        }

    }

    @SuppressWarnings("deprecation")
    public void aliPayQuery(HttpServletRequest req, HttpServletResponse resp) throws AlipayApiException, IOException {
        Object[] trade_nos = Util.read(req.getRealPath("trade_no"));
        if (trade_nos != null) {
            for (int x = 0; x < trade_nos.length; x++) {
                Map<String, String> body = service.query(null, (String) trade_nos[x]);
                Util.println(body, new PrintStream(resp.getOutputStream()));
            }
        }
    }

    @SuppressWarnings("deprecation")
    public void aliPayRefund(HttpServletRequest req, HttpServletResponse resp) throws AlipayApiException, IOException {
        Map<String, String> refund = service.refund(req.getParameter("WIDout_trade_no"), req.getParameter("WIDtrade_no"), req.getParameter("WIDrefund_amount"),
                req.getParameter("WIDrefund_reason"), req.getParameter("WIDout_request_no"));
        Util.write(refund.get("trade_no") + ":" + refund.get("out_trade_no"), req.getRealPath("refund_trade_no"));
        Util.println(refund, new PrintStream(resp.getOutputStream()));
    }

    @SuppressWarnings("deprecation")
    public void aliPayRefundQuery(HttpServletRequest req, HttpServletResponse resp) throws AlipayApiException, IOException {
        Object[] trade_nos = Util.read(req.getRealPath("refund_trade_no"));
        if (trade_nos != null) {
            for (int x = 0; x < trade_nos.length; x++) {
                String[] otn = ((String) trade_nos[x]).split(":");
                Map<String, String> body = service.refundQuery(null, otn[0], otn[1]);
                Util.println(body, new PrintStream(resp.getOutputStream()));
            }
        }
    }

    @Override
    protected String getRoute() {
        return "/aliPay";
    }
}

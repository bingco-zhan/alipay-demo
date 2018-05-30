package com.bingCo.config;

public class AlipayConfig {
    // 商户appid
    public static String APPID = "2016091400509812";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDMO0qVcI+PGbdCEmvRkIJ72agSVi+Sm2vkEEPjDyRb+2UHKABKgaSZOFGq8mv+SudJuRaUo0EPwcrdesRqR41lv4YXo9crDKtP1a1xOd9aMCtF+wki2kDA5d+H1FTtSeUy65swtvLR/tixbQzKuoYCHeT3DhYoR/tT7m0Mvld26LAK58pYA5bGr/m+F2TUo1gZOxEI1D+wQIWV5MaHlleAoS0cplnz9NevHsZ776uLMoBTjxP8gHYjc2UtRtqJuNX+ZC1MM2LFTWmhAGpYzTYnO+jcjfkhCzgCfpXgqC+pe5Qq5zWsPnBOSU+wvsSIEr4RDrsgSPFW6+FE3YEzGj7DAgMBAAECggEBAKDACnSVpGl+VEAtP7AzjoDtUN0d+VPsAYABTW3q/TNpLJv1YEVExcnUCnQDW/1L98CPbraPySYd4w9xEiiVKTZtCZ6R10qLLkwoY9w7MRJL18O9U/Oa1lz+knLXLgGXqE/sIrq1RWsErIr6ypbHObNw1EehTs9l2Oy3Ph5Js45jLvLdW+I88WG6y0P/ZIW1At1m9tREFQePyq2Jdv0Q439MewzKs0GPEsjRLJqZUAdmjPEhD6Na9IWykPAiDlqMTb+Rv3KLn7pr3BaSLmNsjjeJ5wFl3lh5yTqrmDSay6ZNBllDBjHamRp7GFvhMqm32ZMH77bzaG0OMLKXZBATUAECgYEA+LpnW8tRgHFYVUbvViIj5wKoN78FdM4G8vTJoGcrjE5A7/X6TeSOGErU+4VOHku5IpmQSRRobLnaMlHmdGMs1f6ZLxgV5pek0cfM0vvqHMYOJpd7s9IFl5seaFp4vkpgzZgHr3354SVKKlAycgbsg2Z1kWDZQDkFtVTKqaQeu/ECgYEA0jPa4TYuLa9Tm5QBvf88EHDjGw8S4Bs2n6o3jaM8k9nXDlX39SF+d88lows/Mj80BkOc9mX6D+ugtYsVct9Ep1Mrjb7b9xED9JSvHrg6MpwizusfIrJRDWduYIA3EH4b0xajVpffL0sy+6e5/LGIrKMgdGS9q45h5RrpcHRYafMCgYBjxtMDndI6Bj37J5OHJ/bjCsvUYQqdSrC68buYXwq3JAUkvbYFXTQrRomdxGIMATBpUtTwnAEv7HY9TWF3OvmXdqSYob7XW+nARbhCws+tbz3p+UakYhHgOVe/YNui6k9e6ifbHFwLF1xjEPzLhT+HjqiHAyTjgHxru3H5D19M4QKBgQCjR0pfJDHspMMCF47e4hLrBnUywJu93wWoMpVifQkCI/WMGjU4gWL2d77ZjlPxmOy1bPHQ59e9ZB96xWXMzCdM/irXuQiuOtZittfsudfjFitnpXWTAwY4rj4r0Yx0UDEW3//StLfVycj5eAjdg54R08jTAKngSAI875GjG4zOBwKBgCkJnPb1YTFOAhp9aY/jy7vaE/QBO2Zg3rK9tkJxWWCETM5+yVF7KWEBbcWoqukU8GaVndSiepXNT5AI0SJWMHDRjO84B7popL8Ij936sNIhoNbQ1ehz+z0K2mDdoCFjqNGmD0WdaYzxBvLlj7JE4GuZZrJuUDK9thHUC6hkpzl6";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //public static String notify_url = "http://商户网关地址/alipay.trade.wap.pay-JAVA-UTF-8/notify_url.jsp";
    public static String notify_url = "http://bingco.imwork.net:45090/notify_url.jsp";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://bingco.imwork.net:45090/aliPay!aliPaySignature.do";
    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwwT53h0YfJzt7N9wnpiCPKUjhJV45Ex+iAdQaXGc4oZb7ZOKd1vg25fXaii15IopvCeemMzfX7pMOKcb44pKPSXRCy9nUt6aLGaocFkmT/Z71bWuBxyaTqyPZ60WcoNAae18jaOk6wSn9v64oUHzB6s7XP2S3Jxwp6aoZWENjrlWodBxfUas+6E6kN4vztBXqd1ilQiFdEC6sTWQxDk/vPQurAPvy/YMObLBG0oNoXkBks7Tne9fmyDCIjcamatIvGbgKVdjNyzbbNQt3u5SYicl8NM01cAfhH2TR68pV4v+Gsnel8dhDNIbmf6INImHTV6PKe4tKx9esSSMZPy5uwIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}

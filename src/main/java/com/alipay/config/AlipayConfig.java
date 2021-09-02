package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000116687398";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQChHYS2k4I90n0d0b3NQ3VfW+QOKsbGL/FIY2UcdH5cT1LLblQ5lVcMuZBhQTeRZXoDRLjyOjziCv306hnO20j7cX2emTma3qHSieChWWAyYwsj8ST0gzKtPIkUweJvOeBR2S+vgkBAqJgnAepoaJ52q9qYnRdsWzr4RIQ32OUjmfnYt6zFH6CRR1vaOUaxAmxpUWqqN8EnjmKS7sef6umkxbIn6a08270JgHxNSO7aO09zGE+BjWEtdykN3bhEe2USRoMXhm7Oo+IQAqOO6KzK5vvGd+4sDKeNEho6r6TiPF9qjTywtavA3heSpthMJl/UCDmANZoJLD/Sbxds3zQPAgMBAAECggEAaDptaaOhxRbPpCPU0t6KJueOcG/R6dKIGBldBvyB5nvWhw5X5GytROki8xe6uA/a0BR8RQ/R5TXRNsjfa0fHPgschiZ/8qWGMGrL6/Wi2tXiFh1hl+DsPtADqm8HaocZjDmhtLvhjzLCQ7HkkCjmIsVohARUa9/VFVlV478n9VATKA6eycNzKzm8D3BsytFZBr7DXdsIPadIOsArKko5WofI0kmpue+VvevA100OPyxir2PX/S+jD6P8l3+CCg+Lh9Iah49Sj9L3Yz5wrQQNW163jcqgUeeZLiyr2v4sWp0QPyNSSwOSYyoRGJoCFygbRrETjy+dZY7V+JYMlCmw0QKBgQDy7bg6eF9/5WI2TZ7n7X/U396K6KD7T9tyDaYXXsPq8RC0Cbwh/Y/kQItU9yQntbJrgurTxZLpe+MuHeGNVyc22RXMSAQFBV0/bSgwLq2MZOHaplHu9xaRwUA9Uxl0v167dMOoGmSIQILakFa8Y6cG4y3Cau3cQ3gXMEGtNp4TWQKBgQCpyNdfQ+QdkFNPrSuAhxRP1tXI1m+zBHmPQG2pIfshPFgAuEsFrGXhGLNeiwhVuHEWu4ow5al+ig1MLx0JRFRGCq/Cz3leCPs8SC9wNYr0cbWgF88CrPVLKzrMynJCRSRUgLEipR+TM/V1HJKAH2IXBHN9I0gctZMMXDsv25udpwKBgFEdVUWYyfKPqkKNmoDAyc1PzyQ81vvzR0gHDK2DBBIf2j7zfTEwVGqXrq55M0ZbIi9J8JUzxIFXz5ynd5fVQQ/MXr3uUgFHMEKsdR3ZiDN3v/5dSMBtGskiaQvRAo/8Emqva2FnJqhkOg024u9Wqv/evXnMcI36rU+42kU3FoNJAoGBAJky91sniGtAslvflrQ3rpVH9clt7IYs0H75FLobRl279HBZ2p/46kjNH/R8IX4vmiN3/36VjHX4nX0TufCFrkExHVz8FrhKBGBlhiCKv6JIBCm7sffpFsDH9huZxLWVa+2v3aW0NyR9KsuMh4iCSOEaDBdrQYNeN5xckK9jSixbAoGBAOdy5b4jpnzI3wth+AK1oIRdbOj38Rz8yd5FKF39jsOLYizehkOxVLsDXriKFth24xXDbjpM1JWZVz6Vwcmn4p4Gtgb8JiOnCMsp+cwhNpiZn/v8+V1PMpqY+qBRYR15TltQA2p/8/Oe6CJCjOZvoaWyulQA2PRWg3cWICNPMI/r";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoR2EtpOCPdJ9HdG9zUN1X1vkDirGxi/xSGNlHHR+XE9Sy25UOZVXDLmQYUE3kWV6A0S48jo84gr99OoZzttI+3F9npk5mt6h0ongoVlgMmMLI/Ek9IMyrTyJFMHibzngUdkvr4JAQKiYJwHqaGiedqvamJ0XbFs6+ESEN9jlI5n52LesxR+gkUdb2jlGsQJsaVFqqjfBJ45iku7Hn+rppMWyJ+mtPNu9CYB8TUju2jtPcxhPgY1hLXcpDd24RHtlEkaDF4ZuzqPiEAKjjuisyub7xnfuLAynjRIaOq+k4jxfao08sLWrwN4XkqbYTCZf1Ag5gDWaCSw/0m8XbN80DwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/pay/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/pay/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


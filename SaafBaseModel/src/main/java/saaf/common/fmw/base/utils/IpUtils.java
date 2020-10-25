package saaf.common.fmw.base.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取客户端IP
 * 禁止内部用户使用外网IP登录
 */
public class IpUtils {

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-REAL-IP");
        System.out.println("getip===="+ip);
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("x-forwarded-for");
            System.out.println("x-forwarded-for ip: " + ip);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                // 多次反向代理后会有多个ip值，第一个ip才是真实ip
                if( ip.indexOf(",")!=-1 ){
                    ip = ip.split(",")[0];
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                System.out.println("Proxy-Client-IP ip: " + ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                System.out.println("WL-Proxy-Client-IP ip: " + ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                System.out.println("HTTP_CLIENT_IP ip: " + ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Real-IP");
                System.out.println("X-Real-IP ip: " + ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                System.out.println("getRemoteAddr ip: " + ip);
            }

            if("0:0:0:0:0:0:0:1".equals(ip)){
                return "127.0.0.1";
            }
        }

        System.out.println("获取客户端ip: " + ip);
        return ip;
    }

}

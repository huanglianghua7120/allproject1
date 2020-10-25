package saaf.common.fmw.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CrossFilter implements Filter {
    private FilterConfig _filterConfig = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(CrossFilter.class);
    public CrossFilter() {
        super();
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        _filterConfig = filterConfig;
    }

    public void destroy() {
        _filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //((HttpServletResponse)response).addHeader("Access-Control-Allow-Origin", "*");
        //为了保证安全性，指定设置允许的域名请求
        String[] allowDomains = {"http://http://192.168.175.130", "http://http://192.168.175.131"};
        HttpServletResponse rep = ((HttpServletResponse)response);
        Set allowOrigins = new HashSet(Arrays.asList(allowDomains));
        String originHeads = rep.getHeader("Origin");
        if(allowOrigins.contains(originHeads)){
            //设置允许跨域的配置
            //这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
            rep.setHeader("Access-Control-Allow-Origin", originHeads);
        }

        //((HttpServletResponse)response).addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        //为了保证安全性，只保留POST、GET
        ((HttpServletResponse)response).addHeader("Access-Control-Allow-Methods", "POST, GET");
        ((HttpServletResponse)response).addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, XRequested-With, Content-Type, Accept, TokenCode");
        ((HttpServletResponse)response).addHeader("Access-Control-Max-Age", "1728000");
        ((HttpServletResponse)response).setHeader("Content-type", "text/html;charset=UTF-8"); 
        chain.doFilter(request, response);
    }
}
package saaf.common.fmw.schedule.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CommonsServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(CommonsServlet.class);
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            logger.info("配置加载中…………………………");
//            System.out.println("开始执行……………………………1…………………");
            //ApplicationContext context = SaafToolUtils.context;
//            System.out.println("开始执行……………………………2…………………");
            logger.info("配置加载完成…………………………");
        } catch (Exception e) {
            logger.error("未知错误:{}", e);
            logger.error("配置加载失败！" + e);
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);

    }
}

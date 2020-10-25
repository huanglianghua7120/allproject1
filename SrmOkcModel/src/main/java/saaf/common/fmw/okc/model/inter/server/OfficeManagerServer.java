package saaf.common.fmw.okc.model.inter.server;

import com.base.sie.common.utils.ConfigurationFileUtils;
import com.yhg.base.utils.PropertiesUtils;
import org.jodconverter.office.LocalOfficeManager;
import org.jodconverter.office.OfficeException;
import org.jodconverter.office.OfficeManager;
import saaf.common.fmw.base.utils.UtilsException;

import java.net.URLDecoder;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：OfficeManagerServer.java
 * Description：office管理服务类
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date     @Author(Updated By)     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019/6/13      欧阳岛          创建
 * <p>
 * ==============================================================================
 */
public class OfficeManagerServer {

    private static final String officeConfigFilePath = "/officeConfig.properties";

    private static OfficeManager officeManager;

    private static boolean runFlag = false;

    /**
     * 打开LibreOffice
     * @return
     * ==============================================================================
     * Version       Date         Updated By    Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/13      欧阳岛          创建
     * ==============================================================================
     */
    public synchronized static void startOffice() throws Exception {
        if (!runFlag) {
            try {
                String officeConfig = ConfigurationFileUtils.getConfigurationInputStream(officeConfigFilePath).getPath();
                officeConfig = URLDecoder.decode(officeConfig, "UTF-8");
                String LibreOfficeHome = PropertiesUtils.readValue(officeConfig, "libreOffice.home.path");
                officeManager = LocalOfficeManager.builder().officeHome(LibreOfficeHome).install().build();
                officeManager.start();
                runFlag = true;
            } catch (Exception e) {
                //e.printStackTrace();
                //throw new RuntimeException(e.getMessage(), e.getCause());
                throw new UtilsException("LibreOffice打开异常，请检查安装或联系IT查看!");
            }
        }
    }

    /**
     * 关闭LibreOffice
     * @return
     * ==============================================================================
     * Version       Date         Updated By    Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/13      欧阳岛          创建
     * ==============================================================================
     */
    public synchronized static void stopOffice() throws Exception {
//        OfficeUtils.stopQuietly(officeManager);
        try {
            officeManager.stop();
        } catch (OfficeException e) {
            //e.printStackTrace();
            throw new UtilsException("LibreOffice服务关闭异常！");
        }
        runFlag = false;
    }
}

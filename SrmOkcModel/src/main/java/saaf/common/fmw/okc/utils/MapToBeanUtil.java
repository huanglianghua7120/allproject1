package saaf.common.fmw.okc.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;
import java.util.Set;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：MapToBeanUtil.java
 * Description：拷贝Map数据到JavaBean
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date     @Author(Updated By)     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019/6/20      欧阳岛          创建
 * <p>
 * ==============================================================================
 */
public class MapToBeanUtil {
    public static <T> void copyPropertiesInclude(Map<String, Object> updateProperties, T bean) {
        Set<Map.Entry<String, Object>> revisabilityFiledSet = updateProperties.entrySet();
        for (Map.Entry<String, Object> entry : revisabilityFiledSet) {
            Object value = entry.getValue();
//if(value != null){
            try {
                BeanUtils.setProperty(bean, entry.getKey(), value);
            } catch (Exception e) {
            }
//}
        }
    }
}

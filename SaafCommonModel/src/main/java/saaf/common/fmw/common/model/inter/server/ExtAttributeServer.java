package saaf.common.fmw.common.model.inter.server;


import com.yhg.hibernate.core.dao.BaseViewObject;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.common.model.entities.readonly.ExtAccessAttributeEntity_HI_RO;
import saaf.common.fmw.common.model.inter.IExtAttributeServer;


/**
 * Created by huangtao on 2016/9/26.
 */
@Component("extAttributeServer")
public class ExtAttributeServer implements IExtAttributeServer {

//    @Resource(name = "baseDAO")
//    private IBaseDAO<ExtAccessAttributeEntity_HI_RO> baseDAO;
    
    @Autowired
    private BaseViewObject<ExtAccessAttributeEntity_HI_RO> extAccessAttributeDAO_HI_RO;

    public List<ExtAccessAttributeEntity_HI_RO> findAttribute(String interFaceCode) {
        List<ExtAccessAttributeEntity_HI_RO> list = new ArrayList<>();
        if (StringUtils.isBlank(interFaceCode))
            return list;
        String sql = ExtAccessAttributeEntity_HI_RO.query + " and eam.INTERFACE_CODE=?";
        list = extAccessAttributeDAO_HI_RO.findList(sql, new Object[] { "interFaceCode" });
        return list;
    }
}

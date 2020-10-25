package saaf.common.fmw.common.model.inter;


import java.util.List;

import saaf.common.fmw.common.model.entities.readonly.ExtAccessAttributeEntity_HI_RO;


/**
 * Created by huangtao on 2016/9/26.
 */
public interface IExtAttributeServer {

    public List<ExtAccessAttributeEntity_HI_RO> findAttribute(String interFaceCode);
}

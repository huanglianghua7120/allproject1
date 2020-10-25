package saaf.common.fmw.base.model.dao.readonly;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SrmBaseParamsEntity_HI_RO;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

/**
 * 
 * @author zhy
 * Created by zhy on 2018/4/08.
 */
@Component("srmBaseParamsDAO_HI_RO")
public class SrmBaseParamsDAO_HI_RO extends DynamicViewObjectImpl<SrmBaseParamsEntity_HI_RO> {
	
	public SrmBaseParamsDAO_HI_RO(){
		super();
	}
}

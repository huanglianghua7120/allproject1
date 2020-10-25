package saaf.common.fmw.pos.model.dao.readonly;

import org.springframework.stereotype.Component;

import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierGradeLinesEntity_HI_RO;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

/**
 * 
 * @author zhy Created by zhy on 2018/09/04.
 */
@Component("srmPosSupplierGradeLinesDAO_HI_RO")
public class SrmPosSupplierGradeLinesDAO_HI_RO extends DynamicViewObjectImpl<SrmPosSupplierGradeLinesEntity_HI_RO>{
	
	public SrmPosSupplierGradeLinesDAO_HI_RO(){
		super();
	}
}

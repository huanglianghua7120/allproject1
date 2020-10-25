package saaf.common.fmw.pos.model.dao.readonly;

import org.springframework.stereotype.Component;

import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierGradeEntity_HI_RO;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

/**
 * 
 * @author zhy Created by zhy on 2018/09/04.
 */
@Component("srmPosSupplierGradeDAO_HI_RO")
public class SrmPosSupplierGradeDAO_HI_RO extends DynamicViewObjectImpl<SrmPosSupplierGradeEntity_HI_RO> {
	
	public SrmPosSupplierGradeDAO_HI_RO() {
		super();
	}
}

package saaf.common.fmw.oilgasstation.model.dao.readonly;

import org.springframework.stereotype.Component;

import saaf.common.fmw.oilgasstation.model.entities.readonly.SaafOilGasStationEntity_HI_RO;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

@Component("saafOilGasStationDAO_HI_RO")
public class SaafOilGasStationDAO_HI_RO extends DynamicViewObjectImpl<SaafOilGasStationEntity_HI_RO>  {
	public SaafOilGasStationDAO_HI_RO() {
		super();
	}

}

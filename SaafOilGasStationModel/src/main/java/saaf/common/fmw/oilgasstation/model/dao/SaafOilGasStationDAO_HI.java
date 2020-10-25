package saaf.common.fmw.oilgasstation.model.dao;

import org.springframework.stereotype.Component;

import saaf.common.fmw.oilgasstation.model.entities.SaafOilGasStationEntity_HI;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

@Component("saafOilGasStationDAO_HI")
public class SaafOilGasStationDAO_HI extends ViewObjectImpl<SaafOilGasStationEntity_HI>  {
	public SaafOilGasStationDAO_HI() {
		super();
	}

	@Override
	public Object save(SaafOilGasStationEntity_HI entity) {
		return super.save(entity);
	}
	
}

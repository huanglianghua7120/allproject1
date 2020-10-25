package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActReDeploymentEntity_HI;
import org.springframework.stereotype.Component;

@Component("actReDeploymentDAO_HI")
public class ActReDeploymentDAO_HI extends ViewObjectImpl<ActReDeploymentEntity_HI>  {
	public ActReDeploymentDAO_HI() {
		super();
	}

	@Override
	public Object save(ActReDeploymentEntity_HI entity) {
		return super.save(entity);
	}
}

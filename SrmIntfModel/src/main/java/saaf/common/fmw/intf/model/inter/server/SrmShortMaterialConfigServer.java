package saaf.common.fmw.intf.model.inter.server;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.intf.model.inter.ISrmShortMaterialConfig;
import saaf.common.fmw.po.model.entities.SrmPoLinesEntity_HI;
import saaf.common.fmw.po.model.entities.SrmShortMaterialConfigEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmShortMaterialConfigEntity_HI_RO;

@Component("srmShortMaterialConfigServer")
public class SrmShortMaterialConfigServer implements ISrmShortMaterialConfig{
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmShortMaterialInvServer.class);
	
	@Autowired
	private BaseViewObject<SrmShortMaterialConfigEntity_HI_RO> srmShortMaterialConfigDAO_HI_RO;
	
	@Autowired
	private ViewObject<SrmShortMaterialConfigEntity_HI> srmShortMaterialConfigDAO_HI;
	
	@Override
	public Pagination<SrmShortMaterialConfigEntity_HI_RO> findQueryConfig(JSONObject jsonParams, Integer pageIndex,
			Integer pageRows) throws Exception {
		StringBuffer queryString = new StringBuffer(SrmShortMaterialConfigEntity_HI_RO.QUERY_SQL);
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			SaafToolUtils.parperParam(jsonParams, "attr_name", "attrName", queryString, map, "=");
			queryString.append(" order by id ");
			
			Pagination<SrmShortMaterialConfigEntity_HI_RO> resultList = 
					srmShortMaterialConfigDAO_HI_RO.findPagination(queryString, map, pageIndex, pageRows);
			
		     

			return resultList;
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new Exception(e);    
	            
		}
	}

	@Override
	public String saveConfig(JSONObject params) throws Exception {	
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", params.getInteger("id") );
		SrmShortMaterialConfigEntity_HI obj = srmShortMaterialConfigDAO_HI.findByProperty(param).get(0);

		if(null != obj){
			obj.setIsValid(params.getString("isValid"));
			obj.setLastUpdateDate(new Date());
			obj.setOperatorUserId(params.getInteger("varUserId"));
			srmShortMaterialConfigDAO_HI.saveOrUpdate(obj);
			
			return "S";
		}
		
		return "E";
	}

}

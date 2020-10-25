package saaf.common.fmw.intf.model.inter.server;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.intf.model.inter.ISrmShortMaterialInv;
import saaf.common.fmw.po.model.entities.readonly.SrmShortMaterialInvEntity_HI_RO;

@Component("srmShortMaterialInvServer")
public class SrmShortMaterialInvServer implements ISrmShortMaterialInv {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmShortMaterialInvServer.class);

	@Autowired
	private BaseViewObject<SrmShortMaterialInvEntity_HI_RO> srmShortMaterialInvDAO_HI_RO;

	@Override
	public Pagination<SrmShortMaterialInvEntity_HI_RO> findU9MaterielInv(JSONObject jsonParams, Integer pageIndex,
			Integer pageRows) throws Exception {
		StringBuffer queryString = new StringBuffer();
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			queryString.append(SrmShortMaterialInvEntity_HI_RO.QUERY_SQL);
			SaafToolUtils.parperParam(jsonParams, "c.inst_id", "moorg", queryString, map, "=");
			SaafToolUtils.parperParam(jsonParams, "a.MATERIAL_CODE", "itemCode", queryString, map, "like");
			SaafToolUtils.parperParam(jsonParams, "a.seibanno", "seibanno", queryString, map, "like");
		//	SaafToolUtils.parperParam(jsonParams, "a.effective", "effective", queryString, map, "=");
			
			if("1".equals(jsonParams.getString("considerdelivplan") )){
				queryString.append(" and a.considerdelivplan='1' ");
				//SaafToolUtils.parperParam(jsonParams, "a.considerdelivplan", "considerdelivplan", queryString, map, "=");
			}else if("0".equals(jsonParams.getString("considerdelivplan") )){
				//SaafToolUtils.parperParam(jsonParams, "a.considerdelivplan", "considerdelivplan", queryString, map, "<>");
				queryString.append(" and a.considerdelivplan <> '1' ");
			}
			
			String version = jsonParams.getString("version");
			if (null == version || "1".equals(version)) {
				queryString.append(" and a.CREATION_DATE >= Curdate()  ");
			} else if ("0".equals(version)) {
				queryString.append(" and a.CREATION_DATE >= CURDATE() + INTERVAL-(1)day and a.CREATION_DATE < Curdate()  ");
			}
			
		    queryString.append(" order by c.inst_id,a.MATERIAL_CODE ");

			Pagination<SrmShortMaterialInvEntity_HI_RO> resultList = srmShortMaterialInvDAO_HI_RO
					.findPagination(queryString, map, pageIndex, pageRows);

			return resultList;
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new Exception(e);

		}
	}

}

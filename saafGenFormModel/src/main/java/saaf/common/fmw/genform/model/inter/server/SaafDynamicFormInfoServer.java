package saaf.common.fmw.genform.model.inter.server;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.genform.model.dao.SaafDynamicFormInfoDAO_HI;
import saaf.common.fmw.genform.model.dao.readonly.SaafDynamicFormInfoDAO_HI_RO;
import saaf.common.fmw.genform.model.entities.SaafDynamicFormInfoEntity_HI;
import saaf.common.fmw.genform.model.entities.readonly.SaafDynamicFormInfoEntity_HI_RO;
import saaf.common.fmw.genform.model.inter.ISaafDynamicFormInfo;
import saaf.common.fmw.genform.utils.FastDFSUploadUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.dao.ViewObjectImpl;
import com.yhg.hibernate.core.paging.Pagination;

@Component("saafDynamicFormInfoServer")
public class SaafDynamicFormInfoServer implements ISaafDynamicFormInfo{
	private static final Logger LOGGER = LoggerFactory.getLogger(SaafDynamicFormInfoServer.class);
	@Autowired
	private ViewObject<SaafDynamicFormInfoEntity_HI>  saafDynamicFormInfoDAO_HI;
	@Autowired
	private BaseViewObject<SaafDynamicFormInfoEntity_HI_RO> saafDynamicFormInfoDAO_HI_RO;
	
	public SaafDynamicFormInfoServer() {
		super();
	}

	@Override
	public String findPagenation(int pageIndex, int pageSize,
			JSONObject queryParamJSON) {
		String countSql = SaafDynamicFormInfoDAO_HI_RO.COUNTS_SQL;
		StringBuffer querySql = new StringBuffer("select * from saaf_dynamic_form_info where 1=1 ");
		Map<String, Object> parameterMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		Iterator iterator = null;
	    if (null != parameterMap){
	      for (iterator = parameterMap.keySet().iterator(); iterator.hasNext(); ) {
	        String key = (String)iterator.next();
	        querySql.append(" and "+key+"=:"+key);
	      }
	    }
	    querySql.append(" order by id desc");
		Pagination<SaafDynamicFormInfoEntity_HI_RO> list = saafDynamicFormInfoDAO_HI_RO.findPagination(querySql.toString(), countSql, parameterMap, pageIndex, pageSize);
		
		return JSON.toJSONString(list);
	}

	public String findSaafDynamicFormInfoInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<SaafDynamicFormInfoEntity_HI> findListResult = saafDynamicFormInfoDAO_HI.findList("from SaafDynamicFormInfoEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveSaafDynamicFormInfoInfo(JSONObject queryParamJSON) {
		SaafDynamicFormInfoEntity_HI saafDynamicFormInfoEntity_HI = JSON.parseObject(queryParamJSON.toString(), SaafDynamicFormInfoEntity_HI.class);
		Object resultData = saafDynamicFormInfoDAO_HI.save(saafDynamicFormInfoEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}

	@Override
	public boolean deleteSaafDynamicFormInfo(Integer id) {
		boolean result = false;
		try {
			SaafDynamicFormInfoEntity_HI entity = this.getById(id);
			
			//删除记录
			saafDynamicFormInfoDAO_HI.delete(id);
			
			//删除表
			//entity.getTableName();
			
			//删除文件服务器上的页面文件
			FastDFSUploadUtil util = new FastDFSUploadUtil();
			JSONObject ret = util.delete(entity.getGroupname(), entity.getRemotefilename());
			result = ret.getString("staus").equalsIgnoreCase("S")?true:false;
		} catch (Exception e) {
			////e.printStackTrace();
			LOGGER.error("",e);
		}
		return result;
	}

	@Override
	public SaafDynamicFormInfoEntity_HI getById(Integer id) {
		return saafDynamicFormInfoDAO_HI.getById(id);
	}
}

package saaf.common.fmw.base.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SrmBaseLocationsEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseLocationsEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseLocations;
import saaf.common.fmw.common.utils.SaafToolUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：地址
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmBaseLocationsServer")
public class SrmBaseLocationsServer implements ISrmBaseLocations {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseLocationsServer.class);

	@Autowired
	private BaseViewObject<SrmBaseLocationsEntity_HI_RO> srmBaseLocationsEntity_HI_RO;

    @Autowired
    private ViewObject<SrmBaseLocationsEntity_HI> srmBaseLocationsDAO_HI;
	public SrmBaseLocationsServer() {
		super();
	}

	/**
	 * 查询组织
	 * @param jsonParams 查询参数
	 * @param pageIndex 查询页码
	 * @param pageRows 分页大小
	 * @return 查询结果
	 * Update History
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Override
	public Pagination<SrmBaseLocationsEntity_HI_RO> findSrmBaseLocationsInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
		try{
			StringBuffer queryString = new StringBuffer();
			Map<String, Object> queryParamMap = new HashMap<>();
			if(null != jsonParams){
				if("bill".equals(jsonParams.getString("locationType"))){
					queryString.append(SrmBaseLocationsEntity_HI_RO.QUERY_BILL_TO_LOC_SQL);
				}else if ("ship".equals(jsonParams.getString("locationType"))){
					queryString.append(SrmBaseLocationsEntity_HI_RO.QUERY_SHIP_TO_LOC_SQL);
				}else {
					queryString.append(SrmBaseLocationsEntity_HI_RO.QUERY_LOC_SQL);
				}

				if(null != jsonParams.getString("locationCode")){
					SaafToolUtils.parperParam(jsonParams, "t.location_code","locationCode", queryString, queryParamMap, "like");
				}
				if(null != jsonParams.getString("locationName")){
					SaafToolUtils.parperParam(jsonParams, "t.location_name","locationName", queryString, queryParamMap, "like");
				}
                if(null != jsonParams.getInteger("organizationId")){
                    SaafToolUtils.parperParam(jsonParams, "t.organization_id","organizationId", queryString, queryParamMap, "=");
                }
			}
			String countSql = "select count(1) from (" + queryString + ")";
			return srmBaseLocationsEntity_HI_RO.findPagination(queryString.toString(),countSql, queryParamMap, pageIndex, pageRows);
		}catch (Exception e){
			//LOGGER.error("未知错误:{}", e)();
			throw new Exception(e);
		}
	}
	/**
	 * 查询组织
	 * @param paramJSON 查询参数
	 * @param pageIndex 查询页码
	 * @param pageRows 分页大小
	 * @return 查询地址详情结果
	 * Update History
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Override
	public Map<String, Object> findSrmBaseLocationsInfoByDefault(JSONObject paramJSON, Integer pageIndex, Integer pageRows) {
		// 收货方
		Map<String, Object> result = new HashMap<>();
		/*Integer orgId = paramJSON.getInteger("parentInstId");
		Map<String, Object> params = new HashMap<>();
		params.put("orgId", orgId);
		StringBuffer sql = new StringBuffer("select l.location_id locationId, l.location_code locationCode from saaf_institution i\n" +
				"   left join Srm_Base_Locations l on i.ship_to_location_id = l.location_id\n" +
				"    where inst_id = :orgId");*/

        Integer organizationId = paramJSON.getInteger("organizationId");
        String organization=" and t.organization_id="+organizationId;
        StringBuffer sql = new StringBuffer(SrmBaseLocationsEntity_HI_RO.QUERY_SHIP_TO_LOC_SQL);
        sql.append(organization);
		List<SrmBaseLocationsEntity_HI_RO> localtions1 = srmBaseLocationsEntity_HI_RO.findList(sql.toString());
		if(localtions1 != null && localtions1.size() > 0){
			result.put("shipInfo", localtions1.get(0).getLocationId() +"&"+localtions1.get(0).getLocationName());
		}
		// 收单方
		/*StringBuffer sql2 = new StringBuffer("select l.location_id locationId, l.location_code locationCode from saaf_institution i\n" +
				"   left join Srm_Base_Locations l on i.bill_to_organization_id = l.location_id\n" +
				"    where inst_id = :orgId");*/
        StringBuffer sql2 = new StringBuffer(SrmBaseLocationsEntity_HI_RO.QUERY_BILL_TO_LOC_SQL);
        sql2.append(organization);
		List<SrmBaseLocationsEntity_HI_RO> localtions2 = srmBaseLocationsEntity_HI_RO.findList(sql2.toString());
		if(localtions2 != null && localtions2.size() > 0){
			result.put("billInfo", localtions2.get(0).getLocationId() +"&"+localtions2.get(0).getLocationName());
		}
		return result;
	}

    /**
     * Description：删除地点
     * @param
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      谢晓霞          创建
     * ==============================================================================
     */
    @Override
    public JSONObject deleteBaseLocation(JSONObject params) throws Exception {
        LOGGER.info("删除信息，参数：" + params.toString());
        JSONArray locationIds = params.getJSONArray("baseLocationList");
        SrmBaseLocationsEntity_HI line = new SrmBaseLocationsEntity_HI();
        for (int i = 0, j = locationIds.size(); i < j; i++) {
            Integer locationId = locationIds.getInteger(i);
            if (!(locationId == null || "".equals(locationId))) {
                //line = srmBaseLocationsDAO_HI.getById(locationId);
                //srmBaseLocationsDAO_HI.delete(line);
                srmBaseLocationsDAO_HI.delete(locationId);
            }
        }
        return SToolUtils.convertResultJSONObj("S", "删除成功", locationIds.size(), null);
    }

    /**
     * Description：保存地点
     * @param
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      谢晓霞          创建
     * ==============================================================================
     */
    public JSONObject saveBaseLocation(JSONObject jsonParams) throws Exception {
        JSONObject jsonData = new JSONObject();
        Integer userId = jsonParams.getInteger("varUserId"); // 用户Id
        JSONArray baseLocationList = jsonParams.getJSONArray("baseLocationList");
        if(!ObjectUtils.isEmpty(baseLocationList)){
            for(int i=0;i<baseLocationList.size();i++){
                SrmBaseLocationsEntity_HI locationNew=new SrmBaseLocationsEntity_HI();
                JSONObject location=baseLocationList.getJSONObject(i);
                SrmBaseLocationsEntity_HI baseLocation = JSON.parseObject(location.toJSONString(), SrmBaseLocationsEntity_HI.class);
                if(!ObjectUtils.isEmpty(baseLocation.getLocationId())){
                    locationNew=srmBaseLocationsDAO_HI.getById(baseLocation.getLocationId());
                    locationNew.setLocationCode(baseLocation.getLocationCode());
                    locationNew.setLocationName(baseLocation.getLocationName());
                    locationNew.setOrganizationId(baseLocation.getOrganizationId());
                    locationNew.setActiveDate(baseLocation.getActiveDate());
                    locationNew.setInactiveDate(baseLocation.getInactiveDate());
                    locationNew.setBillToSiteFlag(baseLocation.getBillToSiteFlag());
                    locationNew.setShipToSiteFlag(baseLocation.getShipToSiteFlag());
                    locationNew.setRmk(baseLocation.getRmk());
                }else{
                    locationNew=baseLocation;
                }
                locationNew.setOperatorUserId(userId);
                srmBaseLocationsDAO_HI.saveOrUpdate(locationNew);
            }
        }

        return SToolUtils.convertResultJSONObj("S", "保存成功！", 1, jsonData);
    }


}

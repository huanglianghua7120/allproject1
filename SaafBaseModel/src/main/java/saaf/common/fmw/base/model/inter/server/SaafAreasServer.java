package saaf.common.fmw.base.model.inter.server;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafAreasEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafAreasEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafAreas;
import saaf.common.fmw.common.TestToolUtils;
import saaf.common.fmw.common.utils.SaafToolUtils;


@Component("saafAreasServer")
public class SaafAreasServer implements ISaafAreas {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaafAreasServer.class);

    @Autowired
    private ViewObject<SaafAreasEntity_HI> saafAreasDAO_HI;
    @Autowired
    private BaseViewObject<SaafAreasEntity_HI_RO> saafAreasDAO_HI_RO;

    public String findData(JSONObject params, Integer pageIndex, Integer pageRows) {
        StringBuffer where = new StringBuffer();
        where.append(SaafAreasEntity_HI_RO.QUERY_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        String areaLevel = params.get("areaLevel") == null || "".equals(params.get("areaLevel").toString()) ? "1" : params.get("areaLevel").toString();
        where.append(" and sa.area_level = :areaLevel");
        map.put("areaLevel", areaLevel.toString());
        SaafToolUtils.parperParam(params, "sa.area_name", "areaName", where, map, "like");
        map.put("areaName", replaceUnderline(map.get("areaName").toString()));
        SaafToolUtils.parperParam(params, "sa.area_parent_id", "areaParentId", where, map, "=");
        Pagination<SaafAreasEntity_HI> rowSet = saafAreasDAO_HI.findPagination(where.toString(), map, pageIndex, pageRows);
        return JSON.toJSONString(rowSet);
    }

    public boolean save(JSONObject parameters, Integer userId) {
        Object areaId = parameters.get("areaId");
        SaafAreasEntity_HI row = null;
        if (null != areaId && !"".equals(areaId)) {
            try {
                row = saafAreasDAO_HI.getById(Integer.parseInt(areaId.toString()));
            } catch (NumberFormatException e) {
                LOGGER.error("保存区域失败", e);
                //e.printStackTrace();
                return false;
            }
        } else {
            row = new SaafAreasEntity_HI();
        }
        row.setOperatorUserId(userId);
        row.setHotCityFlag(parameters.getString("hotCityFlag"));
        row.setAreaName(parameters.getString("areaName"));
        row.setAreaParentId(parameters.getInteger("areaParentId"));
        row.setAreaCode(parameters.getString("areaCode"));
        row.setAreaLevel(parameters.getInteger("areaLevel"));
        row.setIsLeafNode(parameters.getString("isLeafNode"));
        row.setAreaDesc(parameters.getString("areaDesc"));
        row.setOwnerArea(parameters.getString("areaDesc"));
        saafAreasDAO_HI.saveOrUpdate(row);
        return true;
    }

    public List<SaafAreasEntity_HI_RO> findList(String sql, Map<String, Object> map) {
        return saafAreasDAO_HI_RO.findList(sql, map);
    }

    /**
     * 删除该地区及下属地区
     */
    public boolean delete(Integer areaId) throws Exception {
        try {
            List<SaafAreasEntity_HI> childArea = this.findChildArea(areaId);
            if (childArea.size() > 0) {
                for (int i = 0; i < childArea.size(); i++) {
                    List<SaafAreasEntity_HI> DescendantArea = this.findChildArea(childArea.get(i).getAreaId());
                    for (int j = 0; j < DescendantArea.size(); j++) {
                        this.deleteArea(DescendantArea.get(j).getAreaId());
                    }
                    this.deleteArea(childArea.get(i).getAreaId());
                }
            }
            this.deleteArea(areaId);
        } catch (Exception e) {
            LOGGER.error("", e);
            throw e;
        }
        return true;
    }

    /**
     * 查询下属地区
     * @param areaId
     * @return
     * @throws Exception
     */
    public List<SaafAreasEntity_HI> findChildArea(Integer areaId) throws Exception {
        try {
            return saafAreasDAO_HI.findByProperty("areaParentId", areaId);
        } catch (Exception e) {
            LOGGER.error("", e);
            throw e;
        }
    }

    /**
     * 删除单个地区
     * @param areaId
     * @return
     * @throws Exception
     */
    public boolean deleteArea(Integer areaId) throws Exception {
        try {
            saafAreasDAO_HI.delete(saafAreasDAO_HI.getById(areaId));
            return true;
        } catch (Exception e) {
            LOGGER.error("", e);
            throw e;
        }
    }

    /**
     * 查询城市LOV
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public String findCity(JSONObject params, Integer pageIndex, Integer pageRows) {
        StringBuffer queryString = new StringBuffer();
        queryString.append(SaafAreasEntity_HI_RO.QUERY_SQL_CITY);
        Map<String, Object> map = new HashMap<String, Object>();
        SaafToolUtils.parperParam(params, "sa.AREA_NAME", "cityName", queryString, map, "like");
        Pagination<SaafAreasEntity_HI> rowSet = saafAreasDAO_HI.findPagination(queryString, map, pageIndex, pageRows);
        return JSON.toJSONString(rowSet);
    }

    private static String replaceUnderline(String val) {
        return val.replaceAll("_", "#_");
    }

    public static void main(String[] args) throws Exception {
        ISaafAreas saafAreasServer = (ISaafAreas)TestToolUtils.context.getBean("saafAreasServer");
        saafAreasServer.delete(5);
    }
}

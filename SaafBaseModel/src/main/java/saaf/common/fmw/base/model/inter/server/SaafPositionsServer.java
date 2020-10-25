package saaf.common.fmw.base.model.inter.server;


import com.alibaba.fastjson.JSONObject;

import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafPositionsEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafPositionsEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafPositions;


@Component("baseSaafPositionsServer")
public class SaafPositionsServer implements ISaafPositions {
    public SaafPositionsServer() {
        super();
    }

    @Autowired
    private BaseViewObject<SaafPositionsEntity_HI_RO> saafPositionsDAO_HI_RO;

    @Autowired
    private ViewObject<SaafPositionsEntity_HI> saafPositionsDAO_HI;

    /**
     * LOV:职务名称
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public Pagination findPositionsName(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer hql = new StringBuffer();
            hql.append(SaafPositionsEntity_HI_RO.LOV_POSITIONS_QUERY_SQL);
            Map<String, Object> map = new HashMap();
            if (null != parameters.get("varPosNumber") && !"".equals(parameters.getString("varPosNumber").trim())) {
                hql.append(" AND sp.POS_NUMBER LIKE :posNumber");
                map.put("posNumber", "%" + parameters.getString("varPosNumber") + "%");
            }
            if (null != parameters.get("varPosName") && !"".equals(parameters.getString("varPosName").trim())) {
                hql.append(" AND sp.POS_NAME LIKE :posName");
                map.put("posName", "%" + parameters.getString("varPosName") + "%");
            }
            if (null != parameters.get("varPlatformCode") && !"".equals(parameters.get("varPlatformCode").toString())) {
                hql.append(" AND sp.platform_code = :platform_code");
                map.put("platform_code", parameters.getString("varPlatformCode"));
            }
            Pagination<SaafPositionsEntity_HI_RO> rowSet = saafPositionsDAO_HI_RO.findPagination(hql, map, pageIndex, pageRows);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询部门列表
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public Pagination findPositionsList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer hql = new StringBuffer();
            hql.append(SaafPositionsEntity_HI_RO.POSITIONS_QUERY_SQL);
            Map<String, Object> map = new HashMap();
            if (null != parameters.get("varPosNumber") && !"".equals(parameters.getString("varPosNumber").trim())) {
                hql.append(" AND sp.POS_NUMBER = :posNumber");
                map.put("posNumber", parameters.getString("varPosNumber"));
            }
            if (null != parameters.get("varPosName") && !"".equals(parameters.getString("varPosName").trim())) {
                hql.append(" AND sp.POS_NAME LIKE :posName");
                map.put("posName", "%" + parameters.getString("varPosName") + "%");
            }
            if (null != parameters.get("varPlatformCode") && !"".equals(parameters.get("varPlatformCode").toString())) {
                hql.append(" AND sp.platform_code = :platform_code");
                map.put("platform_code", parameters.getString("varPlatformCode"));
            }
            hql.append(" ORDER BY sp.pos_id DESC ");
            Pagination<SaafPositionsEntity_HI_RO> rowSet = saafPositionsDAO_HI_RO.findPagination(hql.toString(), map, pageIndex, pageRows);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    /**
     * 查询部门
     * @param map
     * @return
     */
    public SaafPositionsEntity_HI_RO findPositionsLine(Map<String, Object> map) throws Exception {
        try {
            SaafPositionsEntity_HI_RO posRow = null;
            StringBuffer hql = new StringBuffer();
            hql.append(SaafPositionsEntity_HI_RO.POSITIONS_QUERY_SQL);
            hql.append(" AND sp.pos_id = :posId ");
            List poList = saafPositionsDAO_HI_RO.findList(hql.toString(), map);
            if (poList.size() == 1) {
                posRow = (SaafPositionsEntity_HI_RO)poList.get(0);
            }
            return posRow;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 保存部门
     * @param parameters
     * @return
     */
    public JSONObject savePositions(JSONObject parameters) throws Exception {
        try {
            SaafPositionsEntity_HI posRow = null;
            int userId = Integer.parseInt(parameters.get("varUserId").toString());
            String posId = SToolUtils.object2String(parameters.get("posId"));
            //            String posNumber = SToolUtils.object2String(parameters.get("posNumber"));
            String posName = SToolUtils.object2String(parameters.get("posName"));

            //判断是否新增
            if (null == parameters.get("posId") || "".equals(posId.trim())) {
                posRow = new SaafPositionsEntity_HI();
                posRow.setCreatedBy(userId);
                posRow.setCreationDate(new Date());
                //部门编码
                try {
                    Object parentPosObject = parameters.get("parentPosId");
                    if (parentPosObject != null && !parentPosObject.toString().equals("")) {
                        int parentPosId = Integer.parseInt(parentPosObject.toString());
                        SaafPositionsEntity_HI parentPos = saafPositionsDAO_HI.getById(parentPosId);
                        String parentPosNumber = parentPos.getPosNumber();
                        String childCode = "";
                        List<SaafPositionsEntity_HI> listp = saafPositionsDAO_HI.findByProperty("parentPosId", parentPosId);
                        if (listp == null) {
                            childCode = "001";
                        } else {
                            String listpSize = "" + (listp.size() + 1);
                            if (listpSize.length() == 1) {
                                childCode = "00" + listpSize;
                            } else if (listpSize.length() == 2) {
                                childCode = "0" + listpSize;
                            } else if (listpSize.length() == 3) {
                                childCode = "" + listpSize;
                            } else {
                                // Error
                                return SToolUtils.convertResultJSONObj("E", "保存部门失败，部门编码已超过 三位数（999）", 0, null);
                            }
                        }
                        posRow.setPosNumber(parentPosNumber + "." + childCode);
                    } else {
                        //表示 ：新增 根
                        posRow.setPosNumber("0");
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                    return SToolUtils.convertResultJSONObj("E", "保存部门失败，设置部门编码失败", 0, null);
                }
                //
                //部门名称
                boolean nameflag = verifyOrgNameRepeat(posName);
                if (nameflag) {
                    return SToolUtils.convertResultJSONObj("E", "保存部门失败，已存在名称：" + posName, 0, null);
                }
            } else {
                posRow = saafPositionsDAO_HI.findByProperty("posId", SToolUtils.object2Int(parameters.get("posId"))).get(0);
            }
            posRow.setLastUpdateDate(new Date());
            posRow.setLastUpdateLogin(userId);
            //            posRow.setPosNumber(posNumber);
            posRow.setPosName(posName);
            posRow.setPlatformCode(SToolUtils.object2String(parameters.get("varPlatformCode")));
            posRow.setPosLevel(SToolUtils.object2Int(parameters.get("level")));
            posRow.setParentPosId(SToolUtils.object2Int(parameters.get("parentPosId")));
            posRow.setRemark(SToolUtils.object2String(parameters.get("remark")));

            String startDate_ = SToolUtils.object2String(parameters.get("startDate"));
            if (null != parameters.get("startDate") && !"".equals(startDate_.trim())) {
                Date startDate = SToolUtils.string2DateTime(startDate_, "yyyy-MM-dd");
                posRow.setStartDate(startDate);
            }
            String endDate_ = SToolUtils.object2String(parameters.get("endDate"));
            if (null != parameters.get("endDate") && !"".equals(endDate_.trim())) {
                Date endDate = SToolUtils.string2DateTime(endDate_, "yyyy-MM-dd");
                posRow.setEndDate(endDate);
            } else {
                posRow.setEndDate(null);
            }
            saafPositionsDAO_HI.saveOrUpdate(posRow);
            return SToolUtils.convertResultJSONObj("S", "保存部门成功：", 1, posRow);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 删除部门
     * @param posId
     * @return
     * @throws Exception
     */
    public JSONObject deletePositions(Integer posId) throws Exception {
        try {
            SaafPositionsEntity_HI postRow = null;
            List posList = saafPositionsDAO_HI.findByProperty("posId", posId);
            if (posList.size() > 0) {
                postRow = (SaafPositionsEntity_HI)posList.get(0);
                saafPositionsDAO_HI.delete(postRow);
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除部门失败，部门不存在!", 0, null);
            }

            return SToolUtils.convertResultJSONObj("S", "删除组部门成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 验证字段positionNumber是否重复
     * @param posNumber
     * @return
     */
    public boolean verifyOrgCodeRepeat(String posNumber) {

        Map map = new HashMap();
        map.put("posNumber", posNumber);
        List rowSet = saafPositionsDAO_HI.findByProperty(map);
        return rowSet.size() > 1;
    }

    /**
     * 验证字段positionName是否重复
     * @param posName
     * @return
     */
    public boolean verifyOrgNameRepeat(String posName) {
        Map map = new HashMap();
        map.put("posName", posName);
        List rowSet = saafPositionsDAO_HI.findByProperty(map);
        return rowSet.size() > 1;
    }
}

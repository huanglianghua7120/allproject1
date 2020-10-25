package saaf.common.fmw.base.model.inter.server;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafInstitutionEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseItemsEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafInstitutionEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafInstitution;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.SrmPoHeadersEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoLinesEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonBidHeadersEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonBidItemPricesEntity_HI;


@Component("baseSaafInstitutionServer")
public class SaafInstitutionServer implements ISaafInstitution {


    private static final Logger LOGGER = LoggerFactory.getLogger(SaafInstitutionServer.class);
    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    public SaafInstitutionServer() {
        super();
    }

    @Autowired
    private BaseViewObject<SaafInstitutionEntity_HI_RO> saafInstitutionDAO_HI_RO;
    @Autowired
    private ViewObject<SaafInstitutionEntity_HI> saafInstitutionDAO_HI;
    @Autowired
    private ViewObject<SrmPoHeadersEntity_HI> srmPoHeadersDAO_HI;
    @Autowired
    private ViewObject<SrmPoLinesEntity_HI> srmPoLinesDAO_HI;
    @Autowired
    private ViewObject<SrmPonBidHeadersEntity_HI> srmPonBidHeadersDAO_HI;
    @Autowired
    private ViewObject<SrmPonBidItemPricesEntity_HI> srmPonBidItemPricesDAO_HI;
    @Autowired
    private ViewObject<SrmBaseItemsEntity_HI> srmBaseItemsDAO_HI;

    /**
     * LOV:查询机构名称
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public Pagination<SaafInstitutionEntity_HI_RO> findInstNameLov(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        StringBuffer hql = new StringBuffer();
        try {
            hql.append(SaafInstitutionEntity_HI_RO.LOV_INST_QUERY_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(parameters, "si.platform_code", "varPlatformCode", hql, map, "=");
            SaafToolUtils.parperParam(parameters, "si.inst_code", "varInstCode", hql, map, "=");
            SaafToolUtils.parperParam(parameters, "si.inst_name", "varInstName", hql, map, "like");
            Pagination<SaafInstitutionEntity_HI_RO> saafInstitutionRow = saafInstitutionDAO_HI_RO.findPagination(hql, map, pageIndex, pageRows);
            return saafInstitutionRow;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 条件查询组织架构
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public Pagination<SaafInstitutionEntity_HI_RO> findInstitution(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        StringBuffer querySql = new StringBuffer();
        try {
            Integer userId = parameters.getInteger("varUserId");
            querySql.append(SaafInstitutionEntity_HI_RO.QUERY_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(parameters, "siv.platform_code", "varPlatformCode", querySql, map, "like");
            SaafToolUtils.parperParam(parameters, "siv.inst_code", "varInstCode", querySql, map, "like");
            SaafToolUtils.parperParam(parameters, "siv.inst_name", "varInstName", querySql, map, "like");

            SaafToolUtils.parperParam(parameters, "siv.parent_inst_id", "varParentInstId", querySql, map, "=");
            SaafToolUtils.parperParam(parameters, "siv.inst_region", "region", querySql, map, "=");
            SaafToolUtils.parperParam(parameters, "siv.inst_type", "instType", querySql, map, "=");
            SaafToolUtils.parperParam(parameters, "siv.Enabled", "enabled", querySql, map, "=");
            if (parameters.getString("flag") != null && "flag".equals(parameters.getString("flag"))) {
                querySql.append(" AND siv.inst_type = 'ORG'");
                querySql.append(" AND check_org_f(" + userId + ", siv.inst_id) = 'Y'");
            }
            if ("Y".equals(parameters.getString("isPo"))) {
                querySql.append(" and siv.inst_id in (SELECT distinct (sua.inst_id) Organization_Id\n" +
                        "                               FROM saaf_user_access_orgs sua,\n" +
                        "                                    saaf_institution      si,\n" +
                        "                                    saaf_users            su\n" +
                        "                              WHERE sua.user_id = su.user_id\n" +
                        "                                AND sua.inst_id = si.inst_id\n" +
                        "                                and sua.platform_code = 'SAAF'\n" +
                        "                                and sua.user_id = " + parameters.getInteger("varUserId") + ") ");
            }
            if (parameters.getString("orderByFlag") != null && "Y".equals(parameters.getString("orderByFlag"))) {
                querySql.append(" ORDER BY sie.inst_name,siv.inst_name ");
            }

            Pagination<SaafInstitutionEntity_HI_RO> saafInstitutionlist = saafInstitutionDAO_HI_RO.findPagination(querySql.toString(), map, pageIndex, pageRows);
            return saafInstitutionlist;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询机构名称Tree(用户组织关系维护)
     *
     * @param parameters
     * @return
     */
    public List<SaafInstitutionEntity_HI_RO> findInstNameTree(JSONObject parameters) throws Exception {
        StringBuffer hql = new StringBuffer();
        try {
            hql.append(SaafInstitutionEntity_HI_RO.LOV_INST_QUERY_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(parameters, "si.platform_code", "varPlatformCode", hql, map, "=");
            List<SaafInstitutionEntity_HI_RO> saafInstitutionRow = saafInstitutionDAO_HI_RO.findList(hql, map);
            return saafInstitutionRow;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询组织架构ROW
     *
     * @param map
     * @return
     */
    public SaafInstitutionEntity_HI findInstitutionLine(Map<String, Object> map) throws Exception {
        try {
            SaafInstitutionEntity_HI saafInstitutionLine = saafInstitutionDAO_HI.findByProperty(map).get(0);
            return saafInstitutionLine;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 编辑或新增组织
     *
     * @param parameters
     * @return
     */
    public JSONObject saveInstitution(JSONObject parameters) throws Exception {
        int userId = SToolUtils.object2Int(parameters.get("varUserId"));
        SaafInstitutionEntity_HI row = null;
        try {
            if (null == parameters.get("instId") || "".equals(SToolUtils.object2String(parameters.get("instId")).trim())) { //判断是否新增
                row = new SaafInstitutionEntity_HI();
                //组织名称
                boolean nameflag = verifyInstNameRepeat(parameters.get("instName").toString());
                if (nameflag) {
                    return SToolUtils.convertResultJSONObj("E", "保存组织机构失败， 组织机构名称：" + parameters.get("instName").toString() + "已存在", 0, null);
                }
            } else {
                row = saafInstitutionDAO_HI.findByProperty("instId", SToolUtils.object2Int(parameters.get("instId"))).get(0);
            }
            //组织编码手输
            // 如果 ‘instId’为空， 或 ‘parentInstId’ 有变化
            /*if (row.getInstId() == null || (row.getParentInstId() != null && !row.getParentInstId().toString().equals(parameters.get("parentInstId")))) {
                //
                try {
                    Object parentInstObject = parameters.get("parentInstId");
                    if (parentInstObject != null && !parentInstObject.toString().equals("")) {
                        int parentInstId = Integer.parseInt(parentInstObject.toString());
                        SaafInstitutionEntity_HI parentInst = saafInstitutionDAO_HI.getById(parentInstId);
                        String parentInstCode = null;
                        //update by liuwenjun 20170216
                        //String childCode = saafSequencesUtil.getDocSequences("SAAF_INSTITUTION", null, 3);

                        if (parentInst != null) {
                            parentInstCode = parentInst.getInstCode().trim();
                           // row.setInstCode(parentInstCode + "." + childCode);
                        } *//*else {
                            row.setInstCode(childCode);
                        }*//*
                       // LOGGER.info("********** 组织维护，生成 组织编码为：" + (parentInstCode + "." + childCode));
                    } *//*else {
                        //表示 ：新增 根
                        row.setInstCode(parameters.getString("instCode"));
                    }*//*

                } catch (Exception e) {
                    //e.printStackTrace();
                    return SToolUtils.convertResultJSONObj("E", "保存组织机构异常", 0, null);
                }
            }*/
            String instName = SToolUtils.object2String(parameters.get("instName"));
            row.setInstName(instName);
            row.setPlatformCode(SToolUtils.object2String(parameters.get("varPlatformCode")));
            if (null != parameters.get("instType")) {
                row.setInstType(SToolUtils.object2String(parameters.get("instType")));
            } else {
                row.setInstType("IST");
            }
            //解决parentInstId会出现空串的bug
            if (null != parameters.get("parentInstId") && !"".equals(parameters.get("parentInstId"))) {
                row.setParentInstId(SToolUtils.object2Int(parameters.get("parentInstId")));
            } else {
                row.setParentInstId(0);
            }
            row.setInstLevel(SToolUtils.object2Int(parameters.get("level")));
            row.setRemark(SToolUtils.object2String(parameters.get("remark")));

            String startDate_ = SToolUtils.object2String(parameters.get("startDate"));
            if (null != parameters.get("startDate") && !"".equals(startDate_.trim())) {
                Date startDate = SToolUtils.string2DateTime(startDate_, "yyyy-MM-dd");
                row.setStartDate(startDate);
            }
            String endDate_ = SToolUtils.object2String(parameters.get("endDate"));
            if (null != parameters.get("endDate") && !"".equals(endDate_.trim())) {
                Date endDate = SToolUtils.string2DateTime(endDate_, "yyyy-MM-dd");
                row.setEndDate(endDate);
            } else {
                row.setEndDate(null);
            }
            row.setEnabled("Y");
            row.setInstRegion(SToolUtils.object2String(parameters.get("instRegion")));
            row.setInstCode(SToolUtils.object2String(parameters.getString("instCode")));
            row.setOperatorUserId(userId);
            saafInstitutionDAO_HI.saveOrUpdate(row);
            return SToolUtils.convertResultJSONObj("S", "保存组织机构成功：", 1, row);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 删除组织机构
     *
     * @param instId
     * @return
     * @throws Exception
     */
    public JSONObject deleteInstitution(Integer instId) throws Exception {
        try {
            SaafInstitutionEntity_HI instRow = null;
            List<SaafInstitutionEntity_HI> instList = saafInstitutionDAO_HI.findByProperty("instId", instId);
            if (instList.size() > 0) {
                instRow = (SaafInstitutionEntity_HI) instList.get(0);
                saafInstitutionDAO_HI.delete(instRow);
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除组织机构失败，组织机构不存在!", 0, null);
            }
            return SToolUtils.convertResultJSONObj("S", "删除组织机构成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 验证字段：organizationCode是否重复
     *
     * @param instCode
     * @return
     */
    public boolean verifyInstCodeRepeat(String instCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("instCode", instCode);
        List<SaafInstitutionEntity_HI> rowSet = saafInstitutionDAO_HI.findByProperty(map);
        if (rowSet.size() == 1) {
            return true;
        }
        return false;
    }

    /**
     * 验证字段：organizationName是否重复
     *
     * @param instName
     * @return
     */
    public boolean verifyInstNameRepeat(String instName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("instName", instName);
        List<SaafInstitutionEntity_HI> rowSet = saafInstitutionDAO_HI.findByProperty(map);
        if (rowSet.size() == 1) {
            return true;
        }
        return false;
    }

    /**
     * 验证手动输入的上级组织是否存在
     *
     * @param parameters
     * @return
     */
    public boolean verifyParentInstNameExist(JSONObject parameters) {
        Map<String, Object> map = new HashMap<String, Object>();
        String parentInstName = parameters.get("parentInstName").toString();
        map.put("instName", parentInstName);
        List<SaafInstitutionEntity_HI> rowSet = saafInstitutionDAO_HI.findByProperty(map);
        return rowSet.size() > 0;
    }

    /**
     * 根据用户ID查询组织机构列表
     *
     * @param pageRows
     * @return
     */
    public Pagination<SaafInstitutionEntity_HI_RO> findSaafInstitutionByUserId(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        //Map<String, Object> map = SaafToolUtils.fastJsonObj2Map(jsonParams);
        Map<String, Object> queryParamMap = new HashMap<String, Object>(); // SToolUtils.fastJsonObj2Map(queryParamJSON);
        StringBuffer hql = new StringBuffer();
        hql.append(SaafInstitutionEntity_HI_RO.INST_QUERY_SQL_BY_USERID);
        queryParamMap.put("userId", jsonParams.getInteger("userId"));
        SaafToolUtils.parperParam(jsonParams, "si.inst_code", "instCode", hql, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "si.inst_name", "instName", hql, queryParamMap, "like");
        Pagination<SaafInstitutionEntity_HI_RO> list = saafInstitutionDAO_HI_RO.findPagination(hql.toString(), queryParamMap, pageIndex, pageRows);
        return list;
    }

    /**
     * LOV:srm查询组织
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SaafInstitutionEntity_HI_RO> queryInstitution(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        StringBuffer hql = new StringBuffer();
        try {
            hql.append(SaafInstitutionEntity_HI_RO.QUERY_INSITITUTION_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(parameters, "si.inst_code", "instCode", hql, map, "like");
            SaafToolUtils.parperParam(parameters, "si.inst_name", "instName", hql, map, "like");
            SaafToolUtils.parperParam(parameters, "si.parent_inst_id", "parentInstId", hql, map, "=");
            Pagination<SaafInstitutionEntity_HI_RO> saafInstitutionRow = saafInstitutionDAO_HI_RO.findPagination(hql, map, pageIndex, pageRows);
            return saafInstitutionRow;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * LOV:srm查询业务实体Lov
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SaafInstitutionEntity_HI_RO> findInstitutionListLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        StringBuffer hql = new StringBuffer();
        String acceptFlag = jsonParams.getString("acceptFlag");
        Integer userId = jsonParams.getInteger("varUserId");
        try {
            hql.append(SaafInstitutionEntity_HI_RO.QUERY_INSITITUTION_SQL_LOV);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(jsonParams, "si.inst_code", "instCode", hql, map, "=");//精准查询
            SaafToolUtils.parperParam(jsonParams, "si.inst_code", "inst_Code", hql, map, "like");
            SaafToolUtils.parperParam(jsonParams, "si.inst_name", "instName", hql, map, "like");
            SaafToolUtils.parperParam(jsonParams, "si.inst_name", "inst_Name", hql, map, "="); //精准查询
            SaafToolUtils.parperParam(jsonParams, "si.parent_inst_id", "parentInstId", hql, map, "=");
            if ("Y".equals(acceptFlag)) {
                hql.append(" AND check_org_f(" + userId + ", si.inst_id) = 'Y' ");
            }
            Pagination<SaafInstitutionEntity_HI_RO> saafInstitutionRow = saafInstitutionDAO_HI_RO.findPagination(hql, map, pageIndex, pageRows);
            return saafInstitutionRow;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * srm根据物料查询组织
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SaafInstitutionEntity_HI_RO> queryItemInstitution(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        pageIndex = 1;
        pageRows = 10;
        StringBuffer hql = new StringBuffer();
        int aa = parameters.getInteger("itemId");
        try {
            hql.append(SaafInstitutionEntity_HI_RO.QUERY_ITEM_INSITITUTION_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(parameters, "it.item_id", "itemId", hql, map, "=");
            Pagination<SaafInstitutionEntity_HI_RO> saafInstitutionRow = saafInstitutionDAO_HI_RO.findPagination(hql.toString(), map, pageIndex, pageRows);
            return saafInstitutionRow;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询当前登录用户的组织表的业务实体/库存组织的权限范围（分页）
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SaafInstitutionEntity_HI_RO> findInstitutionListByUserId(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        StringBuffer sb = new StringBuffer();
        if ("EX".equals(jsonParams.getString("varPlatformCode"))) {
            sb.append(SaafInstitutionEntity_HI_RO.LOV_INST_QUERY_SQL);
        }else{
            sb .append(SaafInstitutionEntity_HI_RO.QUERY_LIST_SQL);
        }
        //StringBuffer sb = new StringBuffer(SaafInstitutionEntity_HI_RO.QUERY_LIST_SQL);
        Integer userId = jsonParams.getInteger("varUserId");//当前登录用户Id
        Map<String, Object> map = new HashMap();
        map.put("userId", userId);
        SaafToolUtils.parperParam(jsonParams, "si.inst_type", "instType", sb, map, "=");
        SaafToolUtils.parperParam(jsonParams, "si.parent_inst_id", "parentInstId", sb, map, "=");
        SaafToolUtils.parperParam(jsonParams, "si.inst_code", "instCode", sb, map, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "si.inst_name", "instName", sb, map, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "si.inst_code", "inst_Code", sb, map, "=");//精准查询
        SaafToolUtils.parperParam(jsonParams, "si.inst_name", "inst_Name", sb, map, "="); //精准查询

        //采购订单库存组织限定,寻源转订单的单据，只能选择物料所在库存组织
        /*if ("Y".equals(jsonParams.getString("isOrder")) && !ObjectUtils.isEmpty(jsonParams.getString("auctionNumber")) && !ObjectUtils.isEmpty(jsonParams.getInteger("poHeaderId"))) {
            Integer poHeaderId = jsonParams.getInteger("poHeaderId");
            SrmPoHeadersEntity_HI poHeader = srmPoHeadersDAO_HI.getById(poHeaderId);
            if (!ObjectUtils.isEmpty(poHeader)) {
                List<SrmPoLinesEntity_HI> poLineList = srmPoLinesDAO_HI.findByProperty("poHeaderId", poHeaderId);
                if (!ObjectUtils.isEmpty(poLineList) && poLineList.get(0) != null) {
                    SrmPoLinesEntity_HI poLine = poLineList.get(0);
                    List<SrmBaseItemsEntity_HI> item = srmBaseItemsDAO_HI.findByProperty("itemId", poLine.getItemId());
                    if (!ObjectUtils.isEmpty(item)) {
                        String combIds = String.valueOf(item.stream().map(SrmBaseItemsEntity_HI::getOrganizationId).collect(Collectors.toList()).stream().map(String::valueOf).collect(Collectors.toList()).stream().distinct().collect(Collectors.joining(",")));
                        sb.append(" and si.inst_id in (" + combIds + ") ");
                    }
                }
            }
        }*/
        Pagination<SaafInstitutionEntity_HI_RO> result = saafInstitutionDAO_HI_RO.findPagination(sb.toString(), map, pageIndex, pageRows);
        return result;
    }

    /**
     * 查询指定业务实体下的库存组织
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SaafInstitutionEntity_HI_RO> queryInstitutionByOrg(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        StringBuffer hql = new StringBuffer();
        try {
            hql.append(SaafInstitutionEntity_HI_RO.QUERY_INSITITUTION_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(parameters, "si.parent_inst_id", "orgId", hql, map, "=");

            /*//合同库存组织限定,寻源转合同的单据，只能选择物料所在库存组织
            if ("Y".equals(parameters.getString("isOrder")) && !ObjectUtils.isEmpty(parameters.getInteger("itemId"))) {
                List<SrmBaseItemsEntity_HI> item = srmBaseItemsDAO_HI.findByProperty("itemId", parameters.getInteger("itemId"));
                if (!ObjectUtils.isEmpty(item)) {
                    String instIds = String.valueOf(item.stream().map(SrmBaseItemsEntity_HI::getOrganizationId).collect(Collectors.toList()).stream().map(String::valueOf).collect(Collectors.toList()).stream().distinct().collect(Collectors.joining(",")));
                    hql.append(" and si.inst_id in (" + instIds + ") ");
                }
            }*/

            Pagination<SaafInstitutionEntity_HI_RO> saafInstitutionRow = saafInstitutionDAO_HI_RO.findPagination(hql, map, pageIndex, pageRows);
            return saafInstitutionRow;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询有效的部门信息
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public Pagination<SaafInstitutionEntity_HI_RO> findInstitutionByDept(JSONObject parameters, Integer pageIndex, Integer pageRows) {
        StringBuffer querySql = new StringBuffer();
        querySql.append(SaafInstitutionEntity_HI_RO.QUERY_INSITITUTION_BY_DEPT);
        Map<String, Object> map = new HashMap<String, Object>();
        SaafToolUtils.parperParam(parameters, "si.platform_code", "varPlatformCode", querySql, map, "like");
        SaafToolUtils.parperParam(parameters, "si.inst_code", "varInstCode", querySql, map, "like");
        SaafToolUtils.parperParam(parameters, "si.inst_name", "varInstName", querySql, map, "like");
        Pagination<SaafInstitutionEntity_HI_RO> saafInstitutionlist = saafInstitutionDAO_HI_RO.findPagination(querySql.toString(), map, pageIndex, pageRows);
        return saafInstitutionlist;
    }

    /**
     * Description：查询组织机构列表
     *
     * @param parameters 物料的JSON格式数据
     * @return 对象
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-03     SIE 谢晓霞          创建
     * ==============================================================================
     */
    public List<SaafInstitutionEntity_HI_RO> findInstitutionList(JSONObject parameters) throws Exception {
        StringBuffer querySql = new StringBuffer();
        try {
            Integer userId = parameters.getInteger("varUserId");
            querySql.append(SaafInstitutionEntity_HI_RO.QUERY_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(parameters, "siv.platform_code", "varPlatformCode", querySql, map, "like");
            SaafToolUtils.parperParam(parameters, "siv.inst_code", "varInstCode", querySql, map, "like");
            SaafToolUtils.parperParam(parameters, "siv.inst_name", "varInstName", querySql, map, "like");

            SaafToolUtils.parperParam(parameters, "siv.parent_inst_id", "varParentInstId", querySql, map, "=");
            SaafToolUtils.parperParam(parameters, "siv.inst_region", "region", querySql, map, "=");
            SaafToolUtils.parperParam(parameters, "siv.inst_type", "instType", querySql, map, "=");
            SaafToolUtils.parperParam(parameters, "siv.Enabled", "enabled", querySql, map, "=");
            if (parameters.getString("flag") != null && "flag".equals(parameters.getString("flag"))) {
                querySql.append(" AND siv.inst_type = 'ORG'");
                querySql.append(" AND check_org_f(" + userId + ", siv.inst_id) = 'Y'");
            }
            if ("Y".equals(parameters.getString("isPo"))) {
                querySql.append(" and siv.inst_id in (SELECT distinct (sua.inst_id) Organization_Id\n" +
                        "                               FROM saaf_user_access_orgs sua,\n" +
                        "                                    saaf_institution      si,\n" +
                        "                                    saaf_users            su\n" +
                        "                              WHERE sua.user_id = su.user_id\n" +
                        "                                AND sua.inst_id = si.inst_id\n" +
                        "                                and sua.platform_code = 'SAAF'\n" +
                        "                                and sua.user_id = " + parameters.getInteger("varUserId") + ") ");
            }
            if (parameters.getString("orderByFlag") != null && "Y".equals(parameters.getString("orderByFlag"))) {
                querySql.append(" ORDER BY sie.inst_name,siv.inst_name ");
            }

            List<SaafInstitutionEntity_HI_RO> saafInstitutionlist = saafInstitutionDAO_HI_RO.findList(querySql.toString(), map);
            return saafInstitutionlist;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


}

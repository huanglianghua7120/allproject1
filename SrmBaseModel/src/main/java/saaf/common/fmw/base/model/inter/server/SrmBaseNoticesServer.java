package saaf.common.fmw.base.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.SrmBaseNoticeFilesEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseNoticeReceiversEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseNoticesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseNoticesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseNoticeFiles;
import saaf.common.fmw.base.model.inter.ISrmBaseNoticeReceivers;
import saaf.common.fmw.base.model.inter.ISrmBaseNotices;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：公告
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("/srmBaseNoticesServer")
public class SrmBaseNoticesServer implements ISrmBaseNotices {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseNoticesServer.class);

    public SrmBaseNoticesServer() {
        super();
    }

    @Autowired
    private BaseViewObject<SrmBaseNoticesEntity_HI_RO> srmBaseNoticesDAO_HI_RO;

    @Autowired
    private ViewObject<SrmBaseNoticesEntity_HI> srmBaseNoticesDAO_HI;

    @Autowired
    private ViewObject<SrmBaseNoticeFilesEntity_HI> srmBaseNoticeFilesDAO_HI;

    @Autowired
    private ViewObject<SrmBaseNoticeReceiversEntity_HI> srmBaseNoticeReceiversDAO_HI;

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private ISrmBaseNoticeFiles iSrmBaseNoticeFiles;

    @Autowired
    private ISrmBaseNoticeReceivers iSrmBaseNoticeReceivers;

    @Override
    /**
     * 查询系统公告
     */
    public Pagination<SrmBaseNoticesEntity_HI_RO> findNoticesList(
            JSONObject params, Integer pageIndex, Integer pageRows)
            throws Exception {
        try {
            Integer userId = params.getIntValue("varUserId");
            StringBuffer queryString = new StringBuffer(SrmBaseNoticesEntity_HI_RO.QUERY_NOTICES_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询过滤条件
            SaafToolUtils.parperParam(params, "n.notice_id", "noticeId", queryString, map, "=");
            SaafToolUtils.parperParam(params, "n.notice_title", "noticeTitle", queryString, map, "like");
            SaafToolUtils.parperParam(params, "n.notice_type", "noticeType", queryString, map, "=");
            SaafToolUtils.parperParam(params, "n.notice_status", "noticeStatus", queryString, map, "=");
            SaafToolUtils.parperParam(params, "n.set_top_flag", "setTopFlag", queryString, map, "=");
            SaafToolUtils.parperParam(params, "u.user_full_name", "employeeName", queryString, map, "=");
//            SaafToolUtils.parperParam(params, "DATE_FORMAT(n.publish_date,'%Y-%m-%d') ", "publishDateTo", queryString, map, ">=");
//            SaafToolUtils.parperParam(params, "DATE_FORMAT(n.publish_date,'%Y-%m-%d') ", "publishDateFrom", queryString, map, "<=");
            if (params.getString("varIsAdmin").equals("N")) {
                queryString.append(" and u.user_id = " + userId + "\r\n");
            }
            String publishDateFrom = params.getString("publishDateFrom");
            if (publishDateFrom != null && !"".equals(publishDateFrom.trim())) {
                queryString.append(" AND trunc(n.publish_date) >= to_date(:publishDateFrom, 'yyyy-mm-dd')\n");
                map.put("publishDateFrom", publishDateFrom);
            }
            String publishDateTo = params.getString("publishDateTo");
            if (publishDateTo != null && !"".equals(publishDateTo.trim())) {
                queryString.append(" AND trunc(n.publish_date) <= to_date(:publishDateTo, 'yyyy-mm-dd')\n");
                map.put("publishDateTo", publishDateTo);
            }
            String countSql = "select count(1) from (" + queryString + ")";
            // 排序
            queryString.append(" ORDER BY n.notice_code DESC");
            Pagination<SrmBaseNoticesEntity_HI_RO> noticesList = srmBaseNoticesDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return noticesList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询公告信息
     */
    @Override
    public List<SrmBaseNoticesEntity_HI_RO> findNoticesInfo(JSONObject params)
            throws Exception {
        LOGGER.info("根据id查询信息，参数：" + params.toString());
        try {
            String sql = SrmBaseNoticesEntity_HI_RO.QUERY_NOTICES_INFO_SQL;
            StringBuffer queryString = new StringBuffer();
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询过滤条件
            SaafToolUtils.parperParam(params, "n.notice_id", "noticeId", queryString, map, "=");
            SaafToolUtils.parperParam(params, "u.supplier_id", "supplierId", queryString, map, "=");
            List<SrmBaseNoticesEntity_HI_RO> list = srmBaseNoticesDAO_HI_RO.findList(sql + queryString, map);
            return list;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    /**
     * 查询已发布的公告
     */
    @Override
    public Pagination<SrmBaseNoticesEntity_HI_RO> findReleaseNoticeList(
            JSONObject params, Integer pageIndex, Integer pageRows)
            throws Exception {
        try {
            Integer userId = params.getIntValue("varUserId");
            StringBuffer queryString = new StringBuffer(SrmBaseNoticesEntity_HI_RO.QUERY_RELEASE_NOTICES_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("varUserId", userId);
            String countSql = "select count(1) from (" + queryString + ")";
            // 排序
            queryString.append(" order by c.setTopFlag desc,c.publishDate desc");
            Pagination<SrmBaseNoticesEntity_HI_RO> noticesList = srmBaseNoticesDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return noticesList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询已发布的公告列表
     */
    @Override
    public Pagination<SrmBaseNoticesEntity_HI_RO> findPublishNoticeList(
            JSONObject params, Integer pageIndex, Integer pageRows)
            throws Exception {
        try {
            Integer userId = params.getIntValue("varUserId");
            StringBuffer queryString = new StringBuffer(SrmBaseNoticesEntity_HI_RO.QUERY_PUBLISH_NOTICES_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("varUserId", userId);
            // 查询过滤条件
            SaafToolUtils.parperParam(params, "c.noticeTitle", "noticeTitle", queryString, map, "like");
            SaafToolUtils.parperParam(params, "c.noticeType", "noticeType", queryString, map, "=");
            SaafToolUtils.parperParam(params, "c.creater", "employeeName", queryString, map, "=");
//            SaafToolUtils.parperParam(params, "DATE_FORMAT(c.publishDate,'%Y-%m-%d') ", "publishDateTo", queryString, map, ">=");
//            SaafToolUtils.parperParam(params, "DATE_FORMAT(c.publishDate,'%Y-%m-%d') ", "publishDateFrom", queryString, map, "<=");
            String publishDateFrom = params.getString("publishDateFrom");
            if (publishDateFrom != null && !"".equals(publishDateFrom.trim())) {
                queryString.append(" AND trunc(c.publishDate) >= to_date(:publishDateFrom, 'yyyy-mm-dd')\n");
                map.put("publishDateFrom", publishDateFrom);
            }
            String publishDateTo = params.getString("publishDateTo");
            if (publishDateTo != null && !"".equals(publishDateTo.trim())) {
                queryString.append(" AND trunc(c.publishDate) <= to_date(:publishDateTo, 'yyyy-mm-dd')\n");
                map.put("publishDateTo", publishDateTo);
            }
            String countSql = "select count(1) from (" + queryString + ")";
            // 排序
            queryString.append(" ORDER BY c.publishDate DESC");
            Pagination<SrmBaseNoticesEntity_HI_RO> noticesList =
                    srmBaseNoticesDAO_HI_RO.findPagination(queryString,countSql ,map, pageIndex, pageRows);
            return noticesList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询自已有多少公告
     */
    @Override
    public Pagination<SrmBaseNoticesEntity_HI_RO> findNoticesBySelf(
            JSONObject params, Integer pageIndex, Integer pageRows)
            throws Exception {
        try {
            Integer userId = params.getIntValue("varUserId");
            StringBuffer queryString = new StringBuffer(SrmBaseNoticesEntity_HI_RO.QUERY_NOTICES_BY_SELF_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("varUserId", userId);
            String countSql = "select count(1) from (" + queryString + ")";
            queryString.append(" order by c.publishDate desc");
            Pagination<SrmBaseNoticesEntity_HI_RO> noticesList = srmBaseNoticesDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return noticesList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：保存公告
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * isTechnologyChange  是否技改  VARCHAR2  N
     * endActiveDate  失效日期  DATE  N
     * noticeId  公告ID  NUMBER  Y
     * noticeCode  公告编号  VARCHAR2  Y
     * noticeTitle  公告标题  VARCHAR2  Y
     * noticeContent  公告内容  CLOB  N
     * noticeType  公告类型  VARCHAR2  Y
     * noticeStatus  公告状态  VARCHAR2  Y
     * publishDate  发布时间  DATE  N
     * publishUserId  发布用户  NUMBER  N
     * cancelDate  撤回时间  DATE  N
     * cancelUserId  撤回用户  NUMBER  N
     * setTopFlag  置顶标识  VARCHAR2  N
     * receiverType  发布对象  VARCHAR2  Y
     * noticeId  公告ID  NUMBER  Y
     * noticeCode  公告编号  VARCHAR2  Y
     * noticeTitle  公告标题  VARCHAR2  Y
     * noticeContent  公告内容  CLOB  N
     * noticeType  公告类型  VARCHAR2  Y
     * noticeStatus  公告状态  VARCHAR2  Y
     * publishDate  发布时间  DATE  N
     * publishUserId  发布用户  NUMBER  N
     * cancelDate  撤回时间  DATE  N
     * cancelUserId  撤回用户  NUMBER  N
     * setTopFlag  置顶标识  VARCHAR2  N
     * receiverType  发布对象  VARCHAR2  Y
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveNotices(JSONObject params) throws Exception {
        LOGGER.info("保存公告,参数：" + params.toString());
        SrmBaseNoticesEntity_HI noticesRow = null;
        try {
            Integer operatorUserId = params.getInteger("varUserId");
            Date date = new Date();
            String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
            // 保存信息判断
            if (null == params.getInteger("noticeId")) {
                noticesRow = new SrmBaseNoticesEntity_HI();
                String noticeCode = saafSequencesUtil.getDocSequences("SRM_BASE_NOTICES", dateFromate, 2);
                noticesRow.setNoticeCode(noticeCode);
            } else {
                // 判断存在就是修改
                noticesRow = srmBaseNoticesDAO_HI.findByProperty("noticeId", params.getInteger("noticeId")).get(0);
            }

            noticesRow.setNoticeType(params.getString("noticeType"));
            noticesRow.setNoticeTitle(params.getString("noticeTitle"));
            noticesRow.setNoticeContent(params.getString("noticeContent"));
            noticesRow.setSetTopFlag(params.getString("setTopFlag"));
            noticesRow.setReceiverType(params.getString("receiverType"));
            noticesRow.setNoticeStatus(params.getString("noticeStatus"));
            noticesRow.setOperatorUserId(operatorUserId);
            srmBaseNoticesDAO_HI.saveOrUpdate(noticesRow);

            LOGGER.info("getNoticeId：" + noticesRow.getNoticeId());

            List<SrmBaseNoticeFilesEntity_HI> noticeFilesList = null;// 公告附件
            if (!(params.getJSONArray("noticeFilesList") == null || "".equals(params.getJSONArray("noticeFilesList")))) {
                JSONArray noticeFilesListJSON = params.getJSONArray("noticeFilesList");
                noticeFilesList = JSON.parseArray(noticeFilesListJSON.toJSONString(), SrmBaseNoticeFilesEntity_HI.class);
            }
            iSrmBaseNoticeFiles.saveNoticeFilesList(noticeFilesList, operatorUserId, noticesRow.getNoticeId());

            String receiverType = params.getString("receiverType");
            LOGGER.info("receiverType：" + receiverType);
            if (receiverType.equals("2")) {
                List<SrmBaseNoticeReceiversEntity_HI> instList = null;// 组织
                if (!(params.getJSONArray("instList") == null || "".equals(params.getJSONArray("instList")))) {
                    JSONArray noticeFilesListJSON = params.getJSONArray("instList");
                    instList = JSON.parseArray(noticeFilesListJSON.toJSONString(), SrmBaseNoticeReceiversEntity_HI.class);
                }
                iSrmBaseNoticeReceivers.saveInstList(instList, operatorUserId, noticesRow.getNoticeId());
            } else if (receiverType.equals("3")) {
                List<SrmBaseNoticeReceiversEntity_HI> employeeList = null;// 组织
                if (!(params.getJSONArray("employeeList") == null || "".equals(params.getJSONArray("employeeList")))) {
                    JSONArray noticeFilesListJSON = params.getJSONArray("employeeList");
                    employeeList = JSON.parseArray(noticeFilesListJSON.toJSONString(), SrmBaseNoticeReceiversEntity_HI.class);
                }
                iSrmBaseNoticeReceivers.saveEmployeeList(employeeList, operatorUserId, noticesRow.getNoticeId());
            } else if (receiverType.equals("4")) {
                List<SrmBaseNoticeReceiversEntity_HI> supplierList = null;// 供应商
                if (!(params.getJSONArray("supplierList") == null || "".equals(params.getJSONArray("supplierList")))) {
                    JSONArray noticeFilesListJSON = params.getJSONArray("supplierList");
                    supplierList = JSON.parseArray(noticeFilesListJSON.toJSONString(), SrmBaseNoticeReceiversEntity_HI.class);
                }
                iSrmBaseNoticeReceivers.saveSupplierList(supplierList, operatorUserId, noticesRow.getNoticeId());
            }

            JSONObject result = SToolUtils.convertResultJSONObj("S", "保存成功", 1, noticesRow);
            result.put("getNoticeId", noticesRow.getNoticeId());
            return result;
        } catch (Exception e) {
            //LOGGER.error("未知错误:{}", e)();
            throw new Exception(e);
        }
    }

    /**
     * 删除公告
     */
    @Override
    public JSONObject deleteNotices(JSONObject params) throws Exception {
        LOGGER.info("删除参数：" + params.toString());
        try {
            if (null != params.getInteger("noticeId")) {
                SrmBaseNoticesEntity_HI noticesRow = (SrmBaseNoticesEntity_HI) srmBaseNoticesDAO_HI.getById(params.getInteger("noticeId"));
                if ("1".equals(noticesRow.getNoticeStatus())) {
                    if (null != noticesRow) {
                        List<SrmBaseNoticeFilesEntity_HI> filesList = srmBaseNoticeFilesDAO_HI.findByProperty("noticeId", noticesRow.getNoticeId());
                        if (filesList != null && filesList.size() > 0) {
                            srmBaseNoticeFilesDAO_HI.delete(filesList);
                        }
                        List<SrmBaseNoticeReceiversEntity_HI> receiversList = srmBaseNoticeReceiversDAO_HI.findByProperty("noticeId", noticesRow.getNoticeId());
                        if (receiversList != null && receiversList.size() > 0) {
                            srmBaseNoticeReceiversDAO_HI.delete(receiversList);
                        }
                        srmBaseNoticesDAO_HI.delete(noticesRow);
                    }
                } else {
                    return SToolUtils.convertResultJSONObj("E", "删除公告失败,只能删除待发布的公告!", 0, null);
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除公告失败，"
                        + params.getString("noticeId") + "不存在", 0, null);
            }
            return SToolUtils.convertResultJSONObj("S", "删除公告成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    /**
     * 撤回公告
     */
    @Override
    public JSONObject updateWithdrawNotices(JSONObject params) throws Exception {
        try {
            Integer noticeId = params.getInteger("noticeId");
            if (noticeId == null) {
                return SToolUtils.convertResultJSONObj("E", "撤回失败,请传入参数noticeId", 0, null);
            }
            SrmBaseNoticesEntity_HI noticesRow = (SrmBaseNoticesEntity_HI) srmBaseNoticesDAO_HI.getById(Integer.parseInt(noticeId.toString()));
            Integer operatorUserId = params.getInteger("varUserId");
            noticesRow.setNoticeStatus("3");
            noticesRow.setCancelUserId(operatorUserId);
            noticesRow.setCancelDate(new Date());
            noticesRow.setOperatorUserId(operatorUserId);
            srmBaseNoticesDAO_HI.update(noticesRow);
            return SToolUtils.convertResultJSONObj("S", "撤回成功", 1, noticesRow);
        } catch (Exception e) {
            //LOGGER.error("未知错误:{}", e)();
            throw new Exception(e);
        }
    }

    /**
     * Description：发布公告
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * isTechnologyChange  是否技改  VARCHAR2  N
     * endActiveDate  失效日期  DATE  N
     * noticeId  公告ID  NUMBER  Y
     * noticeCode  公告编号  VARCHAR2  Y
     * noticeTitle  公告标题  VARCHAR2  Y
     * noticeContent  公告内容  CLOB  N
     * noticeType  公告类型  VARCHAR2  Y
     * noticeStatus  公告状态  VARCHAR2  Y
     * publishDate  发布时间  DATE  N
     * publishUserId  发布用户  NUMBER  N
     * cancelDate  撤回时间  DATE  N
     * cancelUserId  撤回用户  NUMBER  N
     * setTopFlag  置顶标识  VARCHAR2  N
     * receiverType  发布对象  VARCHAR2  Y
     * noticeId  公告ID  NUMBER  Y
     * noticeCode  公告编号  VARCHAR2  Y
     * noticeTitle  公告标题  VARCHAR2  Y
     * noticeContent  公告内容  CLOB  N
     * noticeType  公告类型  VARCHAR2  Y
     * noticeStatus  公告状态  VARCHAR2  Y
     * publishDate  发布时间  DATE  N
     * publishUserId  发布用户  NUMBER  N
     * cancelDate  撤回时间  DATE  N
     * cancelUserId  撤回用户  NUMBER  N
     * setTopFlag  置顶标识  VARCHAR2  N
     * receiverType  发布对象  VARCHAR2  Y
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Override
    public JSONObject saveReleaseNotices(JSONObject params) throws Exception {
        LOGGER.info("发布公告,参数：" + params.toString());
        SrmBaseNoticesEntity_HI noticesRow = null;
        try {
            Integer operatorUserId = params.getInteger("varUserId");
            Date date = new Date();
            String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
            // 保存信息判断
            if (null == params.getInteger("noticeId")) {
                noticesRow = new SrmBaseNoticesEntity_HI();
                String noticeCode = saafSequencesUtil.getDocSequences("SRM_BASE_NOTICES", dateFromate, 2);
                noticesRow.setNoticeCode(noticeCode);
            } else {
                // 判断存在就是修改
                noticesRow = srmBaseNoticesDAO_HI.findByProperty("noticeId", params.getInteger("noticeId")).get(0);
            }

            noticesRow.setNoticeType(params.getString("noticeType"));
            noticesRow.setNoticeTitle(params.getString("noticeTitle"));
            noticesRow.setNoticeContent(params.getString("noticeContent"));
            noticesRow.setSetTopFlag(params.getString("setTopFlag"));
            noticesRow.setReceiverType(params.getString("receiverType"));
            noticesRow.setNoticeStatus(params.getString("noticeStatus"));
            noticesRow.setPublishDate(new Date());
            noticesRow.setPublishUserId(operatorUserId);
            noticesRow.setOperatorUserId(operatorUserId);
            srmBaseNoticesDAO_HI.saveOrUpdate(noticesRow);

            LOGGER.info("getNoticeId：" + noticesRow.getNoticeId());

            List<SrmBaseNoticeFilesEntity_HI> noticeFilesList = null;// 公告附件
            if (!(params.getJSONArray("noticeFilesList") == null || "".equals(params.getJSONArray("noticeFilesList")))) {
                JSONArray noticeFilesListJSON = params.getJSONArray("noticeFilesList");
                noticeFilesList = JSON.parseArray(noticeFilesListJSON.toJSONString(), SrmBaseNoticeFilesEntity_HI.class);
            }
            iSrmBaseNoticeFiles.saveNoticeFilesList(noticeFilesList, operatorUserId, noticesRow.getNoticeId());

            String receiverType = params.getString("receiverType");
            LOGGER.info("receiverType：" + receiverType);
            if (receiverType.equals("2")) {
                List<SrmBaseNoticeReceiversEntity_HI> instList = null;// 组织
                if (!(params.getJSONArray("instList") == null || "".equals(params.getJSONArray("instList")))) {
                    JSONArray noticeFilesListJSON = params.getJSONArray("instList");
                    instList = JSON.parseArray(noticeFilesListJSON.toJSONString(), SrmBaseNoticeReceiversEntity_HI.class);
                }
                iSrmBaseNoticeReceivers.saveInstList(instList, operatorUserId, noticesRow.getNoticeId());
            } else if (receiverType.equals("3")) {
                List<SrmBaseNoticeReceiversEntity_HI> employeeList = null;// 组织
                if (!(params.getJSONArray("employeeList") == null || "".equals(params.getJSONArray("employeeList")))) {
                    JSONArray noticeFilesListJSON = params.getJSONArray("employeeList");
                    employeeList = JSON.parseArray(noticeFilesListJSON.toJSONString(), SrmBaseNoticeReceiversEntity_HI.class);
                }
                iSrmBaseNoticeReceivers.saveEmployeeList(employeeList, operatorUserId, noticesRow.getNoticeId());
            } else if (receiverType.equals("4")) {
                List<SrmBaseNoticeReceiversEntity_HI> supplierList = null;// 供应商
                if (!(params.getJSONArray("supplierList") == null || "".equals(params.getJSONArray("supplierList")))) {
                    JSONArray noticeFilesListJSON = params.getJSONArray("supplierList");
                    supplierList = JSON.parseArray(noticeFilesListJSON.toJSONString(), SrmBaseNoticeReceiversEntity_HI.class);
                }
                iSrmBaseNoticeReceivers.saveSupplierList(supplierList, operatorUserId, noticesRow.getNoticeId());
            }

            JSONObject result = SToolUtils.convertResultJSONObj("S", "发布成功", 1, noticesRow);
            result.put("getNoticeId", noticesRow.getNoticeId());
            return result;
            //return SToolUtils.convertResultJSONObj("S", "保存成功", 1, noticesRow.getNoticeId());
        } catch (Exception e) {
            //LOGGER.error("未知错误:{}", e)();
            throw new Exception(e);
        }
    }
}

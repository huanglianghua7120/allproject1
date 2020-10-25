package saaf.common.fmw.base.model.inter.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.model.entities.SrmBaseNoticeReceiversEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseNoticesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseNoticeReceivers;
import saaf.common.fmw.common.utils.SaafToolUtils;
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
@Component("/srmBaseNoticeReceiversServer")
public class SrmBaseNoticeReceiversServer implements ISrmBaseNoticeReceivers {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseNoticeReceiversServer.class);

    public SrmBaseNoticeReceiversServer() {
        super();
    }

    @Autowired
    private ViewObject<SrmBaseNoticeReceiversEntity_HI> srmBaseNoticeReceiversDAO_HI;

    @Autowired
    private BaseViewObject<SrmBaseNoticesEntity_HI_RO> srmBaseNoticesDAO_HI_RO;


    /**
     * 查询公告接收者(组织)
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmBaseNoticesEntity_HI_RO> findInstReceiversList(
            JSONObject params, Integer pageIndex, Integer pageRows)
            throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmBaseNoticesEntity_HI_RO.QUERY_INST__LIST_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(params, "r.notice_id", "noticeId", queryString, map, "=");
            String countSql = "select count(1) from (" + queryString + ")";
            queryString.append(" order by r.last_update_date desc");
            Pagination<SrmBaseNoticesEntity_HI_RO> instList = srmBaseNoticesDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return instList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询公告接收者(员工)
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmBaseNoticesEntity_HI_RO> findEmployeeReceiversList(
            JSONObject params, Integer pageIndex, Integer pageRows)
            throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmBaseNoticesEntity_HI_RO.QUERY_EMPLOYEE__LIST_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(params, "r.notice_id", "noticeId", queryString, map, "=");
            String countSql = "select count(1) from (" + queryString + ")";
            queryString.append(" order by r.last_update_date desc");
            Pagination<SrmBaseNoticesEntity_HI_RO> employeeList = srmBaseNoticesDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return employeeList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询接收者(供应商)
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmBaseNoticesEntity_HI_RO> findSupplierReceiversList(
            JSONObject params, Integer pageIndex, Integer pageRows)
            throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmBaseNoticesEntity_HI_RO.QUERY_SUPPLIER_LIST_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(params, "r.notice_id", "noticeId", queryString, map, "=");
            String countSql = "select count(1) from (" + queryString + ")";
            queryString.append(" order by r.last_update_date desc");
            Pagination<SrmBaseNoticesEntity_HI_RO> supplierList = srmBaseNoticesDAO_HI_RO.findPagination(queryString,countSql ,map, pageIndex, pageRows);
            return supplierList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 保存接收者(组织)
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public void saveInstList(List<SrmBaseNoticeReceiversEntity_HI> instList,
                             Integer userId, Integer noticeId) throws Exception {
        try {
            if (null != instList && instList.size() > 0) {
                for (SrmBaseNoticeReceiversEntity_HI i : instList) {
                    i.setNoticeId(noticeId);
                    i.setReceiverType("2");
                    i.setOperatorUserId(userId);
                }
                srmBaseNoticeReceiversDAO_HI.saveOrUpdateAll(instList);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 保存接收者(员工)
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public void saveEmployeeList(
            List<SrmBaseNoticeReceiversEntity_HI> employeeList, Integer userId,
            Integer noticeId) throws Exception {
        try {
            if (null != employeeList && employeeList.size() > 0) {
                for (SrmBaseNoticeReceiversEntity_HI i : employeeList) {
                    i.setNoticeId(noticeId);
                    i.setReceiverType("3");
                    i.setOperatorUserId(userId);
                }
                srmBaseNoticeReceiversDAO_HI.saveOrUpdateAll(employeeList);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 保存接收者(供应商)
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public void saveSupplierList(
            List<SrmBaseNoticeReceiversEntity_HI> supplierList, Integer userId,
            Integer noticeId) throws Exception {
        try {
            if (null != supplierList && supplierList.size() > 0) {
                for (SrmBaseNoticeReceiversEntity_HI i : supplierList) {
                    i.setNoticeId(noticeId);
                    i.setReceiverType("4");
                    i.setOperatorUserId(userId);
                }
                srmBaseNoticeReceiversDAO_HI.saveOrUpdateAll(supplierList);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 删除公告接收者
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject deleteNoticeReceivers(JSONObject params) throws Exception {
        LOGGER.info("删除参数：" + params.toString());
        SrmBaseNoticeReceiversEntity_HI receiversRow = null;
        try {
            List<SrmBaseNoticeReceiversEntity_HI> receiversList = srmBaseNoticeReceiversDAO_HI
                    .findByProperty("noticeReceiverId", params.getInteger("noticeReceiverId"));
            if (receiversList != null && receiversList.size() > 0) {
                receiversRow = (SrmBaseNoticeReceiversEntity_HI) receiversList.get(0);
                srmBaseNoticeReceiversDAO_HI.delete(receiversRow);
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除失败，" + params.getString("noticeReceiverId") + "不存在", 0, null);
            }
            return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}

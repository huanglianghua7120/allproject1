package saaf.common.fmw.pos.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierContactsEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierContactsEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierContacts;

import java.util.*;

@Component("srmPosSupplierContactsServer")
public class SrmPosSupplierContactsServer implements ISrmPosSupplierContacts {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSupplierContactsServer.class);

    @Autowired
    private ViewObject<SrmPosSupplierContactsEntity_HI> srmPosSupplierContactsDAO_HI;

    @Autowired
    private BaseViewObject<SrmPosSupplierContactsEntity_HI_RO> srmPosSupplierContactsDAO_HI_RO;

    public SrmPosSupplierContactsServer() {
        super();
    }

    /**
     * 供应商的删除联系人
     */
    @Override
    public JSONObject deleteContact(JSONObject jsonParams) {
        LOGGER.info("参数：" + jsonParams.toString());
        Integer supplierContactId = jsonParams.getInteger("supplierContactId");
        if (!(supplierContactId == null || "".equals(supplierContactId))) {
            SrmPosSupplierContactsEntity_HI row = srmPosSupplierContactsDAO_HI.getById(supplierContactId);
            if (null != row) {
                srmPosSupplierContactsDAO_HI.delete(row.getSupplierContactId());
                return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除失败，无此记录！参数为：" + supplierContactId, 0, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "删除失败，参数" + supplierContactId, 0, null);
        }
    }

    /**
     * 保存供应商的联系人（档案自助维护/内部创建供应商）
     *
     * @param supplierContactsList
     * @param userId
     * @param supplierId
     * @return
     */
    @Override
    public boolean saveSrmPosSupplierContactsInfo(List<SrmPosSupplierContactsEntity_HI> supplierContactsList, Integer userId, Integer supplierId) {
        boolean flag = false;
        List<SrmPosSupplierContactsEntity_HI> newSupplierContactsList = new ArrayList<>();
        if (null != supplierContactsList && supplierContactsList.size() > 0) {
            for (SrmPosSupplierContactsEntity_HI k : supplierContactsList) {
                SrmPosSupplierContactsEntity_HI findRow = srmPosSupplierContactsDAO_HI.getById(k.getSupplierContactId());
                if (null != findRow) {
                    findRow.setContactName(k.getContactName());
                    findRow.setDepartmentName(k.getDepartmentName());
                    findRow.setPositionName(k.getPositionName());
                    findRow.setMobilePhone(k.getMobilePhone());
                    findRow.setEmailAddress(k.getEmailAddress());
                    findRow.setFixedPhone(k.getFixedPhone());
                    findRow.setFaxPhone(k.getFaxPhone());
                    findRow.setSupplierId(supplierId);
                    findRow.setOperatorUserId(userId);
                    newSupplierContactsList.add(findRow);
                } else {
                    k.setSupplierId(supplierId);
                    k.setOperatorUserId(userId);
                    newSupplierContactsList.add(k);
                }
            }
            srmPosSupplierContactsDAO_HI.saveOrUpdateAll(newSupplierContactsList);
            flag = true;
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 保存供应商的联系人——供应商接口（数据输入）
     *
     * @param supplierContactsList
     * @param userId
     * @param supplierId
     */
    @Override
    public void saveSrmPosSupplierContactsInfoExternal(List<SrmPosSupplierContactsEntity_HI> supplierContactsList, Integer userId, Integer supplierId) {
        List<SrmPosSupplierContactsEntity_HI> newSupplierContactsList = new ArrayList<>();
        if (null != supplierContactsList && supplierContactsList.size() > 0) {
            for (SrmPosSupplierContactsEntity_HI k : supplierContactsList) {
                SrmPosSupplierContactsEntity_HI findRow = null;
                JSONObject jsonParams = new JSONObject();
                jsonParams.put("sourceCode", k.getSourceCode());
                jsonParams.put("sourceId", k.getSourceId());
                jsonParams.put("supplierId", supplierId);
                Pagination<SrmPosSupplierContactsEntity_HI_RO> listPagination = findSupplierContacts(jsonParams, 1, 1000);
                List<SrmPosSupplierContactsEntity_HI_RO> listRO = listPagination.getData();
                if (null != listRO && listRO.size() > 0) {
                    SrmPosSupplierContactsEntity_HI_RO findRO = listRO.get(0);
                    findRow = srmPosSupplierContactsDAO_HI.getById(findRO.getSupplierContactId());
                }
                if (null != findRow) {
                    findRow.setContactName(k.getContactName());
                    findRow.setMobilePhone(k.getMobilePhone());
                    findRow.setEmailAddress(k.getEmailAddress());
                    findRow.setFixedPhone(k.getFixedPhone());
                    findRow.setFaxPhone(k.getFaxPhone());
                    findRow.setSupplierId(supplierId);
                    findRow.setOperatorUserId(userId);
                    newSupplierContactsList.add(findRow);
                } else {
                    k.setSupplierId(supplierId);
                    k.setOperatorUserId(userId);
                    newSupplierContactsList.add(k);
                }
            }
            srmPosSupplierContactsDAO_HI.saveOrUpdateAll(newSupplierContactsList);
        }
    }

    /**
     * 校验供应商的联系人必填项——供应商接口（数据输入）
     *
     * @param supplierContactsList
     * @return
     */
    @Override
    public String judgeSpaceSupplierContacts(List<SrmPosSupplierContactsEntity_HI> supplierContactsList) {
        String result = "";
        if (null == supplierContactsList || supplierContactsList.size() <= 0) {
            return result;
        }
        Integer index = 0;
        for (SrmPosSupplierContactsEntity_HI k : supplierContactsList) {
            index++;
            if (null == k.getSourceId() || "".equals(k.getSourceId())) {
                result += "请填写联系人" + "第" + index + "行的数据来源Id——sourceId";
                return result;
            }
            if (null == k.getSourceCode() || "".equals(k.getSourceCode())) {
                result += "请填写联系人" + "第" + index + "行的数据来源类型Code——sourceCode";
                return result;
            }
            if (null == k.getContactName() || "".equals(k.getContactName())) {
                result += "请填写联系人" + "第" + index + "行的必填项——姓名";
                return result;
            }
            if (null == k.getMobilePhone() || "".equals(k.getMobilePhone())) {
                result += "请填写联系人" + "第" + index + "行的必填项——联系电话";
                return result;
            }
            if (null == k.getEmailAddress() || "".equals(k.getEmailAddress())) {
                result += "请填写联系人" + "第" + index + "行的必填项——邮箱";
                return result;
            }
        }
        return result;
    }

    public Pagination<SrmPosSupplierContactsEntity_HI_RO> findSupplierContacts(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer(SrmPosSupplierContactsEntity_HI_RO.QUERY_SUPPLIER_CONTACTS);
        Integer supplierId = -1;
        if (jsonParams.getInteger("supplierId") != null) {
            supplierId = jsonParams.getInteger("supplierId");
        } else if (jsonParams.getInteger("supplier_id") != null) {
            supplierId = jsonParams.getInteger("supplier_id");
        }
        queryParamMap.put("supplierId", supplierId);
        SaafToolUtils.parperParam(jsonParams, "psc.source_code", "sourceCode", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "psc.source_id", "sourceId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosSupplierContactsEntity_HI_RO> result = srmPosSupplierContactsDAO_HI_RO.findPagination
                (sb.toString(), countSql,queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * 保存供应商的联系人
     *
     * @param paramDataList
     * @param userId
     * @param supplierId
     * @return
     */
    public JSONObject saveSupplierContacts(JSONArray paramDataList, int userId, int supplierId) throws Exception {
        JSONObject resultjson = new JSONObject();
        JSONObject paramData = null;
        for (int c = 0; c < paramDataList.size(); c++) {
            paramData = paramDataList.getJSONObject(c);
            SrmPosSupplierContactsEntity_HI row = null;
            if (null == paramData.getInteger("supplierContactId")) {
                row = new SrmPosSupplierContactsEntity_HI();
                row.setSupplierId(supplierId);
                row.setCreationDate(new Date());
                row.setCreatedBy(userId);
            } else {
                row = srmPosSupplierContactsDAO_HI.getById(paramData.getInteger("supplierContactId"));
            }
            row.setContactName(paramData.getString("contactName"));
            row.setMobilePhone(paramData.getString("mobilePhone"));
            row.setEmailAddress(paramData.getString("emailAddress"));
            row.setFixedPhone(paramData.getString("fixedPhone"));
            row.setFaxPhone(paramData.getString("faxPhone"));
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            srmPosSupplierContactsDAO_HI.saveEntity(row);
        }
        return resultjson;
    }

    /**
     * 获取供应商的第一个联系人
     * @param supplierId
     * @return
     */
    public SrmPosSupplierContactsEntity_HI_RO findFirstContact(Integer supplierId) {
        String queryString =
                        "SELECT\n" +
                        "  psc.supplier_contact_id,\n" +
                        "  psc.supplier_id,\n" +
                        "  psc.contact_name,\n" +
                        "  psc.mobile_phone,\n" +
                        "  psc.email_address,\n" +
                        "  psc.department_name,\n" +
                        "  psc.position_name\n" +
                        "FROM\n" +
                        "  srm_pos_supplier_contacts psc\n" +
                        "WHERE psc.supplier_id = :supplierId\n" +
                        "ORDER BY psc.supplier_contact_id";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("supplierId", supplierId);
        List<SrmPosSupplierContactsEntity_HI_RO> list = srmPosSupplierContactsDAO_HI_RO.findList(queryString, paramMap);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 获取供应商的第一个联系人
     * @param supplierId
     * @return
     */
    @Override
    public List<SrmPosSupplierContactsEntity_HI_RO> findSupplierContact(Integer supplierId) {
        String queryString =
                "SELECT\n" +
                        "  psc.supplier_contact_id,\n" +
                        "  psc.supplier_id,\n" +
                        "  psc.contact_name,\n" +
                        "  psc.mobile_phone,\n" +
                        "  psc.email_address,\n" +
                        "  psc.department_name,\n" +
                        "  psc.position_name\n" +
                        "FROM\n" +
                        "  srm_pos_supplier_contacts psc\n" +
                        "WHERE psc.supplier_id = :supplierId\n" +
                        "ORDER BY psc.supplier_contact_id";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("supplierId", supplierId);
        List<SrmPosSupplierContactsEntity_HI_RO> list = srmPosSupplierContactsDAO_HI_RO.findList(queryString, paramMap);
        if (list != null && list.size() > 0) {
            //return list.get(0);
            return list;
        }
        return null;
    }




}

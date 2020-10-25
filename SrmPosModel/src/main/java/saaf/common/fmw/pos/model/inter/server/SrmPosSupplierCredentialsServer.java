package saaf.common.fmw.pos.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierCredentialsEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierCredentialsEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierCredentials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("srmPosSupplierCredentialsServer")
public class SrmPosSupplierCredentialsServer implements ISrmPosSupplierCredentials {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSupplierCredentialsServer.class);

    @Autowired
    private ViewObject<SrmPosSupplierCredentialsEntity_HI> srmPosSupplierCredentialsDAO_HI;

    @Autowired
    private BaseViewObject<SrmPosSupplierCredentialsEntity_HI_RO> srmPosSupplierCredentialsDAO_HI_RO;

    public SrmPosSupplierCredentialsServer() {
        super();
    }

    /**
     * 查询供应商的资质信息
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SrmPosSupplierCredentialsEntity_HI_RO> findSrmPosSupplierCredentialsInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosSupplierCredentialsEntity_HI_RO.QUERY_CREDENTIALS);
        SaafToolUtils.parperParam(jsonParams, "t.supplier_id", "supplier_id", sb, queryParamMap, "=");//如果是供应商查询
        SaafToolUtils.parperParam(jsonParams, "t.supplier_id", "supplierId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.credentials_id", "credentialsId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.source_code", "sourceCode", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.source_id", "sourceId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosSupplierCredentialsEntity_HI_RO> result =
                srmPosSupplierCredentialsDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * 保存供应商的资质信息（档案自助维护/内部创建供应商）
     *
     * @param credentialsList
     * @param userId
     * @param supplierId
     * @return
     */
    @Override
    public boolean saveSrmPosSupplierCredentialsInfo(List<SrmPosSupplierCredentialsEntity_HI> credentialsList, Integer userId, Integer supplierId) {
        boolean flag = false;
        List<SrmPosSupplierCredentialsEntity_HI> newCredentialsList = new ArrayList<>();
        if (null != credentialsList && credentialsList.size() > 0) {
            for (SrmPosSupplierCredentialsEntity_HI k : credentialsList) {
                SrmPosSupplierCredentialsEntity_HI findRow = srmPosSupplierCredentialsDAO_HI.getById(k.getCredentialsId());
                if (null != findRow) {
                    findRow.setEstablishmentDate(k.getEstablishmentDate());
                    findRow.setLicenseNum(k.getLicenseNum());
                    findRow.setLicenseIndate(k.getLicenseIndate());
                    findRow.setLongLicenseIndate(k.getLongLicenseIndate());
                    findRow.setLicenseFileId(k.getLicenseFileId());
                    findRow.setMuchInOne(k.getMuchInOne());
                    findRow.setTissueCode(k.getTissueCode());
                    findRow.setTissueIndate(k.getTissueIndate());
                    findRow.setTissueFileId(k.getTissueFileId());
                    findRow.setTaxCode(k.getTaxCode());
                    findRow.setTaxFileId(k.getTaxFileId());
                    findRow.setBankPermitNumber(k.getBankPermitNumber());
                    findRow.setBankPermitFileId(k.getBankPermitFileId());
                    findRow.setRepresentativeName(k.getRepresentativeName());
                    findRow.setCorporateIdentity(k.getCorporateIdentity());
                    findRow.setBusinessScope(k.getBusinessScope());
                    findRow.setOthersFileId(k.getOthersFileId());
                    findRow.setSupplierId(supplierId);
                    findRow.setOperatorUserId(userId);
                    newCredentialsList.add(findRow);
                } else {
                    k.setSupplierId(supplierId);
                    k.setOperatorUserId(userId);
                    newCredentialsList.add(k);
                }
            }
            srmPosSupplierCredentialsDAO_HI.saveOrUpdateAll(newCredentialsList);
            flag = true;
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 保存供应商的资质信息——供应商接口（数据输入）
     *
     * @param supplierCredentialsInfo
     * @param userId
     * @param supplierId
     */
    @Override
    public void saveSupplierCredentialsInfoExternal(SrmPosSupplierCredentialsEntity_HI supplierCredentialsInfo, Integer userId, Integer supplierId) {
        SrmPosSupplierCredentialsEntity_HI findrCredentialsInfo = null;
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("sourceCode", supplierCredentialsInfo.getSourceCode());
        jsonParams.put("sourceId", supplierCredentialsInfo.getSourceId());
        jsonParams.put("supplierId", supplierId);
        Pagination<SrmPosSupplierCredentialsEntity_HI_RO> listPagination = findSrmPosSupplierCredentialsInfo(jsonParams, 1, 1000);
        List<SrmPosSupplierCredentialsEntity_HI_RO> list = listPagination.getData();
        if (null != list && list.size() > 0) {
            SrmPosSupplierCredentialsEntity_HI_RO findRO = list.get(0);
            findrCredentialsInfo = srmPosSupplierCredentialsDAO_HI.getById(findRO.getCredentialsId());
        }
        if (null != findrCredentialsInfo) {  //update
            findrCredentialsInfo.setEstablishmentDate(supplierCredentialsInfo.getEstablishmentDate());
            findrCredentialsInfo.setLicenseNum(supplierCredentialsInfo.getLicenseNum());
            findrCredentialsInfo.setLicenseIndate(supplierCredentialsInfo.getLicenseIndate());
            findrCredentialsInfo.setLongLicenseIndate(supplierCredentialsInfo.getLongLicenseIndate());
            findrCredentialsInfo.setLicenseFileId(supplierCredentialsInfo.getLicenseFileId());
            findrCredentialsInfo.setMuchInOne(supplierCredentialsInfo.getMuchInOne());
            findrCredentialsInfo.setTissueCode(supplierCredentialsInfo.getTissueCode());
            findrCredentialsInfo.setTissueIndate(supplierCredentialsInfo.getTissueIndate());
            findrCredentialsInfo.setTissueFileId(supplierCredentialsInfo.getTissueFileId());
            findrCredentialsInfo.setTaxCode(supplierCredentialsInfo.getTaxCode());
            findrCredentialsInfo.setTaxFileId(supplierCredentialsInfo.getTaxFileId());
            findrCredentialsInfo.setBankPermitNumber(supplierCredentialsInfo.getBankPermitNumber());
            findrCredentialsInfo.setBankPermitFileId(supplierCredentialsInfo.getBankPermitFileId());
            findrCredentialsInfo.setRepresentativeName(supplierCredentialsInfo.getRepresentativeName());
            findrCredentialsInfo.setCorporateIdentity(supplierCredentialsInfo.getCorporateIdentity());
            findrCredentialsInfo.setBusinessScope(supplierCredentialsInfo.getBusinessScope());
            findrCredentialsInfo.setOthersFileId(supplierCredentialsInfo.getOthersFileId());
            findrCredentialsInfo.setSupplierId(supplierId);
            findrCredentialsInfo.setOperatorUserId(userId);
            srmPosSupplierCredentialsDAO_HI.saveEntity(findrCredentialsInfo);
        } else { //insert
            supplierCredentialsInfo.setSupplierId(supplierId);
            supplierCredentialsInfo.setOperatorUserId(userId);
            srmPosSupplierCredentialsDAO_HI.saveEntity(supplierCredentialsInfo);
        }
    }

    /**
     * 校验供应商的资质信息必填项——供应商接口（数据输入）
     *
     * @param supplierCredentialsInfo
     * @return
     */
    @Override
    public String judgeSpaceSupplierCredentialsInfo(SrmPosSupplierCredentialsEntity_HI supplierCredentialsInfo) {
        String result = "";
        if (null == supplierCredentialsInfo || "".equals(supplierCredentialsInfo)) {
            return result;
        }
        if (null == supplierCredentialsInfo.getSourceId() || "".equals(supplierCredentialsInfo.getSourceId())) {
            result += "请填写资质信息的数据来源Id——sourceId";
            return result;
        }
        if (null == supplierCredentialsInfo.getSourceCode() || "".equals(supplierCredentialsInfo.getSourceCode())) {
            result += "请填写资质信息的数据来源类型Code——sourceCode";
            return result;
        }
        if (null == supplierCredentialsInfo.getLicenseNum() || "".equals(supplierCredentialsInfo.getLicenseNum())) {
            result += "请填写资质信息的营业执照号";
            return result;
        }
        if (null == supplierCredentialsInfo.getBankPermitNumber() || "".equals(supplierCredentialsInfo.getBankPermitNumber())) {
            result += "请填写资质信息的银行开户许可证号";
            return result;
        }
        if (null == supplierCredentialsInfo.getEstablishmentDate() || "".equals(supplierCredentialsInfo.getEstablishmentDate())) {
            result += "请填写资质信息的成立日期";
            return result;
        }
        if (null == supplierCredentialsInfo.getBusinessScope() || "".equals(supplierCredentialsInfo.getBusinessScope())) {
            result += "请填写资质信息的经营范围";
            return result;
        }
        return result;
    }
}

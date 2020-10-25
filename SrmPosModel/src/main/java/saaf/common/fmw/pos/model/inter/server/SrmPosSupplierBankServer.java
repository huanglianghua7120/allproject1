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
import saaf.common.fmw.base.model.entities.readonly.SrmBaseBanksEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseBranchesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseBanks;
import saaf.common.fmw.base.model.inter.ISrmBaseBranches;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierBankEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierBankEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierBank;
import saaf.common.fmw.utils.SrmUtils;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Component("srmPosSupplierBankServer")
public class SrmPosSupplierBankServer implements ISrmPosSupplierBank {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSupplierBankServer.class);

    @Autowired
    private ViewObject<SrmPosSupplierBankEntity_HI> srmPosSupplierBankDAO_HI;

    @Autowired
    private BaseViewObject<SrmPosSupplierBankEntity_HI_RO> srmPosSupplierBankDAO_HI_RO;

    @Autowired
    private ISrmBaseBanks iSrmBaseBanks; //银行

    @Autowired
    private ISrmBaseBranches iSrmBaseBranches;//分行（银行的子表）

    public SrmPosSupplierBankServer() {
        super();
    }

    /**
     * 删除银行信息
     *
     * @param jsonParams
     * @return
     */
    @Override
    public JSONObject deleteSupplierBank(JSONObject jsonParams) {
        LOGGER.info("参数：" + jsonParams.toString());
        Integer bankAccountId = jsonParams.getInteger("bankAccountId");
        if (!(bankAccountId == null || "".equals(bankAccountId))) {
            SrmPosSupplierBankEntity_HI row = srmPosSupplierBankDAO_HI.getById(jsonParams.getInteger("bankAccountId"));
            if (null != row) {
                srmPosSupplierBankDAO_HI.delete(row.getBankAccountId());
                return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除失败，无此记录！参数为：" + bankAccountId, 0, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "删除失败，参数" + bankAccountId, 0, null);
        }
    }

    /**
     * 保存供应商的银行信息（档案自助维护/内部创建供应商）
     *
     * @param supplierBankList
     * @param userId
     * @param supplierId
     * @return
     */
    @Override
    public boolean saveSrmPosSupplierBankInfo(List<SrmPosSupplierBankEntity_HI> supplierBankList, Integer userId, Integer supplierId) {
        boolean flag = false;
        List<SrmPosSupplierBankEntity_HI> newSupplierBankList = new ArrayList<>();
        if (null != supplierBankList && supplierBankList.size() > 0) {
            for (SrmPosSupplierBankEntity_HI k : supplierBankList) {
                SrmPosSupplierBankEntity_HI findRow = srmPosSupplierBankDAO_HI.getById(k.getBankAccountId());
                if (null != findRow) {
                    findRow.setBankUserName(k.getBankUserName());
                    findRow.setBankAccountNumber(k.getBankAccountNumber());
                    findRow.setBankId(k.getBankId());
                    findRow.setBankName(k.getBankName());
                    findRow.setBranchId(k.getBranchId());
                    findRow.setBranchNumber(k.getBranchNumber());
                    findRow.setBranchName(k.getBranchName());
                    findRow.setBankCurrency(k.getBankCurrency());
                    findRow.setSwiftCode(k.getSwiftCode());
                    findRow.setIbanCode(k.getIbanCode());
                    findRow.setBankLink(k.getBankLink());
                    findRow.setSupplierId(supplierId);
                    findRow.setOperatorUserId(userId);
                    newSupplierBankList.add(findRow);
                } else {
                    k.setSupplierId(supplierId);
                    k.setOperatorUserId(userId);
                    newSupplierBankList.add(k);
                }
            }
            srmPosSupplierBankDAO_HI.saveOrUpdateAll(newSupplierBankList);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 保存供应商的银行信息——供应商接口（数据输入）
     *
     * @param supplierBankList
     * @param userId
     * @param supplierId
     */
    @Override
    public void saveSrmPosSupplierBankInfoExternal(List<SrmPosSupplierBankEntity_HI> supplierBankList, Integer userId, Integer supplierId) {
        List<SrmPosSupplierBankEntity_HI> newSupplierBankList = new ArrayList<>();
        if (null != supplierBankList && supplierBankList.size() > 0) {
            for (SrmPosSupplierBankEntity_HI k : supplierBankList) {
                SrmPosSupplierBankEntity_HI findRow = null;
                JSONObject jsonParams = new JSONObject();
                jsonParams.put("sourceCode", k.getSourceCode());
                jsonParams.put("sourceId", k.getSourceId());
                jsonParams.put("supplierId", supplierId);
                Pagination<SrmPosSupplierBankEntity_HI_RO> listPagination = findSupplierBankInfo(jsonParams, 1, 1000);
                List<SrmPosSupplierBankEntity_HI_RO> listRO = listPagination.getData();
                if (null != listRO && listRO.size() > 0) {
                    SrmPosSupplierBankEntity_HI_RO findRO = listRO.get(0);
                    findRow = srmPosSupplierBankDAO_HI.getById(findRO.getBankAccountId());
                }
                if (null != findRow) {
                    findRow.setBankUserName(k.getBankUserName());
                    findRow.setBankAccountNumber(k.getBankAccountNumber());
                    findRow.setBankName(k.getBankName());
                    findRow.setBranchNumber(k.getBranchNumber());
                    findRow.setBranchName(k.getBranchName());
                    findRow.setBankCurrency(k.getBankCurrency());
                    findRow.setSwiftCode(k.getSwiftCode());
                    findRow.setIbanCode(k.getIbanCode());

                    JSONObject jsonParamssrmBaseBank = new JSONObject(); //银行的json
                    JSONObject jsonParamBaseBranches = new JSONObject(); //分行的json
                    jsonParamssrmBaseBank.put("bankName", k.getBankName());//根据银行名称查找出对应的银行的bankId
                    Pagination<SrmBaseBanksEntity_HI_RO> bankListPagination = iSrmBaseBanks.findSrmBaseBanksInfoList(jsonParamssrmBaseBank, 1, 1000);
                    List<SrmBaseBanksEntity_HI_RO> bankListRO = bankListPagination.getData();
                    if (null != bankListRO && bankListRO.size() > 0) {
                        SrmBaseBanksEntity_HI_RO bankRO = bankListRO.get(0);
                        findRow.setBankId(bankRO.getBankId());  //将查出的银行的bankId赋值
                        jsonParamBaseBranches.put("bankId", bankRO.getBankId());
                    }
                    jsonParamBaseBranches.put("branchName", k.getBranchName());//根据分行名称和银行的主键Id查找出对应的分行的branchId
                    Pagination<SrmBaseBranchesEntity_HI_RO> branchesListPagination = iSrmBaseBranches.findSrmBaseBranchesInfoList(jsonParamBaseBranches, 1, 1000);
                    List<SrmBaseBranchesEntity_HI_RO> branchesList = branchesListPagination.getData();
                    if (null != branchesList && branchesList.size() > 0) {
                        SrmBaseBranchesEntity_HI_RO branchesRO = branchesList.get(0);
                        findRow.setBranchId(branchesRO.getBranchId()); //将查出的分行的branchId赋值
                        findRow.setBranchNumber(branchesRO.getBranchName()); //将查出的分行编码赋值
                    }

                    findRow.setSupplierId(supplierId);
                    findRow.setOperatorUserId(userId);
                    newSupplierBankList.add(findRow);
                } else {
                    JSONObject jsonParamssrmBaseBank = new JSONObject(); //银行的json
                    JSONObject jsonParamBaseBranches = new JSONObject(); //分行的json
                    jsonParamssrmBaseBank.put("bankName", k.getBankName());//根据银行名称查找出对应的银行的bankId
                    Pagination<SrmBaseBanksEntity_HI_RO> bankListPagination = iSrmBaseBanks.findSrmBaseBanksInfoList(jsonParamssrmBaseBank, 1, 1000);
                    List<SrmBaseBanksEntity_HI_RO> bankListRO = bankListPagination.getData();
                    if (null != bankListRO && bankListRO.size() > 0) {
                        SrmBaseBanksEntity_HI_RO bankRO = bankListRO.get(0);
                        k.setBankId(bankRO.getBankId());  //将查出的银行的bankId赋值
                        jsonParamBaseBranches.put("bankId", bankRO.getBankId());
                    }
                    jsonParamBaseBranches.put("branchName", k.getBranchName());//根据分行名称和银行的主键Id查找出对应的分行的branchId
                    Pagination<SrmBaseBranchesEntity_HI_RO> branchesListPagination = iSrmBaseBranches.findSrmBaseBranchesInfoList(jsonParamBaseBranches, 1, 1000);
                    List<SrmBaseBranchesEntity_HI_RO> branchesList = branchesListPagination.getData();
                    if (null != branchesList && branchesList.size() > 0) {
                        SrmBaseBranchesEntity_HI_RO branchesRO = branchesList.get(0);
                        k.setBranchId(branchesRO.getBranchId()); //将查出的分行的branchId赋值
                        k.setBranchNumber(branchesRO.getBranchName()); //将查出的分行编码赋值
                    }
                    k.setSupplierId(supplierId);
                    k.setOperatorUserId(userId);
                    newSupplierBankList.add(k);
                }
            }
            srmPosSupplierBankDAO_HI.saveOrUpdateAll(newSupplierBankList);
        }
    }

    /**
     * 校验供应商的银行信息重复与必填项——供应商接口（数据输入）
     *
     * @param supplierBankList
     * @return
     */
    @Override
    public String judgeSpaceSupplierBank(List<SrmPosSupplierBankEntity_HI> supplierBankList) {
        String result = "";
        if (null == supplierBankList || supplierBankList.size() <= 0) {
            return result;
        }
        HashSet<Object> validBankAccountNumber = new HashSet<>();
        Integer index = 0;
        for (SrmPosSupplierBankEntity_HI k : supplierBankList) {
            index++;
            validBankAccountNumber.add(k.getBankAccountNumber());
            if (null == k.getSourceId() || "".equals(k.getSourceId())) {
                result += "请填写银行信息" + "第" + index + "行的数据来源Id——sourceId";
                return result;
            }
            if (null == k.getSourceCode() || "".equals(k.getSourceCode())) {
                result += "请填写银行信息" + "第" + index + "行的数据来源类型Code——sourceCode";
                return result;
            }
            if (null == k.getBankAccountNumber() || "".equals(k.getBankAccountNumber())) {
                result += "请填写银行信息" + "第" + index + "行的必填项——银行账号";
                return result;
            }
            if (null == k.getBankUserName() || "".equals(k.getBankUserName())) {
                result += "请填写银行信息" + "第" + index + "行的必填项——账号名称";
                return result;
            }
            if (null == k.getBankName() || "".equals(k.getBankName())) {
                result += "请填写银行信息" + "第" + index + "行的必填项——银行名称";
                return result;
            }
            if (null == k.getBranchName() || "".equals(k.getBranchName())) {
                result += "请填写银行信息" + "第" + index + "行的必填项——分行名称";
                return result;
            }
        }
        boolean flag = supplierBankList.size() != validBankAccountNumber.size() ? true : false;
        if (flag) {
            result += "银行信息的银行账号有重复，请删除重复的行！";
            return result;
        }
        return result;
    }

    /**
     * 保存供应商的银行信息
     *
     * @param paramDataList
     * @param userId
     * @param supplierId
     * @return
     */
    public JSONObject saveSupplierBankInfo(JSONArray paramDataList, int userId, int supplierId) throws Exception {
        JSONObject resultjson = new JSONObject();
        JSONObject paramData = null;
        for (int b = 0; b < paramDataList.size(); b++) {
            paramData = paramDataList.getJSONObject(b);
            SrmPosSupplierBankEntity_HI row = null;
            if (null == paramData.getInteger("bankAccountId")) {
                row = new SrmPosSupplierBankEntity_HI();
                row.setSupplierId(supplierId);
                row.setCreationDate(new Date());
                row.setCreatedBy(userId);
                row.setStartDate(new Date());
            } else {
                row = srmPosSupplierBankDAO_HI.getById(paramData.getInteger("bankAccountId"));
            }
            row.setBankAccountNumber(paramData.getString("bankAccountNumber"));
            row.setBankUserName(paramData.getString("bankUserName"));
//			row.setBankId(paramData.getIntValue("bankId"));
//			row.setBranchId(paramData.getIntValue("branchId"));
            row.setBankName(paramData.getString("bankName"));
            row.setBranchName(paramData.getString("branchName"));
            row.setBranchNumber(paramData.getString("branchNumber"));
            row.setBankCurrency(paramData.getString("bankCurrency"));
            if (null != SrmUtils.getString(paramData.getString("endDate"))) {
                row.setEndDate(paramData.getDate("endDate"));
            }
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            srmPosSupplierBankDAO_HI.saveEntity(row);
        }
        return resultjson;
    }

    /**
     * 查询供应商银行信息
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public Pagination<SrmPosSupplierBankEntity_HI_RO> findSupplierBankInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM srm_pos_supplier_bank t WHERE 1=1 ");
        SaafToolUtils.parperParam(jsonParams, "t.supplier_id", "supplier_id", sb, queryParamMap, "=");//如果是供应商查询
        SaafToolUtils.parperParam(jsonParams, "t.supplier_id", "supplierId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.source_code", "sourceCode", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.source_id", "sourceId", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPosSupplierBankEntity_HI_RO> result = srmPosSupplierBankDAO_HI_RO.findPagination
                (sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }
}

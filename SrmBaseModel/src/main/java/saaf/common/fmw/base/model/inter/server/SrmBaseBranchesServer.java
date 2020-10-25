package saaf.common.fmw.base.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SrmBaseBanksEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseBranchesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseBranchesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseBranches;
import saaf.common.fmw.common.utils.SaafToolUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：银行分行
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmBaseBranchesServer")
public class SrmBaseBranchesServer implements ISrmBaseBranches{
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseBranchesServer.class);
	private static final String Y = "Y";
	private static final String N = "N";
	@Autowired
	private ViewObject<SrmBaseBranchesEntity_HI> srmBaseBranchesDAO_HI;
	@Autowired
	private BaseViewObject<SrmBaseBranchesEntity_HI_RO> srmBaseBranchesDAO_HI_RO;

	@Autowired
	private ViewObject<SrmBaseBanksEntity_HI> srmBaseBanksDAO_HI;

	public SrmBaseBranchesServer() {
		super();
	}

	/**
	 * 分行查询
	 * @param jsonParams
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * ==============================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2019-05-29     秦晓钊          创建
	 * ==============================================================================
	 */
	@Override
	public Pagination<SrmBaseBranchesEntity_HI_RO> findSrmBaseBranchesInfoList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(SrmBaseBranchesEntity_HI_RO.QUERY_BRANCHES);
		SaafToolUtils.parperParam(jsonParams, "t.bank_id", "bankId", sb, queryParamMap, "like");
		SaafToolUtils.parperParam(jsonParams, "t.branch_id", "branchId", sb, queryParamMap, "like");
		SaafToolUtils.parperParam(jsonParams, "t.branch_code", "branchCode", sb, queryParamMap, "like");
		SaafToolUtils.parperParam(jsonParams, "t.branch_name", "branchName", sb, queryParamMap, "like");
		SaafToolUtils.parperParam(jsonParams, "t.branch_address", "branchAddress", sb, queryParamMap, "like");
		String countSql = "select count(1) from (" + sb + ")";
		Pagination<SrmBaseBranchesEntity_HI_RO> result = srmBaseBranchesDAO_HI_RO.findPagination(sb.toString(),countSql,queryParamMap,pageIndex,pageRows);
		return result;
	}

	/**
	 * Description：银行分行定时任务接口list
	 * @param businessData
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-11-11     zhj             创建
	 * ==============================================================================
	 */
	@Override
	public Integer updateSrmBaseBrancheListJob(JSONArray businessData) {
		Integer count = 0;
		if(null != businessData && businessData.size()>0){
			for(int i=0;i<businessData.size();i++){
                JSONObject obj = businessData.getJSONObject(i);
                SrmBaseBranchesEntity_HI row = new SrmBaseBranchesEntity_HI();
                List<SrmBaseBanksEntity_HI> bankList = new ArrayList<>();
                List<SrmBaseBranchesEntity_HI> list = new ArrayList<>();;
                try{
                String bankId = obj.getString("BANK_ID");
                bankList = srmBaseBanksDAO_HI.findByProperty("sourceId",bankId);
				if(null == bankList || bankList.isEmpty()){
					LOGGER.error("查无此银行信息BANK_ID:{}，参数：{}",bankId,obj.toJSONString());
					continue;
				}
                String branchId = obj.getString("BRANCH_ID");
                String branchCode = obj.getString("BRANCH_NUMBER");
                String branchName = obj.getString("BANK_BRANCH_NAME");
                String branchAddress = obj.getString("BRANCH_ADDRESS");
                Date startDate = null;
                Date endDate = null;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (!ObjectUtils.isEmpty(obj.getString("START_DATE"))) {
                    startDate = sdf.parse(obj.getString("START_DATE"));
                }
                if (!ObjectUtils.isEmpty(obj.getString("END_DATE"))) {
                    endDate = sdf.parse(obj.getString("END_DATE"));
                }

					if(null == branchId || "".equals(branchId) || null == branchName || "".equals(branchName)){
						continue;
					}
					list = srmBaseBranchesDAO_HI.findByProperty("sourceId",branchId);
					if(null == list || list.isEmpty()){
						//insert
						row = new SrmBaseBranchesEntity_HI();
						//采用EBS过来的主键ID
						row.setBranchId(Integer.parseInt(branchId));
						row.setSourceId(branchId);
					}else{
						//update
						row = list.get(0);
					}
					row.setSourceCode("EBS");
					row.setBankId(bankList.get(0).getBankId());
					row.setBranchCode(branchCode);
					row.setBranchName(branchName);
					row.setBranchAddress(branchAddress);
					if(null != startDate && startDate.getTime()<=System.currentTimeMillis() && (null == endDate || endDate.getTime()>System.currentTimeMillis())){
						row.setEnabledFlag(Y);
					}else{
						row.setEnabledFlag(N);
					}
					row.setOperatorUserId(-1);
					srmBaseBranchesDAO_HI.saveOrUpdate(row);
                    srmBaseBranchesDAO_HI.fluch();
					count++;
				}catch (Exception e){
					//LOGGER.error("未知错误:{}", e)();
					LOGGER.error("--------------------------->保存分行信息操作失败！参数：{}" , obj.toJSONString() + "，异常：{}" , e.getMessage());
					continue;
				}
			}
		}
		return count;
	}
}

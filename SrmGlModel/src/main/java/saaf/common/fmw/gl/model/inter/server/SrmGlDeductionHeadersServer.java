package saaf.common.fmw.gl.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.gl.model.entities.SrmGlDeductionHeadersEntity_HI;
import saaf.common.fmw.gl.model.dao.SrmGlDeductionHeadersDAO_HI;
import saaf.common.fmw.gl.model.entities.readonly.SrmGlDeductionHeadersEntity_HI_RO;
import saaf.common.fmw.gl.model.inter.ISrmGlDeductionHeaders;

@Component("srmGlDeductionHeadersServer")
public class SrmGlDeductionHeadersServer implements ISrmGlDeductionHeaders{

	private static final Logger LOGGER = LoggerFactory.getLogger(SrmGlDeductionHeadersServer.class);

	public SrmGlDeductionHeadersServer() {
		super();
	}

	@Autowired
	private ViewObject<SrmGlDeductionHeadersEntity_HI> srmGlDeductionHeadersDAO_HI;

	@Autowired
	private BaseViewObject<SrmGlDeductionHeadersEntity_HI_RO> srmGlDeductionHeadersDAO_HI_RO;


	/**
	 * 查询扣款单类表
	 * @param parameters
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * @throws Exception
	 */
	@Override
	public Pagination<SrmGlDeductionHeadersEntity_HI_RO> findDeductionInfoList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
		try {
			StringBuffer queryString = new StringBuffer(SrmGlDeductionHeadersEntity_HI_RO.QUERY_DEDUCTION_SQL);
			Map<String, Object> map = new HashMap<String, Object>();
			// 查询过滤条件
			SaafToolUtils.parperParam(parameters, "dh.deduction_number", "deductionNumber", queryString, map, "=");
			SaafToolUtils.parperParam(parameters, "dh.deduction_status", "deductionStatus", queryString, map, "=");
			SaafToolUtils.parperParam(parameters, "su.user_full_name", "userFullName", queryString, map, "like");
			SaafToolUtils.parperParam(parameters, "dh.transmit_status", "transmitStatus", queryString, map, "=");
			SaafToolUtils.parperParam(parameters, "si.supplier_number ", "supplierNumber", queryString, map, "=");
			SaafToolUtils.parperParam(parameters, "si.supplier_name ", "supplierName", queryString, map, "like");
			SaafToolUtils.parperParam(parameters, "si.supplier_id ", "supplierId", queryString, map, "=");
			// 排序
			queryString.append(" ORDER BY dh.creation_date DESC");
			Pagination<SrmGlDeductionHeadersEntity_HI_RO> deductionList = srmGlDeductionHeadersDAO_HI_RO.findPagination(queryString, map, pageIndex, pageRows);
			return deductionList;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 查询扣款单号
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * @throws Exception
	 */
	@Override
	public Pagination<SrmGlDeductionHeadersEntity_HI_RO> findDeductionNumber(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
		try {
			StringBuffer sqlBuffer = new StringBuffer(SrmGlDeductionHeadersEntity_HI_RO.QUERY_DEDUCTION_NUMBER_SQL);
			Map<String, Object> map = new HashMap<String, Object>();
			SaafToolUtils.parperParam(params, " sgdh.deduction_number", "deductionNumber", sqlBuffer, map, "like");
			SaafToolUtils.parperParam(params, " sgdh.supplier_id", "supplierId", sqlBuffer, map, "=");
			Pagination<SrmGlDeductionHeadersEntity_HI_RO> list = srmGlDeductionHeadersDAO_HI_RO.findPagination(sqlBuffer, map, pageIndex, pageRows);
			return list;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new Exception("查询失败");
		}
	}

	/**
	 * 查询创建人
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * @throws Exception
	 */
	@Override
	public Pagination<SrmGlDeductionHeadersEntity_HI_RO> findDeductionCreatedByUser(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
		try {
			StringBuffer sqlBuffer = new StringBuffer(SrmGlDeductionHeadersEntity_HI_RO.QUERY_DEDUCTION_CREATED_BY_SQL);
			Map<String, Object> map = new HashMap<String, Object>();
			SaafToolUtils.parperParam(params, " sgdh.created_by", "createdBy", sqlBuffer, map, "like");
			Pagination<SrmGlDeductionHeadersEntity_HI_RO> list = srmGlDeductionHeadersDAO_HI_RO.findPagination(sqlBuffer, map, pageIndex, pageRows);
			return list;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new Exception("查询失败");
		}
	}
}


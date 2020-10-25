package saaf.common.fmw.cua.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.Map;
import saaf.common.fmw.cua.model.entities.readonly.SrmCuaInvoiceLinesEntity_HI_RO;
/**
 * @author zhangxuan
 * @date 2018/11/9
 */
public interface ISrmCuaInvoiceLines {
	/**
	 * 查询发票中可以添加的对账单列表
	 * @param jsonParams
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 */
	Pagination<SrmCuaInvoiceLinesEntity_HI_RO> findValidAccountLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows);
	
	/**
	 * 查询发票单行信息
	 * @param parameters
	 * @param pageIndex
	 * @param pagerows
	 * @return
	 * @throws Exception
	 */
	Pagination<SrmCuaInvoiceLinesEntity_HI_RO> findInvoiceLineList(JSONObject parameters, Integer pageIndex,Integer pagerows)throws Exception;

	/**
	 * 保存对账单行信息，返回对账金额数据
	 * @param parameters
	 * @param line
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> saveInvoiceLine(JSONObject parameters, String line) throws Exception;
	
	/**
	 * 计算金额
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> saveInvoiceLineAmount(JSONObject parameters) throws Exception;
	
	/**
	 * 删除发票单行信息
	 * @param jsonParams
	 * @return
	 * @throws Exception
	 */
	JSONObject deleteInvoiceLine(JSONObject jsonParams)throws Exception;
}

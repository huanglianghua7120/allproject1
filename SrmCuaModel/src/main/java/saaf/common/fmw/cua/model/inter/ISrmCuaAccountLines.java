package saaf.common.fmw.cua.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.Map;
import saaf.common.fmw.cua.model.entities.readonly.SrmCuaAccountLinesEntity_HI_RO;
import saaf.common.fmw.cua.model.entities.readonly.SrmErpTransactionsVEntity_HI_RO;
/**
 * @author zhangxuan
 * @date 2018/10/30
 */
public interface ISrmCuaAccountLines {

	/**
	 * 查询视图中的对账单据信息
	 * @param parameters
	 * @param pageIndex
	 * @param pagerows
	 * @return
	 * @throws Exception
	 */
	Pagination<SrmErpTransactionsVEntity_HI_RO> findViewList(JSONObject parameters, Integer pageIndex,Integer pagerows)throws Exception;

	/**
	 * 查询对账单行信息
	 * @param parameters
	 * @param pageIndex
	 * @param pagerows
	 * @return
	 * @throws Exception
	 */
	Pagination<SrmCuaAccountLinesEntity_HI_RO> findAccountLineList(JSONObject parameters, Integer pageIndex,Integer pagerows)throws Exception;
	
	/**
	 * 导出对账单行信息
	 * @param parameters
	 * @param pageIndex
	 * @param pagerows
	 * @return
	 * @throws Exception
	 */
	Pagination<SrmCuaAccountLinesEntity_HI_RO> findAccountLineForExcel(JSONObject parameters, Integer pageIndex,Integer pagerows)throws Exception;
	
	/**
	 * 保存所有对账单行信息
	 * @param parameters
	 * @throws Exception
	 */
	void saveAllAccountLine(JSONObject parameters)throws Exception;
	
	/**
	 * 删除对账单行信息
	 * @param jsonParams
	 * @return
	 * @throws Exception
	 */
	JSONObject deleteAccountLine(JSONObject jsonParams)throws Exception;
	
	/**
	 * 保存对账单行信息，返回对账金额数据
	 * @param parameters
	 * @param line
	 * @return
	 * @throws Exception
	 */
	boolean saveAccountLine(JSONObject parameters, String line) throws Exception;
	
	/**
	 * 计算金额
	 * @param parameters
	 * @param line
	 * @param isAll
	 * @param isSave
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> saveAccountLineAmount(JSONObject parameters, String line, Boolean isAll, Boolean isSave) throws Exception;


}

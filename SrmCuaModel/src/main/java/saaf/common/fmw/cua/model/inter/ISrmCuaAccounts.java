package saaf.common.fmw.cua.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.model.entities.readonly.SaafInstitutionEntity_HI_RO;
import saaf.common.fmw.cua.model.entities.SrmCuaAccountsEntity_HI;
import saaf.common.fmw.cua.model.entities.readonly.SrmCuaAccountsEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierInfoEntity_HI_RO;

import java.util.Map;
/**
 * @author zhangxuan
 * @date 2018/10/30
 */
public interface ISrmCuaAccounts {
	
	/**
	 * 查询对账单列表
	 * @param parameters
	 * @param pageIndex
	 * @param pagerows
	 * @return
	 * @throws Exception
	 */
	Pagination<SrmCuaAccountsEntity_HI_RO> findAccountList(JSONObject parameters, Integer pageIndex,Integer pagerows)throws Exception;
	/**
	 * 查询当前用户所负责的业务实体lov
	 * @param queryParamJSON
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * @throws Exception
	 */
	Pagination<SaafInstitutionEntity_HI_RO> findAccountInstitutionLov(JSONObject queryParamJSON, Integer pageIndex,
															   Integer pageRows) throws Exception;
	
	/**
	 * 删除对账单
	 * @param jsonParams
	 * @return
	 * @throws Exception
	 */
	JSONObject deleteAccount(JSONObject jsonParams)throws Exception;
	/**
	 * 查询对账单详细信息
	 * @param jsonParams
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> findAccountInfo(JSONObject jsonParams)throws Exception;  
	/**
	 * 保存对账单
	 * @param jsonParams
	 * @param line
	 * @return
	 * @throws Exception
	 */
	SrmCuaAccountsEntity_HI saveAccount(JSONObject jsonParams, String line)throws Exception;
	/**
	 * 更新对账单状态
	 * @param jsonParams
	 * @param line
	 * @return
	 * @throws Exception
	 */
	JSONObject updateAccountStatus(JSONObject jsonParams, String line) throws Exception ;
	/**
	 * 查询对账单状态
	 * @param jsonParams
	 * @return
	 * @throws Exception
	 */
	JSONObject findAccountStatus(JSONObject jsonParams) throws Exception ;

	/**
	 * 查询对账单接收退货报表
	 *
	 * @param jsonParam    查询过滤条件参数
	 * @param pageIndex 页码索引
	 * @param pageRows  每页显示记录数量
	 * @return 返回分页查询的对账单信息的json字符串
	 */
    Pagination<SrmCuaAccountsEntity_HI_RO> findAccountReport(JSONObject jsonParam, Integer pageIndex, Integer pageRows) throws Exception ;

	/**
	 * 查询没有退出冻结的供应商
	 *
	 * @param jsonParams 查询过滤条件
	 * @param pageIndex  页码索引
	 * @param pageRows   每页记录数量
	 * @return 返回查询到的供应商
	 */
	Pagination<SrmCuaAccountsEntity_HI_RO> findValidSupplierLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows);

	/**
	 *  查询供应商lov
	 * @param queryParamJSON
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * @throws Exception
	 */
	Pagination<SrmPosSupplierInfoEntity_HI_RO> findSupplierLov(JSONObject queryParamJSON, Integer pageIndex,
															   Integer pageRows) throws Exception;
}

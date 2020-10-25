package saaf.common.fmw.cua.model.inter;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.model.entities.readonly.SrmBaseBanksEntity_HI_RO;
import saaf.common.fmw.cua.model.entities.SrmCuaInvoicesEntity_HI;
import saaf.common.fmw.cua.model.entities.readonly.SrmCuaInvoicesEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierBankEntity_HI_RO;
/**
 * @author zhangxuan
 * @date 2018/11/9
 */
public interface ISrmCuaInvoices {
	/**
	 * 查询发票列表
	 * @param parameters
	 * @param pageIndex
	 * @param pagerows
	 * @return
	 * @throws Exception
	 */
	Pagination<SrmCuaInvoicesEntity_HI_RO> findInvoiceList(JSONObject parameters, Integer pageIndex,Integer pagerows)throws Exception;
	
	/**
	 * 删除发票
	 * @param jsonParams
	 * @return
	 * @throws Exception
	 */
	JSONObject deleteInvoice(JSONObject jsonParams)throws Exception;
	
    /**
     * 通过id查询发票
     *
     * @param invoiceId 发票的id
     * @return 返回查询到的发票对象
     */
	SrmCuaInvoicesEntity_HI_RO findInvoiceById(JSONObject jsonParams);
	
	/**
	 * 保存发票
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	SrmCuaInvoicesEntity_HI saveInvoice(JSONObject parameters) throws Exception;
	
    /**
     * 银行查询
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public List<SrmPosSupplierBankEntity_HI_RO> findSupplierBankInfoToInvoice(JSONObject jsonParams);


}

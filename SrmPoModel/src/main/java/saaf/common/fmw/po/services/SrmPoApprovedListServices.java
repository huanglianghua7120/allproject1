package saaf.common.fmw.po.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.po.model.entities.readonly.SrmPoApprovedListEntity_HI_RO;
import saaf.common.fmw.po.model.inter.IApprovedList;
import saaf.common.fmw.services.CommonAbstractServices;
/**
 * Project Name：SrmPoApprovedListServices
 * Company Name：SIE
 * Program Name：
 * Description：供应商批准列表
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Path("/srmPoApprovedListServices")
@Component
public class SrmPoApprovedListServices extends CommonAbstractServices{
	
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoApprovedListServices.class);
	
	@Autowired
	private IApprovedList srmPoApprovedListServer;
	
	public SrmPoApprovedListServices(){
		super();
	}

    /**
     * Description：查询批准供应商列表
     * @param params 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("findApprovedList")
    @POST
    public String findApprovedList(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            System.out.println("==========================================>>>>>>");
            LOGGER.info("------jsonParam------" + jsonParam.toString());
            Pagination<SrmPoApprovedListEntity_HI_RO> infoList = srmPoApprovedListServer.findApprovedList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        }catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：保存供应商批准信息
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * certifyProcess  认证合格工序  VARCHAR2  N
     * militaryProductsFlag  用于军品(Y/N)  VARCHAR2  N
     * carProductFlag  用于汽车产品(Y/N)  VARCHAR2  N
     * listId  列表ID  NUMBER  Y
     * supplierId  供应商ID  NUMBER  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  Y
     * itemId  物料ID  NUMBER  N
     * listStatus  状态,快码:ISP_ASL_STATUS  VARCHAR2  N
     * disabledFlag  禁用标识(Y/N)  VARCHAR2  N
     * dayCapacity  （废弃）供应商日产能（日供货量）  NUMBER  N
     * listNumber  （废弃）序号  NUMBER  N
     * passU9Flag  （废弃）推U9标识(N:未推送,S:已推送,U:已推送后修改)  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * introductionModelId  供应商引入头层-型号ID，关联表srm_pos_introduction_model  NUMBER  N
     * resourceModelId  资源开发-型号信息ID,关联表srm_pos_resource_model  NUMBER  N
     * monopolyFlag  是否垄断型供应商(Y/N)  VARCHAR2  N
     * introductionHeaderId  供应商引入头层ID,关联表srm_pos_introduction_header  NUMBER  N
     * introductionSiteId  供应商引入头层-地点ID  NUMBER  N
     * requestId  报文请求ID  VARCHAR2  N
     * supplierSiteId  供应商档案地点Id，关联表srm_pos_supplier_sites  NUMBER  N
     * modelId  型号库ID,关联表srm_base_model  NUMBER  N
     * listId  列表ID  NUMBER  Y
     * supplierId  供应商ID  NUMBER  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  Y
     * itemId  物料ID  NUMBER  N
     * listStatus  状态  VARCHAR2  N
     * disabledFlag  禁用标识(Y/N)  VARCHAR2  N
     * dayCapacity  （废弃）供应商日产能（日供货量）  NUMBER  N
     * listNumber  （废弃）序号  NUMBER  N
     * passU9Flag  （废弃）推U9标识(N:未推送,S:已推送,U:已推送后修改)  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */

    @Path("saveApprovedInfo")
    @POST
    public String saveApprovedInfo(@FormParam("params")
        String params) {
    	LOGGER.info("保存信息:"+params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPoApprovedListServer.saveApprovedInfo(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：导入货源列表
     * @param params 导入参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("saveApprovedList")
    @POST
    public String saveApprovedList(@FormParam("params")
        String params) {
    	LOGGER.info("导入信息:"+params);
        try {
        	JSONObject jsonParam = this.parseObject(params);
            JSONObject object = srmPoApprovedListServer.saveApprovedList(jsonParam);
            return object.toJSONString();
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        }  catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
}

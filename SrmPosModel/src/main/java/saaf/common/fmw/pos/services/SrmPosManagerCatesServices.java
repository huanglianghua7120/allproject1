package saaf.common.fmw.pos.services;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosManagerCatesEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosManagerCatesLinesEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosManagerCatesOthersEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmManagerCates;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.services.CommonAbstractServices;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：需求品类
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     zhj             创建
 * ===========================================================================
 */
@Component("srmPosManagerCatesServices")
@Path("/srmPosManagerCatesServlet")
public class SrmPosManagerCatesServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosManagerCatesServices.class);
 

    public SrmPosManagerCatesServices() {
        super();
    }


    @Autowired
    private ISrmManagerCates srmManagerCates;


    /**   
     * 查询供应商列表
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findManagerCatesList")
    @POST
    public String findManagerCatesList(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
    	LOGGER.info("---->"+params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmPosManagerCatesEntity_HI_RO> supplierList = srmManagerCates.findManagerCatesList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(supplierList);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (Exception e) {
            //LOGGER.error("未知错误:{}", e);
            LOGGER.error("查询合作品类信息异常：" + e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询合作品类信息异常!" + e, 0, null);
        }
    }
    
    
    /**   
     * 根据id删除品类
     * @param params

     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("deleteMangereCates")
    @POST
    public String deleteManagerCates(@FormParam("params")String params){
    	LOGGER.info("删除样品试验信息，参数：" + params.toString());
    	try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmManagerCates.deleteManagerCates(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "删除品类失败!", 0, null);
        }
    }

    /**
     * Description：品类增加修改
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * managerCateId  新增品类单ID  NUMBER  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * orgId  业务实体ID,关联表:srm_base_orgs(废弃)  NUMBER  N
     * documentType  单据类型(POS_MANAGER_CATE_TYPE)  VARCHAR2  Y
     * documentNumber  新增品类单号  VARCHAR2  Y
     * documentStatus  新增品类单状态（POS_QUALIFICATION_STATUS）  VARCHAR2  N
     * needOnsiteInspection  是否现场考察  VARCHAR2  N
     * supplierAdvantage  供方优势  VARCHAR2  N
     * description  说明  VARCHAR2  N
     * reason  原因  VARCHAR2  N
     * fileId  附件id  NUMBER  N
     * managerCateId  新增品类单ID  NUMBER  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * orgId  业务实体ID,关联表:srm_base_orgs(废弃)  NUMBER  N
     * documentType  单据类型(POS_MANAGER_CATE_TYPE)  VARCHAR2  Y
     * documentNumber  新增品类单号  VARCHAR2  Y
     * documentStatus  新增品类单状态（POS_QUALIFICATION_STATUS）  VARCHAR2  N
     * needOnsiteInspection  是否现场考察  VARCHAR2  N
     * supplierAdvantage  供方优势  VARCHAR2  N
     * description  说明  VARCHAR2  N
     * reason  原因  VARCHAR2  N
     * fileId  附件id  NUMBER  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    @Path("saveManagerCates")
    @POST
    public String saveManagerCates(@FormParam("params")String params){
    	//LOGGER.info("删除样品试验信息，参数：" + params.toString());
    	try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmManagerCates.saveManaerCates(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "保存品类失败!", 0, null);
        }
    }
    /**
     * 品类提交
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("subimtManagerCates")
    @POST
    public String subimtManagerCates(@FormParam("params")String params){
    	try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmManagerCates.updateManaerCates(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "提交品类失败!", 0, null);
        }
    }
    
    /**
     * 审批
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("actManagerCates")
    @POST
    public String actManagerCates(@FormParam("params")String params){
    	try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmManagerCates.updateActManagerCates(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "审批失败!", 0, null);
        }
    }
    /**
     * 驳回
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("inActManagerCates")
    @POST
    public String inActManagerCates(@FormParam("params")String params){
    	try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmManagerCates.updateInActManagerCates(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "驳回失败!", 0, null);
        }
    }
    
    /**
     * 查询品类需求
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findManagerCatesLinesList")
    @POST
	public String findManagerCatesLinesList(@FormParam("params") String params,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageRows") Integer pageRows) {
    	LOGGER.info("findManagerCatesLinesList 1 params"+params);
    	 try {
             JSONObject jsonParam = this.parseObject(params);
             Pagination<SrmPosManagerCatesLinesEntity_HI_RO> linesList = srmManagerCates.findManagerCatesLinesList(jsonParam, pageIndex, pageRows);
             return JSON.toJSONString(linesList);
         } catch (NotLoginException e) {
             return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
         }catch (Exception e) {
             //LOGGER.error("未知错误:{}", e);
             LOGGER.error("查询合作品类需求信息异常：" + e);
             if (e instanceof UtilsException) {
                 LOGGER.error("服务异常:" + e);
             }
             return CommonAbstractServices.convertResultJSONObj("E", "查询合作品类需求信息异常!" + e, 0, null);
         }
	}
    
    
    
    /**
     * 查询品类需求
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findManagerCatesOhtersList")
    @POST
	public String findManagerCatesOhtersList(@FormParam("params") String params,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageRows") Integer pageRows) {
    	LOGGER.info("findManagerCatesOhtersList 1 params"+params);
    	 try {
             JSONObject jsonParam = this.parseObject(params);
             Pagination<SrmPosManagerCatesOthersEntity_HI_RO> linesList = srmManagerCates.findManagerCatesOhersList(jsonParam, pageIndex, pageRows);
             return JSON.toJSONString(linesList);
         } catch (NotLoginException e) {
             return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
         }catch (Exception e) {
             //LOGGER.error("未知错误:{}", e);
             LOGGER.error("查询合作品类需求信息异常：" + e);
             if (e instanceof UtilsException) {
                 LOGGER.error("服务异常:" + e);
             }
             return CommonAbstractServices.convertResultJSONObj("E", "查询合作品类需求信息异常!" + e, 0, null);
         }
	}
}

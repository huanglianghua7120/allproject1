package saaf.common.fmw.spm.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.hibernate.core.paging.Pagination;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmIndicatorItemsEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceTplEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmPerformanceTpl;

@Component("srmSpmPerformanceTplService")
@Path("/srmSpmPerformanceTplService")
public class SrmSpmPerformanceTplService extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmPerformanceTplService.class);
    private static final String PARAMS = "params";
    private static final String PAGE_INDEX = "pageIndex";
    private static final String PAGE_ROWS = "pageRows";
    @Autowired
    private ISrmSpmPerformanceTpl srmSpmPerformanceTplServer;

    public SrmSpmPerformanceTplService() {
        super();
    }

    /**
     * Description：查询绩效模板
     * @param params 绩效模板查询参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("queryPerformanceTplDetail")
    public String queryPerformanceTplDetail(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = parseObject(params);
            Pagination<SrmSpmPerformanceTplEntity_HI_RO> page = srmSpmPerformanceTplServer.queryPerformanceTplDetail(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(page);
        } catch (UtilsException e){
            LOGGER.error("查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e.getMessage(), 0, null));
        }
    }
    /**
     * Description：删除绩效模板
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */

    @Path("deletePerformanceTpl")
    @POST
    public String deletePerformanceTpl(@FormParam("params") String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmSpmPerformanceTplServer.deletePerformanceTpl(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        }catch (UtilsException e){
            LOGGER.error("删除失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("删除失败！参数：" + params + ",异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败!" + e, 0, null);
        }
    }
    /**
     * Description：查询绩效模板
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("finderformancetpl")
    @POST
    public String finderformancetpl(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            Integer tplId = jsonParam.getInteger("tplId");
            if (tplId != null) {
                Map<String, Object> frozenInfo = srmSpmPerformanceTplServer.finderformancetpl(tplId);
                jsonParam.put("frozenInfo", frozenInfo);
                jsonParam.put("status", "S");
                jsonParam.put("msg", "查询成功");
                return jsonParam.toJSONString();
            } else {
                return convertResultJSONObj("E", "请检查tplId参数", 0, null);
            }
        }catch (UtilsException e){
            LOGGER.error("查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询失败！", e);
            return convertResultJSONObj("E", "查询失败!" + e, 0, null);
        }
    }

    /**
     * Description：保存绩效模板
     * @param params 绩效模板数据
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("saveformancetpl")
    @POST
    public String saveformancetpl(@FormParam("params") String params) {
        try {
            LOGGER.info(params);
            JSONObject jsonParams = this.parseObject(params);
            //去掉查重逻辑
            /*boolean flag = srmSpmPerformanceTplServer.countformancetpl(jsonParams);
            if (flag) {
                JSONObject jsondata = srmSpmPerformanceTplServer.saveformancetpl(jsonParams);
                return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
            } else {
                return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "此组织指标模板已经存在，无需再次添加!", 0, null));
            }*/
            JSONObject jsondata = srmSpmPerformanceTplServer.saveformancetpl(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        }catch (UtilsException e){
            LOGGER.error("保存失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "保存失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "保存失败!" + e, 0, null));
        }
    }
    /**
     * Description：删除绩效模板行
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("deleteTplLine")
    @POST
    public String deleteTplLine(@FormParam("params") String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmSpmPerformanceTplServer.deleteTplLine(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        }catch (UtilsException e){
            LOGGER.error("删除失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("删除失败！参数：" + params + ",异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败!" + e, 0, null);
        }
    }


    /**
     * Description：保存绩效模板
     * @param params 绩效模板数据
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("approveformancetpl")
    @POST
    public String approveformancetpl(@FormParam("params") String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            //去掉查重校验
/*            boolean flag = srmSpmPerformanceTplServer.countformancetpl(jsonParams);
            if (flag) {
                JSONObject jsondata = srmSpmPerformanceTplServer.updateApproveformancetpl(jsonParams);
                return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
            } else {
                return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "此组织模板已经存在生效，无需再次创建!", 0, null));
            }*/
            JSONObject jsondata = srmSpmPerformanceTplServer.updateApproveformancetpl(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));

        }catch (UtilsException e){
            LOGGER.error("保存失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "保存失败:" + e.getMessage(), 0, null);
        }  catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "保存失败!" + e, 0, null));
        }
    }
    /**
     * Description：更新状态
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("rejecteformancetpl")
    @POST
    public String rejecteformancetpl(@FormParam("params") String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            boolean flag = srmSpmPerformanceTplServer.countCateScheme(jsonParams);
            if (flag) {
                JSONObject jsondata = srmSpmPerformanceTplServer.updateRejecteformancetpl(jsonParams);
                return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
            } else {
                return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "绩效方案已经引用此模板，不能失效!", 0, null));
            }
        }catch (UtilsException e){
            LOGGER.error("保存失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "保存失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "保存失败!" + e, 0, null));
        }
    }
    /**
     * Description：绩效模板导出
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("performanceTplListExport")
    public String performanceTplListExport(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = JSON.parseObject(params);
            List<SrmSpmPerformanceTplEntity_HI_RO> list = srmSpmPerformanceTplServer.performanceTplListExport(paramJSON);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功", list.size(), list);
        }catch (UtilsException e){
            LOGGER.error("查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
        }
    }
    /**
     * Description：保存
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("copyPerformanceTpl")
    @POST
    public String copyPerformanceTpl(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            Integer tplId = jsonParam.getInteger("tplId");
            if (tplId != null) {
                Map<String, Object> frozenInfo = srmSpmPerformanceTplServer.saveCopyPerformanceTpl(tplId);
                jsonParam.put("frozenInfo", frozenInfo);
                jsonParam.put("status", "S");
                jsonParam.put("msg", "复制成功");
                return jsonParam.toJSONString();
				/*boolean flag =srmSpmPerformanceTplServer.countCateScheme(jsonParam);
				if(flag) {
					 Map<String,Object> frozenInfo =srmSpmPerformanceTplServer.saveCopyPerformanceTpl(tplId);
					   jsonParam.put("frozenInfo", frozenInfo);
				       jsonParam.put("status", "S");
				       jsonParam.put("msg", "复制成功");
				       return jsonParam.toJSONString();
				}else {
					return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "此模板已被引用，请先作废相应的绩效方案!", 0, null));
				}*/

            } else {
                return convertResultJSONObj("E", "请检查tplId参数", 0, null);
            }
        } catch (UtilsException e){
            LOGGER.error("保存失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "保存失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
            LOGGER.error("保存失败！", e);
            return convertResultJSONObj("E", "保存失败!" + e, 0, null);
        }
    }
    /**
     * Description：查询绩效模板
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("queryItemTplList")
    public String queryItemTplList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = JSON.parseObject(params);
            if (paramJSON.isEmpty()) {
                paramJSON.put("indicatorId", 0);
                paramJSON.put("indicatorItemIdt", "T");
                pageIndex = 1;
                pageRows = 1;
            }
            Pagination<SrmSpmIndicatorItemsEntity_HI_RO> page = srmSpmPerformanceTplServer.queryItemTplList(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(page);
        } catch (Exception e) {
            //e.printStackTrace();
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
        }
    }


}

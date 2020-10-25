package saaf.common.fmw.base.services;


import com.alibaba.fastjson.JSONObject;

import com.yhg.base.utils.SToolUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafAreasEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafAreas;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.services.CommonAbstractServices;


@Component("saafAreasService")
@Path("/saafAreas")
public class SaafAreasService extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaafAreasService.class);
    @Autowired
    private ISaafAreas saafAreasServer;

    @POST
    @Path("find")
    @Produces(MediaType.APPLICATION_JSON)
    public String find(@FormParam("params")
        String params, @FormParam("pageIndex")
        int pageIndex, @FormParam("pageRows")
        int pageRows) {
        LOGGER.info("params:" + params);
        try {
            //获取用户信息
            JSONObject parameters = this.parseObject(params);
            return saafAreasServer.findData(parameters, pageIndex, pageRows);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询区域失败:" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }
    }

    @POST
    @Path("edit")
    @Produces(MediaType.APPLICATION_JSON)
    public String edit(@FormParam("params")
        String params) {
        LOGGER.info("params:" + params);
        try {
            //获取用户信息
            JSONObject parameters = this.parseObject(params);
            int userId = null == this.getSessionUserId() ? -9999 : this.getSessionUserId();
            if (saafAreasServer.save(parameters, userId)) {
                return SToolUtils.convertResultJSONObj("S", "保存成功!", 0, null).toString();
            } else {
                return SToolUtils.convertResultJSONObj("F", "保存失败!", 0, null).toString();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("编辑区域失败:" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }
    }

    @POST
    @Path("del")
    @Produces(MediaType.APPLICATION_JSON)
    public String del(@FormParam("params")
        String params) {
        LOGGER.info("params:" + params);
        try {
            //获取用户信息
            JSONObject parameters = this.parseObject(params);
            String status = "F";
            String msg = "";
            Object areaId = parameters.get("areaId");

            if (null != areaId && !"".equals(areaId.toString().trim())) {
                try {
                    boolean result = saafAreasServer.delete(parameters.getIntValue("areaId"));
                    if (result) {
                        return SToolUtils.convertResultJSONObj("S", "删除区域成功!", 0, null).toString();
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                    LOGGER.error("", e);
                    return SToolUtils.convertResultJSONObj("F", "删除区域失败!", 0, null).toString();
                }
            } else {
                return SToolUtils.convertResultJSONObj("F", "未获取到ID!", 0, null).toString();
            }
            return SToolUtils.convertResultJSONObj(status, msg, 0, null).toString();
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("删除区域失败:", e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }
    }

    //    /**
    //     * 影院分配
    //     * @return
    //     */
    //    @POST
    //    @Path("dist")
    //    @Produces(MediaType.APPLICATION_JSON)
    //    public String dist() {
    //        try {
    //            this.parseObject("");
    //            JSONObject returnJosn = new JSONObject();
    //            List<SaafAreasEntity_HI_RO> list = findList(0);
    //            for (SaafAreasEntity_HI_RO ase : list) {
    //                returnJosn.put(ase.getAreaName() + "," + ase.getAreaId(), get(ase.getAreaId()));
    //            }
    //            return returnJosn.toString();
    //        } catch (NotLoginException e) {
    //            //e.printStackTrace();
    //            LOGGER.error("删除区域失败:" + e);
    //            return convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
    //        }
    //    }

    /**
     * 层级查询
     * @param parentId
     * @return
     */
    private List<SaafAreasEntity_HI_RO> findList(long parentId) {
        StringBuffer where = new StringBuffer(SaafAreasEntity_HI_RO.QUERY_SQL);
        where.append(" and sa.area_parent_id = :areaParentId ");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("areaParentId", parentId);
        return saafAreasServer.findList(where.toString(), map);
    }

    //    /**
    //     * 获取子节点
    //     * @return
    //     */
    //    private JSONArray get(int areaId) {
    //        JSONArray arr = new JSONArray();
    //        JSONObject json = new JSONObject();
    //        List<SaafAreasEntity_HI_RO> list = findList(areaId);
    //        if (list.size() > 0) {
    //            for (SaafAreasEntity_HI_RO ase : list) {
    //                json.put(ase.getAreaName() + "," + ase.getAreaId(), get(ase.getAreaId()));
    //            }
    //        } else {
    //            List<SaafAreasEntity_HI_RO> listCinema = getCinema(areaId);
    //            for (SaafAreasEntity_HI_RO ase : listCinema) {
    //                json.put(ase.getCinemaName() + "," + ase.getCinemaId(), getScreen(ase.getCinemaId()));
    //            }
    //        }
    //        arr.add(json);
    //        return arr;
    //    }


    /**
     * 获取城市（LOV）
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @POST
    @Path("queryCity")
    @Produces(MediaType.APPLICATION_JSON)
    public String queryCity(@FormParam("params")
        String params, @FormParam("pageIndex")
        int pageIndex, @FormParam("pageRows")
        int pageRows) {
        LOGGER.info("params:" + params);
        try {
            //此处使用到的json包不一致
            JSONObject parameters = this.parseObject(params);
            return saafAreasServer.findCity(parameters, pageIndex, pageRows);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("获取城市LOV失败:" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }
    }
}

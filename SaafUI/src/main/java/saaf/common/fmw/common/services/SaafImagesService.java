package saaf.common.fmw.common.services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import saaf.common.fmw.common.model.inter.ISaafImages;
import saaf.common.fmw.common.model.sqls.SaafImagesSql;
import saaf.common.fmw.services.CommonAbstractServices;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.utils.ObjectUtil;


//import saaf.common.fmw.cinema.model.entities.readonly.CinFilmMessageEntity_HI_RO;
//import saaf.common.fmw.cinema.model.inter.ICinFilmMessage;


@Path("/saafImages")
public class SaafImagesService extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaafImagesService.class);
    @Autowired
    private ISaafImages saafImagesServer;
    
	private boolean isNullOrEmpty(Object obj) {
		return obj == null || obj.toString().length()==0;
	}
    
    @POST
    @Path("find")
    @Produces(MediaType.APPLICATION_JSON)
    public String find(@FormParam("params")
        String params, @FormParam("pageIndex")
        int pageIndex, @FormParam("pageRows")
        int pageRows) {
        try {
            JSONObject parameters = this.parseObject(params);
            StringBuffer where = new StringBuffer();
            where.append(SaafImagesSql.QUERY_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            //id
            Object id = parameters.get("id");
            if (isNullOrEmpty(id) == false) {
                where.append(" and si.id = :id ");
                map.put("id", id.toString());
            }
            //targetId
            Object targetId = parameters.get("targetId");
            if (isNullOrEmpty(targetId) == false) {
                where.append(" and si.target_id = :targetId ");
                map.put("targetId", targetId.toString());
            }
            //targetType
            Object targetType = parameters.get("targetType");
            if (isNullOrEmpty(targetType) == false) {
                where.append(" and si.target_type = :targetType ");
                map.put("targetType", targetType.toString());
            }
            //编码
            Object mtimeFilmCode = parameters.get("mtimeFilmCode");
            if (isNullOrEmpty(mtimeFilmCode) == false) {
                where.append(" and si.mtime_film_code like :mtimeFilmCode ");
                map.put("mtimeFilmCode", "%" + mtimeFilmCode.toString() + "%");
            }
            //片名
            Object mtimeFilmName = parameters.get("mtimeFilmName");
            if (isNullOrEmpty(mtimeFilmName) == false) {
                where.append(" and si.mtimeFilmName = :mtimeFilmName ");
                map.put("mtimeFilmName", mtimeFilmName.toString());
            }
            //状态
            Object status = parameters.get("status");
            if (isNullOrEmpty(status) == false) {
                where.append(" and si.status = :status ");
                map.put("status", status.toString());
            }
            return saafImagesServer.findData(parameters, pageIndex, pageRows);
        } catch (Exception e) {
            LOGGER.error("查询图片信息异常！" + e);
            return convertResultJSONObj("E", "查询图片信息异常!" + e, 0, null);
        }
    }
    //    public String cancel(@FormParam("params")
    //        String params) {
    //        JSONObject parameters = JSONObject.fromObject(params);
    //        int userId = SaafToolUtils.getObjectToInt(this.getSessionUserId(), -9999);
    //        if (SaafToolUtils.isNullOrEmpty(parameters.get("targetType"))) {
    //            return SaafToolUtils.convertResultJSONObj("F", "请传入图片类型!", 0, null).toString();
    //        }
    //        Map<String, Object> map = new HashMap<String, Object>();
    //        map.put("targetId", SaafToolUtils.getObjectToInt(parameters.get("targetId"), 0));
    //        map.put("targetType", parameters.get("targetType"));
    //        List<SaafImagesEntity_HI> list = server.findByProperty(map);
    //        for (SaafImagesEntity_HI saafImages : list) {
    //            saafImages.setTargetId(0);
    //            saafImages.setLastUpdateDate(new Date());
    //            saafImages.setLastUpdateLogin(userId);
    //            saafImages.setLastUpdatedBy(userId);
    //        }
    //        return server.Updatecancel(list, SaafToolUtils.getObjectToInt(parameters.get("targetId"), 0));
    //    }
}

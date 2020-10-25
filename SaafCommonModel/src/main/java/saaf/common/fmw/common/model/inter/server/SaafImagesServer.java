package saaf.common.fmw.common.model.inter.server;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.DynamicBaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.common.model.entities.SaafImagesEntity_HI;
import saaf.common.fmw.common.model.inter.ISaafImages;
import saaf.common.fmw.common.model.sqls.SaafImagesSql;
import saaf.common.fmw.common.utils.SaafToolUtils;


@Component("saafImagesServer")
public class SaafImagesServer implements ISaafImages {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaafImagesServer.class);
    @Autowired
    private DynamicBaseViewObject commonDAO_HI_DY;
    @Autowired
    private ViewObject<SaafImagesEntity_HI> saafImagesDAO_HI;

    /**
     *查询图片
     */
    public String findData(JSONObject params, Integer pageIndex, Integer pageRows) {
        try {
            StringBuffer where = new StringBuffer();
            where.append(SaafImagesSql.QUERY_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            //id
            Object id = params.get("id");
            if (null != id && !"".equals(id.toString().trim())) {
                where.append(" and si.id = :id ");
                map.put("id", id.toString());
            }
            //targetId
            Object targetId = params.get("targetId");
            if (null != targetId && "".equals(targetId.toString().trim())) {
                where.append(" and si.target_id = :targetId ");
                map.put("targetId", targetId.toString());
            }
            //targetType
            Object targetType = params.get("targetType");
            if (null != targetType && !"".equals(targetType.toString().trim())){
                where.append(" and si.target_type like :targetType ");
                map.put("targetType", "%" + targetType.toString() + "%");
            }
            //编码
            Object mtimeFilmCode = params.get("mtimeFilmCode");
            if (null != mtimeFilmCode && !"".equals(mtimeFilmCode.toString().trim())){
                where.append(" and si.mtime_film_code like :mtimeFilmCode ");
                map.put("mtimeFilmCode", "%" + mtimeFilmCode.toString() + "%");
            }
            //片名
            Object mtimeFilmName = params.get("mtimeFilmName");
            if (null != mtimeFilmName && !"".equals(mtimeFilmName.toString().trim())) {
                where.append(" and si.mtimeFilmName = :mtimeFilmName ");
                map.put("mtimeFilmName", mtimeFilmName.toString());
            }
            //imgChannel
            //图片渠道
            Object imgChannel = params.get("imgChannel");
            if (null != imgChannel && !"".equals(imgChannel.toString().trim())) {
                where.append(" and si.img_channel = :imgChannel ");
                map.put("imgChannel", imgChannel.toString());
            }
            //
            //状态
            Object status = params.get("status");
            if (null != status && !"".equals(status.toString().trim())) {
                where.append(" and si.status = :status ");
                map.put("status", status.toString());
            }
            Pagination rowSet = commonDAO_HI_DY.findPagination(where.toString(), map, pageIndex, pageRows);

            return JSON.toJSONString(rowSet);
        } catch (Exception e) {
            //e.printStackTrace();
            return SToolUtils.convertResultJSONObj("E", "图片查询失败!" + e, 0, null).toString();
        }
    }

    public List<SaafImagesEntity_HI> findByProperty(Map<String, Object> map) {
        return saafImagesDAO_HI.findByProperty(map);
    }

    /**
     * 保存图片
     */
    public boolean saveImage(JSONObject parameters) {
        SaafImagesEntity_HI row = new SaafImagesEntity_HI();
        try {
//            row = SaafToolUtils..mergeEntity(row, parameters, parameters.get("id"), baseDAO);
            saafImagesDAO_HI.saveOrUpdate(row);
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 删除图片
     */
    public boolean deleteImage(JSONObject parameters) {
        try {
            saafImagesDAO_HI.delete(parameters.getInteger("id"));
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 影院图片
     * @param obj
     * @param userId
     * @param filmId
     * @return
     */
    //    public boolean saveCinCinemaMessageImage(Object obj, int userId, int filmId) {
    //        if (obj == null) {
    //            return false;
    //        } else {
    //            SaafImagesEntity_HI row = null;
    //            JSONArray jsonArr = JSONArray.fromObject(obj);
    //            for (int i = 0; i < jsonArr.size(); i++) {
    //                Object id = jsonArr.getJSONObject(i).get("id");
    //                if (SaafToolUtils.isNullOrEmpty(id) == false) {
    //                    row = (SaafImagesEntity_HI)baseDAO.getById(Integer.parseInt(id.toString()));
    //                    row.setLastUpdateDate(new Date());
    //                    row.setLastUpdateLogin(userId);
    //                    row.setLastUpdatedBy(userId);
    //                } else {
    //                    row = new SaafImagesEntity_HI();
    //                    row.setCreatedBy(userId);
    //                    row.setCreationDate(new Date());
    //                    row.setLastUpdateDate(new Date());
    //                    row.setLastUpdateLogin(userId);
    //                    row.setLastUpdatedBy(userId);
    //                }
    //                row.setUrl(SaafToolUtils.getObjectToString(jsonArr.getJSONObject(i).get("url").toString(), ""));
    //                row.setImageName(SaafToolUtils.getObjectToString(jsonArr.getJSONObject(i).get("imageName").toString(), ""));
    //                row.setTargetId(filmId);
    //                row.setTargetType(SaafToolUtils.getObjectToString(jsonArr.getJSONObject(i).get("targetType").toString(), "影城图片"));
    //                row.setDisabled("Y");
    //                baseDAO.saveOrUpdate(row);
    //            }
    //            return true;
    //        }
    //    }

    /**
     * 关联时光网
     * @param stillsUrlArr
     * @param filmId
     * @param userId
     */
    //    public void saveTimenet(JSONArray stillsUrlArr, Object filmId, int userId, String FilmEname, String FilmNo, String FilmName) {
    //        String url = "";
    //        String mtimeMessageType = "";
    //        for (int i = 0; i < stillsUrlArr.size(); i++) {
    //            //关联时光网
    //            SaafImagesEntity_HI saafImages = new SaafImagesEntity_HI();
    //            saafImages.setTargetId(Integer.valueOf(filmId.toString()));
    //            //影片居住照、预告片、海报
    //            if (SaafToolUtils.isNullOrEmpty(stillsUrlArr.getJSONObject(i).get("StillsUrl")) == false) {
    //                url = stillsUrlArr.getJSONObject(i).get("StillsUrl").toString();
    //                mtimeMessageType = "STILLSURL";
    //            } else if (SaafToolUtils.isNullOrEmpty(stillsUrlArr.getJSONObject(i).get("POSTER")) == false) {
    //                url = stillsUrlArr.getJSONObject(i).get("POSTER").toString();
    //                mtimeMessageType = "POSTER";
    //            } else if (SaafToolUtils.isNullOrEmpty(stillsUrlArr.getJSONObject(i).get("TRAILER")) == false) {
    //                url = stillsUrlArr.getJSONObject(i).get("TRAILER").toString();
    //                mtimeMessageType = "TRAILER";
    //            }
    //            saafImages.setMtimeFilmCode(FilmNo);
    //            saafImages.setMtimeFilmName(FilmName);
    //            saafImages.setMtimeFilmNameEn(FilmEname);
    //            saafImages.setUrl(url);
    //            saafImages.setMtimeMessageType(mtimeMessageType);
    //            saafImages.setTargetType("FILM_IMAGE");
    //            saafImages.setDisabled("Y");
    //            saafImages.setCreatedBy(userId);
    //            saafImages.setCreationDate(new Date());
    //            saafImages.setLastUpdateDate(new Date());
    //            saafImages.setLastUpdateLogin(userId);
    //            saafImages.setLastUpdatedBy(userId);
    //            baseDAO.saveOrUpdate(saafImages);
    //        }
    //    }

    //    public String Updatecancel(List<SaafImagesEntity_HI> list, int filmId) {
    //        try {
    //            baseDAO.saveOrUpdateAll(list);
    //            //更改关联状态
    //            System.out.println("filmId:======================" + filmId);
    //            CinFilmMessageEntity_HI cinFilmMessage = cinFilmMessage = (CinFilmMessageEntity_HI)cinFilmMessageDAO_HI.getById(filmId);
    //            cinFilmMessage.setMtimeRelStatus("N");
    //            cinFilmMessageDAO_HI.save(cinFilmMessage);
    //            return SaafToolUtils.convertResultJSONObj("S", "取消关联成功!", 0, null).toString();
    //        } catch (Exception e) {
    //            //e.printStackTrace();
    //            return SaafToolUtils.convertResultJSONObj("S", "取消关联失败!", 0, null).toString();
    //        }
    //    }
}


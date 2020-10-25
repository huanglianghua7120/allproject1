package saaf.common.fmw.common.model.inter;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

import saaf.common.fmw.common.model.entities.SaafImagesEntity_HI;


public interface ISaafImages {
    public String findData(JSONObject params, Integer pageIndex, Integer pageRows);

    public boolean saveImage(JSONObject parameters);

    public List<SaafImagesEntity_HI> findByProperty(Map<String, Object> map);

    public boolean deleteImage(JSONObject parameters);
    //    public boolean saveCinCinemaMessageImage(Object JSONArray, int userId, int filmId);
    //    public void saveTimenet(JSONArray stillsUrlArr, Object filmId, int userId, String FilmEname, String FilmNo, String FilmName);
    //    public String Updatecancel(List<SaafImagesEntity_HI> list,int filmId);


}

package saaf.common.fmw.base.model.entities.readonly;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class SaafAreasEntity_HI_RO {

    public static String QUERY_SQL = "  SELECT sa.area_id           \"areaId\"\n" +
        "              ,sa.area_parent_id    \"areaParentId\"\n" +
        "              ,sa.area_level        \"areaLevel\"\n" +
        "              ,sa.area_code         \"areaCode\"\n" +
        "              ,sa.area_name         \"areaName\"\n" +
        "              ,sa.area_desc         \"areaDesc\"\n" +
        "              ,sa.is_leaf_node      \"isLeafNode\"\n" +
        "              ,sa.ower_area_id      \"owerAreaId\"\n" +
        "              ,sa.owner_area        \"ownerArea\"\n" +
        "              ,alv.meaning          \"ownerAreaAlt\"\n" +
        "              ,sa.hot_city_flag     \"hotCityFlag\"\n" +
        "              ,sa.start_date_active \"startDateActive\"\n" +
        "              ,sa.end_date_active \"endDateActive\"\n" +
        "         FROM saaf_areas sa\n" +
        "         LEFT JOIN saaf_lookup_values alv\n" +
        "           ON alv.lookup_code = sa.owner_area\n" +
        "        WHERE 1 = 1";
    public static String DEL_SQL = "SELECT * \n" +
        "  FROM saaf_areas sa\n";
    //        " START WITH sa.area_id = :areaId \n" +
    //        "CONNECT BY sa.area_parent_id = PRIOR sa.area_id";

    //获取影城
    //    public static String QUERY_SQL_CINEMA =
    //        "select " + "  sa.AREA_ID areaId,     " + "  sa.AREA_NAME areaName,     " + "  ccm.CINEMA_ID cinemaId,     " + "  ccm.CINEMA_NAME cinemaName     " +
    //        "  from  saaf_areas sa " + "  left join cin_cinema_message ccm on ccm.AREA_ID = sa.AREA_ID  " + "  where 1=1 ";
    //    //获取影厅
    //    public static String QUERY_SQL_SCREEN =
    //        "select " + "  sc.SCREEN_NAME screenName,     " + "  sc.SCREEN_ID screenId,     " + "  ccdc.SCREEN_ID screenIdChiose     " + "  from  cin_cinema_message ccm  " +
    //        "  left join cin_screen sc on sc.CINEMA_ID = ccm.CINEMA_ID  " + "  left join cha_channel_dis_cinema ccdc on ccdc.SCREEN_ID = sc.SCREEN_ID  " + "  where 1=1 ";

    //获取城市LOV
    public static String QUERY_SQL_CITY = " SELECT sa.area_id   \"cityid\"\n" +
        "    		     ,sa.area_code \"citycode\"\n" +
        "    		     ,sa.area_name \"cityname\"\n" +
        "    		 FROM saaf_areas sa\n" +
        "    		WHERE sa.area_level = 2\n" +
        "    		  AND (sa.start_date_active IS NULL OR\n" +
        "    		      sa.start_date_active <= CURDATE())\n" +
        "    		  AND (sa.end_date_active IS NULL OR\n" +
        "    		      sa.end_date_active >= CURDATE())";

    private Integer areaId;
    private Integer areaParentId;
    private Integer areaLevel;
    private String areaCode;
    private String areaName;
    private Integer cityId;
    private String cityCode;
    private String cityName;
    private String areaDesc;
    private String isLeafNode;
    private Integer owerAreaId;
    private String ownerArea;
    @JSONField(format = "yyyy-MM-dd")
    private Date startDateActive;
    @JSONField(format = "yyyy-MM-dd")
    private Date endDateActive;
    private String ownerAreaAlt;
    private Integer cinemaId;
    private String cinemaName;
    private Integer screenId;
    private String screenName;
    private Integer screenIdChiose;
    private String hotCityFlag;

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaParentId(Integer areaParentId) {
        this.areaParentId = areaParentId;
    }

    public Integer getAreaParentId() {
        return areaParentId;
    }

    public void setAreaLevel(Integer areaLevel) {
        this.areaLevel = areaLevel;
    }

    public Integer getAreaLevel() {
        return areaLevel;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc;
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setIsLeafNode(String isLeafNode) {
        this.isLeafNode = isLeafNode;
    }

    public String getIsLeafNode() {
        return isLeafNode;
    }

    public void setOwerAreaId(Integer owerAreaId) {
        this.owerAreaId = owerAreaId;
    }

    public Integer getOwerAreaId() {
        return owerAreaId;
    }

    public void setOwnerArea(String ownerArea) {
        this.ownerArea = ownerArea;
    }

    public String getOwnerArea() {
        return ownerArea;
    }

    public void setStartDateActive(Date startDateActive) {
        this.startDateActive = startDateActive;
    }

    public Date getStartDateActive() {
        return startDateActive;
    }

    public void setEndDateActive(Date endDateActive) {
        this.endDateActive = endDateActive;
    }

    public Date getEndDateActive() {
        return endDateActive;
    }

    public static void setQUERY_SQL(String QUERY_SQL) {
        SaafAreasEntity_HI_RO.QUERY_SQL = QUERY_SQL;
    }

    public static String getQUERY_SQL() {
        return QUERY_SQL;
    }

    public static void setDEL_SQL(String DEL_SQL) {
        SaafAreasEntity_HI_RO.DEL_SQL = DEL_SQL;
    }

    public static String getDEL_SQL() {
        return DEL_SQL;
    }

    public void setOwnerAreaAlt(String ownerAreaAlt) {
        this.ownerAreaAlt = ownerAreaAlt;
    }

    public String getOwnerAreaAlt() {
        return ownerAreaAlt;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getScreenName() {
        return screenName;
    }


    public void setScreenIdChiose(Integer screenIdChiose) {
        this.screenIdChiose = screenIdChiose;
    }

    public Integer getScreenIdChiose() {
        return screenIdChiose;
    }

    public void setHotCityFlag(String hotCityFlag) {
        this.hotCityFlag = hotCityFlag;
    }

    public String getHotCityFlag() {
        return hotCityFlag;
    }

    public static void setQUERY_SQL_CITY(String QUERY_SQL_CITY) {
        SaafAreasEntity_HI_RO.QUERY_SQL_CITY = QUERY_SQL_CITY;
    }

    public static String getQUERY_SQL_CITY() {
        return QUERY_SQL_CITY;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }
}

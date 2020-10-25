package saaf.common.fmw.base.model.entities.readonly;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

import java.util.Date;


public class SaafPositionsEntity_HI_RO implements Serializable {

    public SaafPositionsEntity_HI_RO() {
        super();
    }

    public static String LOV_POSITIONS_QUERY_SQL =
                    "SELECT sp.pos_id     posId\n" +
                    "      ,sp.pos_number posNumber\n" +
                    "      ,sp.pos_name   posName\n" +
                    "FROM   saaf_positions sp\n" +
                    "WHERE  sp.start_date <= trunc(SYSDATE)\n" +
                    "AND    nvl(sp.end_date, trunc(SYSDATE)) >= trunc(SYSDATE)";

    public static String POSITIONS_QUERY_SQL =
                    "SELECT sp.pos_id posId\n" +
                    "      ,sp.pos_number posNumber\n" +
                    "      ,sp.pos_name posName\n" +
                    "      ,sp.parent_pos_id parentPosId\n" +
                    "      ,(SELECT pos_name FROM saaf_positions WHERE pos_id = sp.parent_pos_id) parentPosName\n" +
                    "      ,sp.pos_sequence posSequence\n" +
                    "      ,sp.remark remark\n" +
                    "      ,sp.end_date endDate\n" +
                    "      ,sp.start_date startDate\n" +
                    "      ,sp.platform_code platformCode\n" +
                    "FROM   saaf_positions sp\n" +
                    "WHERE  1 = 1";

    private Integer posId;
    private String posNumber;
    private String posName;
    private Integer parentPosId;
    private String parentPosName;
    private Integer posLevel;
    private Integer posSequence;
    private String remark;
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    private String platformCode;

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public Integer getPosId() {
        return posId;
    }

    public void setPosNumber(String posNumber) {
        this.posNumber = posNumber;
    }

    public String getPosNumber() {
        return posNumber;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public String getPosName() {
        return posName;
    }

    public void setParentPosId(Integer parentPosId) {
        this.parentPosId = parentPosId;
    }

    public Integer getParentPosId() {
        return parentPosId;
    }

    public void setParentPosName(String parentPosName) {
        this.parentPosName = parentPosName;
    }

    public String getParentPosName() {
        return parentPosName;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPosLevel(Integer posLevel) {
        this.posLevel = posLevel;
    }

    public Integer getPosLevel() {
        return posLevel;
    }

    public void setPosSequence(Integer posSequence) {
        this.posSequence = posSequence;
    }

    public Integer getPosSequence() {
        return posSequence;
    }
}

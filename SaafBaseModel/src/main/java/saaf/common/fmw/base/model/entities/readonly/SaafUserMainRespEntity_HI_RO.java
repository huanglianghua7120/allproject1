package saaf.common.fmw.base.model.entities.readonly;


import java.io.Serializable;


public class SaafUserMainRespEntity_HI_RO implements Serializable {
    public static String QUERY_SQL =
                    "SELECT\n" +
                    "  s.user_resp_id userRespId,\n" +
                    "  s.user_id userId,\n" +
                    "  s.responsibility_id responsibilityId,\n" +
                    "  resp.responsibility_name responsibilityName\n" +
                    "FROM\n" +
                    "  saaf_user_resp s,\n" +
                    "  saaf_responsibilitys resp\n" +
                    "WHERE s.responsibility_id = resp.responsibility_id";

    public static String QUERY_SUPPLIER_RESP_SQL =
                    "SELECT s.User_Resp_Id           userRespId\n" +
                    "      ,s.User_Id                userId\n" +
                    "      ,s.Responsibility_Id      responsibilityId\n" +
                    "      ,Resp.Responsibility_Name responsibilityName\n" +
                    "      ,Slv.Lookup_Code          supplierType\n" +
                    "  FROM Saaf_User_Resp       s\n" +
                    "      ,Saaf_Responsibilitys Resp\n" +
                    "      ,Saaf_Lookup_Values   Slv\n" +
                    " WHERE s.Responsibility_Id = Resp.Responsibility_Id\n" +
                    "   AND Slv.Lookup_Type = 'POS_SUPPLIER_TYPE'\n" +
                    "   AND Slv.Enabled_Flag = 'Y'\n" +
                    "   AND Resp.Responsibility_Name = Slv.Tag";


    private Integer userRespId;
    private Integer userId;
    private Integer responsibilityId;
    private String responsibilityName;
    private String supplierType;


    public void setUserRespId(Integer userRespId) {
        this.userRespId = userRespId;
    }

    public Integer getUserRespId() {
        return userRespId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setResponsibilityId(Integer responsibilityId) {
        this.responsibilityId = responsibilityId;
    }

    public Integer getResponsibilityId() {
        return responsibilityId;
    }

    public void setResponsibilityName(String responsibilityName) {
        this.responsibilityName = responsibilityName;
    }

    public String getResponsibilityName() {
        return responsibilityName;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }
}

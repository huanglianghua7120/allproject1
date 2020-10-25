package saaf.common.fmw.common.model.entities.readonly;


import java.io.Serializable;

import java.util.Date;


public class ExtAccessAttributeEntity_HI_RO implements Serializable {

    public static final String query = "SELECT\n" +
        "  eam.EXT_INT_ID      AS extINtId,\n" +
        "  eam.INTERFACE_CODE  AS interfaceCode,\n" +
        "  eam.INTERFACE_NAME  AS interfaceName,\n" +
        "  eam.INTERFACE_TYPE  AS interfaceType,\n" +
        "  eam.ENABLE_FLAG     AS enableFlag,\n" +
        "  eam.CREATION_DATE   AS creationDate,\n" +
        "  eaa.ATTRIBUTE_NAME  AS attributeName,\n" +
        "  eaa.ATTRIBUTE_VALUE AS attributeValue,\n" +
        "  eaa.EXT_DIS_ID      AS extDisId\n" +
        " FROM ext_access_message AS eam\n" +
        "  LEFT JOIN ext_access_attribute AS eaa ON eam.EXT_INT_ID = eaa.EXT_INT_ID\n" +
        " WHERE 1 = 1 and eam.ENABLE_FLAG='Y' ";

    private String extINtId;
    private String interfaceCode;
    private String interfaceName;
    private String interfaceType;
    private String enableFlag;
    private Date creationDate;
    private String attributeName;
    private String attributeValue;
    private String extDisId;

    public String getExtINtId() {
        return extINtId;
    }

    public void setExtINtId(String extINtId) {
        this.extINtId = extINtId;
    }

    public String getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public String getExtDisId() {
        return extDisId;
    }

    public void setExtDisId(String extDisId) {
        this.extDisId = extDisId;
    }
}

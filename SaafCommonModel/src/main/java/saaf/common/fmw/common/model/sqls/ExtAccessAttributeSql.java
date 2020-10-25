package saaf.common.fmw.common.model.sqls;


import java.io.Serializable;


public class ExtAccessAttributeSql implements Serializable {

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
        " WHERE 1 = 1 ";

}

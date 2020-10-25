package saaf.common.fmw.base.model.entities.readonly;

import java.io.Serializable;

public class SaafLookupTypesEntity_HI_RO implements Serializable {

    public SaafLookupTypesEntity_HI_RO() {
        super();
    }

    public static String QUERY_SQL =
                    "SELECT slt.lookup_type_id      lookupTypeId\n" +
                    "      ,slt.lookup_type         lookupType\n" +
                    "      ,slt.meaning             meaning\n" +
                    "      ,slt.customization_level customizationLevel\n" +
                    "      ,slt.description         description\n" +
                    "      ,elv.meaning             levelName\n" +
                    "FROM   saaf_lookup_types  slt\n" +
                    "      ,saaf_lookup_values elv\n" +
                    "WHERE  slt.customization_level = elv.lookup_code\n" +
                    "AND    elv.lookup_type = 'CODE_LEVEL'\n" +
                    "AND    elv.enabled_flag = 'Y'\n" +
                    "AND    elv.start_date_active <= trunc(SYSDATE)\n" +
                    "AND    nvl(elv.end_date_active, trunc(SYSDATE)) >= trunc(SYSDATE)\n";

    private int lookupTypeId;
    private String lookupType;
    private String meaning;
    private String customizationLevel;
    private String description;
    private String levelName;


    public void setLookupTypeId(int lookupTypeId) {
        this.lookupTypeId = lookupTypeId;
    }

    public int getLookupTypeId() {
        return lookupTypeId;
    }

    public void setLookupType(String lookupType) {
        this.lookupType = lookupType;
    }

    public String getLookupType() {
        return lookupType;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCustomizationLevel(String customizationLevel) {
        this.customizationLevel = customizationLevel;
    }

    public String getCustomizationLevel() {
        return customizationLevel;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelName() {
        return levelName;
    }
}

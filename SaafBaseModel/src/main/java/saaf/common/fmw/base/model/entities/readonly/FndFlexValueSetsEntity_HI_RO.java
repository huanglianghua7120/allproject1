package saaf.common.fmw.base.model.entities.readonly;

import java.io.Serializable;

public class FndFlexValueSetsEntity_HI_RO implements Serializable {
    public FndFlexValueSetsEntity_HI_RO() {
        super();
    }
    public static String QUERY_SQL =
        "SELECT  t.FLEX_VALUE_SET_ID flexValueSetId,t.FLEX_VALUE_SET_NAME flexValueSetName,t.VALIDATION_TYPE validationType,t.DESCRIPTION description from fnd_flex_value_sets t where 1=1 ";
    private Integer flexValueSetId;
    private String flexValueSetName;
    private String validationType;

    public String getValidationType() {
        return validationType;
    }

    public void setValidationType(String validationType) {
        this.validationType = validationType;
    }
    private String description;

    public Integer getFlexValueSetId() {
        return flexValueSetId;
    }

    public void setFlexValueSetId(Integer flexValueSetId) {
        this.flexValueSetId = flexValueSetId;
    }

    public String getFlexValueSetName() {
        return flexValueSetName;
    }

    public void setFlexValueSetName(String flexValueSetName) {
        this.flexValueSetName = flexValueSetName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

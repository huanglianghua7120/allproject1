package saaf.common.fmw.po.model.entities.readonly;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SrmPoBaseCategoriesEntity_HI_RO implements Serializable {
	
	

    public static final String QUERY_CATEGORIES_SQL = "SELECT cate.`CATEGORY_ID` categoryId FROM srm_base_categories cate WHERE 1=1";
	private Integer categoryId;
    private String categoryCode;
    private String categoryName;
    private String bigCategoryCode;
    private String bigCategoryName;
    private String middleCategoryCode;
    private String middleCategoryName;
    private String smallCategoryCode;
    private String smallCategoryName;
    private String enabledFlag;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date disableDate;
    private String retrospectFlag;


    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBigCategoryCode() {
        return bigCategoryCode;
    }

    public void setBigCategoryCode(String bigCategoryCode) {
        this.bigCategoryCode = bigCategoryCode;
    }

    public String getBigCategoryName() {
        return bigCategoryName;
    }

    public void setBigCategoryName(String bigCategoryName) {
        this.bigCategoryName = bigCategoryName;
    }

    public String getMiddleCategoryCode() {
        return middleCategoryCode;
    }

    public void setMiddleCategoryCode(String middleCategoryCode) {
        this.middleCategoryCode = middleCategoryCode;
    }

    public String getMiddleCategoryName() {
        return middleCategoryName;
    }

    public void setMiddleCategoryName(String middleCategoryName) {
        this.middleCategoryName = middleCategoryName;
    }

    public String getSmallCategoryCode() {
        return smallCategoryCode;
    }

    public void setSmallCategoryCode(String smallCategoryCode) {
        this.smallCategoryCode = smallCategoryCode;
    }

    public String getSmallCategoryName() {
        return smallCategoryName;
    }

    public void setSmallCategoryName(String smallCategoryName) {
        this.smallCategoryName = smallCategoryName;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }

    public String getRetrospectFlag() {
        return retrospectFlag;
    }

    public void setRetrospectFlag(String retrospectFlag) {
        this.retrospectFlag = retrospectFlag;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}

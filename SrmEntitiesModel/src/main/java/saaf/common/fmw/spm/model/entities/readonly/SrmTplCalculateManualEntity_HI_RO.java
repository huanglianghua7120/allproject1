package saaf.common.fmw.spm.model.entities.readonly;

import java.io.Serializable;
import java.math.BigDecimal;

public class SrmTplCalculateManualEntity_HI_RO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final String QUERY_MANUAL_INFO_LIST="SELECT  catId.`scheme_Id` schemeId,catId.`dimension_Id` dimensionId,catId.`tpl_Id` tplId,catId.`tpl_Category_Id` tplCategoryId,catId.`Category_Id` categoryId,catId.`indicator_Id` indicatorId,catId.`ORG_ID` orgId,catId.`SUPPLIER_ID` supplierId, catId.`score` score, catId.`dimension_Weight` dimensionWeight, catId.`tpl_Indicator_Id` tplIndicatorId FROM srm_spm_catculate_manual catId  WHERE 1=1";
	//public static final String QUERY_DIMENSION_INFO_LIST_old="SELECT tt.Category_Id categoryId,tt.dimension_Id dimensionId,tt.num numt,tt.qty, tt.SUPPLIER_ID supplierId FROM (SELECT  n.`Category_Id`, n.`dimension_Id`, SUM( IFNULL(n.`score`,0) * n.`dimension_Weight`/100) num,get_sum_delivery_qty(n.`SUPPLIER_ID`,n.`Category_Id`,n.`scheme_Id`,n.`attribute4`,n.`attribute2`,n.`attribute3`)qty,n.`SUPPLIER_ID`  FROM srm_spm_catculate_manual n WHERE  n.`scheme_Id` =:schemeId AND n.`Category_Id` =:categoryId AND n.`ORG_ID` =:orgId  AND n.dimension_Id=:dimensionId GROUP BY n.`SUPPLIER_ID`,n.`Category_Id`,n.`dimension_Id`) tt ORDER BY  tt.num DESC,tt.qty DESC ";
	public static final String QUERY_DIMENSION_INFO_LIST="SELECT\n" +
			"  tt.Category_Id categoryId,\n" +
			"  tt.dimension_Id dimensionId,\n" +
			"  tt.num numt,\n" +
			"  tt.SUPPLIER_ID supplierId\n" +
			"FROM\n" +
			"  (SELECT\n" +
			"    n.`Category_Id`,\n" +
			"    n.`dimension_Id`,\n" +
			"    SUM(\n" +
			"      IFNULL(n.`score`, 0) * n.`dimension_Weight` / 100\n" +
			"    ) num,\n" +
			"    n.`SUPPLIER_ID`\n" +
			"  FROM\n" +
			"    srm_spm_catculate_manual n\n" +
			"  WHERE n.`scheme_Id` = :schemeId\n" +
			"    AND n.`Category_Id` = :categoryId\n" +
			"    AND n.`ORG_ID` = :orgId\n" +
			"    AND n.dimension_Id = :dimensionId\n" +
			"  GROUP BY n.`SUPPLIER_ID`,\n" +
			"    n.`Category_Id`,\n" +
			"    n.`dimension_Id`) tt\n" +
			"ORDER BY tt.num DESC\r\n";
	public static final String QUERY_INDICATOR_INFO ="SELECT  n.`Category_Id` categoryId, n.`dimension_Id` dimensionId, SUM( IFNULL(n.`score`,0) * n.`dimension_Weight`/100) numt,n.`SUPPLIER_ID` supplierId, n.`indicator_Id` indicatorId FROM srm_spm_catculate_manual n  WHERE n.`scheme_Id` =:schemeId  AND n.`Category_Id` =:categoryId  AND n.`ORG_ID` =:orgId  AND n.dimension_Id =:dimensionId AND n.`SUPPLIER_ID`=:supplierId  GROUP BY n.`SUPPLIER_ID`, n.`Category_Id`, n.`dimension_Id`, n.`indicator_Id`";
	//public static final String QUERY_CATE_INFO="SELECT yy.categoryId categoryId, yy.supplierId supplierId,SUM(yy.numt) numt FROM(SELECT tt.Category_Id categoryId,tt.dimension_Id dimensionId,tt.num * sp.DIMENSION_WEIGHT/100 numt,tt.SUPPLIER_ID supplierId FROM (SELECT  n.`Category_Id`, n.`dimension_Id`,SUM( IFNULL(n.`score`,0) * n.`dimension_Weight`/100) num, n.`SUPPLIER_ID`   FROM srm_spm_catculate_manual n  WHERE n.`score` IS NOT NULL  AND n.`scheme_Id` =:schemeId AND n.`Category_Id` =:categoryId  AND n.`ORG_ID` =:orgId GROUP BY n.`SUPPLIER_ID`, n.`Category_Id`, n.`dimension_Id`) tt,SRM_SPM_TPL_DIMENSION sp  WHERE tt.dimension_Id = sp.`TPL_DIMENSION_ID`) yy GROUP BY yy.categoryId, yy.supplierId ORDER BY SUM(yy.numt)DESC ,yy.supplierId ASC  ";
	public static final String QUERY_CATE_INFO_old="SELECT  yy.categoryId categoryId, yy.supplierId supplierId, SUM(yy.numt) numt FROM (SELECT  tt.Category_Id categoryId, tt.dimension_Id dimensionId, tt.num * sp.DIMENSION_WEIGHT / 100 numt, tt.SUPPLIER_ID supplierId, tt.attribute3,tt.scheme_Id,tt.attribute4  FROM (SELECT  n.`Category_Id`, n.`dimension_Id`, SUM( IFNULL(n.`score`, 0) * n.`dimension_Weight` / 100) num,n.`SUPPLIER_ID`,n.attribute3,n.scheme_Id, n.attribute4 FROM  srm_spm_catculate_manual n  WHERE n.`score` IS NOT NULL   AND n.`scheme_Id` =:schemeId  AND n.`Category_Id` =:categoryId AND n.`ORG_ID` =:orgId GROUP BY n.`SUPPLIER_ID`, n.`Category_Id`,n.`dimension_Id`) tt,srm_spm_tpl_dimension sp WHERE tt.dimension_Id = sp.`TPL_DIMENSION_ID`) yy GROUP BY yy.categoryId, yy.supplierId ORDER BY SUM(yy.numt) DESC, get_category_delivery_qty ( yy.categoryId,yy.scheme_Id,yy.attribute3,yy.attribute4) DESC, yy.supplierId ASC ";
	public static final String QUERY_CATE_INFO="SELECT\n" +
			"  yy.categoryId categoryId,\n" +
			"  yy.supplierId supplierId,\n" +
			"  SUM(yy.numt) numt\n" +
			"FROM\n" +
			"  (SELECT\n" +
			"    tt.Category_Id categoryId,\n" +
			"    tt.dimension_Id dimensionId,\n" +
			"    tt.num * sp.DIMENSION_WEIGHT / 100 numt,\n" +
			"    tt.SUPPLIER_ID supplierId,\n" +
			"    tt.scheme_Id\n" +
			"  FROM\n" +
			"    (SELECT\n" +
			"      n.`Category_Id`,\n" +
			"      n.`dimension_Id`,\n" +
			"      SUM(\n" +
			"        IFNULL(n.`score`, 0) * n.`dimension_Weight` / 100\n" +
			"      ) num,\n" +
			"      n.`SUPPLIER_ID`,\n" +
			"      n.scheme_Id\n" +
			"    FROM\n" +
			"      srm_spm_catculate_manual n\n" +
			"    WHERE n.`score` IS NOT NULL\n" +
			"      AND n.`scheme_Id` = :schemeId\n" +
			"      AND n.`Category_Id` = :categoryId\n" +
			"      AND n.`ORG_ID` = :orgId\n" +
			"    GROUP BY n.`SUPPLIER_ID`,\n" +
			"      n.`Category_Id`,\n" +
			"      n.`dimension_Id`) tt,\n" +
			"    srm_spm_tpl_dimension sp\n" +
			"  WHERE tt.dimension_Id = sp.`TPL_DIMENSION_ID`) yy\n" +
			"GROUP BY yy.categoryId,yy.supplierId\n" +
			"ORDER BY SUM(yy.numt) DESC,yy.supplierId ASC\r\n";
	private Integer calId;
    private Integer schemeId;
    private Integer tplId;
    private Integer tplCategoryId;
    private Integer categoryId;
    private Integer tplIndicatorId;
    private Integer indicatorId;
    private Integer orgId;
    private Integer supplierId;
    private Integer dimensionId;
    private BigDecimal score;
    private BigDecimal dimensionWeight;
    
    private BigDecimal numt;
    
	public Integer getCalId() {
		return calId;
	}
	public void setCalId(Integer calId) {
		this.calId = calId;
	}
	public Integer getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}
	public Integer getTplId() {
		return tplId;
	}
	public void setTplId(Integer tplId) {
		this.tplId = tplId;
	}
	public Integer getTplCategoryId() {
		return tplCategoryId;
	}
	public void setTplCategoryId(Integer tplCategoryId) {
		this.tplCategoryId = tplCategoryId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getTplIndicatorId() {
		return tplIndicatorId;
	}
	public void setTplIndicatorId(Integer tplIndicatorId) {
		this.tplIndicatorId = tplIndicatorId;
	}
	public Integer getIndicatorId() {
		return indicatorId;
	}
	public void setIndicatorId(Integer indicatorId) {
		this.indicatorId = indicatorId;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public Integer getDimensionId() {
		return dimensionId;
	}
	public void setDimensionId(Integer dimensionId) {
		this.dimensionId = dimensionId;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	public BigDecimal getDimensionWeight() {
		return dimensionWeight;
	}
	public void setDimensionWeight(BigDecimal dimensionWeight) {
		this.dimensionWeight = dimensionWeight;
	}
	public BigDecimal getNumt() {
		return numt;
	}
	public void setNumt(BigDecimal numt) {
		this.numt = numt;
	}
    
    
    

}

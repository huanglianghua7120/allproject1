package saaf.common.fmw.spm.model.entities.readonly;

import java.io.Serializable;
import java.math.BigDecimal;

public class SrmTplCalculateTempEntity_HI_RO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final String QUERY_COUNT_ITEM_INFO = "SELECT  tt.SUPPLIER_ID supplierId,tt.scheme_Id schemeId,tt.num auntscore FROM(SELECT  t1.scheme_Id,t1.SUPPLIER_ID,t1.agScore*(1-(t2.mScore-t1.agAuntScore)) num FROM(SELECT  temp.`scheme_Id`, temp.`SUPPLIER_ID`, temp.`CATEGORY_ID`, AVG(temp.`score`) agScore, AVG(temp.auntScore) agAuntScore FROM `srm_spm_calculate_temp` temp  WHERE temp.category_id=:categoryId AND scheme_Id=:schemeId GROUP BY temp.`scheme_Id`, temp.`SUPPLIER_ID`, temp.`CATEGORY_ID`) t1,(SELECT  temp.`scheme_Id`, AVG(temp.auntScore) mScore FROM `srm_spm_calculate_temp` temp  WHERE temp.category_id=:categoryId AND scheme_Id=:schemeId GROUP BY temp.`scheme_Id`) t2 WHERE t1.scheme_Id = t2.scheme_Id )tt WHERE EXISTS(SELECT *FROM srm_spm_request_datas datas WHERE datas.`VENDOR_ID`=tt.tt.SUPPLIER_ID AND datas.`SCHEME_ID`=:schemeId AND datas.`CATEGORY_ID`=:categoryId ) ORDER BY tt.num DESC ";
	
	public static final String QUERY_COUNT_SUP_INFO="SELECT  DISTINCT(SUPPLIER_ID) supplierId FROM  srm_spm_calculate_temp WHERE category_id=:categoryId AND scheme_id=:schemeId";
	public static final String QUERY_COUNT_SUP_INFO1 ="SELECT  temp.`scheme_Id` schemeId,temp.`SUPPLIER_ID` supplierId,temp.`CATEGORY_ID` categoryId ,temp.`item_id` itemId,temp.`score` score FROM  srm_spm_calculate_temp temp WHERE category_id=:categoryId AND scheme_id=:schemeId AND item_id=:itemsId AND SUPPLIER_ID=:supplierId";
	public static final String QUERY_COUNT_SUP_INFO2 ="SELECT IFNULL(max(temp.`score`),0) score FROM  srm_spm_calculate_temp temp WHERE category_id=:categoryId AND scheme_id=:schemeId AND item_id=:itemsId";

	public static final String QUERY_COUNT_SUP_INFO3 ="SELECT IFNULL(min(temp.score),0) score FROM  srm_spm_calculate_temp temp WHERE category_id=:categoryId AND scheme_id=:schemeId AND item_id=:itemsId";

	public static final String QUERY_COUNT_SUP_INFO4="SELECT  tt.supplier_id supplierId,ROUND(SUM(IF(tt.score<=0,0,(1- (tt.score - tt.auntscore) / tt.score) * tt.rn)),2)score  FROM (SELECT  res.`scheme_id`,res.`CATEGORY_ID`, res.`item_id`, res.`supplier_id`, res.`score`, res.`auntscore`, item.`score` rn  FROM srm_spm_calculate_result res, srm_tpl_calculate_temp_item item  WHERE res.`item_id` = item.`item_id`  AND res.category_id =:categoryId  AND res.scheme_id =:schemeId) tt GROUP BY tt.supplier_id";
	
	public static final String QUERY_COUNT_SUP_INFO5="SELECT IFNULL(MAX(t.score),0) score FROM(SELECT  tt.supplier_id supplierId,ROUND( SUM(IF(tt.score<=0,0,(1- (tt.score - tt.auntscore) / tt.score) * tt.rn)),2)score  FROM (SELECT  res.`scheme_id`,res.`CATEGORY_ID`, res.`item_id`, res.`supplier_id`, res.`score`, res.`auntscore`, item.`score` rn  FROM srm_spm_calculate_result res, srm_tpl_calculate_temp_item item  WHERE res.`item_id` = item.`item_id`  AND res.category_id =:categoryId  AND res.scheme_id =:schemeId) tt GROUP BY tt.supplier_id)t";

	private Integer tempId;
    private Integer schemeId;
    private Integer supplierId;
    private Integer itemId;
    private Integer categoryId;
    private BigDecimal score;
    private BigDecimal auntscore;
	public Integer getTempId() {
		return tempId;
	}
	public void setTempId(Integer tempId) {
		this.tempId = tempId;
	}
	public Integer getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	public BigDecimal getAuntscore() {
		return auntscore;
	}
	public void setAuntscore(BigDecimal auntscore) {
		this.auntscore = auntscore;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
    
    

}

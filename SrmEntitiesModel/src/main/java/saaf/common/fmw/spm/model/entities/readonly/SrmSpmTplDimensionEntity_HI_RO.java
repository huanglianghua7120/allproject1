package saaf.common.fmw.spm.model.entities.readonly;

import java.io.Serializable;
import java.math.BigDecimal;

public class SrmSpmTplDimensionEntity_HI_RO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String QUERY_DIMENSION_INFO_LIST =
					"SELECT sd.tpl_dimension_id   tplDimensionId\n" +
					"      ,sd.evaluate_dimension evaluateDimension\n" +
					"      ,sd.tpl_id             tplId\n" +
					"      ,sd.dimension_weight   dimensionWeight\n" +
					"      ,sd.description        descriptione\n" +
					"      ,svf.meaning           evaluateDimensionName\n" +
					"FROM   srm_spm_tpl_dimension sd\n" +
					"LEFT   JOIN saaf_lookup_values svf\n" +
					"ON     sd.evaluate_dimension = svf.lookup_code\n" +
					"AND    svf.lookup_type = 'SPM_INDICATOR_DIMENSION'\n" +
					"WHERE  1 = 1\n";

	private Integer tplDimensionId;  
	private Integer tplId;  
	private String evaluateDimension;  
	private String evaluateDimensionName;
	private BigDecimal dimensionWeight;  
	private String descriptione;
	public Integer getTplDimensionId() {
		return tplDimensionId;
	}
	public void setTplDimensionId(Integer tplDimensionId) {
		this.tplDimensionId = tplDimensionId;
	}
	public Integer getTplId() {
		return tplId;
	}
	public void setTplId(Integer tplId) {
		this.tplId = tplId;
	}
	public String getEvaluateDimension() {
		return evaluateDimension;
	}
	public void setEvaluateDimension(String evaluateDimension) {
		this.evaluateDimension = evaluateDimension;
	}
	public String getEvaluateDimensionName() {
		return evaluateDimensionName;
	}
	public void setEvaluateDimensionName(String evaluateDimensionName) {
		this.evaluateDimensionName = evaluateDimensionName;
	}
	public BigDecimal getDimensionWeight() {
		return dimensionWeight;
	}
	public void setDimensionWeight(BigDecimal dimensionWeight) {
		this.dimensionWeight = dimensionWeight;
	}
	public String getDescriptione() {
		return descriptione;
	}
	public void setDescriptione(String descriptione) {
		this.descriptione = descriptione;
	}

	
	

}

package saaf.common.fmw.base.model.entities.readonly;

import java.io.Serializable;

/**
 * 
 * @author zhy
 * Created by zhy on 2018/4/08.
 */
public class SrmBaseParamsEntity_HI_RO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static final String QUERY_PARAMS_SQL =
					"SELECT\n" +
					"  bp.param_id paramId,\n" +
					"  bp.param_code paramCode,\n" +
					"  bp.param_value1 paramValue1,\n" +
					"  bp.description description,\n" +
					"  bp.explaining explaining,\n" +
					"  bp.param_title paramTitle\n" +
					"FROM\n" +
					"  srm_base_params bp\n" +
					"WHERE 1 = 1";

	private Integer paramId;
    private String paramCode;
    private String paramValue1;
    private String description;
    private String explaining;
    private String paramTitle;
    
	public Integer getParamId() {
		return paramId;
	}
	public void setParamId(Integer paramId) {
		this.paramId = paramId;
	}
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	public String getParamValue1() {
		return paramValue1;
	}
	public void setParamValue1(String paramValue1) {
		this.paramValue1 = paramValue1;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExplaining() {
		return explaining;
	}
	public void setExplaining(String explaining) {
		this.explaining = explaining;
	}
	public String getParamTitle() {
		return paramTitle;
	}
	public void setParamTitle(String paramTitle) {
		this.paramTitle = paramTitle;
	}
	
}

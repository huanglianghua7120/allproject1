package saaf.common.fmw.report.model.entities.readonly;

public class SaafChartsEntity_HI_RO {
    
    public static final String QUERY_SQL =
					"SELECT sc.charts_id              chartsId\n" +
					"      ,sc.charts_code            chartsCode\n" +
					"      ,sc.charts_name            chartsName\n" +
					"      ,sc.charts_title           chartsTitle\n" +
					"      ,sc.charts_type            chartsType\n" +
					"      ,slv.meaning               chartsTypeStr\n" +
					"      ,sc.demo_url               demoUrl\n" +
					"      ,sc.charts_script          chartsScript\n" +
					"      ,sc.dimensions             dimensions\n" +
					"      ,sc.dimension_length       dimensionLength\n" +
					"      ,sc.data_conversion_script dataConversionScript\n" +
					"      ,sc.description            description\n" +
					"  FROM saaf_charts        sc\n" +
					"      ,saaf_lookup_values slv\n" +
					" WHERE sc.charts_type = slv.lookup_code\n" +
					"   AND slv.lookup_type = 'charts_type'";

	public SaafChartsEntity_HI_RO() {
        super();
    }
    private Integer chartsId;
    private String chartsCode;
    private String chartsName;
    private String chartsTitle;
    private String chartsType;
    private String chartsTypeStr;
    private String demoUrl;
    private String chartsScript;
    private String demensions;
    private Integer dimensionLength;
    private String dataConversionScript;
    private String description;
	public Integer getChartsId() {
		return chartsId;
	}
	public void setChartsId(Integer chartsId) {
		this.chartsId = chartsId;
	}
	public String getChartsCode() {
		return chartsCode;
	}
	public void setChartsCode(String chartsCode) {
		this.chartsCode = chartsCode;
	}
	public String getChartsName() {
		return chartsName;
	}
	public void setChartsName(String chartsName) {
		this.chartsName = chartsName;
	}
	public String getChartsTitle() {
		return chartsTitle;
	}
	public void setChartsTitle(String chartsTitle) {
		this.chartsTitle = chartsTitle;
	}
	public String getChartsType() {
		return chartsType;
	}
	public void setChartsType(String chartsType) {
		this.chartsType = chartsType;
	}
	public String getChartsTypeStr() {
		return chartsTypeStr;
	}
	public void setChartsTypeStr(String chartsTypeStr) {
		this.chartsTypeStr = chartsTypeStr;
	}
	public String getDemoUrl() {
		return demoUrl;
	}
	public void setDemoUrl(String demoUrl) {
		this.demoUrl = demoUrl;
	}
	public String getChartsScript() {
		return chartsScript;
	}
	public void setChartsScript(String chartsScript) {
		this.chartsScript = chartsScript;
	}
	public String getDemensions() {
		return demensions;
	}
	public void setDemensions(String demensions) {
		this.demensions = demensions;
	}
	public Integer getDimensionLength() {
		return dimensionLength;
	}
	public void setDimensionLength(Integer dimensionLength) {
		this.dimensionLength = dimensionLength;
	}
	public String getDataConversionScript() {
		return dataConversionScript;
	}
	public void setDataConversionScript(String dataConversionScript) {
		this.dataConversionScript = dataConversionScript;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
}

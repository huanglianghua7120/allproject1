package saaf.common.fmw.bpm.model.entities.readonly;

public class ActReProcdefEntity_HI_RO {
	public static final String QUERY_SQL = " SELECT " +
			" 	entity.ID_ , " +
			" 	entity.REV_ , " +
			" 	entity.CATEGORY_ , " +
			" 	entity.NAME_ , " +
			" 	entity.KEY_ , " +
			" 	entity.VERSION_ , " +
			" 	entity.DEPLOYMENT_ID_ , " +
			" 	entity.RESOURCE_NAME_ , " +
			" 	entity.DGRM_RESOURCE_NAME_ , " +
			" 	entity.DESCRIPTION_ , " +
			" 	entity.HAS_START_FORM_KEY_ , " +
			" 	entity.HAS_GRAPHICAL_NOTATION_ , " +
			" 	entity.SUSPENSION_STATE_ , " +
			" 	entity.TENANT_ID_ " +
			" FROM " +
			" 	act_re_procdef entity " +
			" INNER JOIN( " +
			" 	SELECT " +
			" 		KEY_ , " +
			" 		max(version_) AS version_ " +
			" 	FROM " +
			" 		act_re_procdef " +
			" 	GROUP BY " +
			" 		key_ " +
			" ) temp ON temp.key_ = entity.key_ " +
			" AND temp.version_ = entity.version_ " +
			" where 1=1 ";
	
	private String id_;
	private Integer rev_;
	private String category_;
	private String name_;
	private String key_;
	private Integer version_;
	private String deploymentId_;
	private String resourceName_;
	private String dgrmResourceName_;
	private String description_;
	private Byte hasStartFormKey_;
	private Byte hasGraphicalNotation_;
	private Integer suspensionState_;
	private String tenantId_;

	public String getId_() {
		return id_;
	}

	public void setId_(String id_) {
		this.id_ = id_;
	}

	public Integer getRev_() {
		return rev_;
	}

	public void setRev_(Integer rev_) {
		this.rev_ = rev_;
	}

	public String getCategory_() {
		return category_;
	}

	public void setCategory_(String category_) {
		this.category_ = category_;
	}

	public String getName_() {
		return name_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}

	public String getKey_() {
		return key_;
	}

	public void setKey_(String key_) {
		this.key_ = key_;
	}

	public Integer getVersion_() {
		return version_;
	}

	public void setVersion_(Integer version_) {
		this.version_ = version_;
	}

	public String getDeploymentId_() {
		return deploymentId_;
	}

	public void setDeploymentId_(String deploymentId_) {
		this.deploymentId_ = deploymentId_;
	}

	public String getResourceName_() {
		return resourceName_;
	}

	public void setResourceName_(String resourceName_) {
		this.resourceName_ = resourceName_;
	}

	public String getDgrmResourceName_() {
		return dgrmResourceName_;
	}

	public void setDgrmResourceName_(String dgrmResourceName_) {
		this.dgrmResourceName_ = dgrmResourceName_;
	}

	public String getDescription_() {
		return description_;
	}

	public void setDescription_(String description_) {
		this.description_ = description_;
	}

	public Byte getHasStartFormKey_() {
		return hasStartFormKey_;
	}

	public void setHasStartFormKey_(Byte hasStartFormKey_) {
		this.hasStartFormKey_ = hasStartFormKey_;
	}

	public Byte getHasGraphicalNotation_() {
		return hasGraphicalNotation_;
	}

	public void setHasGraphicalNotation_(Byte hasGraphicalNotation_) {
		this.hasGraphicalNotation_ = hasGraphicalNotation_;
	}

	public Integer getSuspensionState_() {
		return suspensionState_;
	}

	public void setSuspensionState_(Integer suspensionState_) {
		this.suspensionState_ = suspensionState_;
	}

	public String getTenantId_() {
		return tenantId_;
	}

	public void setTenantId_(String tenantId_) {
		this.tenantId_ = tenantId_;
	}

}

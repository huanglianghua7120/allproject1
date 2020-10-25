package saaf.common.fmw.base.model.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * SrmBaseItemsIdEntity_HI Entity Object
 * Fri Jun 08 14:09:59 CST 2018  Auto Generate
 */
@Embeddable
public class SrmBaseItemsIdEntity_HI implements Serializable{
	private static final long serialVersionUID = -3304319243957837925L;
	private Integer itemId; //物料ID
	private Integer organizationId; //库存组织ID

	public SrmBaseItemsIdEntity_HI(){

	}

	public SrmBaseItemsIdEntity_HI(Integer itemId, Integer organizationId){
		this.itemId = itemId;
		this.organizationId = organizationId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (itemId == null ? 0 : itemId.hashCode());
		result = PRIME * result + (organizationId == null ? 0 : organizationId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof SrmBaseItemsIdEntity_HI){
			SrmBaseItemsIdEntity_HI key = (SrmBaseItemsIdEntity_HI)o ;
			if(this.itemId == key.getItemId() && this.organizationId.equals(key.getOrganizationId())){
				return true ;
			}
		}
		return false ;
	}
}

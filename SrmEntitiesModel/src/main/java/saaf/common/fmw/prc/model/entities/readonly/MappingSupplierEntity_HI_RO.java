package saaf.common.fmw.prc.model.entities.readonly;

import saaf.common.fmw.prc.model.entities.SrmPrcMappingItemsEntity_HI;
import saaf.common.fmw.prc.model.entities.SrmPrcMappingSuppliersEntity_HI;

import java.util.List;

public class MappingSupplierEntity_HI_RO {
    public MappingSupplierEntity_HI_RO() {
    }
    private SrmPrcMappingItemsEntity_HI itemsEntityHi;
    private List<SrmPrcMappingSuppliersEntity_HI> suppliersEntityHiList;

    public SrmPrcMappingItemsEntity_HI getItemsEntityHi() {
        return itemsEntityHi;
    }

    public void setItemsEntityHi(SrmPrcMappingItemsEntity_HI itemsEntityHi) {
        this.itemsEntityHi = itemsEntityHi;
    }

    public List<SrmPrcMappingSuppliersEntity_HI> getSuppliersEntityHiList() {
        return suppliersEntityHiList;
    }

    public void setSuppliersEntityHiList(List<SrmPrcMappingSuppliersEntity_HI> suppliersEntityHiList) {
        this.suppliersEntityHiList = suppliersEntityHiList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MappingSupplierEntity_HI_RO that = (MappingSupplierEntity_HI_RO) o;

        return itemsEntityHi != null ? itemsEntityHi.equals(that.itemsEntityHi) : that.itemsEntityHi == null;
    }

    @Override
    public int hashCode() {
        return itemsEntityHi != null ? itemsEntityHi.hashCode() : 0;
    }

    public MappingSupplierEntity_HI_RO(SrmPrcMappingItemsEntity_HI itemsEntityHi, List<SrmPrcMappingSuppliersEntity_HI> suppliersEntityHiList) {
        this.itemsEntityHi = itemsEntityHi;
        this.suppliersEntityHiList = suppliersEntityHiList;
    }


}

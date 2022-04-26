package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.CatalogData;

public class CatalogRecievedEvent {
    private CatalogData catalog;

    public CatalogData getCatalog() {
        return catalog;
    }

    public CatalogRecievedEvent(CatalogData catalog) {
        this.catalog = catalog;
    }
}

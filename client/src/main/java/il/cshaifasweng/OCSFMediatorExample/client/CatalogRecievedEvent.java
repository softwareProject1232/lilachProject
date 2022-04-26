package il.cshaifasweng.OCSFMediatorExample.client;

public class CatalogRecievedEvent {
    private Catalog catalog;

    public Catalog getCatalog() {
        return catalog;
    }

    public CatalogRecievedEvent(Catalog catalog) {
        this.catalog = catalog;
    }
}

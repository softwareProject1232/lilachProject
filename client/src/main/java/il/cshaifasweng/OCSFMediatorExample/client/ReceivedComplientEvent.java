package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ComplaintListData;

public class ReceivedComplientEvent {
    private ComplaintListData comp;

    public ComplaintListData getComplients() {
        return comp;
    }

    public ReceivedComplientEvent(ComplaintListData rcomp) {
        this.comp = rcomp;
    }
}
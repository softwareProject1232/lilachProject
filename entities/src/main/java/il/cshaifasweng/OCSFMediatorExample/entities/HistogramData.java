package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HistogramData implements Serializable {
    public List<Integer> complaintsNumber;

    public List<Integer> getComplaintsNumber() {
        return complaintsNumber;
    }

    public void setComplaintsNumber(List<Integer> complaintsNumber) {
        this.complaintsNumber = complaintsNumber;
    }

    public HistogramData() {
        String g = "gagag";
        this.complaintsNumber = new ArrayList<Integer>();
    }
}

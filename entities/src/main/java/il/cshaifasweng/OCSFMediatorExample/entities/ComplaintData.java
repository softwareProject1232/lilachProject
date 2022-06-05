package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class ComplaintData implements Serializable {
    public String complaintDescription;
    public UserData issuedBy;

    public ComplaintData(String com, UserData us) {
        complaintDescription = com;
        issuedBy = us;
    }

}

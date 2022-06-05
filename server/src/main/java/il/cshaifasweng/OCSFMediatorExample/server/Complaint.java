package il.cshaifasweng.OCSFMediatorExample.server;

import javax.persistence.*;

@Entity
@Table(name = "complaint")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public String complaintDescription;
    @ManyToOne(fetch = FetchType.LAZY)
    public User issuedBy;

    public Complaint() {}

    public String getComplaintDescription() {
        return complaintDescription;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }

    public User getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(User issuedBy) {
        this.issuedBy = issuedBy;
    }
}

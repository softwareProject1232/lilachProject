package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.ComplaintData;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "complaint")
public class Complaint {
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public String complaintDescription;
    public java.time.LocalDate date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public User issuedBy;

    public Complaint() {}
    public Complaint(ComplaintData com)
    {
        date=java.time.LocalDate.now();
        complaintDescription=com.complaintDescription;
        issuedBy=App.branches.SearchUserById(com.issuedBy.dbId);
    }
    public ComplaintData getComplaintData()
    {
        ComplaintData com = new ComplaintData(complaintDescription,issuedBy.getUserData());
        return com;
    }
    public String getComplaintDescription() {
        return complaintDescription;
    }
    public String Respond()
    {
        return "We are very sorry to hear your complaint, we are going to check the incident and understand what we did wrong\nThank you for your understanding";
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

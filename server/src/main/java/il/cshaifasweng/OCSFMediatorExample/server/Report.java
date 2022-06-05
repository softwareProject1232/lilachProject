package il.cshaifasweng.OCSFMediatorExample.server;


import java.util.List;

public class Report {

    public String report;

    public List<Complaint> reportComplaints()
    {
        return App.branches.getComplaints().complaints;
    }
}

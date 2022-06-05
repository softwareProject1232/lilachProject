package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.ComplaintData;
import il.cshaifasweng.OCSFMediatorExample.entities.OrderData;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class Complaints {
    public List<Complaint> complaints;
    public Complaints()
    {
        complaints=new ArrayList<Complaint>();
    }
    public void pullOrdersFromDB()
    {
        CriteriaBuilder builder = App.session.getCriteriaBuilder();
        CriteriaQuery<Complaint> query = builder.createQuery(Complaint.class);
        query.from(Complaint.class);
        List<Complaint> data = App.session.createQuery(query).getResultList();
        complaints.clear();
        complaints.addAll(data);
    }
    public void addComplaint(ComplaintData complaintData){
        App.session.beginTransaction();
        Complaint com = new Complaint(complaintData);
        App.session.save(com);
        App.session.flush();
        App.session.getTransaction().commit();
        complaints.add(com);
    }
}

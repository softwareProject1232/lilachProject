package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.ComplaintData;
import il.cshaifasweng.OCSFMediatorExample.entities.ComplaintListData;

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
        App.SafeStartTransaction();
        Complaint com = new Complaint(complaintData);
        App.session.save(com);
        App.session.flush();
        App.SafeCommit();
        complaints.add(com);
    }
    public ComplaintData GetComplaintData(Complaint c)
    {
        ComplaintData com=new ComplaintData(c.complaintDescription,c.issuedBy.getUserData());
        return com;
    }
    public ComplaintListData GetComplaintListData()
    {
        List <ComplaintData> list= new ArrayList<ComplaintData>();
        for(Complaint c: complaints)
        {
            ComplaintData t=GetComplaintData(c);
            list.add(t);
        }
        return new ComplaintListData(list);
    }

    public void removeComplaint(int id) {
        for(Complaint c: complaints)
        {
            if(c.getId()==id)
            {
                App.SafeStartTransaction();
                complaints.remove(c);
                App.session.delete(c);
                App.session.flush();
                App.SafeCommit();
            }
        }
    }
}

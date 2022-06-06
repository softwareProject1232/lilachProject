package il.cshaifasweng.OCSFMediatorExample.server;


import il.cshaifasweng.OCSFMediatorExample.entities.HistogramData;

import java.time.LocalDate;

public class Report {

    public int getIncome(String branchName)
    {
        if(branchName.equals("network")) {
            int sum = 0;
            for(Branch b: App.branches.branchList)
            {
                for(Order o: b.orders.orderList)
                {
                    sum+= o.price;
                }
            }
            return sum;
        }
        else
        {
            Branch b=App.branches.GetBranchByName(branchName);
            if(b!=null) {
                int s = 0;
                for (Order o : b.orders.orderList) {
                    s += o.price;
                }
                return s;
            }
            return 0;
        }

    }
    public Orders getOrders(String branchName)
    {
        if(branchName.equals("network"))
        {
            Orders o=new Orders();
            for(Branch b:App.branches.branchList)
            {
                o.orderList.addAll(b.orders.orderList);
            }
            return o;
        }
        else {
            Branch b = App.branches.GetBranchByName(branchName);
            if(b!=null)
            {
                return b.getOrders();
            }
            return new Orders();
        }
    }
    public HistogramData reportComplaints()
    {
        HistogramData hs= new HistogramData();
        for(int i=0;i<8;i++)
        {
            hs.complaintsNumber.add(0);
        }
        java.time.LocalDate date = LocalDate.now();
        for(int i=0;i<=7;i++) {
            for (Complaint c : App.branches.complaints.complaints) {
                if (date.minusDays(i).isAfter(c.getDate())) {
                    hs.complaintsNumber.set(i,hs.complaintsNumber.get(i)+1);
                }
            }
        }
        return hs;

    }
}

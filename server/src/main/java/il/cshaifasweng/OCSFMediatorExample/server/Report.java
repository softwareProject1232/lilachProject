package il.cshaifasweng.OCSFMediatorExample.server;


import il.cshaifasweng.OCSFMediatorExample.entities.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Report {

    public static int getIncome(String branchName)
    {
        if(branchName.equals("network")) {
            int sum = 0;
            for(Branch b: App.branches.branchList)
            {
                sum += b.income;
            }
            return sum;
        }
        else
        {
            Branch b=App.branches.GetBranchByName(branchName);
            if(b!=null) {
                return b.income;
            }
            return 0;
        }

    }

    public static IncomeHistogramData getIncomeHistogramData()
    {
        IncomeHistogramData incomeHistogramData = new IncomeHistogramData();
        for(Branch b: App.branches.branchList)
        {
            incomeHistogramData.addBranch(b.name,b.income);
        }
        return incomeHistogramData;
    }

    public static ReportOrdersByItems getOrdersReport(String branchName,int days)
    {
        java.time.LocalDate date = LocalDate.now();
        date = date.minusDays(days + 1);
        if(branchName.equals("network"))
        {
            int i=0;
            List<ReportItem> report=new ArrayList<ReportItem>();
            for(Item item: App.catalog.items)
            {
                ReportItem tep=new ReportItem(item.GetItemData(),0);
                for(Branch b:App.branches.branchList)
                {
                    for(Order o:b.orders.orderList)
                    {
                        for(BasketItem basket:o.items)
                        {
                            for(Item tempitem:basket.listItems)
                            {
                                if(tempitem.getName().equals(item.getName())&&date.isBefore(o.orderDate.toLocalDate()))
                                    tep.timesOrdered=tep.timesOrdered+1;
                            }
                        }
                    }
                }
                report.add(tep);
            }
            return new ReportOrdersByItems(report);
        }
        else {
            Branch b = App.branches.GetBranchByName(branchName);
            if(b!=null)
            {
                int i=0;
                List<ReportItem> report=new ArrayList<ReportItem>();
                for(Item item: App.catalog.items)
                {
                    ReportItem tep=new ReportItem(item.GetItemData(),0);
                        for(Order o:b.orders.orderList) {
                            for (BasketItem basket : o.items) {
                                for (Item tempitem : basket.listItems) {
                                    if (tempitem.getName().equals(item.getName())&&date.isBefore(o.orderDate.toLocalDate()))
                                        tep.timesOrdered = tep.timesOrdered + 1;
                                }
                            }
                        }
                    report.add(tep);
                }
                return new ReportOrdersByItems(report);
            }
            return new ReportOrdersByItems(null);//here we send back null in case of wrong branch
        }
    }
    public static HistogramData reportComplaints(int days)
    {
        days++; //to include the current day
        HistogramData hs= new HistogramData();
        for(int i=0;i<days;i++)
        {
            hs.complaintsNumber.add(0);
        }
        java.time.LocalDate date = LocalDate.now();
        for(int i=0;i<days;i++) {
            for (Complaint c : App.branches.complaints.complaints) {
                if (date.minusDays(i).isEqual(c.getDate())) {
                    hs.complaintsNumber.set(i,hs.complaintsNumber.get(i)+1);
                }
            }
        }
        return hs;
    }
    public static ComplaintListData GetAllComplaints()
    {
        return App.branches.complaints.GetComplaintListData();
    }
}

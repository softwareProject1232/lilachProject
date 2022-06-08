package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IncomeHistogramData implements Serializable {
    public List<Integer> incomeList;
    public BranchNameData branchNameList;

    public List<Integer> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Integer> incomeList) {
        this.incomeList = incomeList;
    }

    public IncomeHistogramData() {
        this.incomeList = new ArrayList<Integer>();
        this.branchNameList = new BranchNameData();
    }

    public void addBranch(String branchName, int income) {
        this.branchNameList.bracnhList.add(branchName);
        this.incomeList.add(income);
    }
}

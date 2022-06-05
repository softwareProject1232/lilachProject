package il.cshaifasweng.OCSFMediatorExample.entities;


import java.io.Serializable;
import java.util.List;

public class BranchNameData implements Serializable {
    List<String> bracnhList;

    public BranchNameData(List<String> bracnhList) {
        this.bracnhList = bracnhList;
    }

    public List<String> getBracnhList() {
        return bracnhList;
    }

    public void setBracnhList(List<String> bracnhList) {
        this.bracnhList = bracnhList;
    }
}

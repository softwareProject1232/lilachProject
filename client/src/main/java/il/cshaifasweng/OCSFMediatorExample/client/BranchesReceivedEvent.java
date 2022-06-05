package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.BranchNameData;
import il.cshaifasweng.OCSFMediatorExample.entities.UserData;

import java.util.List;

public class BranchesReceivedEvent {
    private BranchNameData branchNames;

    public BranchNameData getBranches() {
        return branchNames;
    }

    public BranchesReceivedEvent(BranchNameData branchNames) {
        this.branchNames = branchNames;
    }
}

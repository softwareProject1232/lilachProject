package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserListData  implements Serializable {
    public List<UserData> users;

    public UserListData() {
        users = new ArrayList<UserData>();
    }

    public void add(UserListData other)
    {
        users.addAll(other.users);
    }
}

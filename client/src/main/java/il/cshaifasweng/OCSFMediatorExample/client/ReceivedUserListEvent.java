package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.UserListData;
public class ReceivedUserListEvent {
    private UserListData user;

    public UserListData getUsers() {
        return user;
    }

    public ReceivedUserListEvent(UserListData user) {
        this.user = user;
    }
}

package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.UserData;

public class RegisteredReceivedEvent {
    private UserData user;

    public UserData getUser() {
        return user;
    }

    public boolean didSuccessfullyRegister() {
        //check if all strings in user are not empty and all integers are not 0
        return user.getUsername().length() > 0 && user.getPassword().length() > 0 && user.getEmail().length() > 0 && user.getCreditCard().length() > 0 && user.getId().length() > 0 && user.getType() > 0;
    }
    public RegisteredReceivedEvent(UserData user) {
        this.user = user;
    }
}

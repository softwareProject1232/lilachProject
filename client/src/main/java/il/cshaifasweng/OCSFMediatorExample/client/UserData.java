package il.cshaifasweng.OCSFMediatorExample.client;

public class UserData {
    public String username;
    public String password;
    public String Email;
    public boolean type;
    public String CreditCard;
    public String id;

    public UserData(String username, String password, String email, boolean type, String creditCard, String id) {
        this.username = username;
        this.password = password;
        Email = email;
        this.type = type;
        CreditCard = creditCard;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getCreditCard() {
        return CreditCard;
    }

    public void setCreditCard(String creditCard) {
        CreditCard = creditCard;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

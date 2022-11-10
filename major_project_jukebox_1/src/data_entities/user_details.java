
package data_entities;

public class user_details
{
    private int user_Id;
    private String user_Name;
    private String password;
    private String email;
    private long phone_No;

    public user_details(int user_Id, String user_Name, String password, String email, long phone_No) {
        this.user_Id = user_Id;
        this.user_Name = user_Name;
        this.password = password;
        this.email = email;
        this.phone_No = phone_No;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone_No() {
        return phone_No;
    }

    public void setPhone_No(long phone_No) {
        this.phone_No = phone_No;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "user_Id=" + user_Id +
                ", user_Name='" + user_Name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone_No=" + phone_No +
                '}';
    }
}


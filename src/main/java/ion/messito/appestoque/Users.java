package ion.messito.appestoque;

public class Users {
    int id;
    String fullName;
    String login;
    String pass;
    String email;
    int acess_type;

    public Users(String fullName, String login, String pass, String email, int acess_type) {
        this.setFullName(fullName);
        this.setLogin(login);
        this.setPass(pass);
        this.setEmail(email);
        this.setAcess_type(1);
    }

    @Override
    public String toString() {
        return "id=" + id 
                + " fullName=" + fullName
                + " login=" + login 
                + " pass=" + pass 
                + " email=" + email 
                + " acess_type=" + acess_type;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getAcess_type() {
        return acess_type;
    }
    public void setAcess_type(int acess_type) {
        this.acess_type = acess_type;
    }
     
 
}

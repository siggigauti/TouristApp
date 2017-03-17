package is.siggigauti.touristapp.model;

public class User {

    private  int ID;
    private String name;
    private String nationality;
    private String password;
    private String email;


    public int getID() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
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

}


package entities;

/**
 *
 * @author andressa
 */
public class User {

    private String name;
    private String email;
    private String phone;
    private String password;
    private String cpf;
    private String cep;
    private String birthDate;
    private String gender;
    boolean admin;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getCPF() {
        return cpf;
    }

    public String getCEP() {
        return cep;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public void setCEP(String cep) {
        this.cep = cep;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}

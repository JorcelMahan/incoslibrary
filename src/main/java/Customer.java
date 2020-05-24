public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private int cellphone;
    private String email;
    private int nit;
    private String bussinessName;

    public Customer() {
    }

    public Customer(int id, String firstName, String lastName, int cellphone, String email, int nit, String bussinessName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellphone = cellphone;
        this.email = email;
        this.nit = nit;
        this.bussinessName = bussinessName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCellphone() {
        return cellphone;
    }

    public void setCellphone(int cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getBussinessName() {
        return bussinessName;
    }

    public void setBussinessName(String bussinessName) {
        this.bussinessName = bussinessName;
    }
}

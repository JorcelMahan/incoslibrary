public class Supplier {
    private int id;
    private String companyName;
    private String contactName;
    private String city;
    private String country;
    private int cellphone;
    private  String email;

    public Supplier() {
    }

    public Supplier(int id, String companyName, String contactName, String city, String country, int cellphone, String email) {
        this.id = id;
        this.companyName = companyName;
        this.contactName = contactName;
        this.city = city;
        this.country = country;
        this.cellphone = cellphone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
}


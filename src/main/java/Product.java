public class Product {
    private int id;
    private String productName;
    private int supplierId;
    private float unitPrice;
    private int stock;
    private boolean isDiscontinued;

    private String supplierCompanyName;



    public Product() {
    }

    public Product(int id, String productName, int suppliedId, float unitPrice, int stock, boolean isDiscontinued) {
        this.id = id;
        this.productName = productName;
        this.supplierId = suppliedId;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.isDiscontinued = isDiscontinued;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int suppliedId) {
        this.supplierId = suppliedId;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isDiscontinued() {
        return isDiscontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        isDiscontinued = discontinued;
    }

    public String getSupplierCompanyName() {
        return supplierCompanyName;
    }

    public void setSupplierCompanyName(String supplierCompanyName) {
        this.supplierCompanyName = supplierCompanyName;
    }
}

import io.javalin.http.Context;

import java.sql.*;
import java.util.ArrayList;

public class ProductController {
    static String connectionUrl = "jdbc:mysql://db4free.net:3306/testlibrary?serverTimezone=UTC";

    public static void getAllProducts(Context ctx) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionUrl, "incosjohan", "incos123");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        ArrayList<Product> products = new ArrayList<>();
        assert conn != null;
        String query = " SELECT *, supplier.company_name FROM product INNER JOIN supplier ON product.supplier_id = supplier.id WHERE product.is_discontinued=false";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String productName = rs.getString("product_name");
                int supplierId = rs.getInt("supplier_id");
                float unitPrice = rs.getFloat("unit_price");
                int stock = rs.getInt("stock");
                boolean isDiscontinued = rs.getBoolean("is_discontinued");
                String supplierCompanyName = rs.getString("company_name");
                Product product = new Product(id, productName, supplierId, unitPrice, stock, isDiscontinued);
                product.setSupplierCompanyName(supplierCompanyName);
                products.add(product);
            }
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ctx.json(products);
    }

    public static void create(Context ctx) {
        Connection conn = null;
        Product product = ctx.bodyAsClass(Product.class);
        try {
            conn = DriverManager.getConnection(connectionUrl, "incosjohan", "incos123");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        assert conn != null;
        String query = " INSERT INTO product (product_name, supplier_id, unit_price, stock) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getSupplierId());
            ps.setFloat(3, product.getUnitPrice());
            ps.setInt(4, product.getStock());
            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ctx.result("Product create with success");
    }

    public static void getOne(Context ctx) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionUrl, "incosjohan", "incos123");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        ArrayList<Product> products = new ArrayList<>();
        assert conn != null;
        String query = " SELECT *, supplier.company_name FROM product INNER JOIN supplier ON product.supplier_id = supplier.id WHERE product.id=?";
        Product product = null;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(ctx.pathParam("id")));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int supplierId = rs.getInt("supplier_id");
                String productName = rs.getString("product_name");
                float unitPrice = rs.getFloat("unit_price");
                int stock = rs.getInt("stock");
                boolean isDiscontinued = rs.getBoolean("is_discontinued");
                String supplierCompanyName = rs.getString("company_name");
                product = new Product(id, productName, supplierId, unitPrice, stock, isDiscontinued);
                product.setSupplierCompanyName(supplierCompanyName);
            }
            ps.close();
            rs.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assert product != null;
        ctx.json(product);
    }

    public static void update(Context ctx) {
        Connection conn = null;
        Product product = ctx.bodyAsClass(Product.class);
        try {
            conn = DriverManager.getConnection(connectionUrl, "incosjohan", "incos123");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        assert conn != null;
        String query = " UPDATE product SET product_name=?, supplier_id=?, unit_price=?, stock=? WHERE id=? ";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getSupplierId());
            ps.setFloat(3, product.getUnitPrice());
            ps.setInt(4, product.getStock());
            ps.setInt(5, Integer.parseInt(ctx.pathParam("id")));
            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ctx.result("User edit with success");
    }

    public static void delete(Context ctx) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionUrl, "incosjohan", "incos123");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        assert conn != null;
        String query = " UPDATE product SET product.is_discontinued=? WHERE id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, true);
            ps.setInt(2, Integer.parseInt(ctx.pathParam("id")));
            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ctx.result("Delete with success");
    }
}

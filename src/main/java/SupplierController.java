import io.javalin.http.Context;

import java.sql.*;
import java.util.ArrayList;

public class SupplierController {
    static String connectionUrl = "jdbc:mysql://db4free.net:3306/testlibrary?serverTimezone=UTC";

    public static void create(Context ctx) {
        Supplier supplier = ctx.bodyAsClass(Supplier.class);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionUrl, "incosjohan", "incos123");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assert conn != null;
        String query = " INSERT INTO supplier (company_name, contact_name, city, country, cellphone, email)" + " VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, supplier.getCompanyName());
            ps.setString(2, supplier.getContactName());
            ps.setString(3, supplier.getCity());
            ps.setString(4, supplier.getCountry());
            ps.setInt(5, supplier.getCellphone());
            ps.setString(6, supplier.getEmail());
            ps.execute();
            conn.close();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ctx.result("Supplier create with success");
    }

    public static void getAll(Context ctx) {
        Connection conn = null;
        try {
             conn = DriverManager.getConnection(connectionUrl, "incosjohan", "incos123");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        ArrayList<Supplier> suppliers = new ArrayList<>();
        assert conn != null;
        String query = " SELECT * FROM supplier WHERE is_habil=true";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String companyName = rs.getString("company_name");
                String contactName = rs.getString("contact_name");
                String city = rs.getString("city");
                String country = rs.getString("country");
                int cellphone = rs.getInt("cellphone");
                String email = rs.getString("email");
                Supplier supplier = new Supplier(id, companyName, contactName, city, country, cellphone, email);
                suppliers.add(supplier);
            }
            conn.close();
            rs.close();
            ps.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        ctx.json(suppliers);
    }

    public static void getOne(Context ctx) {
        Connection conn = null;
        try {
             conn = DriverManager.getConnection(connectionUrl, "incosjohan", "incos123");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        assert conn != null;
        String query = " SELECT * FROM supplier WHERE id=?";
        Supplier supplier = null;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(ctx.pathParam("id")));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String companyName = rs.getString("company_name");
                String contactName = rs.getString("contact_name");
                String city = rs.getString("city");
                String country = rs.getString("country");
                int cellphone = rs.getInt("cellphone");
                String email = rs.getString("email");
                supplier = new Supplier(id, companyName, contactName, city, country, cellphone, email);
            }
            ps.close();
            rs.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assert supplier != null;
        ctx.json(supplier);
    }

    public static void update(Context ctx) {
        Connection conn = null;
        Supplier supplier = ctx.bodyAsClass(Supplier.class);
        try {
             conn = DriverManager.getConnection(connectionUrl, "incosjohan", "incos123");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        assert conn != null;
        String query = " UPDATE supplier SET company_name=?, contact_name=?, city=?, country=?, cellphone=?, email=? WHERE id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, supplier.getCompanyName());
            ps.setString(2, supplier.getContactName());
            ps.setString(3, supplier.getCity());
            ps.setString(4, supplier.getCountry());
            ps.setInt(5, supplier.getCellphone());
            ps.setString(6, supplier.getEmail());
            ps.setInt(7, Integer.parseInt(ctx.pathParam("id")));
            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ctx.result("Supplier edit with success");
    }

    public static void delete(Context ctx) {
        Connection conn = null;
        try {
             conn = DriverManager.getConnection(connectionUrl, "incosjohan", "incos123");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        assert conn != null;
        String query = " UPDATE supplier SET is_habil=? WHERE id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, false);
            ps.setInt(2, Integer.parseInt(ctx.pathParam("id")));
            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ctx.result("Supplier delete with success");
    }
}

import io.javalin.http.Context;

import java.sql.*;
import java.util.ArrayList;
public  class UserController {
    static String connectionUrl = "jdbc:mysql://localhost/library?serverTimezone=UTC";
    public UserController() {
    }
    public static void getAllUsers(Context ctx)  {
        ArrayList<User> users = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionUrl, "root", "");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        assert conn != null;
        String query = " SELECT * FROM users ";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                User user = new User(id, name, lastname, username, password);
                users.add(user);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        ctx.json(users);
    };

    public static void create(Context ctx) {
        User user = ctx.bodyAsClass(User.class);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionUrl, "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assert conn != null;
        String query = " INSERT INTO users (name, lastname, username, password)" + " VALUES (?, ?, ?, ?)";
       try {
           PreparedStatement ps = conn.prepareStatement(query);
           ps.setString(1, user.getName());
           ps.setString(2, user.getLastname());
           ps.setString(3, user.getUsername());
           ps.setString(4, user.getPassword());
           ps.execute();
           conn.close();
       } catch (SQLException throwable) {
           throwable.printStackTrace();
       }

        ctx.result("User create with success");
    }

    public static void getOne(Context ctx) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionUrl, "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assert conn != null;
        String query = " SELECT * FROM users WHERE id=? ";
        User user = null;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(ctx.pathParam("id")));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                user = new User(id, name, lastname, username, password);
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assert user != null;
        ctx.json(user);
    }

    public static void update(Context ctx) {
        User user = ctx.bodyAsClass(User.class);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionUrl, "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assert conn != null;
        String query = " UPDATE users SET name=?, lastname=?, username=?, password=? WHERE id=? ";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setInt(5, Integer.parseInt(ctx.pathParam("id")));
            ps.execute();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ctx.result("User edit with success");
    }

    public static void delete(Context ctx) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionUrl, "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assert conn != null;
        try {
            String query = " DELETE FROM users WHERE id=? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(ctx.pathParam("id")));
            ps.execute();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ctx.result("Delete User with success");
    }
}

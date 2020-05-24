
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.patch;
import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.put;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.defaultContentType = "application/json";
            config.enableCorsForAllOrigins();
        }).start(getHerokuAssignedPort());
        app.routes(() -> {
            path("users", () -> {
                get(UserController::getAllUsers);
                post(UserController::create);
                path(":id", () -> {
                    get(UserController::getOne);
                    patch(UserController::update);
                    delete(UserController::delete);
                });
            });
            path("products", () -> {
                get(ProductController::getAllProducts);
                post(ProductController::create);
                path(":id", () -> {
                    get(ProductController::getOne);
                    put(ProductController::update);
                    patch(ProductController::delete);
                });
            });
            path("supplier", () -> {
                get(SupplierController::getAll);
                post(SupplierController::create);
                path(":id", () -> {
                    get(SupplierController::getOne);
                    put(SupplierController::update);
                    patch(SupplierController::delete);
                });
            });
            path("customer", () -> {
                get(CustomerController::getAll);
                post(CustomerController::create);
            });
            path("order", () -> {
                get(OrderController::getAll);
                post(OrderController::create);
            });
            path("order-item", () -> {
                get(OrderItemController::getAll);
                post(OrderItemController::create);
            });
        });
    }

    private static int getHerokuAssignedPort(){
        String herokuPort = System.getenv("PORT");
        if(herokuPort != null) {
            return Integer.parseInt(herokuPort);
        }
        return 7000;
    }
}

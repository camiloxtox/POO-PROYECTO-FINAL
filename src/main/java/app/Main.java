

package app;

import controller.AuthorController;
import controller.BookController;
import controller.CategoryController;
import controller.CityController;
import controller.CountryController;
import controller.DepartmentController;
import controller.EventController;
import controller.LibraryController;
import controller.LoanController;
import controller.MessageController;
import controller.NotificationController;
import controller.PaymentController;
import controller.PermissionController;
import controller.PublisherController;
import controller.ReservationController;
import controller.ReviewController;
import controller.RoleController;
import controller.StudentController;
import controller.TeacherController;
import controller.UserController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7002);
        BookController.configureRoutes(app);
        AuthorController.configureRoutes(app);
        CityController.configureRoutes(app);
        CategoryController.configureRoutes(app);
        UserController.configureRoutes(app);
        CountryController.configureRoutes(app);
        DepartmentController.configureRoutes(app);
        EventController.configureRoutes(app);
        LibraryController.configureRoutes(app);
        LoanController.configureRoutes(app);
        MessageController.configureRoutes(app);
        NotificationController.configureRoutes(app);
        PaymentController.configureRoutes(app);
        PermissionController.configureRoutes(app);
        PublisherController.configureRoutes(app);
        ReservationController.configureRoutes(app);
        ReviewController.configureRoutes(app);
        RoleController.configureRoutes(app);
        StudentController.configureRoutes(app);
        TeacherController.configureRoutes(app);
    }
}

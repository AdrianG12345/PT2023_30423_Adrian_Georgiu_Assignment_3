package mvc;

import mvc.controller.Controller;
import mvc.models.DatabaseConnection;
import mvc.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        DatabaseConnection databaseConnection = new DatabaseConnection();

        Controller controller = new Controller(view, databaseConnection);
    }
}

package mvc;

import mvc.bussinessLogic.Controller;
import mvc.models.DatabaseConnection;
import mvc.presentation.View;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        DatabaseConnection databaseConnection = new DatabaseConnection();

        Controller controller = new Controller(view, databaseConnection);
    }
}

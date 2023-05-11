package mvc;

import mvc.controller.Controller;
import mvc.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new View();

        Controller controller = new Controller(view);
    }
}

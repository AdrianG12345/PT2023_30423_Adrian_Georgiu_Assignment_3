package mvc;

import mvc.presentation.Controller;
import mvc.presentation.View;

public class Main {
    public static void main(String[] args) {
        View view = new View();

        Controller controller = new Controller(view);
    }
}

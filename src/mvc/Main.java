package mvc;

import mvc.presentation.Controller;
import mvc.presentation.View;

/**
 * runs the program
 */
public class Main {
    public static void main(String[] args) {
        /**
         * main interface required
         */
        View view = new View();
        Controller controller = new Controller(view);
    }
}

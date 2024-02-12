package mynotebook;

import mynotebook.controller.UserController;
import mynotebook.model.repository.impl.UserRepository;
import mynotebook.util.DBConnector;
import mynotebook.view.UserView;


public class Main {
    public static void main(String[] args) {
        DBConnector.createDB();
        UserRepository repository = new UserRepository();
        UserController controller = new UserController(repository);
        UserView view = new UserView(controller);
        view.run();
    }
}

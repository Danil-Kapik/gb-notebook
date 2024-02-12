package mynotebook.view;

import mynotebook.controller.UserController;
import mynotebook.model.User;

import java.util.Scanner;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        while (true) {
            printMenu();
            int choice = Integer.parseInt(prompt("Выберите команду: "));

            switch (choice) {
                case 1:
                    userController.saveUser(userController.createUser());
                    break;
                case 2:
                    String id = prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    userController.readAllUser();
                    break;
                case 4:
                    String userId = prompt("Enter user id: ");
                    userController.updateUser(userId, userController.createUser());
                    break;
                case 5:
                    String idUser = prompt("Enter user id: ");
                    userController.deleteUser(Long.parseLong(idUser));
                    break;
                case 6:
                    System.out.println("Выход");
                    return;
                default:
                    System.out.println("Неверная команда. Попробуйте еще раз.");
            }
        }
    }

    private void printMenu() {
        System.out.println("Меню:");
        System.out.println("1. Создать пользователя");
        System.out.println("2. Прочитать пользователя");
        System.out.println("3. Показать всех пользователей");
        System.out.println("4. Обновить пользователя");
        System.out.println("5. Удалить пользователя");
        System.out.println("6. Выйти");
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}

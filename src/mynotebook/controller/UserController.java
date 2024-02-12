package mynotebook.controller;

import mynotebook.model.User;
import mynotebook.model.repository.GBRepository;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserController {
    private final GBRepository repository;

    public UserController(GBRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.create(user);
    }

    public void readAllUser() {
        List<User> users = repository.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    public User readUser(Long userId) throws Exception {
        List<User> users = repository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                return user;
            }
        }
        throw new RuntimeException("User not found");
    }

    public User createUser() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        return new User(firstName, lastName, phone);
    }

    public void updateUser(String userId, User update) {
        update.setId(Long.parseLong(userId));
        repository.update(Long.parseLong(userId), update);
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    public void deleteUser(Long userId) {
        List<User> users = repository.findAll();
        User userToDelete = null;
        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                userToDelete = user;
                break;
            }
        }
        if (userToDelete != null) {
            users.remove(userToDelete);
            repository.write(users);
            System.out.println("User deleted: " + userToDelete);
        } else {
            throw new RuntimeException("User not found");
        }
    }
}

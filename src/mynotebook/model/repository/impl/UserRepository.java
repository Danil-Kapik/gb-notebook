package mynotebook.model.repository.impl;

import mynotebook.model.User;
import mynotebook.model.repository.GBRepository;
import mynotebook.util.DBConnector;
import mynotebook.util.mapper.impl.UserMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static mynotebook.util.DBConnector.DB_PATH;

public class UserRepository extends GBRepository {
    private final UserMapper mapper;


    public UserRepository() {
        this.mapper = new UserMapper();

    }

    @Override
    public User create(User user) {
        List<User> users = findAll();
        long max = 0L;
        for (User u : users) {
            long id = u.getId();
            if (max < id){
                max = id;
            }
        }
        long next = max + 1;
        user.setId(next);
        users.add(user);
        write(users);
        return user;
    }

    @Override
    public Optional<User> update(Long userId, User update) {
        List<User> users = findAll();
        User editUser = users.stream()
                .filter(u -> u.getId()
                        .equals(userId))
                .findFirst().orElseThrow(() -> new RuntimeException("User not found"));
        editUser.setFirstName(update.getFirstName());
        editUser.setLastName(update.getLastName());
        editUser.setPhone(update.getPhone());
        write(users);
        return Optional.of(update);
    }

    public void write(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User u: users) {
            lines.add(mapper.toInput(u));
        }
        saveAll(lines);
    }

    public void saveAll(List<String> data) {
        try (FileWriter writer = new FileWriter(DBConnector.DB_PATH, false)) {
            for (String line : data) {
                writer.write(line);
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> findAll() {
        List<String> lines = readAll();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.toOutput(line));
        }
        return users;
    }

    @Override
    public List<String> readAll() {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(DB_PATH);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            if (line != null) {
                lines.add(line);
            }
            while (line != null) {
                line = reader.readLine();
                if (line != null) {
                    lines.add(line);
                }
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}

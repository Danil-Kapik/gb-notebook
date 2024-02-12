package mynotebook.model.repository;

import mynotebook.model.User;

import java.util.List;
import java.util.Optional;

public abstract class GBRepository {
    public abstract User create (User user);
    public abstract List<String> readAll();

    public abstract Optional<User> update(Long userId, User update);

    public List<User> findAll() {
        return null;
    }

    public void write(List<User> users) {
    }
}

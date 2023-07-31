package code.dao;

import code.model.User;

import java.util.List;

public interface UserDao {
    public void save(User user);
    public void delete(long id);
    public User get(long id);
    public void update(User user);
    public List<User> getAll();
}


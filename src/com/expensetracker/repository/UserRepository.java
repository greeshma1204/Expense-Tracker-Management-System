package com.expensetracker.repository;

import java.util.ArrayList;
import java.util.List;

import com.expensetracker.model.User;
import com.expensetracker.util.FileManager;

public class UserRepository {

    private List<User> users;
    private FileManager fileManager;

    public UserRepository(
            FileManager fileManager) {

        this.fileManager =
                fileManager;

        this.users =
                new ArrayList<>(
                        fileManager.loadUsers()
                );
    }

    public void addUser(User user) {

        users.add(user);

        fileManager.saveUsers(users);
    }

    public User findUserById(int userId) {

        for (User user : users) {

            if (user.getUserId()
                    == userId) {

                return user;
            }
        }

        return null;
    }

    public User findUserByEmail(
            String email) {

        for (User user : users) {

            if (user.getEmail()
                    .equalsIgnoreCase(email)) {

                return user;
            }
        }

        return null;
    }

    public ArrayList<User> findAllUsers() {

        return new ArrayList<>(users);
    }

    public boolean updateUser(User user) {

        for (int i = 0;
                i < users.size();
                i++) {

            if (users.get(i).getUserId()
                    == user.getUserId()) {

                users.set(i, user);

                fileManager.saveUsers(
                        users
                );

                return true;
            }
        }

        return false;
    }

    public boolean deleteUser(int userId) {

        for (int i = 0;
                i < users.size();
                i++) {

            if (users.get(i).getUserId()
                    == userId) {

                users.remove(i);

                fileManager.saveUsers(
                        users
                );

                return true;
            }
        }

        return false;
    }
}
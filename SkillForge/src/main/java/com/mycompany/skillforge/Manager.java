package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.Random;
        
public class Manager {
    private static ArrayList<User> users = new ArrayList<>();
    private static User loggedInUser = null;

    
    public static String signup(String username, String email, String password, String role) {

        if (!InputValidator.isNotEmpty(username) ||
            !InputValidator.isNotEmpty(email) ||
            !InputValidator.isValidEmail(email) ||
            !InputValidator.isValidPassword(password)) {
            return "Invalid Input!";
        }

       
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email))
                return "Email already exists!";
        }

        Random rand = new Random();
        String userId = String.valueOf(1000 + rand.nextInt(9000));
        String hashed = Password.hashPassword(password);

        User newUser;

        if (role.equalsIgnoreCase("student")) {
            newUser = new Student(userId, username, email, hashed);
        } else {
            newUser = new Instructor(userId, username, email, hashed);
        }

        users.add(newUser);

        return "Signup successful!";
    }

    
    public static String login(String email,String password,String role) {
        String hashed = Password.hashPassword(password);

        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getPasswordHash().equals(hashed)&&u.getRole().equalsIgnoreCase(role)) {
                loggedInUser = u;
                return "Login successful ";
            }
        }

        return "Invalid email or password or role";
    }

    public static void logout() {
        loggedInUser = null;
    }

    
}

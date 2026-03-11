package Ex3.Bai3;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        UserRepository repo = new UserRepository();

        Optional<User> optionalUser = repo.findUserByUsername("alice");

        String loginMessage = optionalUser
                .map(user -> "Welcome " + user.username())
                .orElse("Guest login");
        System.out.println(loginMessage);

        System.out.println("---");
    }
}
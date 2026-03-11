package Ex3.Bai4;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User(1, "nguyenvana", "a1@gmail.com"),
                new User(2, "lethib", "b@gmail.com"),
                new User(3, "nguyenvana", "a2_moi@gmail.com"),
                new User(4, "tranc", "c@gmail.com"),
                new User(5, "lethib", "b_fake@gmail.com")
        );

        System.out.println("\n--- Danh sách không trùng lặp ---");

        List<User> uniqueUsersByMap = users.stream()
                .collect(Collectors.toMap(
                        User::username,
                        user -> user,
                        (existing, replacement) -> existing
                ))
                .values()
                .stream()
                .toList();

        uniqueUsersByMap.forEach(System.out::println);
    }
}

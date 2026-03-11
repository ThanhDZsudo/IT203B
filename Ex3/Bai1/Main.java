package Ex3.Bai1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User("alice", "alice@example.com", Status.ACTIVE),
                new User("bob", "bob@example.com", Status.INACTIVE),
                new User("charlie", "charlie@example.com", Status.ACTIVE)
        );

        System.out.println("Danh sách người dùng:");
        users.forEach(System.out::println);
    }
}

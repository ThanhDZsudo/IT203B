package Ex3.Bai3;

import java.util.List;
import java.util.Optional;

class UserRepository {
    private final List<User> users = List.of(
            new User("alice", "alice@gmail.com", Status.ACTIVE),
            new User("bob", "bob@yahoo.com", Status.INACTIVE),
            new User("charlie", "charlie@gmail.com", Status.ACTIVE)
    );

    public Optional<User> findUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.username().equals(username))
                .findFirst();
    }
}
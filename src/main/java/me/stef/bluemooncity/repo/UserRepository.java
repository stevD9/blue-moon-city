package me.stef.bluemooncity.repo;

import me.stef.bluemooncity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    Optional<User> findByEmail(String email);
}

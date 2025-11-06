package vn.duonghai.springboot3_beginner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.duonghai.springboot3_beginner.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

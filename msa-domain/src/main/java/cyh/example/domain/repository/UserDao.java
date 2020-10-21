package cyh.example.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cyh.example.domain.entity.User;

@Repository ("UserDao")
public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
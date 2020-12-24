package cyh.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cyh.domain.entity.User;

@Repository ("UserDao")
public interface UserDao extends JpaRepository<User, Integer> {
    User findByUserId(String userId);
}
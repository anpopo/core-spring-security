package io.security.corespringsecurity.repository;

import io.security.corespringsecurity.domain.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = {"userRoles"})
    Optional<User> findByUsername(String username);
    int countByUsername(String username);
}

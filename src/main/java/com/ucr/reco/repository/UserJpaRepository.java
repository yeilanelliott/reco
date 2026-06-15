package com.ucr.reco.repository;

import com.ucr.reco.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {
    List<User> findAll();

    User save(User user);

    User getById(Integer id);

    boolean existsById(Integer id);

    User getByName(String name);

    boolean existsByEmail(String email);

    User getByEmail(String email);

    User findById(int id);

    Optional<User> findUserByEmail(String email);
    // @Query("SELECT u FROM User u WHERE u.email=:email")
    //User findByEmail(@Param("email") String email);
}

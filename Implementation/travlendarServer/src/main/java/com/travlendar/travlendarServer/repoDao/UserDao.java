package com.travlendar.travlendarServer.repoDao;

import com.travlendar.travlendarServer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserDao extends JpaRepository<User, Long> {

    @Query(value="SELECT users.name FROM users  WHERE users.email = :emailParam",nativeQuery = true)
    String findByEmail(@Param("emailParam") String emailParam);
}
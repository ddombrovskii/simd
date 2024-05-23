package ru.ssau.simd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.simd.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

package com.wnet.pdvapp.repository;

import com.wnet.pdvapp.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CustomJpaRepository<User, Long> {
}

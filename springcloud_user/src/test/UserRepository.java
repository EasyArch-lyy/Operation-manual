package com.springcloud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lyy
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}

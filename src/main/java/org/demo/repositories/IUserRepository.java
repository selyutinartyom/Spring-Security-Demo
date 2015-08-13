package org.demo.repositories;

import org.demo.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Selutin_AV on 05.08.2015.
 */
public interface IUserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}

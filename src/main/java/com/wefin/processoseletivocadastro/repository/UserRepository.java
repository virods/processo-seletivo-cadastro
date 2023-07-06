package com.wefin.processoseletivocadastro.repository;

import com.wefin.processoseletivocadastro.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}

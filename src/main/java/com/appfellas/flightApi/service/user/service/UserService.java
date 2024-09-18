package com.appfellas.flightApi.service.user.service;

import com.appfellas.flightApi.service.user.dto.UserInput;
import com.appfellas.flightApi.service.user.entity.User;
import com.appfellas.flightApi.service.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.mongoTemplate = mongoTemplate;
    }

    public User findByEmail(String email) {
        return mongoTemplate.findOne(Query.query(Criteria.where("email").is(email)), User.class);
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<User> findAllByIds(List<String> ids){
        return ids.stream().map(id -> mongoTemplate.findById(id, User.class)).toList();
    }

    public void save(UserInput user){
        userRepository.save(userMapper.createEntity(user));
    }

    public void update(String id, UserInput input){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.save(userMapper.updateEntity(user, input));
    }

    public void delete(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}

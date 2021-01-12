package server.msauser.service;

import java.util.List;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import cyh.domain.entity.User;
import cyh.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import server.msauser.component.MessageProducer.UserUpdateSource;

@RequiredArgsConstructor
@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final UserUpdateSource userUpdateSource;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // id 유무로 create or update
    public User saveUser(User user) {
        user = userRepository.save(user);
        userUpdateSource.configUpdate().send(MessageBuilder.withPayload(user).build());
        return user;
    }
}

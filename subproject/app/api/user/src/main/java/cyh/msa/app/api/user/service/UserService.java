package cyh.msa.app.api.user.service;

import java.util.List;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import cyh.msa.domain.entity.User;
import cyh.msa.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import cyh.msa.app.api.user.component.MessageProducer.UserUpdateSource;

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

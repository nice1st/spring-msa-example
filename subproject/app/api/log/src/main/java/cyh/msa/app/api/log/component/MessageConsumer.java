package cyh.msa.app.api.log.component;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableBinding(MessageConsumer.UserUpdateSink.class)
public class MessageConsumer {

    @StreamListener(UserUpdateSink.userUpdate)
    public void userUpdateHandler(String message) {

        log.info(">>>>>>>> recive ::: " + message);
    }

    public interface UserUpdateSink {
        String userUpdate = "user-update";

        @Input(userUpdate)
        SubscribableChannel userUpdateListener();
    }
}

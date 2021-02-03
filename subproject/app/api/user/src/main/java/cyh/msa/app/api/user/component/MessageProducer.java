package cyh.msa.app.api.user.component;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

@EnableBinding(MessageProducer.UserUpdateSource.class)
public class MessageProducer {
    
    public interface UserUpdateSource {
        String userUpdate = "user-update";
    
        @Output(userUpdate)
        MessageChannel configUpdate();
    }
}

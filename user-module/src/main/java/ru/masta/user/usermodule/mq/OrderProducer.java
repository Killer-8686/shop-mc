package ru.masta.user.usermodule.mq;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(OrderBinding.class)
public class OrderProducer {

    private OrderBinding binding;

    public OrderProducer(OrderBinding binding) {
        this.binding = binding;
    }

    public void newUserAdding(Long userId){
        Message message = MessageBuilder.withPayload(userId).build();

        binding.orderChannel().send(message);
    }
}

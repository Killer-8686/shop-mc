package ru.masta.orders.ordermodule.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

// Описывает каналы для работы с mq
public interface UserBinding {
    String INPUT_CHANNEL = "userInputChannel"; // magic strings

    @Input(INPUT_CHANNEL)
    MessageChannel userInputChannel();

}


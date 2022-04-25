package ru.masta.user.usermodule.mq;


// Интерфейс для работы mq

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

// Здесь создаем нужные каналы
public interface OrderBinding {

    String OUTPUT_CHANNEL = "userOutputChannel"; // Нужен для ссылки на него в mq, не использовать magic strings

    @Output(OUTPUT_CHANNEL)
    MessageChannel orderChannel();
}

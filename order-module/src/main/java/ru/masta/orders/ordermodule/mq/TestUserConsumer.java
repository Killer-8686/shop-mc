package ru.masta.orders.ordermodule.mq;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import ru.masta.orders.ordermodule.service.TestUserData;

@Component
@EnableBinding(UserBinding.class)
public class TestUserConsumer {

    private TestUserData testUserData;

    public TestUserConsumer(TestUserData testUserData) {
        this.testUserData = testUserData;
    }

    @StreamListener(target = UserBinding.INPUT_CHANNEL)
    public void initTestData(Long userId){
        testUserData.testDataInit(userId);
    }
}

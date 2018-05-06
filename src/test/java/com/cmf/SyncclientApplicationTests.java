package com.cmf;

import com.cmf.mq.MQProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SyncclientApplication.class})
public class SyncclientApplicationTests {

    @Autowired
    private MQProducer mqProducer;

    @Test
    public void testProducer() {
        for (int i = 0; i < 10; i++) {
            if (i < 6) {
                mqProducer.sendMessage("Hello RocketMQ " + i, "TopicTest",
                        "TagTest", "Key" + i);
            } else {
                mqProducer.sendMessage("Hello RocketMQ " + i, "TopicTest2",
                        "TagTest2", "Key" + i);
            }
        }

        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

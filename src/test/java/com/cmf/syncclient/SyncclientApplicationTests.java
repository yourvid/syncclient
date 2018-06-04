package com.cmf.syncclient;

import com.cmf.SyncclientApplication;
import com.cmf.config.ClientProperties;
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
    private ClientProperties clientProperties;

    @Autowired
    private MQProducer mqProducer;

    @Test
    public void testProducer() {
        System.out.print("-------------------------->"+clientProperties.getAddress());
    }


}

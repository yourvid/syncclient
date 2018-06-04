package com.cmf.mq;

import com.cmf.exception.enums.HandlerMessageInterfaceEnums;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yaowei on 2018/5/4.
 */
@Component
public class MQPushConsumer implements MessageListenerConcurrently {

    private static final Logger LOGGER = LoggerFactory.getLogger(MQPushConsumer.class);

    @Value("${spring.rocketmq.namesrvaddr}")
    private String namesrvAddr;


    @Resource
    private DealMsgHandler dealMsgHandler;

    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("CmfDataSyncConSumer");

    /**
     * 初始化
     *
     * @throws MQClientException
     */
    @PostConstruct
    public void start() {
        try {
            LOGGER.info("MQ：启动消费者");

            consumer.setNamesrvAddr(namesrvAddr);
            // 从消息队列头开始消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            // 集群消费模式
            consumer.setMessageModel(MessageModel.CLUSTERING);
            // 订阅主题
            consumer.subscribe(HandlerMessageInterfaceEnums.USER_NEXT_ID.getMessage(), "*");//获取下个用户ID
            consumer.subscribe(HandlerMessageInterfaceEnums.USER_INSERT.getMessage(), "*");//新增用户
            consumer.subscribe(HandlerMessageInterfaceEnums.ORDER_INSERT.getMessage(), "*");//新增订单
            consumer.subscribe(HandlerMessageInterfaceEnums.EQUIP_APPLY_INSERT.getMessage(), "*");//设备购买记录新增
            consumer.subscribe(HandlerMessageInterfaceEnums.CARD_APPLY_INSERT.getMessage(), "*");//北斗卡购买记录新增
            consumer.subscribe(HandlerMessageInterfaceEnums.SERVICE_APPLY_INSERT.getMessage(), "*");//北斗服务购买记录新增
            consumer.subscribe(HandlerMessageInterfaceEnums.ORDER_COUPON_INSERT.getMessage(), "*");//订单忧患券关系新增
            consumer.subscribe(HandlerMessageInterfaceEnums.FACTORY_INFO_INSERT.getMessage(), "*");//北斗设备新增
            consumer.subscribe(HandlerMessageInterfaceEnums.BD_EQUIP_INFO_INSERT.getMessage(), "*");//北斗设备新增
            consumer.subscribe(HandlerMessageInterfaceEnums.BD_EQUIP_MODEL_INSERT.getMessage(), "*");//北斗设备型号新增
            consumer.subscribe(HandlerMessageInterfaceEnums.BD_EQUIP_TYPE_INSERT.getMessage(), "*");//北斗设备类型新增
            // 注册消息监听器
            consumer.registerMessageListener(this);
            // 启动消费端
            consumer.start();
        } catch (MQClientException e) {
            LOGGER.error("MQ：启动消费者失败：{}-{}", e.getResponseCode(), e.getErrorMessage());
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    /**
     * 消费消息
     * @param msgs
     * @param context
     * @return
     */
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        int index = 0;
        try {
            for (; index < msgs.size(); index++) {
                MessageExt msg = msgs.get(index);
                String messageBody = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
                LOGGER.info("MQ：消费者接收新信息: {} {} {} {} {}", msg.getMsgId(), msg.getTopic(), msg.getTags(), msg.getKeys(), messageBody);
                dealMsgHandler.dealMsg(msg.getTopic(),messageBody);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;   // 重试
        } finally {
            if (index < msgs.size()) {
                context.setAckIndex(index + 1);
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    @PreDestroy
    public void stop() {
        if (consumer != null) {
            consumer.shutdown();
            LOGGER.error("MQ：关闭消费者");
        }
    }

}

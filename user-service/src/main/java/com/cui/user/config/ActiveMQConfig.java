package com.cui.user.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class ActiveMQConfig {

	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("ActiveMQQueue");
	}

	@Bean
	public Topic topic() {
		return new ActiveMQTopic("ActiveMQTopic");
	}

//	创建连接工厂，按照给定的url地址，采用默认用户和密码
	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory(brokerUrl);
	}

	// Queue模式连接注入
//	@Bean
//	public JmsListenerContainerFactory<?> jmsListenerContainerQueue(
//			ActiveMQConnectionFactory connectionFactory) {
//		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
//		bean.setConnectionFactory(connectionFactory);
//		return bean;
//	}
//
//	// Topic模式连接注入
//	@Bean
//	public JmsListenerContainerFactory<?> jmsListenerContainerTopic(
//			ActiveMQConnectionFactory connectionFactory) {
//		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
//		// 设置为发布订阅方式, 默认情况下使用的生产消费者方式
//		bean.setPubSubDomain(true);
//		bean.setConnectionFactory(connectionFactory);
//		return bean;
//	}

	// 消费者
	@Bean(name = "jmsQueueListener")
	public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory(
			ActiveMQConnectionFactory activeMQConnectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(activeMQConnectionFactory);
		// 设置连接数
		factory.setConcurrency("1-10");
		// 重连间隔时间
		factory.setRecoveryInterval(1000L);
		factory.setSessionAcknowledgeMode(4);
		return factory;
	}

	@Bean
	public RedeliveryPolicy redeliveryPolicy() {
		RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
		// 是否在每次尝试重新发送失败后,增长这个等待时间
		redeliveryPolicy.setUseExponentialBackOff(true);
		// 重发次数,默认为6次 这里设置为10次
		redeliveryPolicy.setMaximumRedeliveries(10);
		// 重发时间间隔,默认为1秒
		redeliveryPolicy.setInitialRedeliveryDelay(5);
		// 第一次失败后重新发送之前等待500毫秒,第二次失败再等待500 * 2毫秒,这里的2就是value
		redeliveryPolicy.setBackOffMultiplier(10);
		// 是否避免消息碰撞
		redeliveryPolicy.setUseCollisionAvoidance(false);
		// 设置重发最大拖延时间-1 表示没有拖延只有UseExponentialBackOff(true)为true时生效
		redeliveryPolicy.setMaximumRedeliveryDelay(-1);
		return redeliveryPolicy;
	}

	@Bean
	public JmsTemplate jmsTemplate(ActiveMQConnectionFactory activeMQConnectionFactory, Queue queue) {
		JmsTemplate jmsTemplate = new JmsTemplate();
//		jmsTemplate.setJMSExpiration(); // 设置故其时间， 默认永不过期
		jmsTemplate.setDeliveryMode(2);// 进行持久化配置 1表示非持久化【服务器宕机消息就不存在了】，2表示持久化  默认为持久化
		jmsTemplate.setConnectionFactory(activeMQConnectionFactory);
		jmsTemplate.setDefaultDestination(queue); // 此处可不设置默认，在发送消息时也可设置队列【消息发送的目的地，主要是指Queue和Topic】
		jmsTemplate.setSessionAcknowledgeMode(4);// 客户端签收模式  【持久化策略和非持久化策略】
		return jmsTemplate;
	}
}

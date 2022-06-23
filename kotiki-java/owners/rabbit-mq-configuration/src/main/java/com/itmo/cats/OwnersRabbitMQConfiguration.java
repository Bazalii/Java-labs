package com.itmo.cats;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OwnersRabbitMQConfiguration {

    @Bean
    public DirectExchange ownersDirectExchange() {
        return new DirectExchange("owners-exchange");
    }

    @Bean
    public Queue ownersGetByIdQueue() {
        return new Queue("ownersGetByIdQueue");
    }

    @Bean
    public Queue ownersAddQueue() {
        return new Queue("ownersAddQueue");
    }

    @Bean
    public Queue ownersUpdateQueue() {
        return new Queue("ownersUpdateQueue");
    }

    @Bean
    public Queue ownersGetAllQueue() {
        return new Queue("ownersGetAllQueue");
    }

    @Bean
    public Queue ownersDeleteByIdQueue() {
        return new Queue("ownersDeleteByIdQueue");
    }

    @Bean
    public Binding bindOwnersGetByIdQueue() {
        return BindingBuilder.bind(ownersGetByIdQueue()).to(ownersDirectExchange()).with("ownersGetByIdQueue");
    }

    @Bean
    public Binding bindOwnersAddQueue() {
        return BindingBuilder.bind(ownersAddQueue()).to(ownersDirectExchange()).with("ownersAddQueue");
    }

    @Bean
    public Binding bindOwnersUpdateQueue() {
        return BindingBuilder.bind(ownersUpdateQueue()).to(ownersDirectExchange()).with("ownersUpdateQueue");
    }

    @Bean
    public Binding bindOwnersGetAllQueue() {
        return BindingBuilder.bind(ownersGetAllQueue()).to(ownersDirectExchange()).with("ownersGetAllQueue");
    }

    @Bean
    public Binding bindOwnersDeleteByIdQueue() {
        return BindingBuilder.bind(ownersDeleteByIdQueue()).to(ownersDirectExchange()).with("ownersDeleteByIdQueue");
    }
}

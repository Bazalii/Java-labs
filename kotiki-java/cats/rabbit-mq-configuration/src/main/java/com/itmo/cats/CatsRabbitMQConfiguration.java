package com.itmo.cats;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatsRabbitMQConfiguration {
    @Bean
    public DirectExchange catsDirectExchange() {
        return new DirectExchange("cats-exchange");
    }

    @Bean
    public Queue catsGetByIdQueue() {
        return new Queue("catsGetByIdQueue");
    }

    @Bean
    public Queue catsAddQueue() {
        return new Queue("catsAddQueue");
    }

    @Bean
    public Queue catsUpdateQueue() {
        return new Queue("catsUpdateQueue");
    }

    @Bean
    public Queue catsGetAllQueue() {
        return new Queue("catsGetAllQueue");
    }

    @Bean
    public Queue catsAddFriendByIdQueue() {
        return new Queue("catsAddFriendByIdQueue");
    }

    @Bean
    public Queue catsDeleteByIdQueue() {
        return new Queue("catsDeleteByIdQueue");
    }

    @Bean
    public Queue catsRemoveFriendByIdQueue() {
        return new Queue("catsRemoveFriendByIdQueue");
    }

    @Bean
    public Binding bindCatsGetByIdQueue() {
        return BindingBuilder.bind(catsGetByIdQueue()).to(catsDirectExchange()).with("catsGetByIdQueue");
    }

    @Bean
    public Binding bindCatsAddQueue() {
        return BindingBuilder.bind(catsAddQueue()).to(catsDirectExchange()).with("catsAddQueue");
    }

    @Bean
    public Binding bindCatsUpdateQueue() {
        return BindingBuilder.bind(catsUpdateQueue()).to(catsDirectExchange()).with("catsUpdateQueue");
    }

    @Bean
    public Binding bindCatsGetAllQueue() {
        return BindingBuilder.bind(catsGetAllQueue()).to(catsDirectExchange()).with("catsGetAllQueue");
    }

    @Bean
    public Binding bindCatsAddFriendByIdQueue() {
        return BindingBuilder.bind(catsAddFriendByIdQueue()).to(catsDirectExchange()).with("catsAddFriendByIdQueue");
    }

    @Bean
    public Binding bindCatsDeleteByIdQueue() {
        return BindingBuilder.bind(catsDeleteByIdQueue()).to(catsDirectExchange()).with("catsDeleteByIdQueue");
    }

    @Bean
    public Binding bindCatsRemoveFriendByIdQueue() {
        return BindingBuilder.bind(catsRemoveFriendByIdQueue()).to(catsDirectExchange()).with("catsRemoveFriendByIdQueue");
    }

}

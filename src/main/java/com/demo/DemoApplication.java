package com.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@SpringBootApplication
@EnableTransactionManagement
@EnableSwagger2
@EnableKafka
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @KafkaListener(topics = {"order", "customer"})
    public void listener(ConsumerRecord<String, String> record) {
        System.out.println(record.partition() + " : " + record.value());
    }


    @Bean
    public DataSource getDataSource(){
        var dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/demo");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("Anton050");
        return dataSourceBuilder.build();
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.demo")).build();
    }


}

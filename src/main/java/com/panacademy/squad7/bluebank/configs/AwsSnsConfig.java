package com.panacademy.squad7.bluebank.configs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@PropertySources(value = {@PropertySource("classpath:aws.properties"),@PropertySource("classpath:secret.properties")})
public class AwsSnsConfig {

    @Value("${bluebank.aws.accessKey}")
    public String ACCESS_KEY;

    @Value("${bluebank.aws.secretKey}")
    public String SECRET_KEY;

    @Bean
    @Primary
    public AmazonSNSClient getSnsClient() {
        return (AmazonSNSClient) AmazonSNSClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY)))
                .build();
    }
}

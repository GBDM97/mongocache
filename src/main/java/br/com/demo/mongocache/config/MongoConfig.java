package br.com.demo.mongocache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import br.com.demo.mongocache.model.Tutorial;

@Configuration
@EnableMongoAuditing
public class MongoConfig {
}

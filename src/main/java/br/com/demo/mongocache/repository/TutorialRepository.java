package br.com.demo.mongocache.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.demo.mongocache.model.Tutorial;;

public interface TutorialRepository extends MongoRepository<Tutorial, String> {
  List<Tutorial> findByTitleContaining(String title);
  List<Tutorial> findByPublished(boolean published);
}

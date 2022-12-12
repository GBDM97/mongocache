package br.com.demo.mongocache.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class Tutorial implements Serializable {

  @Id
  private String id;
  
  @NotNull(message = "˜not null˜")
  @NotEmpty(message = "no empty")
  private String title;

  @NotNull(message = "˜not null˜")
  @NotEmpty(message = "no empty")
  @NotBlank(message = "not black")

  private String description;

  @NotNull
  private boolean published;

  // public Tutorial() {
  //   this.description = null;
  // }

  // public Tutorial(String title, String description, boolean published) {
  //   this.title = title;
  //   this.description = description;
  //   this.published = published;
  // }

  // public String getId() {
  //   return id;
  // }

  // public String getTitle() {
  //   return title;
  // }

  // public void setTitle(String title) {
  //   this.title = title;
  // }

  // public String getDescription() {
  //   return description;
  // }

  // public void setDescription(String description) {
  //   this.description = description;
  // }

  // public boolean isPublished() {
  //   return published;
  // }

  // public void setPublished(boolean isPublished) {
  //   this.published = isPublished;
  // }

  // @Override
  // public String toString() {
  //   return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
  // }
}
package br.com.demo.mongocache.controller;

import br.com.demo.mongocache.model.Tutorial;
import br.com.demo.mongocache.repository.TutorialRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

@RestController
@Validated
@RequestMapping("/api")
public class TutorialController {

  @Autowired
  TutorialRepository tutorialRepository;

  @GetMapping("/tutorials")
  public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
    try {
      List<Tutorial> tutorials = new ArrayList<Tutorial>();

      if (title == null)
        tutorialRepository.findAll().forEach(tutorials::add);
      else
        tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  /*
   * @GetMapping("/tutorials/{id}")
   * public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") String
   * id) {
   * 
   * }
   */

  @PostMapping(value = "/tutorials", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Tutorial> createTutorial(@RequestBody @Valid Tutorial tutorial) {
    try {

        LocalDateTime dt1 = getCurrentUTC();
      Tutorial newTutorial = Tutorial.builder()
          .title(tutorial.getTitle())
          .description(tutorial.getDescription())
          .published(false)
          .build();
      Tutorial tutorialSaved = tutorialRepository.save(newTutorial);
      return new ResponseEntity<>(tutorialSaved, HttpStatus.CREATED);
    } catch (Exception e) {
      return null;
    }

  }

  /*
   * @PutMapping("/tutorials/{id}")
   * public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") String
   * id, @RequestBody Tutorial tutorial) {
   * 
   * }
   * 
   * @DeleteMapping("/tutorials/{id}")
   * public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String
   * id) {
   * 
   * }
   * 
   * @DeleteMapping("/tutorials")
   * public ResponseEntity<HttpStatus> deleteAllTutorials() {
   * 
   * }
   * 
   * @GetMapping("/tutorials/published")
   * public ResponseEntity<List<Tutorial>> findByPublished() {
   * 
   * }
   */

  // create getCurrentUtcTime() method to get the current UTC time
  public static Date getCurrentUtcTime() throws ParseException { // handling ParseException
    // create an instance of the SimpleDateFormat class
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
    // set UTC time zone by using SimpleDateFormat class
    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    // create another instance of the SimpleDateFormat class for local date format
    SimpleDateFormat ldf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
    // declare and initialize a date variable which we return to the main method
    Date d1 = null;
    // use try catch block to parse date in UTC time zone
    try {
      // parsing date using SimpleDateFormat class
      d1 = ldf.parse(sdf.format(new Date()));
    }
    // catch block for handling ParseException
    catch (java.text.ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    // pass UTC date to main method.
    return d1;
  }

  public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
    return Instant.ofEpochMilli(dateToConvert.getTime())
        .atZone(ZoneId.systemDefault())
        .toLocalDate();
  }

  private LocalDateTime getCurrentUTC() {
    Date date = new Date();
    DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
    String formatted = format.format(date);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    LocalDateTime ldate = LocalDateTime.parse(format.format(date), formatter);
    return ldate;
  }

}

package com.vaadin.ktr.idea.domain;

import com.vaadin.ktr.idea.domain.model.User;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController("/users")
public class UsersController {

  @PostMapping(path = "/users/user",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<String> postUser(@RequestBody User user) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<ResponseEntity<String>> postUsers(@RequestBody List<User> users) {
    return Arrays.asList(new ResponseEntity<>(HttpStatus.OK),
        new ResponseEntity<>(HttpStatus.OK), new ResponseEntity<>(HttpStatus.OK),
        new ResponseEntity<>(HttpStatus.OK), new ResponseEntity<>(HttpStatus.OK));
  }


  @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<User> getUsers() {
    return Collections.singletonList(
        new User()
            .setEmail("test@email.com")
            .setFirstName("firstTest")
            .setLastName("lastTest")
            .setId("TestId")
    );
  }
}
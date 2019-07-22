package com.vaadin.ktr.idea.domain;

import com.vaadin.ktr.idea.domain.model.User;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController("/users")
public class UsersController {

  @PostMapping(path = "/users/user",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<User> postUser(@RequestBody User user) {
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<ResponseEntity<String>> postUsers(@RequestBody List<User> users) {
    return Arrays.asList(new ResponseEntity<>(HttpStatus.OK),
        new ResponseEntity<>(HttpStatus.OK), new ResponseEntity<>(HttpStatus.OK),
        new ResponseEntity<>(HttpStatus.OK), new ResponseEntity<>(HttpStatus.OK));
  }


  @GetMapping(path = "/users",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<User> getUsers() {
    return Collections.singletonList(new User());
  }

  @GetMapping(path = "users/id/{id}",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public User getUserById(@PathVariable @NotBlank @NotNull String id) {
    return new User()
        .setEmail("test@email.com")
        .setFirstName("firstTest")
        .setLastName("lastTest")
        .setId(id);
  }

  @GetMapping(path = "/users/email/{email}",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public User getUserByEmail(@PathVariable @NotBlank @NotNull String email) {
    return new User()
        .setEmail(email)
        .setFirstName("firstTest")
        .setLastName("lastTest")
        .setId("TestId");
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<User> putUser(@RequestBody @NotNull User user) {
    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
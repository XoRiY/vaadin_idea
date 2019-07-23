package com.vaadin.ktr.idea.domain;

import com.vaadin.ktr.idea.domain.model.User;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UsersController {

  private Validator validator;

  @Autowired
  public void setValidator(final Validator validator) {
    this.validator = validator;
  }

  @PostMapping(path = "/users/user",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<User> postUser(@RequestBody User user) {
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PostMapping(path = "/users",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<ResponseEntity<String>> postUsers(@RequestBody List<User> users) {
    return Arrays.asList(new ResponseEntity<>(HttpStatus.OK),
        new ResponseEntity<>(HttpStatus.OK), new ResponseEntity<>(HttpStatus.OK),
        new ResponseEntity<>(HttpStatus.OK), new ResponseEntity<>(HttpStatus.OK));
  }


  @GetMapping(path = "/users",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<User> getUsers() {
    return Collections.singletonList(
        new User()
            .setId("5d35f26c1d168c9021b852ee")
            .setLastName("Acevedo")
            .setFirstName("Simone")
            .setEmail("SimoneAcevedo@aquazure.com")
    );
  }

  @GetMapping(path = "users/id/{id}",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public User getUserById(@PathVariable @NotBlank @NotNull final String id) {
    return new User()
        .setEmail("test@email.com")
        .setFirstName("firstTest")
        .setLastName("lastTest")
        .setId(id);
  }

  @GetMapping(path = "/users/search",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public User getUserByEmail(@RequestParam @NotBlank @NotNull final String email) {

    validator.validate(email);
    return new User()
        .setEmail(email)
        .setFirstName("Wise")
        .setLastName("Mitchell")
        .setId("5d35f26c55e92a6448d0f822");
  }

  @PutMapping(path = "/users",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<User> putUser(@RequestBody @NotNull User user) {
    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
package com.vaadin.ktr.idea.domain;

import com.vaadin.ktr.idea.domain.model.User;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
@Validated
public class UsersController {


  @PostMapping(path = "/user",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<User> postUser(@RequestBody User user) {
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
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
            .setId("5d35f26c1d168c9021b852ee")
            .setLastName("Acevedo")
            .setFirstName("Simone")
            .setEmail("SimoneAcevedo@aquazure.com")
    );
  }

  @GetMapping(path = "/id/{id}",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public User getUserById(@PathVariable @NotBlank @NotNull final String id) {

    Objects.requireNonNull(id);
    return new User()
        .setId("5d35f26c1d168c9021b852ee")
        .setLastName("Acevedo")
        .setFirstName("Simone")
        .setEmail("SimoneAcevedo@aquazure.com");
  }

  @GetMapping(path = "/search",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Valid
  public User getUserByEmail(
      @RequestParam
      @NotNull(message = "le paramètre de requete ne peut pas Null")
      @NotBlank(message = "le paramètre de requête ne peut être vide")
       final String email) {

    return new User()
        .setEmail(email)
        .setFirstName("Wise")
        .setLastName("Mitchell")
        .setId("5d35f26c55e92a6448d0f822");
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<User> putUser(@RequestBody @NotNull User user) {
    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
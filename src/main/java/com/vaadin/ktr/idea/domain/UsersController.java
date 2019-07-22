package com.vaadin.ktr.idea.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.vaadin.ktr.idea.domain.model.User;


@RestController("/users")
public class UsersController {


  @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<String> postUser(@RequestBody User user) {

    return new ResponseEntity<>(HttpStatus.OK);
  }


}

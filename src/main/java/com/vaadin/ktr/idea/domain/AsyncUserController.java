package com.vaadin.ktr.idea.domain;

import com.vaadin.ktr.idea.domain.model.User;
import com.vaadin.ktr.idea.domain.service.UserAsyncService;
import com.vaadin.ktr.idea.domain.service.UserSyncService;
import java.util.Arrays;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
@Validated
public class AsyncUserController {

  private UserAsyncService userAsyncService;

  @Autowired
  public AsyncUserController(final UserAsyncService userAsyncService) {
    this.userAsyncService = userAsyncService;
  }


  @PostMapping(path = "/user",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<User> postUser(@RequestBody User user) {
    userAsyncService.saveUser(user);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<ResponseEntity<String>> postUsers(@RequestBody List<User> users) {
    userAsyncService.saveUsers(users);
    return Arrays.asList(new ResponseEntity<>(HttpStatus.OK),
        new ResponseEntity<>(HttpStatus.OK), new ResponseEntity<>(HttpStatus.OK),
        new ResponseEntity<>(HttpStatus.OK), new ResponseEntity<>(HttpStatus.OK));
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<User> putUser(@RequestBody @NotNull User user) {
    userAsyncService.updateUser(user);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
package com.vaadin.ktr.idea.domain;

import com.vaadin.ktr.idea.domain.model.User;
import com.vaadin.ktr.idea.domain.service.UserSyncService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
@Validated
public class SyncUserController {

  private UserSyncService userAsyncService;

  @Autowired
  public SyncUserController(final UserSyncService userSyncService) {
    this.userAsyncService = userSyncService;
  }


  @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<List<User>> getUsers() {

    List<User> list = userAsyncService.getUsers();

    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping(path = "/id/{id}",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<User> getUserById(@PathVariable @NotBlank @NotNull final String id) {
    User userById = userAsyncService.getUserById(id);
    return new ResponseEntity<>(userById, HttpStatus.OK);
  }

  @GetMapping(path = "/search",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Valid
  public ResponseEntity<User> getUserByEmail(
      @RequestParam
      @NotNull(message = "le paramètre de requete ne peut pas Null")
      @NotBlank(message = "le paramètre de requête ne peut être vide") final String email) {
    User userById = userAsyncService.getUserByEmail(email);
    return new ResponseEntity<>(userById, HttpStatus.OK);
  }

}
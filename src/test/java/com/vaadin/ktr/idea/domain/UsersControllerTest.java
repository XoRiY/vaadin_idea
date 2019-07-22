package com.vaadin.ktr.idea.domain;

import com.vaadin.ktr.idea.domain.model.User;
import java.util.Collections;
import java.util.List;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsersControllerTest {

  @Autowired
  private WebTestClient webClient;

  private static final String URI = "/users";

  private static final String POST_USER_URI = "/users/user";

  private static final String POST_USERS_URI = "/users";

  private static final String GET_BY_EMAIL_URI = "";

  private static final String GET_BY_ID_URI = "";

  private static final String WRONG_URI = "/wrong/uri";


  @Test
  public void postUser() {
    User givenUser = getUser();

    webClient.post()
        .uri(POST_USER_URI)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(givenUser))
        .exchange()
        .expectStatus()
        .isOk().expectBody(new ParameterizedTypeReference<User>() {
    }).isEqualTo(getUser());
  }

  @Test
  public void postUserWithWrongURI() {
    User givenUser = getUser();
    webClient.post()
        .uri(WRONG_URI)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(givenUser))
        .exchange()
        .expectStatus()
        .isNotFound()
        .expectStatus()
        .isEqualTo(404)
        .expectBody()
        .jsonPath("$.message")
        .isEqualTo("No message available");
  }


  @Test
  public void postUsers() {
    List<User> givenUsers = getUsers();

    webClient.post()
        .uri(POST_USERS_URI)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(givenUsers))
        .exchange()
        .expectStatus()
        .isOk();
    //TODO body
  }

  @Test
  public void postUsersWithWrongURI() {
    List<User> givenUsers = getUsers();
    webClient.post()
        .uri(WRONG_URI)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(givenUsers))
        .exchange()
        .expectStatus()
        .isNotFound()
        .expectStatus()
        .isEqualTo(404)
        .expectBody()
        .jsonPath("$.message")
        .isEqualTo("No message available");

  }

  @Test
  public void getUsersTest() {
    webClient.get()
        .uri(URI)
        .exchange()
        .expectStatus()
        .isOk().expectBody(new ParameterizedTypeReference<User>() {
    }).isEqualTo(getUser());
  }

  @Test
  public void getUsersTestWithWrongURI() throws NotImplementedException {
    throw new NotImplementedException("not implemented yet exception");
  }

  @Test
  public void getUserByEmailTest() throws NotImplementedException {
    throw new NotImplementedException("not implemented yet exception");
  }

  @Test
  public void getUserByEmailWithWrongURITest() throws NotImplementedException {
    throw new NotImplementedException("not implemented yet exception");
  }

  @Test
  public void getUserByIdTest() throws NotImplementedException {
    throw new NotImplementedException("not implemented yet exception");
  }

  @Test
  public void putUserTest() throws NotImplementedException {
    throw new NotImplementedException("not implemented yet exception");
  }

  @Test
  public void putUserWithWrongURITest() throws NotImplementedException {
    throw new NotImplementedException("not implemented yet exception");
  }


  private User getUser() {
    return new User()
        .setId("5d35f26c1d168c9021b852ee")
        .setEmail("AcevedoSimone@aquazure.com")
        .setFirstName("Simone")
        .setLastName("Acevedo");
  }

  private List<User> getUsers() {
    return Collections.singletonList(getUser());
  }


}
package com.vaadin.ktr.idea.domain;

import com.vaadin.ktr.idea.domain.model.User;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SyncUserControllerTest {

  @Autowired
  private WebTestClient webClient;

  private static final String URI = "/users";

    private static final String GET_BY_EMAIL_URI = "/users/search";

  private static final String GET_BY_ID_URI = "/users/id/5d35f26c1d168c9021b852ee";

  private static final String WRONG_URI = "/wrong/uri";

  @Test
  public void getUsersTest() {
    webClient.get()
        .uri(URI)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .json("[{"
            + "\"id\": \"5d35f26c1d168c9021b852\","
            + "\"email\": \"AcevedoSimone@aquazure.com\","
            + "\"firstName\": \"Simone\","
            + "\"lastName\": \"Acevedo\""
            + "}]");
  }

  @Test
  public void getUsersTestWithWrongURI() {

    webClient.get()
        .uri(WRONG_URI)
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
  public void getUserByEmailTest() {

    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.set("email", "WiseMitchell@aquazure.com");

    final String getBuildedUri = UriComponentsBuilder.fromUriString(GET_BY_EMAIL_URI)
        .queryParams(params)
        .toUriString();

    webClient.get()
        .uri(getBuildedUri)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .json("{"
            + "\"id\": \"5d35f26c1d168c9021b852\","
            + "\"email\": \"AcevedoSimone@aquazure.com\","
            + "\"firstName\": \"Simone\","
            + "\"lastName\": \"Acevedo\""
            + "}");
  }

  @Test
  public void getUserByEmailTestUsingNullValueParam() {

    webClient.get()
        .uri(GET_BY_EMAIL_URI)
        .exchange()
        .expectStatus()
        .is4xxClientError()
        .expectBody()
        .jsonPath("$.message")
        .isEqualTo("Required String parameter 'email' is not present")
        .jsonPath("$.status")
        .isEqualTo(400)
        .jsonPath("$.error")
        .isEqualTo("Bad Request");

    //TODO model exemple pr les autres tests?
  }

  @Test
  public void getUserByEmailTestUsingBlankValueParam() {

    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.set("email", "");

    final String getBuildUri = UriComponentsBuilder.fromUriString(GET_BY_EMAIL_URI)
        .queryParams(params)
        .toUriString();

    webClient.get()
        .uri(getBuildUri)
        .exchange()
        .expectStatus()
        .is5xxServerError()
        .expectBody()
        .jsonPath("$.message")
        .isEqualTo("getUserByEmail.email: le paramètre de requête ne peut être vide")
        .jsonPath("$.status")
        .isEqualTo(500)
        .jsonPath("$.error")
        .isEqualTo("Internal Server Error");
    ;

    //TODO ne donne pas le resultat attendu, la méthode ne leve pas d'exception quand l'email est null ou vide
  }

  @Test
  public void getUserByEmailWithWrongURITest() {
    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.set("email", "WiseMitchell@aquazure.com");

    final String getBuildedUri = UriComponentsBuilder.fromUriString(WRONG_URI)
        .queryParams(params)
        .toUriString();

    webClient.get()
        .uri(getBuildedUri)
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
  public void getUserByIdTest() {

    webClient.get()
        .uri(GET_BY_ID_URI)
        .exchange()
        .expectStatus()
        .isOk()
        .expectStatus()
        .isEqualTo(200)
        .expectStatus()
        .isEqualTo(HttpStatus.OK)
        .expectBody()
        .json("{"
            + "\"id\": \"5d35f26c1d168c9021b852\","
            + "\"email\": \"AcevedoSimone@aquazure.com\","
            + "\"firstName\": \"Simone\","
            + "\"lastName\": \"Acevedo\""
            + "}");
  }

  @Test
  public void getUserByIdTestWithNullValueId() {

    webClient.get()
        .uri("/users/id/")
        .exchange()
        .expectStatus()
        .isNotFound()
        .expectStatus()
        .isEqualTo(404)
        .expectStatus()
        .isEqualTo(HttpStatus.NOT_FOUND)
        .expectStatus()
        .is4xxClientError()
        .expectBody()
        .jsonPath("$.message")
        .isEqualTo("No message available");
  }

  @Test
  public void getUserByIdTestWithWrongUriValueId() {

    webClient.get()
        .uri("/users/id/wrong/5d35f26c1d168c9021b852ee")
        .exchange()
        .expectStatus()
        .isNotFound()
        .expectStatus()
        .isEqualTo(404)
        .expectStatus()
        .isEqualTo(HttpStatus.NOT_FOUND)
        .expectStatus()
        .is4xxClientError()
        .expectBody()
        .jsonPath("$.message")
        .isEqualTo("No message available");
  }

}
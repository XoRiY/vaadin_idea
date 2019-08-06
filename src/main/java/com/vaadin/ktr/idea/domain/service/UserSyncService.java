package com.vaadin.ktr.idea.domain.service;

import com.vaadin.ktr.idea.domain.model.User;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserSyncService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserSyncService.class);

  public User getUserByEmail(String userEmail) {

    LOGGER.info("getting user by email");
    return getUser();
  }

  public User getUserById(String userEmail) {

    LOGGER.info("getting user by id");
    return getUser();
  }

  public User searchUser(String userEmail) {

    LOGGER.info("search user");
    return getUser();
  }

  public List<User> getUsers() {
    LOGGER.info("search user");
    return Arrays.asList(getUser());
  }



  private User getUser() {
    return new User()
        .setId("5d35f26c1d168c9021b852")
        .setEmail("AcevedoSimone@aquazure.com")
        .setFirstName("Simone")
        .setLastName("Acevedo");
  }
}

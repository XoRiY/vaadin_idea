package com.vaadin.ktr.idea.domain.service;

import com.vaadin.ktr.idea.domain.model.User;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserAsyncService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserAsyncService.class);

  public void saveUser(User user) {

    LOGGER.info("save user");

  }

  public void saveUsers(List<User> users) {

    LOGGER.info("save users");

  }

  public void updateUser(User user) {

    LOGGER.info("update user");

  }

}

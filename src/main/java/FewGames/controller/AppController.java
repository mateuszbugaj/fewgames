package FewGames.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/")
public class AppController {

  @GetMapping("hello")
  public String getStudent() {
    return "Hello from secured API";
  }

  @RequestMapping("user")
  public Principal user(Principal user) {
    return user;
  }
}

package FewGames.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MyController {

  @GetMapping("/home")
  public String hello(@AuthenticationPrincipal final Principal principal) {
    return "Hello, " + principal.getName() + "!";
  }
}

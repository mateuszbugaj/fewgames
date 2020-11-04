package FewGames.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  public static Logger logger = LoggerFactory.getLogger(AuthController.class);

  @RequestMapping("/login")
  public Principal login(@AuthenticationPrincipal Principal principal){
    if(principal != null){
      logger.info(principal.toString());
    } else {
      logger.info("Null principal");
    }

    return principal;
  }
}

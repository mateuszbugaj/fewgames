package FewGames.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {
  public static Logger logger = LoggerFactory.getLogger(AuthenticationProviderImpl.class);


  @Override
  public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
    // この辺りは認証フィルタ(今回の場合Basic認証を指定しているので
    // BasicAuthenticationFilter)が生成している
    logger.info("Got authentication: " + authentication.toString());

    final UsernamePasswordAuthenticationToken upAuth = (UsernamePasswordAuthenticationToken) authentication;
    final String name = (String) authentication.getPrincipal();

    final String password = (String) upAuth.getCredentials();

    final String storedPassword = "pass";

    if (Objects.equals(password, "") || !Objects.equals(password, storedPassword)) {
      throw new BadCredentialsException("illegal id or passowrd");
    }

    final Object principal = authentication.getPrincipal();
    final UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
      principal, authentication.getCredentials(),
      Collections.emptyList());
    result.setDetails(authentication.getDetails());

    return result;
  }

  @Override
  public boolean supports(final Class<?> authentication) {
    return true;
  }

}

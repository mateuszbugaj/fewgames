package FewGames.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles(profiles = "test")
class AuthServiceTest {

  @Autowired
  AuthService authService;

  @Test
  void loadExistingUserByUsername() {
    // Give
    String name = "tom";

    // When
    UserDetails userDetails = authService.loadUserByUsername(name);

    // Then
    Assert.assertNotNull(userDetails);
  }

  @Test
  void loadNonExistingUserByUsername() {
    // Give
    String name = "nonExisting";

    // When
    Assert.assertThrows(UsernameNotFoundException.class, () -> authService.loadUserByUsername(name));
  }
}

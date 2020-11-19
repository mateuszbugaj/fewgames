package FewGames.dao;

import FewGames.entity.AppUser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles(profiles = "test")
class UserDaoServiceTest {

  @Autowired
  UserDao userDao;

  @Autowired
  PasswordEncoder passwordEncoder;

  @ParameterizedTest
  @CsvSource({
    "tom, true",
    "bob, true",
    "nonExistent, false"
  })
  void selectByName(String name, Boolean value){
    Assert.assertEquals(userDao.selectByName(name).isPresent(), value);
  }

  @Test
  void addUserWithNewName(){
    // Given
    AppUser newUser = new AppUser("nonExistent", passwordEncoder.encode("pass"));

    // when
    boolean saved = userDao.save(newUser);

    // then
    Assert.assertTrue(saved);
  }

  @Test
  void addUserWithExistingName(){
    // Given
    AppUser newUser = new AppUser("tom", passwordEncoder.encode("pass"));

    // when
    boolean saved = userDao.save(newUser);

    // then
    Assert.assertFalse(saved);
  }
}

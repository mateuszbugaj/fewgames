package FewGames.auth;

import FewGames.security.AppUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository("fake")
public class FakeUserDaoService implements UserDao{

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public FakeUserDaoService(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Optional<AppUser> selectAppUserByUsername(String username) {
    return getAppUsers().stream().filter(user -> user.getUsername().equals(username)).findFirst();
  }

  private List<AppUser> getAppUsers(){
    List<AppUser> users = new ArrayList<>();
    users.addAll(Arrays.asList(
      new AppUser(
        "tom",
        passwordEncoder.encode("pass"),
        AppUserRole.USER.getGrantedAuthorities(),
        true,
        true,
        true,
        true
      )
    ));


    return users;
  }
}

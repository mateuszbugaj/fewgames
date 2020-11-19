package FewGames.dao;

import FewGames.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Repository("fakeUserRepo")
public class FakeUserDaoImpl implements UserDao{
  private final ArrayList<AppUser> users;

  @Autowired
  public FakeUserDaoImpl(PasswordEncoder passwordEncoder) {

    users = new ArrayList<>();
    users.addAll(Arrays.asList(
      new AppUser(
        "tom",
        passwordEncoder.encode("pass")
      ),
      new AppUser(
        "bob",
        passwordEncoder.encode("pass")
      )
    ));
  }

  @Override
  public Optional<AppUser> selectByName(String username) {
    return users
      .stream()
      .filter(user -> user.getUsername().equals(username))
      .findFirst();
  }

  @Override
  public boolean save(AppUser appUser) {
    boolean noneMatch = users
      .stream()
      .noneMatch(u -> u.getUsername().equals(appUser.getUsername()));

    if (noneMatch){
      users.add(appUser);
      return true;
    }

    return false;
  }
}

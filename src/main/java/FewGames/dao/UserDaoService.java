package FewGames.dao;

import FewGames.entity.AppUser;
import FewGames.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository("real")
public class UserDaoService implements UserDao {

  private final UserRepo userRepo;

  @Autowired
  public UserDaoService(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public Optional<AppUser> selectByName(String username) {
    return StreamSupport
      .stream(userRepo.findAll().spliterator(), false)
      .filter(i -> i.getUsername().equals(username))
      .findFirst();
  }

  @Override
  public void save(AppUser appUser) {
    userRepo.save(appUser);
  }
}

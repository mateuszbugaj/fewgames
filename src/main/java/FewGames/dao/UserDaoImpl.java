package FewGames.dao;

import FewGames.entity.AppUser;
import FewGames.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository("realUserRepo")
public class UserDaoImpl implements UserDao {

  private final UserRepo userRepo;

  @Autowired
  public UserDaoImpl(UserRepo userRepo) {
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
  public boolean save(AppUser appUser) {
    userRepo.save(appUser);
    return true;
  }
}

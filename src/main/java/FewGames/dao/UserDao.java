package FewGames.dao;

import FewGames.entity.AppUser;

import java.util.Optional;

public interface UserDao {

  public Optional<AppUser> selectAppUserByUsername(String username);

}

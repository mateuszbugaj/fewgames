package FewGames.auth;

import java.util.Optional;

public interface UserDao {

  public Optional<AppUser> selectAppUserByUsername(String username);

}

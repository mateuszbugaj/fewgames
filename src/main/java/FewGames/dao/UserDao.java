package FewGames.dao;

import FewGames.entity.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDao{
  Optional<AppUser> selectByName(String username);
  void save(AppUser appUser);
}

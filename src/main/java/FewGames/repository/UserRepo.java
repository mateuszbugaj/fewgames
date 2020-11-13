package FewGames.repository;

import FewGames.entity.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<AppUser, Long> {
}

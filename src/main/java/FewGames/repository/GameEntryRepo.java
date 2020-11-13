package FewGames.repository;

import FewGames.entity.AppUser;
import FewGames.entity.GameEntry;
import org.springframework.data.repository.CrudRepository;

public interface GameEntryRepo extends CrudRepository<GameEntry, Long> {
}

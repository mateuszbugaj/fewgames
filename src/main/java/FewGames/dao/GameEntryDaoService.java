package FewGames.dao;

import FewGames.entity.GameEntry;
import FewGames.repository.GameEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository("realGameRepo")
public class GameEntryDaoService implements GameEntryDao {

  private final GameEntryRepo gameEntryRepo;

  @Autowired
  public GameEntryDaoService(GameEntryRepo gameEntryRepo) {
    this.gameEntryRepo = gameEntryRepo;
  }

  @Override
  public List<GameEntry> selectGameEntryByGameName(String gameName) {
    return StreamSupport
      .stream(gameEntryRepo.findAll().spliterator(), false)
      .filter(i -> i.getGameName().equals(gameName))
      .collect(Collectors.toList());
  }

  @Override
  public List<GameEntry> selectGameEntryByUserName(String userName) {
    return StreamSupport
      .stream(gameEntryRepo.findAll().spliterator(), false)
      .filter(i -> i.getUser().getUsername().equals(userName))
      .collect(Collectors.toList());
  }

  @Override
  public void addGameEntry(GameEntry gameEntry) {
    gameEntryRepo.save(gameEntry);
  }
}

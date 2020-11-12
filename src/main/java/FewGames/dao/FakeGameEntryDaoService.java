package FewGames.dao;

import FewGames.entity.GameEntry;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository("fakeGameRepo")
public class FakeGameEntryDaoService implements GameEntryDao {

  @Override
  public List<GameEntry> selectGameEntryByGameName(String gameName) {
    return getEntries().stream().filter(i -> i.getGameName().equals(gameName)).collect(Collectors.toList());
  }

  @Override
  public List<GameEntry> selectGameEntryByUserName(String userName) {
    return getEntries()
      .stream()
      .filter(i -> i.getUser().getUsername().equals(userName))
      .collect(Collectors.toList());
  }

  @Override
  public void addGameEntry(GameEntry gameEntry) {

  }

  private List<GameEntry> getEntries(){

    GameEntry gameEntry1 = new GameEntry(
      1L,
      "Snake",
      15,
      null,
      new Timestamp(System.currentTimeMillis())
    );

    GameEntry gameEntry2 = new GameEntry(
      2L,
      "Snake",
      23,
      null,
      new Timestamp(System.currentTimeMillis())
    );

    GameEntry gameEntry3 = new GameEntry(
      3L,
      "Other",
      34,
      null,
      new Timestamp(System.currentTimeMillis())
    );

    return Arrays.asList(gameEntry1, gameEntry2, gameEntry3);
  }
}

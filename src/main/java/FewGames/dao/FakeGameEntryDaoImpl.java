package FewGames.dao;

import FewGames.entity.GameEntry;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository("fakeGameRepo")
public class FakeGameEntryDaoImpl implements GameEntryDao {

  private List<GameEntry> entries = new ArrayList<>();

  public FakeGameEntryDaoImpl() {
    entries.addAll(Arrays.asList(
      new GameEntry(
        1L,
        "Snake",
        15,
        null,
        new Timestamp(System.currentTimeMillis())
      ), new GameEntry(
      2L,
      "Snake",
      23,
      null,
      new Timestamp(System.currentTimeMillis())
    ), new GameEntry(
      3L,
      "Asteroids",
      34,
      null,
      new Timestamp(System.currentTimeMillis())
    )
    ));
  }

  @Override
  public List<GameEntry> selectGameEntryByGameName(String gameName) {
    return entries
      .stream()
      .filter(i -> i.getGameName().equals(gameName))
      .collect(Collectors.toList());
  }

  @Override
  public List<GameEntry> selectGameEntryByUserName(String userName) {
    return entries
      .stream()
      .filter(i -> i.getUser()!=null)
      .filter(i -> i.getUser().getUsername().equals(userName))
      .collect(Collectors.toList());
  }

  @Override
  public void addGameEntry(GameEntry gameEntry) {
    entries.add(gameEntry);
  }
}

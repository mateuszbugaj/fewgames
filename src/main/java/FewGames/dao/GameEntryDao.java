package FewGames.dao;

import FewGames.entity.GameEntry;

import java.util.List;

public interface GameEntryDao {

  public List<GameEntry> selectGameEntryByGameName(String gameName);
  public List<GameEntry> selectGameEntryByUserName(String userName);
  public void addGameEntry(GameEntry gameEntry);
}

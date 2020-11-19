package FewGames.dao;

import FewGames.entity.AppUser;
import FewGames.entity.GameEntry;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles(profiles = "test")
class GameEntryImplTest {

  @Autowired
  GameEntryDao gameEntryDao;

  @Test
  void addingEntry(){
    // Given
    String gameName = "Snake";
    GameEntry gameEntry = new GameEntry(
      0L,
      gameName,
      5,
      null,
      new Timestamp(System.currentTimeMillis())
    );

    int entriesNumberBefore = gameEntryDao.selectGameEntryByGameName(gameName).size();

    // When
    gameEntryDao.addGameEntry(gameEntry);

    // Then
    int entriesNumberAfter = gameEntryDao.selectGameEntryByGameName(gameName).size();
    Assert.assertEquals(entriesNumberBefore + 1, entriesNumberAfter);

  }

  @Test
  void selectEntryByGameName(){
    // Given
    String name = "gameEntry123";
    GameEntry gameEntry = new GameEntry(666L, name, 5, null, new Timestamp(System.currentTimeMillis()));
    gameEntryDao.addGameEntry(gameEntry);

    // When
    List<GameEntry> entries = gameEntryDao.selectGameEntryByGameName(name);

    // Then
    Assert.assertEquals(1, entries.size());
  }

  @Test
  void selectEntryByUserName(){
    // Given
    String name = "userName123";
    AppUser user = new AppUser(name, "pass");
    GameEntry gameEntry = new GameEntry(777L, "Snake", 5, user, new Timestamp(System.currentTimeMillis()));
    gameEntryDao.addGameEntry(gameEntry);

    // When
    List<GameEntry> entries = gameEntryDao.selectGameEntryByUserName(name);

    // Then
    Assert.assertEquals(1, entries.size());
  }
}

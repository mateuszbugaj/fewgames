package FewGames.controller;

import FewGames.dao.FakeGameEntryDaoService;
import FewGames.entity.AppUser;
import FewGames.dao.FakeUserDaoService;
import FewGames.dto.GameEntryDto;
import FewGames.entity.GameEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;

@RestController
@RequestMapping("api/v1/")
public class AppController {

  private final FakeUserDaoService userDaoService;
  private final FakeGameEntryDaoService gameEntryDaoService;

  @Autowired
  public AppController(@Qualifier("fake") FakeUserDaoService userDaoService,
                       @Qualifier("fakeGameRepo") FakeGameEntryDaoService gameEntryDaoService) {

    this.userDaoService = userDaoService;
    this.gameEntryDaoService = gameEntryDaoService;
  }

  @GetMapping("hello")
  public String getStudent() {
    return "Hello from secured API";
  }

  @PostMapping("saveEntry")
  public void saveGameEntry(@RequestBody GameEntryDto gameEntryDto){
    AppUser user;

    user = userDaoService.selectAppUserByUsername(gameEntryDto.getUserName()).orElse(null);

    GameEntry gameEntry = new GameEntry(
      0L,
      gameEntryDto.getGameName(),
      gameEntryDto.getScore(),
      user,
      new Timestamp(System.currentTimeMillis())
    );

    gameEntryDaoService.addGameEntry(gameEntry);
  }

  @GetMapping("entries/{gameName}")
  public Iterable<GameEntry> gameEntries(@PathVariable("gameName") String gameName){
    return gameEntryDaoService.selectGameEntryByGameName(gameName);
  }

  @RequestMapping("user")
  public Principal user(Principal user) {
    return user;
  }
}

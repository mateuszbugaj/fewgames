package FewGames.controller;

import FewGames.dao.GameEntryDao;
import FewGames.dao.UserDao;
import FewGames.dto.UserDto;
import FewGames.entity.AppUser;
//import FewGames.dao.FakeUserDaoService;
import FewGames.dto.GameEntryDto;
import FewGames.entity.GameEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;

@RestController
@RequestMapping("api/v1/")
public class AppController {
  public static Logger logger = LoggerFactory.getLogger(AppController.class);

  private final UserDao userDaoService;
  private final GameEntryDao gameEntryDaoService;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public AppController(@Qualifier("real") UserDao userDaoService,
                       @Qualifier("realGameRepo") GameEntryDao gameEntryDaoService, PasswordEncoder passwordEncoder) {

    this.userDaoService = userDaoService;
    this.gameEntryDaoService = gameEntryDaoService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("saveUser")
  public void saveGameEntry(@RequestBody UserDto userDto){
    logger.info("Saving user: " + userDto.getUsername());

    AppUser user = new AppUser(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()));
    if(userDaoService.selectByName(userDto.getUsername()).isEmpty()){
      userDaoService.save(user);
    }
  }

  @PostMapping("saveEntry")
  public void saveGameEntry(@RequestBody GameEntryDto gameEntryDto){
    logger.info("Saving entry");
    AppUser user = userDaoService.selectByName(gameEntryDto.getUserName()).orElse(null);

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

  @GetMapping("entries/u/{userName}")
  public Iterable<GameEntry> userEntries(@PathVariable("userName") String userName){
    return gameEntryDaoService.selectGameEntryByUserName(userName);
  }

  @RequestMapping("user")
  public Principal user(Principal user) {
    return user;
  }
}

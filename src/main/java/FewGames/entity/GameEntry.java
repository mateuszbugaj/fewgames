package FewGames.entity;

import java.sql.Timestamp;

public class GameEntry {

  private final Long id;

  private final String gameName;

  private final Integer score;

  private final AppUser user;

  //https://www.baeldung.com/jpa-java-time
  private final Timestamp date;

  public GameEntry(Long id, String gameName, Integer score, AppUser user, Timestamp date) {
    this.id = id;
    this.gameName = gameName;
    this.score = score;
    this.user = user;
    this.date = date;
  }

  public Long getId() {
    return id;
  }

  public String getGameName() {
    return gameName;
  }

  public Integer getScore() {
    return score;
  }

  public AppUser getUser() {
    return user;
  }

  public Timestamp getDate() {
    return date;
  }
}

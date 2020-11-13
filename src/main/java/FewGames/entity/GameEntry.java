package FewGames.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class GameEntry {

  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String gameName;

  @Column
  private Integer score;

  @ManyToOne
  private AppUser user;

  //https://www.baeldung.com/jpa-java-time
  @Column
  private Timestamp date;

  public GameEntry(Long id, String gameName, Integer score, AppUser user, Timestamp date) {
    this.id = id;
    this.gameName = gameName;
    this.score = score;
    this.user = user;
    this.date = date;
  }

  public GameEntry() {
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

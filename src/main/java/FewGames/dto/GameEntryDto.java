package FewGames.dto;

public class GameEntryDto {
  private final String gameName;
  private final Integer score;
  private final String userName;

  public GameEntryDto(String gameName, Integer score, String userName) {
    this.gameName = gameName;
    this.score = score;
    this.userName = userName;
  }

  public String getGameName() {
    return gameName;
  }

  public Integer getScore() {
    return score;
  }

  public String getUserName() {
    return userName;
  }
}

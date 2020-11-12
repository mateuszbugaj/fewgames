package FewGames.auth;

public enum AppUserPermission {

  SCORE_READ("score:read"),
  SCORE_WRITE("score:write");

  private final String permission;

  AppUserPermission(String permission) {
    this.permission = permission;
  }

  public String getPermission() {
    return permission;
  }
}

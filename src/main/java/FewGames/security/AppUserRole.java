package FewGames.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum AppUserRole {
  USER(new HashSet<AppUserPermission>(Arrays.asList(AppUserPermission.SCORE_READ, AppUserPermission.SCORE_WRITE)));

  private final Set<AppUserPermission> permissions;


  AppUserRole(Set<AppUserPermission> permissions) {
    this.permissions = permissions;
  }

  public Set<AppUserPermission> getPermissions() {
    return permissions;
  }

  public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
    Set<SimpleGrantedAuthority> permissions = getPermissions()
      .stream()
      .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
      .collect(Collectors.toSet());

    permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

    return permissions;
  }
}

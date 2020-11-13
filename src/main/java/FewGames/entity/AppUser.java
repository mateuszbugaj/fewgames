package FewGames.entity;

import FewGames.auth.AppUserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;

@Entity
public class AppUser implements UserDetails {

  @Id
  @GeneratedValue
  private Long id;

  @Column(unique = true)
  private String username;

  @Column
  private String password;

  public AppUser(String username,
                 String password) {

    this.username = username;
    this.password = password;
  }

  public AppUser() {
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return AppUserRole.USER.getGrantedAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

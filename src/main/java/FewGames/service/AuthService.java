package FewGames.service;

import FewGames.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

@Service
public class AuthService implements UserDetailsService {

  private final UserDao userDao;

  @Autowired
  public AuthService(@Qualifier("real") UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userDao.selectByName(username)
      .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
  }
}

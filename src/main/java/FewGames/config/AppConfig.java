package FewGames.config;

import FewGames.dao.GameEntryDao;
import FewGames.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Autowired
  private ApplicationContext context;

  @Bean
  public UserDao userDao(@Value("${user_repo_name}") String qualifier){
    return (UserDao) context.getBean(qualifier);
  }

  @Bean
  public GameEntryDao gameEntryDao(@Value("${game_entry_repo_name}") String qualifier){
    return (GameEntryDao) context.getBean(qualifier);
  }
}

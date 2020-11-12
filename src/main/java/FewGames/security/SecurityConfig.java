package FewGames.security;

import FewGames.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  private final CustomAuthenticationEntryPoint authenticationEntryPoint;
  private final AuthService authService;


  @Autowired
  public SecurityConfig(PasswordEncoder passwordEncoder,
                        CustomAuthenticationEntryPoint authenticationEntryPoint,
                        AuthService authService) {

    this.passwordEncoder = passwordEncoder;
    this.authenticationEntryPoint = authenticationEntryPoint;
    this.authService = authService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
        http
          .csrf().disable()
          .authorizeRequests()
          .antMatchers("/api/**").hasRole(AppUserRole.USER.name())
          .antMatchers("/**").permitAll()
          .anyRequest()
          .authenticated()
          .and()
          .httpBasic().authenticationEntryPoint(authenticationEntryPoint);
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(authService);
    return provider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());
  }
}

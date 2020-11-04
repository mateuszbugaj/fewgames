package FewGames.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthenticationProviderImpl authProvider;

//  @Bean
//  @Override
//  public AuthenticationManager authenticationManagerBean() throws Exception {
//    return super.authenticationManagerBean();
//  }

//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.authenticationProvider(authProvider);
//    auth.inMemoryAuthentication()
//      .withUser("memuser")
//      .password("pass")
//      .roles("USER");
//  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
//    http.csrf().disable()
//      .authorizeRequests()
//      .antMatchers("/api/auth/**")
//      .permitAll()
//      .anyRequest().authenticated();

//    http.httpBasic()
//      .and()
//      .authorizeRequests()
//      .antMatchers("/api/auth/**")
//      .authenticated().and().authenticationProvider(authProvider);

//    http.csrf().disable()
//      .authorizeRequests().antMatchers("/loginFailed", "/login", "/api/auth/login").permitAll()
//      .and().authenticationProvider(new AuthenticationProviderImpl());

    http
      .authorizeRequests()
      .antMatchers("/home")
      .authenticated()
      //.and().authorizeRequests().antMatchers("/api/auth/login").permitAll().anyRequest().authenticated()
      .and().formLogin()
      .loginPage("/")
      .loginProcessingUrl("/api/auth/login");
    http.authenticationProvider(authProvider);

  }
}

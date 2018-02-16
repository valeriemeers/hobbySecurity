package be.qnh.hobby.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableGlobalMethodSecurity
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAuthenticationProvider authenticationProvider;

    public static final String APIROLE = "APIROLE";

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
                                @Value("${api.username}") String apiUsername,
                                @Value("${api.password}") String apiPassword) throws Exception{

        auth.inMemoryAuthentication().withUser(apiUsername).password(apiPassword).roles(APIROLE);
        auth.authenticationProvider(authenticationProvider);
    }

    @Configuration
    @Order(1)
    public static class AuWebSecurityAdapterRest extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();

            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.authorizeRequests().antMatchers("/api/**").hasRole(APIROLE).and().httpBasic();
            http.authorizeRequests().anyRequest().permitAll();
        }
    }
}

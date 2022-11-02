package cis.springboot.Users;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private String [] PUBLIC_END_POINTS={"/api/v1/auth/**"};
    @Bean
    public AuthFilter authFilter(){
        return new AuthFilter();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(PUBLIC_END_POINTS).permitAll()
                .anyRequest().authenticated().and().addFilterAfter(authFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}

package hh.swd20.contactdetails;

//import java.util.ArrayList;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import hh.swd20.contactdetails.web.UserDetailServiceImpl;

//tänne täytyy tehdä muutoksia, jotta tietty user voi tehdä muutoksia (url suojaus/rajoitukset configuressa)
//muutoksia täytyy tehdä myös thymeleaf templatelle

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity // annotation enables SpringSecurity web security support
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { // configure(HttpSecurity http) metodissa määritellaan mitkä URLit on turvattu ja pääsy login formiin
	//metodien kutsuja peräkkäin (säännöstö)
		http
		.authorizeRequests()
		.antMatchers("/css/**").permitAll()
		.and()
		.authorizeRequests().antMatchers("/h2-console/**").permitAll()
		.and().csrf().ignoringAntMatchers("/h2-console/**")
        .and().headers().frameOptions().sameOrigin()
		.and()
		.authorizeRequests()
		//.antMatchers("/delete/{id}").hasRole("USER") //vain tietty rooli(USER) voi poistaa
		//.antMatchers("/edit/{id}").hasRole("USER")
		.anyRequest().authenticated() //jos mikään ylläolevista ei mätsää sitten suoritetaan nämä eli kaikki sisäänkirjautuneet (missä tahansa roolissa olevat) voisuorittaa  
		.and()
		.formLogin() 
		//.loginPage("/login") ei tarvita, koska default login page tulee Spring securityltä
		.defaultSuccessUrl("/personlist")
		.permitAll()
		.and()
		.logout()
		.permitAll();
}
	// tietokantapohjainen käyttäjä
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder()); //salasanan bcryptaus
	}
/*	
//luo testikäyttöön käyttäjätunnus
@Bean
@Override
public UserDetailsService userDetailsService() {
	List<UserDetails> users = new ArrayList();
	UserDetails user = User.withDefaultPasswordEncoder()
			.username("user")
			.password("user")
			.roles("USER")
			.build();
	users.add(user);
	
	return new InMemoryUserDetailsManager(users);
} */
}

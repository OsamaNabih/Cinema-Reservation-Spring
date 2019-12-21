package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//import com.makolab.translation.security.JwtAccessDeniedHandler;
//import com.makolab.translation.security.JwtAuthenticationEntryPoint;
//import com.makolab.translation.security.JwtAuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	/*
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private JwtAccessDeniedHandler accessDeniedHandler;
	
	*/
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	/*
	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter();
		authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
		return authenticationTokenFilter;
	}
	
	@Bean
	public AccessDeniedHandler accessDeniedBean() throws Exception {
		return accessDeniedHandler;
	}
	*/
	
	 private final String uri = "/users/*";

	    @Override
	    public void configure(final HttpSecurity http) throws Exception {
	        http.csrf().disable();
	        //http.headers().httpStrictTransportSecurity().disable();
	        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	        
	        AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(uri);
	        // Authorize sub-folders permissions
	        http.authorizeRequests().requestMatchers(antPathRequestMatcher).permitAll()
	        	.anyRequest().authenticated();
	        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	    }
}
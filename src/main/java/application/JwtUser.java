package application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class JwtUser implements UserDetails {

	private static final long serialVersionUID = -4368579280320175076L;
	private final Long userId;
	private final String firstName;
	private final String lastName;
	private final String password;
	private final String email;
	private final Integer userType;
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean enabled;
	
	public JwtUser(User user) {
		this.userId = user.getUserId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.userType = user.getUserType();
		this.authorities = null;
		this.enabled = true;
	}
	
	/*
	public JwtUser(Long id, String password, String email) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		firstname = null;
		lastname = null;
		this.authorities = null;
		this.enabled = true;
		this.userType = 1;
	}

	public JwtUser(Long id, String password, String email, Collection<? extends GrantedAuthority> authorities,
			boolean enabled) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		firstname = null;
		lastname = null;
		this.authorities = authorities;
		this.enabled = enabled;
		this.userType = 1;
	}

	public JwtUser(Long id, String firstname, String lastname, String email, String password,
			Collection<? extends GrantedAuthority> authorities, boolean enabled) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.enabled = enabled;
		this.userType = 1;
	}
	
	*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}

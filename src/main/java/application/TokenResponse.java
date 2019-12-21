package application;

import lombok.Data;

@Data
public class TokenResponse extends Response {
	private final String jwt;
	
	TokenResponse(String jwt) {
		this.jwt = jwt;
	}
	
	TokenResponse(){this.jwt = null;}
}

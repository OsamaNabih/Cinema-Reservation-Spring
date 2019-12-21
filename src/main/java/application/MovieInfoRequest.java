package application;

import lombok.Data;

@Data
public class MovieInfoRequest {
	private String genre;
	
	private String name;
	
	private Long screenNumber;
	
	private Integer runtime;
}

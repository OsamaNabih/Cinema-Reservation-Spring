package application;

import lombok.Data;

@Data
public class MovieResponse extends Response {
	private Long movieId;
	
	private String genre;
	
	private String name;
	
	private Integer runtime;
	
	private Long screenNumber;
	
	MovieResponse(Movie movie) {
		this.movieId = movie.getMovieId();
		this.genre = movie.getGenre();
		this.name = movie.getName();
		this.runtime = movie.getRuntime();
		this.screenNumber = movie.getScreen().getScreenNumber();
	}
	
	MovieResponse(){}
}

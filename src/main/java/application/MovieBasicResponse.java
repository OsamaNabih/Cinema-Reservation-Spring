package application;

import lombok.Data;

@Data
public class MovieBasicResponse extends Response {
	private Long movieId;
	
	private String genre;
	
	private String name;
	
	private Integer runtime;
	
	MovieBasicResponse(Movie movie) {
		this.movieId = movie.getMovieId();
		this.genre = movie.getGenre();
		this.name = movie.getName();
		this.runtime = movie.getRuntime();
	}
}

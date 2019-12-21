package application;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MovieFullResponse extends Response {
	private MovieResponse movie;
	
	private List<ScreeningResponse> screenings;
	
	MovieFullResponse(Movie movie) {
		this.movie = new MovieResponse(movie);
		this.screenings = new ArrayList<ScreeningResponse>();
		for (Screening screening: movie.getScreenings()) {
			this.screenings.add(new ScreeningResponse(screening));
		}
	}
	
	MovieFullResponse(){}
}

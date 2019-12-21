package application;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Transactional
public class MovieController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ScreenRepository screenRepository;
	
	@Autowired
	private ScreeningRepository screeningRepository;
	
	@Autowired
	private SeatRepository seatRepository;
	
	@PostMapping(path="/movies/add", consumes={"application/json"}, produces= {"application/json"}) 
	public ResponseEntity<String> addMovie (@RequestBody MovieRequest movieRequest) {
		
		Movie movie = new Movie(movieRequest.getMovie());
		LocalDate date = movieRequest.getStartDate().toLocalDate();
		LocalDate endDate = movieRequest.getEndDate().toLocalDate();
		
		Screen screen = screenRepository.findById(movieRequest.getMovie().getScreenNumber()).get();
		movie.setScreen(screen);
		System.out.println(screen.toString());
		List<Screening> screenings = new ArrayList<Screening>();
		
		List<Time>screeningTimes = movieRequest.getScreeningTimes();
		while(!date.isAfter(endDate)) {
			
			java.sql.Date currentDate = java.sql.Date.valueOf(date);
			for (Time screeningTime:screeningTimes) {
				Screening screening = new Screening(currentDate, screeningTime, screen, movie);
				screenings.add(screening);
			}
			date = date.plusDays(1);
		}
		movie.setScreenings(screenings);
		movieRepository.save(movie);
		return ResponseEntity.ok("Movie added successfully");
	}
	
	@GetMapping(path="/movies/{movieId}", produces= {"application/json"})
	public ResponseEntity<MovieFullResponse> getMovie(@PathVariable Long movieId) {
		Movie movie = movieRepository.findById(movieId).get();
		MovieFullResponse movieResponse = new MovieFullResponse(movie);
		System.out.println(movieResponse.toString());
		return ResponseEntity.ok().body(movieResponse);
	}
	
	@GetMapping(path="/movies", produces= {"application/json"})
	public ResponseEntity<MoviesResponse> getMovies() {
		List<Movie> movies = movieRepository.findAll();
		List<MovieBasicResponse> basicMoviesResponse = new ArrayList<MovieBasicResponse>();
		movies.forEach(movie -> basicMoviesResponse.add(new MovieBasicResponse(movie)));
		MoviesResponse moviesResponse = new MoviesResponse();
		moviesResponse.setMovies(basicMoviesResponse);
		return ResponseEntity.ok().body(moviesResponse);
	}
	
	@GetMapping(path="/movies/{movieId}/{screenId}/{screeningId}/seats", produces= {"application/json"})
	public ResponseEntity<SeatsResponse> getSeats(@PathVariable Long movieId, @PathVariable Long screenId, @PathVariable Long screeningId) {
		SeatsResponse seatsResponse = new SeatsResponse();
		Screening screening = screeningRepository.findById(screeningId).get();
		List<Seat> seats = seatRepository.findAllByScreening(screening);
		Screen screen = screenRepository.findById(screenId).get();
		List<SeatResponse> seatResponses = new ArrayList<SeatResponse>();
		seats.forEach(seat -> seatResponses.add(new SeatResponse(seat)));
		seatsResponse.setSeats(seatResponses);
		seatsResponse.setRows(screen.getScreenRows());
		seatsResponse.setCols(screen.getScreenColumns());
		return ResponseEntity.ok(seatsResponse);
	}
	
	@PostMapping(path="/movies/{movieId}/{screenId}/{screeningId}/seats", consumes={"application/json"}, produces= {"application/json"})
	public ResponseEntity<String> addSeats(@PathVariable Long movieId, @PathVariable Long screenId, @PathVariable Long screeningId, @RequestBody SeatsRequest seatsRequest) {
		User user = userRepository.findById(seatsRequest.getUserId()).get();
		Screening screening = screeningRepository.findById(screeningId).get();
		List<Seat> seats = new ArrayList<Seat>();
		seatsRequest.getSeats().forEach(seatRequest -> seats.add(new Seat(seatRequest, user, screening, movieId)));
		seatRepository.saveAll(seats);
		return ResponseEntity.ok("Your seats have been reserved");
	}


}

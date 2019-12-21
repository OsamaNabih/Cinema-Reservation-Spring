package application;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.BatchSize;

import lombok.Data;

@Data
@Entity
public class Movie {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long movieId;
	
	private String genre;
	
	private String name;
	
	private Integer runtime;
	
	@OneToOne
	@JoinColumn(name = "screen_Number")
	private Screen screen;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "movie", orphanRemoval = true)
	@BatchSize(size = 2000)
	private List<Screening> screenings;
	
	Movie(MovieInfoRequest movie) {
		this.name = movie.getName();
		this.genre = movie.getGenre();
		this.runtime = movie.getRuntime();
	}
	
	Movie(){}
}

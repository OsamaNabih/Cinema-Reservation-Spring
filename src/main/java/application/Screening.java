package application;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Screening {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long screeningId;
	
	private Date screeningDate;
	
	private Time screeningTime;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	@NotNull
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "screen_number")
	private Screen screen;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "screening", orphanRemoval = true)
	private List<Seat> seats;
	
	Screening(Date screeningDate, Time screeningTime, Screen screen, Movie movie) {
		this.screeningDate = screeningDate;
		this.screeningTime = screeningTime;
		this.screen = screen;
		this.movie = movie;
	}
	
	Screening(){}
}

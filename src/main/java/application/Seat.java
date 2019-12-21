package application;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Seat {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long seatId;
	
	@NotNull
	private Integer rowNum;
	
	@NotNull
	private Integer colNum;
	
	private Long ticketNumber;
	
	private Boolean reserved;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "screening_id")
	private Screening screening;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	Seat(SeatRequest seat, User user, Screening screening, Long movieId) {
		this.rowNum = seat.getRowNum();
		this.colNum = seat.getColNum();
		this.user = user;
		this.screening = screening;
		this.reserved = true;
		String idChain =  user.getUserId().toString() + movieId.toString() + screening.getScreen().getScreenNumber().toString() + screening.getScreeningId().toString();
		String ticketString = idChain + rowNum.toString() + colNum.toString();
		this.ticketNumber = Long.valueOf(ticketString);
	}
	
	Seat(){}
}

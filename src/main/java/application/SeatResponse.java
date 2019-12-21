package application;

import java.util.List;

import lombok.Data;

@Data
public class SeatResponse extends Response {
	private Integer rowNum;
	
	private Integer colNum;
	
	private Boolean reserved;
	
	private Long ticketNumber;
	
	SeatResponse(Seat seat) {
		this.rowNum = seat.getRowNum();
		this.colNum = seat.getColNum();
		this.reserved = seat.getReserved();
		this.ticketNumber = seat.getTicketNumber();
	}
	
	SeatResponse(){}
}

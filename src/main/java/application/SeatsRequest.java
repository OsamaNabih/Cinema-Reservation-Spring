package application;

import java.util.List;

import lombok.Data;

@Data
public class SeatsRequest {
	private Long userId;
	
	private List<SeatRequest> seats;
}

package application;

import java.util.List;

import lombok.Data;

@Data
public class SeatsResponse extends Response {
	private Integer rows;
	
	private Integer cols;
	
	List<SeatResponse> seats;
}

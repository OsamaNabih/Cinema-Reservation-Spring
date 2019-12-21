package application;

import java.sql.Date;
import java.sql.Time;

import lombok.Data;

@Data
public class ScreeningResponse extends Response {
	private Long screeningId;
	
	private Date screeningDate;
	
	private Time screeningTime;
	
	ScreeningResponse(Screening screening) {
		this.screeningDate = screening.getScreeningDate();
		this.screeningTime = screening.getScreeningTime();
		this.screeningId = screening.getScreeningId();
	}
	
	ScreeningResponse(){}
}

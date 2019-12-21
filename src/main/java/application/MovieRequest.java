package application;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class MovieRequest {
	private MovieInfoRequest movie;
	
	private List<Time> screeningTimes;
	
	private Date startDate;
	
	private Date endDate;
}

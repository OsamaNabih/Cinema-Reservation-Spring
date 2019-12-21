package application;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Screen {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long screenNumber;
	
	private Integer screenRows;
	
	private Integer screenColumns;
}

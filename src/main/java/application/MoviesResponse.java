package application;

import java.util.List;

import lombok.Data;

@Data
public class MoviesResponse extends Response {
	List<MovieBasicResponse> movies;
}

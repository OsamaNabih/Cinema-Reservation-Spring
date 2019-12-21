package application;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface SeatRepository extends JpaRepository<Seat, Long> {
	List<Seat> findAllByScreening(Screening screening);
}

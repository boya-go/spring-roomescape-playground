package roomescape;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    private List<Reservation> reservations = new ArrayList<>();

    public ReservationController() {
        reservations.add(new Reservation(1L, "브라운", LocalDate.of(2023,1,1), LocalTime.of(10,0)));
        reservations.add(new Reservation(2L, "브라운", LocalDate.of(2023,1,2), LocalTime.of(11,0)));
    }

    @GetMapping("/reservations")
    @ResponseBody
    public List<Reservation> getReservations() {
        return reservations;
    }
}

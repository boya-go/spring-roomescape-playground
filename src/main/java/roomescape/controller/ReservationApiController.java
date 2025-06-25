package roomescape.controller;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.domain.Reservation;
import roomescape.domain.dao.Reservations;
import roomescape.domain.dto.ReservationRequest;

@RestController
@RequestMapping("/reservations")
public class ReservationApiController {

    private final Reservations reservations;

    public ReservationApiController(Reservations reservations) {
        this.reservations = reservations;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations() {
        return ResponseEntity.ok(reservations.findAll());
    }

    @PostMapping
    public ResponseEntity<Reservation> addReservation(@Valid @RequestBody ReservationRequest dto) {
        Reservation reservation = reservations.insert(dto);
        return ResponseEntity
                .created(URI.create("/reservations/" + reservation.getId()))
                .body(reservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable long id) {
        reservations.delete(id);
        return ResponseEntity.noContent().build();
    }
}

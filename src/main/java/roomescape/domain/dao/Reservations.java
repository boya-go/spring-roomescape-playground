package roomescape.domain.dao;

import java.sql.ResultSet;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import roomescape.domain.Reservation;
import roomescape.domain.dto.ReservationRequest;
import roomescape.exception.ReservationNotFoundException;

@Repository
public class Reservations {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public Reservations(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("reservation")
                .usingGeneratedKeyColumns("id");
    }

    public Reservation insert(ReservationRequest dto) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(dto);
        Number id = jdbcInsert.executeAndReturnKey(parameterSource);
        return new Reservation(id.longValue(), dto.getName(), dto.getDate(), dto.getTime());
    }

    public List<Reservation> findAll() {
        String sql = "SELECT * FROM reservation";
        return jdbcTemplate.query(sql, rowMapper());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM reservation WHERE ID = ?";
        int count = jdbcTemplate.update(sql, id);
        if (count == 0) {
            throw new ReservationNotFoundException("삭제할 수 있는 예약이 존재하지 않습니다.");
        }
    }

    private RowMapper<Reservation> rowMapper() {
        return (ResultSet resultset, int rowNum) -> new Reservation(
                resultset.getLong("id"),
                resultset.getString("name"),
                resultset.getDate("date").toLocalDate(),
                resultset.getTime("time").toLocalTime()
        );
    }
}

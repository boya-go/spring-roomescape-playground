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
public class ReservationDAO {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private static final String TABLE_NAME = "reservation";
    private static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;
    private static final String DELETE_BY_ID = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";

    public ReservationDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns("id");
    }

    public Reservation insert(ReservationRequest dto) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(dto);
        Number id = jdbcInsert.executeAndReturnKey(parameterSource);
        return new Reservation(id.longValue(), dto.getName(), dto.getDate(), dto.getTime());
    }

    public List<Reservation> findAll() {
        return jdbcTemplate.query(SELECT_ALL, rowMapper());
    }

    public void delete(Long id) {
        int count = jdbcTemplate.update(DELETE_BY_ID, id);
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

package application.repository;

import application.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Integer> {
    List<Point> getAllByUser(String user);

    void deleteAllByUser(String user);

    @Transactional
    @Modifying
    @Query(value = "delete from points where death<=current_timestamp", nativeQuery = true)
    void deleteAllDeadRows();
}

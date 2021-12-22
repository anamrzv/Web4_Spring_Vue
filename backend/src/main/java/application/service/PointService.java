package application.service;

import application.domain.Point;
import application.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PointService {
    @Autowired
    private PointRepository pointRepository;

    @Transactional
    public List<Point> findAllByUser(String login) {
        return pointRepository.getAllByUser(login);
    }

    @Transactional
    @Modifying
    public void deleteAllByUser(String login) {
        pointRepository.deleteAllByUser(login);
    }

    @Transactional
    @Modifying
    public void deleteDeadRows() {
        pointRepository.deleteAllDeadRows();
    }
}

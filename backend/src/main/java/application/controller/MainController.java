package application.controller;

import application.domain.Point;
import application.pojo.PointRequest;
import application.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/points")
@CrossOrigin(origins = "*")
public class MainController {

    @Autowired
    private PointRepository pointRepository;

    @GetMapping(value = "/main",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    private ResponseEntity<List<Point>> getPoints() {
        System.out.println(pointRepository.findAll());
        return ResponseEntity.ok().body(pointRepository.findAll());
    }

    @PostMapping(value = "/main",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    private ResponseEntity<Point> addNewPoint(@RequestBody PointRequest point) {
        float x1 = Float.valueOf(point.getX());
        float y1 = Float.valueOf(point.getY());
        float r1 = Float.valueOf(point.getR());
        boolean isInFigure = isInTriangle(x1, y1, r1) || isInRectangle(x1, y1, r1) || isInCircle(x1, y1, r1);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");
        String time = dateFormat.format(LocalDateTime.now());
        String answer;
        if (isInFigure) answer = "Попадание";
        else answer = "Промах";
        Point savedPoint = pointRepository.save(new Point(x1, y1, r1, answer, time));
        System.out.println(savedPoint + " saved into DB");
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping(value = "/main",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    private ResponseEntity<String> deletePoints(){
        pointRepository.deleteAll();
        return ResponseEntity.ok().body(null);
    }

    private boolean isInTriangle(float x, float y, float r) {
        return x <= 0 && y >= 0 && y <= x + r;
    }

    private boolean isInRectangle(float x, float y, float r) {
        return x <= 0 && y <= 0 && y >= -r / 2 && x >= -r;
    }

    private boolean isInCircle(float x, float y, float r) {
        return x >= 0 && y <= 0 && x * x + y * y <= r * r;
    }
}

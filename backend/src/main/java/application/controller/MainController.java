package application.controller;

import application.configuration.jwt.JwtProvider;
import application.domain.Point;
import application.domain.User;
import application.pojo.PointRequest;
import application.repository.PointRepository;
import application.service.PointService;
import application.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/points")
@CrossOrigin(origins = "*")
public class MainController {

    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private PointService pointService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().findAndRegisterModules().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    @GetMapping(value = "/main",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    private ResponseEntity<List<Point>> getPoints(HttpServletRequest req) {
        if (jwtProvider.getTokenFromRequest(req) != null) {
            User user = userService.findByLogin(jwtProvider.getLoginFromToken(jwtProvider.getTokenFromRequest(req)));
            return ResponseEntity.ok().body(pointRepository.getAllByUser(user.getUsername()));
        } else return new ResponseEntity("Ваша сессия закончилась. Требуется войти в систему.", HttpStatus.BAD_GATEWAY);
    }

    @PostMapping(value = "/main",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    private ResponseEntity<Point> addNewPoint(@RequestBody PointRequest point, HttpServletRequest req) {
        if (jwtProvider.getTokenFromRequest(req) != null) {
            float x1 = Float.valueOf(point.getX());
            float y1 = Float.valueOf(point.getY());
            float r1 = Float.valueOf(point.getR());
            User user = userService.findByLogin(jwtProvider.getLoginFromToken(jwtProvider.getTokenFromRequest(req)));
            boolean isInFigure = isInTriangle(x1, y1, r1) || isInRectangle(x1, y1, r1) || isInCircle(x1, y1, r1);
            String answer;
            if (isInFigure) answer = "Попадание";
            else answer = "Промах";
            Point savedPoint = new Point(x1, y1, r1, answer, LocalDateTime.now(), point.getTtl(), user.getUsername());
            pointRepository.save(savedPoint);
            System.out.println(savedPoint + " saved into DB");
            return ResponseEntity.ok().body(null);
        } else return new ResponseEntity("Ваша сессия закончилась. Требуется войти в систему.", HttpStatus.BAD_GATEWAY);
    }

    @DeleteMapping(value = "/main",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    private ResponseEntity<String> deletePoints(HttpServletRequest req) {
        if (jwtProvider.getTokenFromRequest(req) != null) {
            User user = userService.findByLogin(jwtProvider.getLoginFromToken(jwtProvider.getTokenFromRequest(req)));
            pointService.deleteAllByUser(user.getUsername());
            return ResponseEntity.ok().body(null);
        } else return new ResponseEntity("Ваша сессия закончилась. Требуется войти в систему.", HttpStatus.BAD_GATEWAY);
    }

    @PostMapping(value = "/pdf",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> returnFile(HttpServletRequest req) throws IOException {
        if (jwtProvider.getTokenFromRequest(req) != null) {
            User user = userService.findByLogin(jwtProvider.getLoginFromToken(jwtProvider.getTokenFromRequest(req)));

            fillFileWithCurrentData(user.getUsername());
            File file = new File("data.txt");
            FileInputStream in = new FileInputStream(file);
            byte[] arr = new byte[(int) file.length()];
            in.read(arr);
            in.close();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            String filename = "points.txt";
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            ResponseEntity<byte[]> response = new ResponseEntity<>(arr, headers, HttpStatus.OK);
            return response;
        } else return new ResponseEntity("Ваша сессия закончилась. Требуется войти в систему.", HttpStatus.BAD_GATEWAY);
    }

    private void fillFileWithCurrentData(String user) {
        try {
            File toReturnFile = new File("data.txt");
            PrintWriter writer = new PrintWriter(toReturnFile);

            List<Point> points = pointService.findAllByUser(user);
            for (Point object : points) {
                writer.write(OBJECT_MAPPER.writeValueAsString(object) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

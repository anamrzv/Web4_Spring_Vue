package application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Data
@Entity
@Table(name = "points")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "x")
    private Float x;
    @Column(name = "y")
    private Float y;
    @Column(name = "r")
    private Float r;
    @Column(name = "result")
    private String result;
    @Column(name = "username")
    private String user;
    @Column(name = "ttl")
    private LocalTime ttl;
    @Column(name = "creation")
    @JsonIgnore
    private LocalDateTime creation;
    @Column(name = "death")
    @JsonIgnore
    private LocalDateTime death;
    @Column(name = "creation_string")
    private String creationString;
    @Column(name = "death_string")
    private String deathString;


    public Point(float x, float y, float r, String answer, LocalDateTime creation, String ttl, String user) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = answer;
        this.creation = creation;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.ttl = LocalTime.parse(ttl, formatter);
        this.death = creation.plusMinutes(this.ttl.getMinute()).plusHours(this.ttl.getHour());
        this.user = user;
        this.creationString = getDateTimeAsString(creation);
        this.deathString = getDateTimeAsString(death);
    }

    @JsonIgnore
    public String getDateTimeAsString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return date.format(formatter);
    }


}


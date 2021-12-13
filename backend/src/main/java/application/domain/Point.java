package application.domain;

import lombok.*;

import javax.persistence.*;

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
    @Column
    private String result;
    @Column
    private String time;

    public Point(float x, float y, float r, String answer, String time){
        this.x=x;
        this.y=y;
        this.r=r;
        this.result=answer;
        this.time=time;
    }
}


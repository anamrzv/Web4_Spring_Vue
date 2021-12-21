package application.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PointRequest {
    private String x;
    private String y;
    private String r;
    private String ttl;
}

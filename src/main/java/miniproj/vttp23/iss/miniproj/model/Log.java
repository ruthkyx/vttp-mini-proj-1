package miniproj.vttp23.iss.miniproj.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    
    private Date date;

    private String text;
}

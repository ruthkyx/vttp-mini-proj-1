package miniproj.vttp23.iss.miniproj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Verse {
    
    private String book; 

    private Integer chapter;

    private Integer verse;

}

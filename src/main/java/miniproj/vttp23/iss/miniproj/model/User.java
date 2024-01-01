package miniproj.vttp23.iss.miniproj.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @NotEmpty(message="Field cannnot be empty")
    @Size(min=3, max=12)
    private String username; 

    @NotEmpty(message="Field cannnot be empty")
    @Size(min=8, max= 16)
    private String password;
}

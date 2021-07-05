package cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private long id;
    @NotBlank
    private String title;
    private LocalDateTime date;
    @Min(20)
    private int maxSpaces;
    private int freeSpaces;

    public void reserve(int rev){
        if (rev > freeSpaces) throw new IllegalStateException();
        this.freeSpaces = this.freeSpaces - rev;
    }
}

package cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private String title;
    private LocalDateTime date;
    private int freeSpaces;

    public void reserve(int rev){
        if (rev > freeSpaces) throw new IllegalStateException("No more free spaces!");
        this.freeSpaces = this.freeSpaces - rev;
    }
}

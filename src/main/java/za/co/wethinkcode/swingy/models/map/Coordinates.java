package za.co.wethinkcode.swingy.models.map;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Coordinates
{
    @NotNull
    @Min(value = 0, message = "x-coor muust be greater than zero.")
    @Max(value = 2147483646, message = "x-coor must be lesss than MAX_INT value.")
    private int x;

    @NotNull
    @Min(value = 0, message = "y-coord must be greater than zero.")
    @Max(value = 2147483646, message = "y-coord must  be less than MAX_INT value.")
    private int y;

    public Coordinates(int x, int y)
    {
        this.setX(x);
        this.setY(y);
    }
}

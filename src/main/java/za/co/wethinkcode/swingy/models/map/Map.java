package za.co.wethinkcode.swingy.models.map;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.swingy.annotations.ValidateMapGrid;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Map
{
    @NotNull
    @Min(value = 5, message = "The dimension must be 5.")
    @Max(value = 55, message = "The map's dimensions can't exceed The Max value of 55.")
    private int size;

    @ValidateMapGrid
    private int[][] grid;

    public Map(int size)
    {
       this.setSize(size);
       this.setGrid(new int[size][size]);
    }
}

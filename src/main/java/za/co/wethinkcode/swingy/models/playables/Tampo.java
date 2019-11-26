package za.co.wethinkcode.swingy.models.playables;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.swingy.annotations.ValidateType;
import za.co.wethinkcode.swingy.models.map.Coordinates;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Tampo extends Player
{
    public Tampo(String name, String type, int level, int exp, int atk, int def, int hp,
                        Coordinates coordinates)
    {
        super(name, type, level, exp, atk, def, hp, coordinates);
    }

    public Tampo(String name, Coordinates coordinates)
    {
        super(name, "Tampo",1, 1000, 40, 25, 300, coordinates);
    }
}

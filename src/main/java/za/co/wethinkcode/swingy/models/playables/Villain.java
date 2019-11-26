package za.co.wethinkcode.swingy.models.playables;


import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.swingy.annotations.ValidateType;
import za.co.wethinkcode.swingy.models.map.Coordinates;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Setter
@Getter
public class Villain extends Player
{
    @NotNull
    @Size(min = 5, max = 30, message = "The Enemy villain's catchphrase must be  between 5-30 characters long.")
    private String catchPhrase;

    public Villain(String name, String type, int level, int exp, int atk, int def, int hp, Coordinates pos)
    {
        super(name,type, level, exp, atk, def, hp, pos);
    }
}

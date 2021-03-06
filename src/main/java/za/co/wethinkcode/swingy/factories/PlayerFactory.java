package za.co.wethinkcode.swingy.factories;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.swingy.controllers.GameController;
import za.co.wethinkcode.swingy.models.map.Coordinates;
import za.co.wethinkcode.swingy.models.playables.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Setter
@Getter
public class PlayerFactory
{
    private static Player validatePlayer(Player player, GameController controller)
    {
         ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
         Validator validator = factory.getValidator();

         Set<ConstraintViolation<Player>> constraintViolations = validator.validate(player);
         if (constraintViolations.size() > 0 )
         {
             for (ConstraintViolation<Player> constraints : constraintViolations)
                 controller.getErrors().add("Error :" + constraints.getMessage());
             return (null);
     }
       return (player);
    }

    public static Player customPlayer(String name, String type, int lvl, int exp, int atk, int def, int hp,
                                   Coordinates coordinates, GameController controller)
    {
        Player player;

        if (type.equals("Vandam"))
            player = new Vandam(name, type, lvl, exp, atk, def, hp, coordinates);
        else if (type.equals("Brecelee"))
            player = new Brecelee(name, type, lvl, exp, atk, def, hp, coordinates);
        else if (type.equals("Tampo"))
            player = new Tampo(name, type, lvl, exp, atk, def, hp, coordinates);
        else
            player = null;
        return (validatePlayer(player, controller));
    }

    public static Player defaultPlayer(String name, String type, Coordinates coordinates, GameController controller)
    {
        Player player;
        if (type.equals("Vandam"))
            player = new Vandam(name, coordinates);
        else if (type.equals("Brecelee"))
            player = new Brecelee(name, coordinates);
        else if (type.equals("Tampo"))
            player = new Tampo(name, coordinates);
        else
            player = null;
        return (validatePlayer(player, controller));
    }
}

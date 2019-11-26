package za.co.wethinkcode.swingy.views.consoleViews;

import za.co.wethinkcode.swingy.controllers.GameController;
import za.co.wethinkcode.swingy.models.map.Map;
import za.co.wethinkcode.swingy.models.playables.Player;
import za.co.wethinkcode.swingy.views.IDisplay;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleDisplay implements IDisplay
{
    private GameController controller;
    private Scanner stdin;
    private boolean switched = false;
    public ConsoleDisplay(GameController controller)
    {
        this.controller = controller;
        stdin = new Scanner(System.in);
    }

    public void setSwitched(boolean value)
    {
        switched = value;
    }
//https://stackoverflow.com/questions/55672661/what-this-character-sequence-033h-033j-does-in-c
    public void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void displayBeginview()
    {
        String input = "";
        while (!(input.equals("1") || input.equals("2") || input.equals("3"))) {
            clearScreen();
            System.out.print(
                    "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                           "@   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ @ \n" +
                            "@    @  Welcome To our second project called Swingy              @   @\n" +
                            "@     @                                                         @    @\n" +
                            "@      @   You are free to choose any player of your choise    @     @\n" +
                            "@       @  1. New player                                      @      @\n" +
                            "@        @ 2. Saved player                                   @       @\n" +
                            "@         @3. Quit                                         @         @\n" +
                            "@          @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ @           @\n" +
                            "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
            System.out.print("Please Make a choice: ");
            if (stdin.hasNext())
                input = stdin.next();
            else
                System.exit(0);
            if (!(input.equals("1") || input.equals("2") || input.equals("3")))
            {
                System.out.println("\nYour choice does not match. Enter either 1, 2, or 3.");
                try { Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        controller.receiveUserInput(input);
    }


    public void displayPlayerSelectionView(ArrayList<Player> heroes)
    {
        String input = "";
        while (!(input.matches("\\d+$") || input.equals("q") || input.equals("b")))
        {
            clearScreen();
            System.out.println(
                    "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                    "@                                   Select a saved hero                                    @\n" +
                    " @                                                                                      @\n" +
                       " @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
            );
            int index = 0;
            for(Player player : heroes)
            {
                index++;
                String line = String.format("" +
                                "" +
                                "\t%d. Name: %s , Class: %s , Level: %d, Exp: %d, ATK: %d, DEF: %d, HP: %d\n\n",
                        index, player.getName(), player.getType(), player.getLevel(), player.getExp(), player.getAtk(),
                        player.getDef(),player.getHp()
                        );
                System.out.print(line);

            }
            System.out.print(
                    "\n*  q - Quit                                                                                @\n" +
                    "@    b - Back                                                                                @\n" +
                    " @                                                                                          @\n" +
                      "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                    "Please Make Choice: ");
            if (stdin.hasNext())
                input = stdin.next();
            else
                System.exit(0);
            if (!(input.matches("\\d+$") || input.equals("q") || input.equals("b")))
            {
                System.out.println("Your choice is not valid. Enter a number that represent a hero");
                try{Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        controller.receiveUserInput(input);
    }


    public void displayCreatePlayerView()
    {
        String input = "";

        while (!(input.equals("1") || input.equals("2") || input.equals("3")))
        {
            clearScreen();
            System.out.print(
                    "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                    "@                                                                   @\n" +
                    "@        Which heroe class do you want to select                   @\n" +
                      "@                                                               @\n" +
                       "@             1. Tampo                                       @\n" +
                        "@            2. Brucelee                                    @\n" +
                         "@           3. Vandam                                     @\n" +
                          "@                                                       @\n" +
                           "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                    "Please Make Choice: "
            );
            if(stdin.hasNext())
                input = stdin.next();
            else
                System.exit(0);
            if (!(input.equals("1") || input.equals("2") || input.equals("3")))
            {
                System.out.println("Your choice does not match. Enter either 1, 2 or 3.");
                try { Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        controller.receiveUserInput(input);
    }

    public void displayHeroNamePrompt()
    {
        String input = "";
        System.out.print(
                "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                  "@                                    @\n" +
                   "@    Please Enter The Hero Name    @\n" +
                    "@                                @\n" +
                     "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "Name: "
                );
        if (stdin.hasNext())
            input = stdin.next();
        else
            System.exit(0);
        controller.receiveUserInput(input);
    }

    public void displayDefaultOrCustomHero()
    {
        String input = "";

        while (!(input.equals("1") || input.equals("2")))
        {
            clearScreen();
            System.out.print(
                    "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                     "@                                                   @\n" +
                        "@        Please Select custom or default stats @\n" +
                          "@                                           @\n" +
                            "@    1. The Custom stats                 @\n" +
                              "@  2. The Default stats               @\n" +
                               "@                                   @\n" +
                                 "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                            "Please Make a choice: "
            );
            if (stdin.hasNext())
                input = stdin.next();
            else
                System.exit(0);
            if (!(input.equals("1") || input.equals("2")))
            {
                System.out.println("Your choice does not match. Enter either 1 or 2.");
                try { Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        controller.receiveUserInput(input);
    }

    private int retrieveStat(String stat)
    {
        String value = "";
        while (!value.matches("-?\\d+$"))
        {
            clearScreen();
            System.out.print(
                    "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                        "@                                        @\n" +
                         "@        Entering hero statistiques    @\n" +
                           "@                                   @\n" +
                            "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
            );
            System.out.print("\n" + stat +": ");
            if (stdin.hasNext())
                value = stdin.next();
            else
                System.exit(0);
            if(!value.matches("-?\\d+$"))
            {
                System.out.println("\nInvalid " + stat +". Please a number is required.");
                try { Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        return (Integer.parseInt(value));
    }

    public void displayHeroStatsPrompt(String name, String type)
    {
        int level, atk, def, hp;

        level = retrieveStat("Level");
        atk = retrieveStat("Atk");
        def = retrieveStat("Def");
        hp = retrieveStat("Hp");
        controller.createCustomHero(type, name,level, atk, def, hp);
    }

    public void displayErrors(ArrayList<String> errors)
    {
        String input = "";
        while (!input.equals("b"))
        {
            clearScreen();
            System.out.println(
                         "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                            "@                                                                   @\n" +
                             "@               The detected hero is not valid!                   @\n" +
                              "@                                                              @\n" +
                                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
            );
            for (String error : errors)
                System.out.println("\t" + error);
            System.out.print("\nb - Back\n\nChoice: ");
            if (stdin.hasNext())
                input = stdin.next();
            else
                System.exit(0);
            if (!input.equals("b"))
            {
                System.out.println("Your choice is not valid. Enter b to go back.");
                try {Thread.sleep(1000); } catch (InterruptedException ex) {ex.printStackTrace(); }
            }
        }
        controller.receiveUserInput(input);
    }

    public void displayBattleReport(String report)
    {
        System.out.println(
            "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
            "@                                                                 @\n" +
             "@                            Fight Report!                      @\n" +
              "@                                                              @\n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
        System.out.println(report);
    }

    public void displayGameOver(boolean heroWon)
    {
        String status = heroWon ? "won the fight!" : "lost the fight!";
        String input="";
        while (!(input.equals("1") || input.equals("2")))
        {
            System.out.print(
                "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                 "@                                               @\n" +
                    "@   The Game is over you " + status + "    @\n" +
                     "@                                       @\n" +
                       "@     1. Main Menu                  @\n" +
                         "@   2. Quit                      @\n" +
                          "@                              @\n" +
                            "@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                        "Please Make a choice: "
            );
            if (stdin.hasNext())
                input =  stdin.next();
            else
                System.exit(0);
            if (!(input.equals("1") || input.equals("2")))
            {
                System.out.println("The choice is not valid.");
                try { Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        controller.receiveUserInput(input);
    }

    public void displayQuitDialogue()
    {
        String input="";
        while (!(input.equals("1") || input.equals("2")))
        {
            clearScreen();
            System.out.print(
                    "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                     "@                                                  @\n" +
                        "@       Are you sure you want to quit Game?   @\n" +
                         "@                                           @\n" +
                           "@      1. Yes                            @\n" +
                             "@    2. No                           @\n" +
                              "@                                  @\n" +
                               "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                            "Please Make a choice: "
             );
            if (stdin.hasNext())
                input =  stdin.next();
            else
                System.exit(0);
            if (!(input.equals("1") || input.equals("2")))
            {
                System.out.println("Your choice is not valid. Enter 1 or 2.");
                try { Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        controller.receiveUserInput(input);
    }

    private void displayOptions()
    {
        System.out.print(
                "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                 "@                                                         @\n" +
                    "@           Direction        Game Options             @\n" +
                     "@                                                   @\n" +
                        "@       w - Up        q - Quit the game          @\n" +
                          "@     a - Left                               @\n" +
                            "@   s - Down        x - use Gui    @\n" +
                             "@  d - Right                             @\n" +
                               "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                        "Please Make a choice: "
        );
    }

    public void displayMap(final Map map)
    {
        Player hero = controller.getHero();
        System.out.println(
                      "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                         "@      @@@@@@@@@@@@@@@@@@@@@@@           Hero Statistiques       @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                          "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
        String stats =  String.format("Name: %s , Class: %s , Level: %d, Exp: %d, ATK: %d, DEF: %d, HP: %d\n\n",
             hero.getName(), hero.getType(), hero.getLevel(), hero.getExp(), hero.getAtk(), hero.getDef(), hero.getHp());
        System.out.print(stats);
        for (int i = 0; i < map.getSize(); i++)
        {
            for (int j = 0; j < map.getSize(); j++)
            {
                if (map.getGrid()[i][j] == 1)
                    System.out.print("[H]  ");
                else if(map.getGrid()[i][j] == 2)
                    System.out.print("[E]  ");
                else
                    System.out.print("[ ]  ");
            }
            System.out.println("");
        }
    }

    public void displayFightOrRunPrompt()
    {
        String input = "";

        while (!(input.equals("1") || input.equals("2")))
        {
            System.out.print(
                    "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                        "@                                    @\n" +
                          "@          Enemy                  @\n" +
                            "@                              @\n" +
                              "@     1. Fight              @\n" +
                               "@    2. Run              @\n" +
                                "@                      @\n" +
                                  "@@@@@@@@@@@@@@@@@@@@\n" +
                            "Please Make a choice: "
            );
            if (stdin.hasNext())
                input = stdin.next();
            else
                System.exit(0);
            if (!(input.equals("1") || input.equals("2")))
            {
                System.out.println("Your choice does not match. Enter either 1 or 2.");
                try {Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace(); }
            }
        }
        controller.receiveUserInput(input);
    }

    public void displayForcedFightNotice()
    {
        String input = "";

        while (!input.equals("c"))
        {
            System.out.print(
                    "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                        "@    @                                                                @     @\n" +
                         "@    @                  You are forced to fight!                    @     @\n" +
                          "@    @                                                            @    @\n" +
                            "@   @              c - continue the fight                     @     @\n" +
                             "@                                                                @\n" +
                               "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");

            if (stdin.hasNext())
                input = stdin.next();
            else
                System.exit(0);
            if (!input.equals("c"))
            {
                System.out.println("Your input is not valid . Enter c to continue.");
                try {Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        controller.receiveUserInput(input);
    }

    public void displayPlayView()
    {
        String input = "";

        while (!(input.equals("w") || input.equals("a") || input.equals("s") || input.equals("d") || input.equals("q") ||
                input.equals("x") || input.equals("h")))
        {
            displayMap(controller.getMap());
            displayOptions();
            if (stdin.hasNext())
                input = stdin.next();
            else
                System.exit(0);
            if (!(input.equals("w") || input.equals("a") || input.equals("s") || input.equals("d") || input.equals("q") ||
                    input.equals("x") || input.equals("h")))
            {
                System.out.println("Your choice is not a validchoice.");
                try { Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        controller.receiveUserInput(input);
    }

    public void displayRenderGame()
    {
        while (controller.isGameContinues() && !switched)
            controller.displayStage();
    }
}
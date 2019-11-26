package za.co.wethinkcode.swingy.views.guiViews;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.swingy.controllers.GameController;
import za.co.wethinkcode.swingy.models.map.Map;
import za.co.wethinkcode.swingy.models.playables.Player;
import za.co.wethinkcode.swingy.views.IDisplay;

import javax.swing.*;
import java.util.ArrayList;

@Getter
@Setter
public class GuiDisplay implements IDisplay
{
    private GameController controller;

    private GuiPlayView playView;

    private JFrame errorFrame;


    public GuiDisplay(GameController controller)
    {
        this.controller = controller;
        playView = new GuiPlayView(controller);
    }

    public void displayBeginview()
    {
        new GuiStartView(controller).displayStartView();
    }

    public void displayPlayerSelectionView(ArrayList<Player> heroes)
    {
       new GuiPlayerSelectionView(controller).displaySelectionView(heroes);
    }

    public void displayCreatePlayerView()
    {
        new GuiCreatePlayerView(controller).displayCreatePlayerView();
    }

    public void displayPlayView()
    {
        playView.displayPlayView();
    }

    public void displayMap(final Map map)
    {
        playView.drawMap(controller.getMap());
    }

    public void setRedrawMap(boolean value)
    {
        playView.setReDrawMap(value);
    }

    public void displayFightOrRunPrompt()
    {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (playView.getFrame(), "Do you want to fight?","Message",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION)
            controller.receiveUserInput("1");
        else
            controller.receiveUserInput("2");
        controller.displayStage();
    }

    public void displayBattleReport(String report)
    {
        playView.displayBattle(report);
    }

    public void displayErrors(ArrayList<String> errors)
    {
        String message = "";
        for (String error : errors)
            message+= "\t" + error + "\n";
        JOptionPane.showMessageDialog(errorFrame, message);
        errorFrame.dispose();
        controller.receiveUserInput("b");
        controller.displayStage();
    }

    public void displayGameOver(boolean heroWon)
    {
        String result = heroWon? "Won!" : "Lost!";

        Object[] options1 = { "Main menu", "Quit" };

        int dialogResult = JOptionPane.showOptionDialog(playView.getFrame(), "You " + result,"Game Over",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options1, null);
        if(dialogResult == JOptionPane.YES_OPTION)
        {
            controller.receiveUserInput("1");
            playView.getFrame().dispose();
            controller.displayStage();
        }
        else
        {
            controller.receiveUserInput("2");
            playView.getFrame().dispose();
        }
    }

    public void displayQuitDialogue()
    {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (playView.getFrame(), "Do you want to quit?","Exit game",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION)
        {
            controller.receiveUserInput("1");
            playView.getFrame().dispose();
        }
        else
        {
            controller.receiveUserInput("2");
            controller.displayStage();
        }
    }

    public void displayForcedFightNotice()
    {
        String message = "You are forced to fight";
        JOptionPane.showMessageDialog(playView.getFrame(), message);
        controller.receiveUserInput("c");
        controller.displayStage();
    }

    public void displayRenderGame()
    {
        controller.displayStage();
    }
}

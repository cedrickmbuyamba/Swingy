package za.co.wethinkcode.swingy.views.guiViews;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.swingy.controllers.GameController;
import za.co.wethinkcode.swingy.models.map.Map;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
public class GuiPlayView
{
    private boolean initialised = false, reDrawMap = true, switched = false;
    private GameController controller;
    private  JFrame frame;
    private  JButton btnQuit, btnNorth, btnSouth, btnWest, btnEast, btnSwitch;
    private  JPanel  mapPanel, arenaPanel, movementPanel, statsPanel, reportPanel;
    private  JLabel reportLabel, statsLabel, arenaLabel;
    private  JTextArea reportText, statsText;
    private  JScrollPane reportScroll, statsScroll;

    public GuiPlayView(GameController controller)
    {
        this.controller = controller;
    }
    
    private   void initPlayView()
    {
        btnQuit = new JButton("Quit");
        btnSwitch = new JButton("Use the console");
        btnWest = new JButton("LEFT");
        btnEast = new JButton("RIGHT");
        btnSouth = new JButton("DOWN");
        btnNorth = new JButton("UP");
        mapPanel = new JPanel();
        arenaPanel = new JPanel();
        movementPanel = new JPanel();
        statsPanel = new JPanel();
        reportPanel = new JPanel();
        statsLabel = new JLabel("Statistic");
        reportLabel = new JLabel("Fight Report");
        arenaLabel = new JLabel("SPACE");
        statsText = new JTextArea();
        reportText = new JTextArea();
        statsScroll = new JScrollPane(statsText);
        reportScroll = new JScrollPane(reportText);

        statsText.setEditable(false);
        statsPanel.setLayout(null);

        reportText.setEditable(false);
        reportPanel.setLayout(null);

        movementPanel.setLayout(null);
        arenaPanel.setLayout(null);
        setColors();
        setBounds();
        setListeners();
        addToPanel();
    }

    private  void setListeners()
    {
        btnNorth.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.receiveUserInput("w");
                controller.displayStage();
            }
        });

        btnSouth.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.receiveUserInput("s");
                controller.displayStage();
            }
        });

        btnWest.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.receiveUserInput("a");
                controller.displayStage();
            }
        });

        btnEast.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.receiveUserInput("d");
                controller.displayStage();
            }
        });

        btnSwitch.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                initialised = false;
                controller.receiveUserInput("x");
            }
        });

        btnQuit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.receiveUserInput("q");
                controller.displayStage();
            }
        });
    }

    private  void setBounds()
    {
        statsLabel.setBounds(120, 10, 100, 20);
        statsScroll.setBounds(20, 40, 270, 460);
        statsPanel.setPreferredSize( new Dimension( 295, 495 ) );

        reportLabel.setBounds(120, 10, 100, 20);;
        reportScroll.setBounds(0, 40, 270, 460);
        reportPanel.setPreferredSize( new Dimension( 295, 495 ) );

        btnNorth.setBounds(560, 20, 120, 20);
        btnSouth.setBounds(560, 100, 120, 20);
        btnEast.setBounds(700, 60, 120, 20);
        btnWest.setBounds(420, 60, 120, 20);
        btnQuit.setBounds(700, 140, 160, 20);
        btnSwitch.setBounds(380, 140, 160, 20);
        movementPanel.setPreferredSize( new Dimension( 1200, 195));
        arenaPanel.setPreferredSize( new Dimension( 600, 495 ) );
        arenaLabel.setBounds(280, 10, 100, 20);
        mapPanel.setBounds(0,40,600,455);
    }

    private  void setColors()
    {
        statsLabel.setForeground(Color.RED);
        statsText.setBackground(Color.GRAY);
        statsText.setForeground(Color.RED);
        statsPanel.setBackground(Color.GRAY);
        reportLabel.setForeground(Color.RED);
        reportText.setBackground(Color.GRAY);
        reportText.setForeground(Color.RED);
        reportPanel.setBackground(Color.GRAY);
        movementPanel.setBackground(Color.GRAY);
        arenaLabel.setForeground(Color.RED);
        arenaPanel.setBackground(Color.GRAY);
    }

    private  void addToPanel()
    {
        statsPanel.add(statsLabel);
        statsPanel.add(statsScroll);

        reportPanel.add(reportLabel);
        reportPanel.add(reportScroll);

        movementPanel.add(btnNorth);
        movementPanel.add(btnSouth);
        movementPanel.add(btnEast);
        movementPanel.add(btnWest);
        movementPanel.add(btnQuit);
        movementPanel.add(btnSwitch);

        arenaPanel.add(arenaLabel);
        arenaPanel.add(mapPanel);
    }

    public  void displayPlayView()
    {
        if (!initialised)
        {
            initPlayView();
            frame = new JFrame("Swingy");
            frame.add(statsPanel,BorderLayout.WEST);
            frame.add(arenaPanel, BorderLayout.CENTER);
            frame.add(reportPanel,BorderLayout.EAST);
            frame.add(movementPanel, BorderLayout.SOUTH);
            frame.setBackground(Color.WHITE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(1200, 720));
            frame.setResizable(false);
            frame.pack();
            initialised = true;
        }
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);
        drawMap(controller.getMap());
    }

    public void drawMap(final Map map)
    {
        displayStats();

        mapPanel.removeAll();
        mapPanel.setLayout(new GridLayout(map.getSize(), map.getSize()));
        for (int i = 0; i < map.getSize(); i++)
        {
            for (int j = 0; j < map.getSize(); j++)
            {
                JButton button;
                if (map.getGrid()[i][j] == 1)
                {
                    button = new JButton();
                    button.setBackground(Color.GREEN);
                }
                else if (map.getGrid()[i][j] == 2)
                {
                    button = new JButton();
                    button.setBackground(Color.BLACK);
                }
                else
                {
                    button = new JButton();
                    button.setBackground(Color.LIGHT_GRAY);
                }
                button.setOpaque(true);
                button.setEnabled(false);
                mapPanel.add(button);
            }
        }
        mapPanel.revalidate();
        mapPanel.repaint();
    }

    private void displayStats()
    {
        String detail = "\n\tName: "+ controller.getHero().getName() +"\n\n\n";
        detail += "\tClass: "+ controller.getHero().getType() +"\n\n\n";
        detail += "\tLevel: "+ controller.getHero().getLevel() +"\n\n\n";
        detail += "\tExp: "+ controller.getHero().getExp() +"\n\n\n";
        detail += "\tAtk: "+ controller.getHero().getAtk() +"\n\n\n";
        detail += "\tDef: "+ controller.getHero().getDef() +"\n\n\n";
        detail += "\tHp: "+ controller.getHero().getHp() +"\n\n\n";
        statsText.setText(detail);
    }

    public void displayBattle(String report)
    {
        reportText.setText(report);
    }
}

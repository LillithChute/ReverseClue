package view.implementations;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.DefaultCaret;

import controller.ControllerFeatures;
import dtos.PlayerCreation;
import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.iteminterfaces.ItemViewModel;
import gameinterfaces.playerinterfaces.PlayerViewModel;
import gameinterfaces.spaceinterfaces.SpaceViewModel;
import gameinterfaces.targetinterfaces.TargetViewModel;
import view.interfaces.ImainForm;

public class MainForm extends JFrame implements ImainForm {
  private GridBagLayout mainLayout;
  private JMenuBar bar;
  private JMenu fileMenu;
  private JMenuItem restartGame;
  private JMenuItem newGame;
  private JMenuItem exitGame;

  private JMenu gameMenu;
  private JMenuItem addHuman;
  private JMenuItem addCpu;

  private JMenu helpMenu;
  private JMenuItem gameHelp;
  private JMenuItem gameAbout;
  private JMenuItem startGame;

  private JLabel imageLabel;
  private JScrollPane imagePane;

  private JLabel turnInfo;

  private JTextArea logInfo;
  private JScrollPane logInfoPane;

  private JLabel itemGroundLabel;
  private JList<ItemViewModel> itemOnGroundBox;
  private JScrollPane itemOnGroundPane;

  private JLabel backpackLabel;
  private JList<ItemViewModel> backpack;
  private JScrollPane backpackPane;

  private JButton pickUpButton;

  private JButton lookaroundButton;

  private JButton attackButton;

  private ControllerFeatures features;

  private List<Component> preGameComponents;
  private List<Component> inGameComponents;

  static String welcomeMsg;

  static {
    welcomeMsg = "Welcome to CS 5010 Milestone, The world!\n"
            + "Authors: Lillith Chute & Donglin Xu";
  }


  public MainForm(String caption) {
    super(caption);
    this.preGameComponents = new ArrayList<>();
    this.inGameComponents = new ArrayList<>();

    setSize(800, 600);
    setPreferredSize(new Dimension(1350, 950));
    setLocation(400,400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.mainLayout = new GridBagLayout();
    this.setLayout(mainLayout);

    bar = new JMenuBar();
    fileMenu = new JMenu("File");
    bar.add(fileMenu);

    restartGame = new JMenuItem("New Game With Same World...");
    newGame = new JMenuItem("New Game With Another World...");
    exitGame = new JMenuItem("Exit");

    fileMenu.add(newGame);
    fileMenu.add(restartGame);
    fileMenu.add(exitGame);

    newGame.addActionListener(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        File selected = chooseFile();
        if (selected == null) {
          showMessageDialog(null, "File not selected!");
        }
        features.setModel(selected);
      }
    });
    restartGame.addActionListener(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        features.restartGame();
      }
    });

    gameMenu = new JMenu("Game");
    addHuman = new JMenuItem("Add Human Player");
    addCpu = new JMenuItem("Add CPU Player");
    startGame = new JMenuItem("Start Game");
    gameMenu.add(addHuman);
    gameMenu.add(addCpu);
    gameMenu.add(startGame);
    addHuman.addActionListener(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        PlayerCreation result = getPlayerInput();
        if (result != null) {
          features.addPlayer(result.getName(), result.getLocation(), result.getLimit());
        }
      }
    });
    addCpu.addActionListener(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        PlayerCreation result = getPlayerInput();
        if (result != null) {
          features.addComputerPlayer(result.getName(), result.getLocation(), result.getLimit());
        }
      }
    });


    bar.add(gameMenu);

    helpMenu = new JMenu("Help");
    gameHelp = new JMenuItem("Game Help");
    gameAbout = new JMenuItem("About...");
    gameAbout.addActionListener(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        welcome();
      }
    });
    startGame.addActionListener(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        processGameStart();
      }
    });
    helpMenu.add(gameHelp);
    helpMenu.add(gameAbout);
    bar.add(helpMenu);


    this.setJMenuBar(bar);

    imageLabel = new JLabel("Add Players and Start the game!");
    imagePane = new JScrollPane(imageLabel);
    GridBagConstraints imagePaneConstraints = new GridBagConstraints();
    imagePaneConstraints.gridx = 1;
    imagePaneConstraints.gridy = 1;
    imagePaneConstraints.gridwidth = 4;
    imagePaneConstraints.gridheight = 4;
    imagePaneConstraints.weightx = 1;
    imagePaneConstraints.weighty = 1;
    imagePaneConstraints.fill = GridBagConstraints.BOTH;
    this.mainLayout.addLayoutComponent(imagePane, imagePaneConstraints);

    this.add(imagePane);

    logInfo = new JTextArea();
    logInfo.setEditable(false);
    DefaultCaret caret = (DefaultCaret)logInfo.getCaret();
    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    logInfoPane = new JScrollPane(logInfo);
    GridBagConstraints locationInfoConstraints = new GridBagConstraints();
    locationInfoConstraints.gridx = 0;
    locationInfoConstraints.gridy = 5;
    locationInfoConstraints.gridwidth = 6;
    locationInfoConstraints.gridheight = 1;
    locationInfoConstraints.weightx = 1;
    locationInfoConstraints.weighty = 1;
    locationInfoConstraints.fill = GridBagConstraints.BOTH;
    this.mainLayout.addLayoutComponent(logInfoPane, locationInfoConstraints);
    this.add(logInfoPane);


    turnInfo = new JLabel("Turn information will be here:");
    GridBagConstraints turnInfoConstraints = new GridBagConstraints();
    turnInfoConstraints.gridx = 1;
    turnInfoConstraints.gridy = 0;
    turnInfoConstraints.gridwidth = 4;
    turnInfoConstraints.gridheight = 1;
    turnInfoConstraints.weightx = 0;
    turnInfoConstraints.weighty = 0;
    this.mainLayout.addLayoutComponent(turnInfo, turnInfoConstraints);
    this.add(turnInfo);

    itemGroundLabel = new JLabel("Items on the ground:");
    itemOnGroundBox = new JList<>();
    JPanel groundStackPanel = new JPanel();
    groundStackPanel.setLayout(new BoxLayout(groundStackPanel, BoxLayout.Y_AXIS));
    groundStackPanel.add(itemGroundLabel);
    groundStackPanel.add(itemOnGroundBox);
    itemOnGroundPane = new JScrollPane(groundStackPanel);
    GridBagConstraints itemOnGroundConstraints = new GridBagConstraints();
    itemOnGroundConstraints.gridx = 5;
    itemOnGroundConstraints.gridy = 1;
    itemOnGroundConstraints.gridwidth = 1;
    itemOnGroundConstraints.gridheight = 2;
    itemOnGroundConstraints.weightx = 0.2;
    itemOnGroundConstraints.weighty = 1;
    itemOnGroundConstraints.fill = GridBagConstraints.BOTH;
    itemOnGroundBox.setSize(itemGroundLabel.getSize());
    this.mainLayout.addLayoutComponent(itemOnGroundPane, itemOnGroundConstraints);
    this.add(itemOnGroundPane);

    backpackLabel = new JLabel("Items of PLAYER:");
    backpack = new JList<>();
    JPanel backpackStackPanel = new JPanel();
    backpackStackPanel.setLayout(new BoxLayout(backpackStackPanel, BoxLayout.Y_AXIS));
    backpackStackPanel.add(backpackLabel);
    backpackStackPanel.add(backpack);
    backpackPane = new JScrollPane(backpackStackPanel);
    GridBagConstraints backpackConstraints = new GridBagConstraints();
    backpackConstraints.gridx = 5;
    backpackConstraints.gridy = 3;
    backpackConstraints.gridwidth = 1;
    backpackConstraints.gridheight = 2;
    backpackConstraints.weightx = 0.2;
    backpackConstraints.weighty = 1;
    backpackConstraints.fill = GridBagConstraints.BOTH;
    this.mainLayout.addLayoutComponent(backpackPane, backpackConstraints);
    this.add(backpackPane);

    pickUpButton = new JButton("Pickup Item");
    GridBagConstraints pickupButtonConstraints = new GridBagConstraints();
    pickupButtonConstraints.gridx = 0;
    pickupButtonConstraints.gridy = 1;
    pickupButtonConstraints.gridwidth = 1;
    pickupButtonConstraints.gridheight = 1;
    pickupButtonConstraints.weightx = 0.2;
    pickupButtonConstraints.weighty = 1;
    pickUpButton.setMnemonic('P');
    this.mainLayout.addLayoutComponent(pickUpButton, pickupButtonConstraints);
    this.add(pickUpButton);

    lookaroundButton = new JButton("Look Around");
    GridBagConstraints lookaroundConstraints = new GridBagConstraints();
    lookaroundConstraints.gridx = 0;
    lookaroundConstraints.gridy = 2;
    lookaroundConstraints.gridwidth = 1;
    lookaroundConstraints.gridheight = 1;
    lookaroundConstraints.weightx = 0.2;
    lookaroundConstraints.weighty = 1;
    lookaroundButton.setMnemonic('L');
    this.mainLayout.addLayoutComponent(lookaroundButton, lookaroundConstraints);
    this.add(lookaroundButton);

    attackButton = new JButton("Attack!");
    GridBagConstraints attackConstraints = new GridBagConstraints();
    attackConstraints.gridx = 0;
    attackConstraints.gridy = 3;
    attackConstraints.gridwidth = 1;
    attackConstraints.gridheight = 1;
    attackConstraints.weightx = 0.2;
    attackConstraints.weighty = 1;
    attackButton.setMnemonic('A');
    this.mainLayout.addLayoutComponent(attackButton, attackConstraints);
    this.add(attackButton);

    //Image Label- Hit detection
    imageLabel.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
          processPopup(e);
        }
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
          processPopup(e);
        }
      }
    });

    //Look around button click
    lookaroundButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        features.lookaround();
      }
    });

    pickUpButton.addActionListener(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ItemViewModel selected = itemOnGroundBox.getSelectedValue();
        if (selected == null) {
          promptError("No Item is selected for pickup!");
          return;
        }
        features.pickup(selected.getNameOfItem());
      }
    });

    attackButton.addActionListener(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ItemViewModel selected = backpack.getSelectedValue();
        try {
          if (selected == null) {
            features.attack(null);
          } else {
            features.attack(selected.getNameOfItem());
          }
        } catch (IllegalStateException ex) {
          promptError(ex.getMessage());
        }
      }
    });

    this.preGameComponents.add(addHuman);
    this.preGameComponents.add(addCpu);
    this.preGameComponents.add(startGame);

    this.inGameComponents.add(pickUpButton);
    this.inGameComponents.add(lookaroundButton);
    this.inGameComponents.add(attackButton);
    this.inGameComponents.add(imagePane);
    this.inGameComponents.add(itemOnGroundPane);
    this.inGameComponents.add(backpackPane);
    this.inGameComponents.add(logInfoPane);

    pack();
    setVisible(true);
  }

  @Override
  public void setFeatures(ControllerFeatures f) {
    this.features = f;

  }

  @Override
  public void welcome() {
    showMessageDialog(null, welcomeMsg);
  }

  @Override
  public void setPreGameMenuVisibility(boolean enabled) {
    this.addHuman.setEnabled(enabled);
    this.addCpu.setEnabled(enabled);
    this.startGame.setEnabled(enabled);
    this.turnInfo.setText("ADD PLAYERS AND START THE GAME!");
  }


  private File chooseFile() {
    File selected;
    JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new File("."));
    chooser.setSelectedFile(new File(""));
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    chooser.addChoosableFileFilter(new FileFilter() {
      @Override
      public boolean accept(File f) {
        return f.getName().toLowerCase().endsWith(".txt");
      }

      @Override
      public String getDescription() {
        return "World Description Text File (*.txt)";
      }
    });
    if (chooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG) {
      return chooser.getSelectedFile();

    } else {
      return null;
    }
  }

  private PlayerCreation getPlayerInput() {
    JTextField name = new JTextField();
    JList<SpaceViewModel> avaliable = new JList<>();
    avaliable.setListData(features.spawningRooms().toArray(new SpaceViewModel[0]));
    JScrollPane avaliablePane = new JScrollPane(avaliable);
    avaliable.setSelectedIndex(0);
    JTextField limit = new JTextField();
    JPanel panel = new JPanel();
    panel.add(new JLabel("Player Name:"));
    panel.add(name);
    panel.add(new JLabel("Starting Location:"));
    panel.add(avaliablePane);
    panel.add(new JLabel("Carry Limit:"));
    panel.add(limit);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    int result = JOptionPane.showConfirmDialog(this, panel,
            "Enter Player Info...", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      return new PlayerCreation(name.getText(),
              avaliable.getSelectedValue().getTheNameOfThisSpace(),
              Integer.parseInt(limit.getText()));
    } else {
      return null;
    }
  }

  private void processGameStart() {
    JLabel prompt = new JLabel("Maximum Turns:");
    JTextField input = new JTextField();
    JPanel panel = new JPanel();
    panel.add(prompt);
    panel.add(input);
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    int result = JOptionPane.showConfirmDialog(this, panel, "Enter Max Turns:",
            JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      try {
        int turnCount = Integer.parseInt(input.getText());
        features.startGame(turnCount);
      } catch (NumberFormatException ex) {
        promptError("Illegal Turn Number!");
      }
    }
  }

  @Override
  public void logGameplay(String msg) {
    Objects.requireNonNull(msg);
    this.logInfo.append(msg);
    this.logInfo.append("\n");
  }

  @Override
  public void setGraphics(BufferedImage img) {
    this.imageLabel.setIcon(new ImageIcon(img));
  }

  @Override
  public void setTurnInfo(String msg) {
    this.turnInfo.setText(features.getTurnInformation());
  }

  private void processPopup(MouseEvent e) {
    if (!features.gameStarted()) {
      return;
    }
    JPopupMenu popup = new JPopupMenu();
    SpaceViewModel hit = features.hitDetection(e.getX(), e.getY());
    if (hit == null) {
      return;
    }
    JMenuItem move = new JMenuItem("Move player here..");
    move.addActionListener(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        features.move(hit.getTheNameOfThisSpace());
      }
    });
    popup.add(move);

    JMenuItem movePet = new JMenuItem("Move Pet here...");
    movePet.addActionListener(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        features.movePet(hit.getTheNameOfThisSpace());
      }
    });
    popup.add(movePet);

    for (PlayerViewModel p : hit.getPlayersInThisSpace()) {
      if (p != features.getCurrentPlayer()) {
        continue;
      }
      JMenuItem playerinfo = new JMenuItem(String.format("%s Info", p.getPlayerName()));
      playerinfo.addActionListener(new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
          features.describePlayer();
        }
      });
      popup.add(playerinfo);
    }
    if (hit.isTargetInThisSpace() && !hit.hasPet()) {
      TargetViewModel target = hit.getTargetFromThisSpace();
      JMenuItem targetinfo = new JMenuItem("Target Character Info");
      targetinfo.addActionListener(new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
          logGameplay(String.format("Target %s, Health %d", target.getTargetName(),
                  target.getCurrentHealth()));
        }
      });
      popup.add(targetinfo);
    }

    popup.show(this, imagePane.getMousePosition().x + imagePane.getX(),
            imagePane.getMousePosition().y + imagePane.getY());
  }

  @Override
  public void setStartedState(boolean started) {
    if (started) {
      for (Component c : this.preGameComponents) {
        c.setEnabled(false);
      }
      for (Component c: this.inGameComponents) {
        c.setEnabled(true);
      }
    } else {
      for (Component c : this.preGameComponents) {
        c.setEnabled(true);
      }
      for (Component c: this.inGameComponents) {
        c.setEnabled(false);
      }
      this.imageLabel.setIcon(null);
      this.backpack.setListData(new ItemViewModel[0]);
      this.itemOnGroundBox.setListData(new ItemViewModel[0]);
      this.logInfo.setText("");
      this.turnInfo.setText("ADD PLAYERS AND START THE GAME!");

    }
  }

  @Override
  public void showEndingPrompt(String winner) {
    JOptionPane.showMessageDialog(this,
            String.format("Game Ended! %s", winner));
    System.exit(0);
  }

  @Override
  public void setGroundItems(List<ItemViewModel> items) {
    this.itemOnGroundBox.setListData(items.toArray(new ItemViewModel[0]));
  }

  @Override
  public void setBackpackItems(List<ItemViewModel> items) {
    this.backpack.setListData(items.toArray(new ItemViewModel[0]));
  }

  @Override
  public void setPlayerName(PlayerViewModel player) {
    this.backpackLabel.setText(String.format("Items of %s:", player.getPlayerName()));
  }

  @Override
  public void promptError(String msg) {
    JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
  }
}

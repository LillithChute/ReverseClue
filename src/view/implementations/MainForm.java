package view.implementations;

import static javax.swing.JOptionPane.showMessageDialog;

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

import controller.ControllerFeatures;
import dtos.PlayerCreation;
import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.playerinterfaces.Player;
import gameinterfaces.playerinterfaces.PlayerViewModel;
import gameinterfaces.spaceinterfaces.SpaceViewModel;
import gameinterfaces.targetinterfaces.TargetViewModel;
import gamemodels.itemmodels.ItemImpl;
import gamemodels.playermodels.PlayerImpl;
import view.interfaces.ImainForm;

public class MainForm extends JFrame implements ImainForm {
  private GridBagLayout mainLayout;
  private JMenuBar bar;
  private JMenu fileMenu;
  private JMenuItem restartGame;
  private JMenuItem newGame;
  private JMenuItem exitGame;

  private JMenu gameMenu;
  private JMenuItem targetInfo;
  private JMenuItem gameInfo;
  private JMenuItem addHuman;
  private JMenuItem addCpu;

  private JMenu helpMenu;
  private JMenuItem gameHelp;
  private JMenuItem gameAbout;

  private JLabel imageLabel;
  private JScrollPane imagePane;

  private JLabel turnInfo;

  private JTextArea logInfo;
  private JScrollPane logInfoPane;

  private JList<Item> itemOnGroundBox;
  private JScrollPane itemOnGroundPane;

  private JList<Item> backpack;
  private JScrollPane backpackPane;

  private JButton pickUpButton;

  private JButton lookaroundButton;

  private JButton attackButton;

  private ControllerFeatures features;

  static String welcomeMsg;

  static {
    welcomeMsg = "Welcome to CS 5010 Milestone, The world!\n"
            + "Authors: Lillith Chute & Donglin Xu";
  }


  public MainForm(String caption) {
    super(caption);

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

    gameMenu = new JMenu("Game");
    targetInfo = new JMenuItem("Target Character Info");
    gameInfo = new JMenuItem("Game Info");
    addHuman = new JMenuItem("Add Human Player");
    addCpu = new JMenuItem("Add CPU Player");
    gameMenu.add(addHuman);
    gameMenu.add(addCpu);
    gameMenu.add(targetInfo);
    gameMenu.add(gameInfo);
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
    helpMenu.add(gameHelp);
    helpMenu.add(gameAbout);
    bar.add(helpMenu);


    this.setJMenuBar(bar);

    imageLabel = new JLabel("No game found at the moment.");
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
    logInfoPane = new JScrollPane(logInfo);
    GridBagConstraints locationInfoConstraints = new GridBagConstraints();
    locationInfoConstraints.gridx = 0;
    locationInfoConstraints.gridy = 1;
    locationInfoConstraints.gridwidth = 1;
    locationInfoConstraints.gridheight = 2;
    locationInfoConstraints.weightx = 0.2;
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


    itemOnGroundBox = new JList<>();
    itemOnGroundPane = new JScrollPane(itemOnGroundBox);
    //TODO: remove testing code
    List<Item> groundItems = new ArrayList<>();
    groundItems.add(new ItemImpl("Test Item", 1, 0));
    itemOnGroundBox.setListData(groundItems.toArray(new Item[0]));
    GridBagConstraints itemOnGroundConstraints = new GridBagConstraints();
    itemOnGroundConstraints.gridx = 0;
    itemOnGroundConstraints.gridy = 3;
    itemOnGroundConstraints.gridwidth = 1;
    itemOnGroundConstraints.gridheight = 2;
    itemOnGroundConstraints.weightx = 0.2;
    itemOnGroundConstraints.weighty = 1;
    itemOnGroundConstraints.fill = GridBagConstraints.BOTH;
    this.mainLayout.addLayoutComponent(itemOnGroundPane, itemOnGroundConstraints);
    this.add(itemOnGroundPane);

    backpack = new JList<>();
    backpack.setListData(groundItems.toArray(new Item[0]));
    backpackPane = new JScrollPane(backpack);
    GridBagConstraints backpackConstraints = new GridBagConstraints();
    backpackConstraints.gridx = 5;
    backpackConstraints.gridy = 1;
    backpackConstraints.gridwidth = 1;
    backpackConstraints.gridheight = 4;
    backpackConstraints.weightx = 0.2;
    backpackConstraints.weighty = 1;
    backpackConstraints.fill = GridBagConstraints.BOTH;
    this.mainLayout.addLayoutComponent(backpackPane, backpackConstraints);
    this.add(backpackPane);

    pickUpButton = new JButton("Pickup Item");
    GridBagConstraints pickupButtonConstraints = new GridBagConstraints();
    pickupButtonConstraints.gridx = 1;
    pickupButtonConstraints.gridy = 5;
    pickupButtonConstraints.gridwidth = 1;
    pickupButtonConstraints.gridheight = 1;
    pickupButtonConstraints.weightx = 0.2;
    pickupButtonConstraints.weighty = 1;
    this.mainLayout.addLayoutComponent(pickUpButton, pickupButtonConstraints);
    this.add(pickUpButton);

    lookaroundButton = new JButton("Look Around");
    GridBagConstraints lookaroundConstraints = new GridBagConstraints();
    lookaroundConstraints.gridx = 2;
    lookaroundConstraints.gridy = 5;
    lookaroundConstraints.gridwidth = 1;
    lookaroundConstraints.gridheight = 1;
    lookaroundConstraints.weightx = 0.2;
    lookaroundConstraints.weighty = 1;
    lookaroundButton.setMnemonic('L');
    this.mainLayout.addLayoutComponent(lookaroundButton, lookaroundConstraints);
    this.add(lookaroundButton);

    attackButton = new JButton("Attack!");
    GridBagConstraints attackConstraints = new GridBagConstraints();
    attackConstraints.gridx = 3;
    attackConstraints.gridy = 5;
    attackConstraints.gridwidth = 1;
    attackConstraints.gridheight = 1;
    attackConstraints.weightx = 0.2;
    attackConstraints.weighty = 1;
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
        String description = features.lookaround();
        logInfo.setText(description);
      }
    });

    pack();
    setVisible(true);
  }

  @Override
  public void setFeatures(ControllerFeatures f) {
    this.features = f;
    //TODO: move this to an appropriate location
    this.imageLabel.setIcon(new ImageIcon(features.obtainImage()));
    this.imageLabel.setText(null);

    //TODO: Adding a test player
    features.addPlayer("Test Player", "Jotunheim", 3);
    features.addPlayer("Alice", "Beach Head One", 5);
    features.addPlayer("Bob", "Beach Head One", 5);
    features.addPlayer("Charlie", "Beach Head One", 5);

    // Set the turn information for the first player
    this.turnInfo.setText(features.getTurnInformation());

    // Get the items in the space for the player whose turn it is.
    itemOnGroundBox.setListData(features.getItemsOnTheGround().toArray(new Item[0]));

  }

  @Override
  public void welcome() {
    showMessageDialog(null, welcomeMsg);
  }

  @Override
  public void setPreGameMenuVisibility(boolean enabled) {
    this.addHuman.setEnabled(enabled);
    this.addCpu.setEnabled(enabled);
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
    JTextField location = new JTextField();
    JTextField limit = new JTextField();
    JPanel panel = new JPanel();
    panel.add(new JLabel("Player Name:"));
    panel.add(name);
    panel.add(new JLabel("Starting Location:"));
    panel.add(location);
    panel.add(new JLabel("Carry Limit:"));
    panel.add(limit);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    int result = JOptionPane.showConfirmDialog(this, panel,
            "Enter Player Info...", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      return new PlayerCreation(name.getText(), location.getText(),
              Integer.parseInt(limit.getText()));
    } else {
      return null;
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

    for (PlayerViewModel p : hit.getPlayersInThisSpace()) {
      if (p != features.getCurrentPlayer()) {
        continue;
      }
      JMenuItem playerinfo = new JMenuItem(String.format("%s Info", p.getPlayerName()));
      playerinfo.addActionListener(new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
          logGameplay(p.describePlayer());
        }
      });
      popup.add(playerinfo);
    }
    if (hit.isTargetInThisSpace()) {
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
  public void promptError(String msg) {
    JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
  }
}

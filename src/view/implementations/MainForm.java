package view.implementations;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.ControllerFeatures;
import gameinterfaces.iteminterfaces.ItemViewModel;
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

  private JMenu helpMenu;
  private JMenuItem gameHelp;
  private JMenuItem gameAbout;

  private JLabel imageLabel;
  private JScrollPane imagePane;

  private JLabel turnInfo;

  private JTextArea logInfo;
  private JScrollPane logInfoPane;

  private JList<ItemViewModel> itemOnGroundBox;
  private JScrollPane itemOnGroundPane;

  private JList<ItemViewModel> backpack;
  private JScrollPane backpackPane;

  private JButton pickUpButton;

  private JButton lookaroundButton;

  private JButton attackButton;

  private ControllerFeatures features;


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

    fileMenu.add(restartGame);
    fileMenu.add(newGame);
    fileMenu.add(exitGame);

    gameMenu = new JMenu("Game");
    targetInfo = new JMenuItem("Target Character Info");
    gameInfo = new JMenuItem("Game Info");
    gameMenu.add(targetInfo);
    gameMenu.add(gameInfo);
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
    //TODO: remove lorem ipsum from here
    logInfo.setText(testTextHelper("Location Information"));
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
    List<ItemViewModel> groundItems = new ArrayList<>();
    for(int i = 0; i < 20;i++)
    {
      groundItems.add(new TestingItem());
    }
    itemOnGroundBox.setListData(groundItems.toArray(new ItemViewModel[0]));
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
    backpack.setListData(groundItems.toArray(new ItemViewModel[0]));
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
      public void mouseClicked(MouseEvent e) {
        features.hitDetection(e.getX(), e.getY());
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
    features.addPlayer("Test Player", "Forest", 3);
  }

  //TODO: remove this in actual code
  private String testTextHelper(String hint) {
    return String.valueOf(String.format("Lorem ipsum %s \n dolor sit amet\n", hint)).repeat(20);
  }
}

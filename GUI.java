
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class GUI extends JFrame {
	
	String setPlayer = "";
	Map<String,String> playerList = new HashMap<String,String>();
	Cluedo game;
	
	public GUI(Cluedo game){
		this.game = game;
		//mainMenu();
		playGame();
	}
	
	/**
	 * sets up GUI for the main menu of cluedo
	 */
	public void mainMenu() {
		JFrame mainMenu = new JFrame("Cluedo");
		mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenu.setPreferredSize(new Dimension(600,450));
		mainMenu.setResizable(false);
		
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(Box.createRigidArea(new Dimension(0,60)));
		
		JLabel title = new JLabel("Welcome to Cluedo\n");
		title.setFont(new Font(title.getFont().getName(),title.getFont().getStyle(), 50));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(title);
		
		panel.add(Box.createRigidArea(new Dimension(0,60)));
		
		JButton start = new JButton("Start");
		start.setAlignmentX(Component.CENTER_ALIGNMENT);
		start.setFont(new Font(title.getFont().getName(),title.getFont().getStyle(), 25));
		start.setMaximumSize(new Dimension(150, 40));
		start.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
				String inp = JOptionPane.showInputDialog(panel,"Please enter the amount of players (3-6)");
				try {
					int noPlayers = Integer.parseInt(inp);
					if(noPlayers < 3 || noPlayers>6) {
						JOptionPane.showMessageDialog(panel, "You must enter a valid number between 3-6", "Bad Number",JOptionPane.ERROR_MESSAGE);
					}
					else {
						JFrame playerSelect = new JFrame("Player Select");
						ButtonGroup btnGrp = new ButtonGroup();
						playerSelect.setLayout(new GridLayout(8,1));
						playerSelect.setResizable(false);
						
						playerSelect.add(new JLabel("Player's Name"));
						
						JTextField playerName = new JTextField();
						playerSelect.add(playerName);
						
						JRadioButton missScar = new JRadioButton("Miss Scarlet");
						btnGrp.add(missScar);
						missScar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								setPlayer = "Miss Scarlet";
							}
						});
						playerSelect.add(missScar);
						
						JRadioButton colMust = new JRadioButton("Col. Mustard");
						btnGrp.add(colMust);
						colMust.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								setPlayer = "Colonel Mustard";
							}
						});
						playerSelect.add(colMust);
						
						JRadioButton mrsWhite = new JRadioButton("Mrs. White");
						btnGrp.add(mrsWhite);
						mrsWhite.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								setPlayer = "Mrs. White";
							}
						});
						playerSelect.add(mrsWhite);
						
						JRadioButton mrGreen = new JRadioButton("Mr. Green");
						btnGrp.add(mrGreen);
						mrGreen.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								setPlayer = "Mr. Green";
							}
						});
						playerSelect.add(mrGreen);
						
						JRadioButton mrsPea = new JRadioButton("Mrs. Peacock");
						btnGrp.add(mrsPea);
						mrsPea.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								setPlayer = "Mrs. Peacock";
							}
						});
						playerSelect.add(mrsPea);
						
						JRadioButton profPlum = new JRadioButton("Prof. Plum");
						btnGrp.add(profPlum);
						profPlum.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								setPlayer = "Prof. Plum";
							}
						});
						playerSelect.add(profPlum);
						
						playerName.addActionListener(new ActionListener(){
							int count = 0;
							public void actionPerformed(ActionEvent e){
								if(btnGrp.getSelection() != null) {
									if(count != noPlayers) {
										if(playerList.putIfAbsent(playerName.getText(),setPlayer) == null) {
											btnGrp.getSelection().setEnabled(false);
											count++;
											playerName.setText("");
										}
										else{	
										JOptionPane.showMessageDialog(playerName, "You cant have the same name as someone else", 
												"Bad Name",JOptionPane.ERROR_MESSAGE);
										}
									}
									if(count == noPlayers) {
										playerSelect.setVisible(false);
								    	playerSelect.dispose();
										mainMenu.setVisible(false);
								    	mainMenu.dispose();
								    	game.setup(playerList);
										playGame();
									}
								}
										
							 }
						});
						
						playerSelect.pack();
						playerSelect.setVisible(true);
					}
				}
				catch(NumberFormatException error){
					JOptionPane.showMessageDialog(panel, "You must enter a valid number between 3-6", "Bad Number",JOptionPane.ERROR_MESSAGE);
				}
		    }
		});
		panel.add(start);
		
		
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		
		JButton rules = new JButton("Rules");
		rules.setAlignmentX(Component.CENTER_ALIGNMENT);
		rules.setFont(new Font(title.getFont().getName(),title.getFont().getStyle(), 25));
		rules.setMaximumSize(new Dimension(150, 40));
		rules.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){ 
		    	JOptionPane.showMessageDialog(panel, Cluedo.checkForHelp());
		    }
		});
		panel.add(rules);
		
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		
		JButton exit = new JButton("Quit");
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit.setFont(new Font(title.getFont().getName(),title.getFont().getStyle(), 25));
		exit.setMaximumSize(new Dimension(150, 40));
		exit.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){ 
		    	mainMenu.setVisible(false);
		    	mainMenu.dispose();
		    	System.exit(0);
		    }
		});
		
		panel.add(exit);
		
		mainMenu.add(panel);
		
		mainMenu.pack();
		mainMenu.setVisible(true);
	}
	
	
	/**
	 * set up GUI for the actual game of cluedo and controls it
	 */
	public void playGame() {
		JFrame gameFrame = new JFrame("Cluedo");
		
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setPreferredSize(new Dimension(600,450));
		gameFrame.setLayout(new BorderLayout());
		
		JMenuBar menu = new JMenuBar();
		menu.setPreferredSize(new Dimension(gameFrame.getWidth(),30));
		JMenuItem file = new JMenuItem("File");
		JMenuItem game = new JMenuItem("Game");
		menu.add(file);
		menu.add(game);
		gameFrame.add(menu, BorderLayout.PAGE_START);
		
		JButton but = new JButton("Graph");
		gameFrame.add(but, BorderLayout.CENTER);
		
		JPanel botDisp = new JPanel();
		botDisp.setPreferredSize(new Dimension(gameFrame.getWidth(),100));
		gameFrame.add(botDisp, BorderLayout.PAGE_END);
		
		gameFrame.pack();
		gameFrame.setVisible(true);
	}

}

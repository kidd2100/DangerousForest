import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.awt.event.ActionEvent;

public class DangerousForest extends JFrame implements ActionListener{
	protected final int GAME_HEIGHT = 300, GAME_WIDTH = 450;
	protected JLabel lblName = new JLabel();
	protected JLabel lblTitle = new JLabel();
	protected JButton button, button1, button3;
	protected JTextArea textArea;
	protected JScrollPane scrollPane;
	protected player player = new player();
	protected ForestExit ForestExit = new ForestExit();
	protected JPanel panel, panel_1, panel_2, panel_3, panel_4;
	protected GridBagConstraints gbc;
	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangerousForest frame = new DangerousForest();
					frame.setVisible(true);			        
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public DangerousForest() throws IOException {
		super("The Dangerous Forest");
		BufferedImage image = ImageIO.read(new File("src\\Resources\\background.png"));
		BufferedImage warrior = ImageIO.read(new File("src\\Resources\\warrior.png"));
		BufferedImage warriorNorth = ImageIO.read(new File("src\\Resources\\warriorNorth.png"));
		BufferedImage warriorSouth = ImageIO.read(new File("src\\Resources\\warriorSouth.png"));
		BufferedImage warriorFlipped = ImageIO.read(new File("src\\Resources\\warriorFlipped.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, GAME_WIDTH, GAME_HEIGHT);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBounds(50, 50, 50, 50);
		panel.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		
		panel_1 = new JPanel()	{
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(player.getDirection() == "EAST") {
                g.drawImage(warrior, 0, 0, null);
                g.drawImage(warrior, GAME_WIDTH - 65, 0, null);
                }
                else if(player.getDirection() == "WEST") {
                g.drawImage(warriorFlipped, 0,0,null);
                g.drawImage(warriorFlipped, GAME_WIDTH - 65, 0, null);
                }
                else if(player.getDirection() == "NORTH") {
                g.drawImage(warriorNorth,0,0,null);
                g.drawImage(warriorNorth,GAME_WIDTH - 65, 0, null);
                }
                else {
                g.drawImage(warriorSouth, 0, 0, null);
                g.drawImage(warriorSouth, GAME_WIDTH - 75, 0, null);
                }
                 
            }
        };
         
		panel_1.setLayout(new GridBagLayout());
		panel_1.setBackground(Color.BLACK);
		lblTitle.setText("The Dangerous Forest!");
		lblTitle.setForeground(new Color(49, 100, 34));
		lblTitle.setFont(new Font("Serif", Font.BOLD, 32));
		panel_1.add(lblTitle);
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		panel_2 = new JPanel()
		{
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
               
            }
        };
		panel_2.setLayout(new GridBagLayout());
		panel_2.setBackground(Color.black);
		button = new JButton("West");
		button.addActionListener(this);
		button.setBackground(new Color(49, 100, 34));
		button.setForeground(Color.black);
		gbc.gridy = 1;
		gbc.gridx = 0;
		panel_2.add(button, gbc);
		button1 = new JButton("North");
		button1.setBounds(5,5,5,5);
		button1.setBackground(new Color(49, 100, 34));
		button1.setForeground(Color.black);
		button1.addActionListener(this);
		gbc.gridx = 2;
		gbc.gridy = 0;
		panel_2.add(button1, gbc);
		button3 = new JButton("East");
		button3.setBackground(new Color(49, 100, 34));
		button3.setForeground(Color.black);
		button3.addActionListener(this);
		gbc.gridx = 5;
		gbc.gridy = 1;
		panel_2.add(button3, gbc);
		contentPane.add(panel_2, BorderLayout.CENTER);
		
		panel_4 = new JPanel();
		panel_4.setLayout(new GridBagLayout());
		panel_4.setVisible(true);
		lblName.setText(" ");
		panel_4.add(lblName);
		contentPane.add(panel_4, BorderLayout.SOUTH);
		textArea = new JTextArea(5,20);
		textArea.setBackground(Color.black);
		textArea.setForeground(Color.green);
		textArea.setText(generateIntro());
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(5, 5, 200, 200);
		textArea.setCaretPosition(0);
		scrollPane = new JScrollPane(); 
		scrollPane.setViewportView(textArea);
		gbc.gridx = 2;
		gbc.gridy = 1;
		panel_2.add(scrollPane, gbc);
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == button) {
			lblName.setText("You have chosen to head West");
			player.setDirection("WEST");
			panel_1.repaint();
			move();
		}
		else if(e.getSource() == button1) {
			lblName.setText("You have chosen to head North");
			player.setDirection("NORTH");
			panel_1.repaint();
			move();
		}
		else if(e.getSource() == button3) {
			lblName.setText("You have chosen to head East");
			player.setDirection("EAST");
			panel_1.repaint();
			move();
		}
	}

	public static String generateIntro() {
		String story = "Hello Mighty Adventurer! You have justed entered the most dangerous forest in all of the land. Prepare you sword for battle as you fight you way thru! We hope to see you on the other side.";		 
		return story;
	}

	public void move() {
		double randomAttack = (Math.random() * (100 - 1 + 1) + 1);

		if(player.getDirection() == "NORTH" && player.getPlayerY() < 12) {
			moveNorth();
			player.setPlayerLocation(player.getPlayerX(), player.getPlayerY()+1);
			if((ForestExit.getExitX() - player.getPlayerX() == 1 || ForestExit.getExitX() - player.getPlayerX() == -1 || ForestExit.getExitX() - player.getPlayerX() == 0) &&(ForestExit.getExitY() - player.getPlayerY() == 1 || ForestExit.getExitY() - player.getPlayerY() == -1 || ForestExit.getExitY() - player.getPlayerY() == 0)) {
				panel_4.setBackground(Color.red);
				lblName.setText("The forest exit is very near...");
			}
			else if((ForestExit.getExitX() - player.getPlayerX() > 1 || ForestExit.getExitX() - player.getPlayerX() < -1 || ForestExit.getExitX() - player.getPlayerX() == 0) &&(ForestExit.getExitY() - player.getPlayerY() > 1 || ForestExit.getExitY() - player.getPlayerY() < -1 || ForestExit.getExitY() - player.getPlayerY() == 0))
				panel_4.setBackground(Color.white);
		}
		else if(player.getDirection() == "WEST" && player.getPlayerX() > 0) {
			moveWest();
			player.setPlayerLocation(player.getPlayerX()-1, player.getPlayerY());
			if((ForestExit.getExitX() - player.getPlayerX() == 1 || ForestExit.getExitX() - player.getPlayerX() == -1 || ForestExit.getExitX() - player.getPlayerX() == 0) &&(ForestExit.getExitY() - player.getPlayerY() == 1 || ForestExit.getExitY() - player.getPlayerY() == -1 || ForestExit.getExitY() - player.getPlayerY() == 0)) {
				panel_4.setBackground(Color.red);
				lblName.setText("The forest exit is very near...");
			}
			else if((ForestExit.getExitX() - player.getPlayerX() > 1 || ForestExit.getExitX() - player.getPlayerX() < -1 || ForestExit.getExitX() - player.getPlayerX() == 0) &&(ForestExit.getExitY() - player.getPlayerY() > 1 || ForestExit.getExitY() - player.getPlayerY() < -1 || ForestExit.getExitY() - player.getPlayerY() == 0))
				panel_4.setBackground(Color.white);
		}
		else if(player.getDirection() == "EAST" && player.getPlayerX() < 12) {
			moveEast();
			player.setPlayerLocation(player.getPlayerX()+1, player.getPlayerY());
			if((ForestExit.getExitX() - player.getPlayerX() == 1 || ForestExit.getExitX() - player.getPlayerX() == -1 || ForestExit.getExitX() - player.getPlayerX() == 0) &&(ForestExit.getExitY() - player.getPlayerY() == 1 || ForestExit.getExitY() - player.getPlayerY() == -1 || ForestExit.getExitY() - player.getPlayerY() == 0)) {
				panel_4.setBackground(Color.red);
				lblName.setText("The forest exit is very near...");
			}
			else if((ForestExit.getExitX() - player.getPlayerX() > 1 || ForestExit.getExitX() - player.getPlayerX() < -1 || ForestExit.getExitX() - player.getPlayerX() == 0) &&(ForestExit.getExitY() - player.getPlayerY() > 1 || ForestExit.getExitY() - player.getPlayerY() < -1 || ForestExit.getExitY() - player.getPlayerY() == 0))
				panel_4.setBackground(Color.white);
		}
		else
		{
			textArea.setText("You have reached the edge of the world and can not go any further " + player.getDirection());
		}
		 checkGameWon();
		if((int)randomAttack <= 10) {
			battle();
		}
 
	}

	public void moveEast() {
		String[] eastStoryLine = {"You travel East thru the dangerous forest. You look up in awe over the magnificent branches displayed on the massive trees."
				,"You travel East, worried about what lays ahead in front of you.."
				,"You move East, looking all around for possible enemies!"
				,"You go East, hoping to find the end of the forest."
				,"Towards the east you go, feet growing weary of the travels taken."
				,"You travel East, hand on your sword ready for what ever danger lurks in front of you."
				,"You travel East, your body trembles with fear over the dangers that await.."};
		double multiplier = (Math.random()*((eastStoryLine.length-1) - 0 + 1) + 0);
		textArea.setText(eastStoryLine[(int)multiplier]);


	}

	public void moveWest() {
		String[] westStoryLine = {"You travel West thru the dangerous forest. You look up in awe over the magnificent branches displayed on the massive trees."
				,"You travel West, worried about what lays ahead in front of you.."
				,"West you go, with the smell of the forest around you, and the stench of your fear!"
				,"To the West you travel, the sounds of the forest surround you, and every foot step loudly giving away your position."
				,"West you go, afraid you may have taken a wrong turn, hesitant to turn back."
				,"To the west you turn, you hear loud shrieks in the distance." 
				,"You travel West, hand on your sword ready for what ever danger lurks in front of you."
				,"You travel West, your body trembles with fear over the dangers that await.."};
		double multiplier = (Math.random()*((westStoryLine.length-1) - 0 + 1) + 0);
		textArea.setText(westStoryLine[(int)multiplier]);
	}

	public void moveNorth() {
		String[] northStoryLine = {"You travel North thru the dangerous forest. You look up in awe over the magnificent branches displayed on the massive trees."
				,"You travel North, worried about what lays ahead in front of you.."
				,"You travel North, hand on your sword ready for what ever danger lurks in front of you."
				,"You travel North, your body trembles with fear over the dangers that await.."};
		double multiplier = (Math.random()*((northStoryLine.length-1) - 0 + 1) + 0);
		textArea.setText(northStoryLine[(int)multiplier]);
	}

	public void checkGameWon() {
		if(player.getPlayerX() == ForestExit.getExitX() && player.getPlayerY() == ForestExit.getExitY()) {
			panel_2.removeAll();
			panel_2.add(textArea);
			textArea.setText("Congratulations, you have found the exit from the Dangerous Forest!");
			validate();
			repaint();
		}

	}
	public void battle() {
		enemy enemy = new enemy();
		textArea.setText("An enemy jumps out and attacks you!\n");
		textArea.append("Enemy has " + enemy.getEnemyLife() + " life.\n");
		textArea.append("You have " + Integer.toString(player.getPlayerHealth()) + " life.\n");
		panel_2.remove(button);
		panel_2.remove(button1);
		panel_2.remove(button3);
		repaint();
  
		JButton btnAttack = new JButton("Attack");
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		panel_2.add(btnAttack, gbc);
		JButton btnDefend = new JButton("Defend");
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.EAST;
		panel_2.add(btnDefend, gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		validate();
		btnAttack.setEnabled(false);
		btnDefend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double enemyAttack = (Math.random()*(10 - 0 + 1) + 0);
				double randomEnemyAttack = (Math.random()*(5-0+1)+0);
				String[] randomEnemyAttackSaying = {"The enemy takes a mighty swing at you!" , "The enemy swings wildy at you!", "The enemy charges you swinging his sword at you!", "With such force you see the enemy's sword swing down at you!", "The enemy screams out as he lashes out with his sword!", "The enemy's rage increases while he swings his sword again and again at you!" };

				btnDefend.setEnabled(false);
				btnAttack.setEnabled(true);
				textArea.setText(randomEnemyAttackSaying[(int)randomEnemyAttack]);
				player.setPlayerHealth((int)enemyAttack);

				textArea.append("\nYour current health is: " + player.getPlayerHealth() + "\n");
				if(player.getPlayerHealth() <= 0) {
					textArea.setText("You have died. Game Over");
					panel_2.remove(btnDefend);
					panel_2.remove(btnAttack);
					validate();
					repaint();
				}
			}
		});

		btnAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double playerAttack = (Math.random()*(20 - 0 + 1) + 0);
				double randomAttack = (Math.random()*(5-0+1)+0);
				String[] randomAttackSaying = {"You swing your sword at the enemy!" , "With a mighty roar you swing your sword down!", "You attempt to slash the enemy!", "You see fear in the enemies eye as you swing your sword!", "You whirl around using the momentum to cause a damaging blow to your enemy!", "Again and again you slash out at the enemy with your sword!" };

				btnAttack.setEnabled(false);
				btnDefend.setEnabled(true);
				validate();
				textArea.setText(randomAttackSaying[(int)randomAttack]);
				enemy.setEnemyLife((int)playerAttack);
				textArea.append("\nEnemy's Current life: " + String.valueOf(enemy.getEnemyLife()) + "\n");
				if(enemy.getEnemyLife() <= 0) {
					player.setBonusHealth();
					textArea.setText("You are victorious!");
					textArea.append("\nYou have been awarded " + player.getBonusHealth() + " bonus health.");
					textArea.append("\nYou may continue your journey.");
					panel_2.remove(btnAttack);
					panel_2.remove(btnDefend);
					gbc.gridy = 1;
					gbc.gridx = 0;
					panel_2.add(button, gbc);
					gbc.gridx = 2;
					gbc.gridy = 0;
					panel_2.add(button1, gbc);
					gbc.gridx = 5;
					gbc.gridy = 1;
					panel_2.add(button3, gbc);
					validate();
					repaint();
				}

			}
		});

	}

}

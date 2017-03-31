import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import core.DevelopmentCardType;
import core.GameBoard;
import core.HexType;
import core.ResourceCardType;

public class UserInterface extends JPanel {
	static BufferedImage KnightDevCard;
	static BufferedImage VictoryPointDevCard;
	static BufferedImage RoadDevCard;
	static BufferedImage MonopolyDevCard;
	static BufferedImage YearOfPlentyDevCard;
	static boolean first = false;
	final static int screenHeight = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height * .9);
	final static int screenWidth = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width * .8);
	// final static int screenWidth = screenHeight;
	static boolean continueGame = true;
	Font font2 = new Font("Jokerman", Font.PLAIN, 20);
	Font font4 = new Font("Jokerman", Font.PLAIN, 50);
	private static final int UPDATE_RATE = 12;
	static JFrame frame = new JFrame();
	static String buttonAction = "End Turn";
	static BufferedImage brickImage;
	static BufferedImage forestImage;
	static BufferedImage sandImage;
	static BufferedImage sheepImage;
	static BufferedImage stoneImage;
	static BufferedImage wheatImage;
	static BufferedImage robberImage;
	static BufferedImage portImage;
	static BufferedImage brickResourceCard;
	static BufferedImage forestResourceCard;
	static BufferedImage sheepResourceCard;
	static BufferedImage stoneResourceCard;
	static BufferedImage wheatResourceCard;
	static int playerCardSelect = 0;
	static boolean server = true;
	static int cardSelect = 0;
	private static boolean seeMap = true;
	static HashSet<Integer> selectedJoints = new HashSet<Integer>();
	static HashSet<DevelopmentCardType> selectedCards = new HashSet<DevelopmentCardType>();
	static GameBoard gameBoard;

	public UserInterface() {

		try {
			brickImage = ImageIO.read(new File("Extra/brick_hex.png"));
			forestImage = ImageIO.read(new File("Extra/forest_hex.png"));
			sandImage = ImageIO.read(new File("Extra/sand_hex.png"));
			sheepImage = ImageIO.read(new File("Extra/sheep_hex.png"));
			stoneImage = ImageIO.read(new File("Extra/stone_hex.png"));
			wheatImage = ImageIO.read(new File("Extra/wheat_hex.png"));
			robberImage = ImageIO.read(new File("Extra/robber.png"));
			portImage = ImageIO.read(new File("Extra/port.png"));
			brickResourceCard = ImageIO.read(new File("Extra/Brick.png"));
			forestResourceCard = ImageIO.read(new File("Extra/Lumber.png"));
			sheepResourceCard = ImageIO.read(new File("Extra/Sheep.png"));
			stoneResourceCard = ImageIO.read(new File("Extra/Stone.png"));
			wheatResourceCard = ImageIO.read(new File("Extra/Wheat.png"));
			KnightDevCard = ImageIO.read(new File("Extra/DevelopmentCards/Knight.png"));
			VictoryPointDevCard = ImageIO.read(new File("Extra/DevelopmentCards/University.png"));
			RoadDevCard = ImageIO.read(new File("Extra/DevelopmentCards/Roads.png"));
			MonopolyDevCard = ImageIO.read(new File("Extra/DevelopmentCards/Monopoly.png"));
			YearOfPlentyDevCard = ImageIO.read(new File("Extra/DevelopmentCards/YearOfPlenty.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread gameThread = new Thread() {
			public void run() {
				while (continueGame) {

					repaint(); // Callback paintComponent()
					// Delay for timing control and give other threads a chance
					try {
						Thread.sleep(1000 / UPDATE_RATE); // milliseconds
					} catch (InterruptedException ex) {
					}
				}
			}
		};
		gameThread.start(); // Callback run()
	}

	private BufferedImage GetHexImage(HexType hexType) {
		if (hexType == HexType.BRICK)
			return brickImage;
		else if (hexType == HexType.LUMBER)
			return UserInterface.forestImage;
		else if (hexType == HexType.SAND)
			return UserInterface.sandImage;
		else if (hexType == HexType.SHEEP)
			return UserInterface.sheepImage;
		else if (hexType == HexType.STONE)
			return UserInterface.stoneImage;
		else if (hexType == HexType.WHEAT)
			return UserInterface.wheatImage;
		else
			return null;
	}
	// paint

	public UserInterface(GameBoard game) {
		// TODO Auto-generated constructor stub
		gameBoard = game;
		
	}

	private void drawBank(Graphics2D g2) {
		g2.setFont(font4);
		g2.setColor(Color.cyan);

		g2.drawImage(this.sheepResourceCard, (int) (screenWidth * .5), (int) (screenHeight * .2),
				(int) (screenWidth * .05), (int) (screenHeight * .1), this);
		if (playerCardSelect == 1)
			g2.drawString(">>>>>", (int) (screenWidth * .45), (int) (screenHeight * .25));

		g2.drawImage(this.wheatResourceCard, (int) (screenWidth * .5), (int) (screenHeight * .3),
				(int) (screenWidth * .05), (int) (screenHeight * .1), this);
		if (playerCardSelect == 2)
			g2.drawString(">>>>>", (int) (screenWidth * .45), (int) (screenHeight * .35));

		g2.drawImage(this.forestResourceCard, (int) (screenWidth * .5), (int) (screenHeight * .4),
				(int) (screenWidth * .05), (int) (screenHeight * .1), this);
		if (playerCardSelect == 3)
			g2.drawString(">>>>>", (int) (screenWidth * .45), (int) (screenHeight * .45));

		g2.drawImage(this.stoneResourceCard, (int) (screenWidth * .5), (int) (screenHeight * .5),
				(int) (screenWidth * .05), (int) (screenHeight * .1), this);
		if (playerCardSelect == 4)
			g2.drawString(">>>>>", (int) (screenWidth * .45), (int) (screenHeight * .55));

		g2.drawImage(this.brickResourceCard, (int) (screenWidth * .5), (int) (screenHeight * .6),
				(int) (screenWidth * .05), (int) (screenHeight * .1), this);
		if (playerCardSelect == 5)
			g2.drawString(">>>>>", (int) (screenWidth * .45), (int) (screenHeight * .65));

		g2.drawImage(this.sheepResourceCard, (int) (screenWidth * .8), (int) (screenHeight * .2),
				(int) (screenWidth * .05), (int) (screenHeight * .1), this);
		if (cardSelect == 1)
			g2.drawString("<<<<<", (int) (screenWidth * .9), (int) (screenHeight * .25));

		g2.drawImage(this.wheatResourceCard, (int) (screenWidth * .8), (int) (screenHeight * .3),
				(int) (screenWidth * .05), (int) (screenHeight * .1), this);

		if (cardSelect == 2)
			g2.drawString("<<<<<", (int) (screenWidth * .9), (int) (screenHeight * .35));

		g2.drawImage(this.forestResourceCard, (int) (screenWidth * .8), (int) (screenHeight * .4),
				(int) (screenWidth * .05), (int) (screenHeight * .1), this);

		if (cardSelect == 3)
			g2.drawString("<<<<<", (int) (screenWidth * .9), (int) (screenHeight * .45));

		g2.drawImage(this.stoneResourceCard, (int) (screenWidth * .8), (int) (screenHeight * .5),
				(int) (screenWidth * .05), (int) (screenHeight * .1), this);

		if (cardSelect == 4)
			g2.drawString("<<<<<", (int) (screenWidth * .9), (int) (screenHeight * .55));

		g2.drawImage(this.brickResourceCard, (int) (screenWidth * .8), (int) (screenHeight * .6),
				(int) (screenWidth * .05), (int) (screenHeight * .1), this);

		if (cardSelect == 5)
			g2.drawString("<<<<<", (int) (screenWidth * .9), (int) (screenHeight * .65));
	}

	private void drawMap(Graphics2D g2) {
		for (int i = 0; i < gameBoard.allHexes.size(); i++) {
			if (gameBoard.allHexes.get(i).wasCreated) {
				// g2.setColor(gameBoard.allHexes.get(i).color); set up the
				// colors
				// g2.fillPolygon(gameBoard.allHexes.get(i).xLocs,
				// gameBoard.allHexes.get(i).yLocs, 6);
				BufferedImage hexImage = this.GetHexImage(gameBoard.allHexes.get(i).GetHexType());
				g2.drawImage(hexImage,
						(int) ((gameBoard.allHexes.get(i).centerX * screenWidth) - ((int) (.05 * screenWidth))),
						(int) ((gameBoard.allHexes.get(i).centerY * screenHeight) - ((int) (.095 * screenHeight))),
						(int) (.1 * screenWidth), (int) (.195 * screenHeight), this);

				if (gameBoard.rob != null) {
					if (i == gameBoard.rob.location) {
						g2.drawImage(robberImage,
								(int) ((gameBoard.allHexes.get(i).centerX * screenWidth) - ((int) (.05 * screenWidth))),
								(int) ((gameBoard.allHexes.get(i).centerY * screenHeight)
										- ((int) (.1 * screenHeight))),
								(int) (.1 * screenWidth), (int) (.195 * screenHeight), this);
					}
				}

				g2.setColor(Color.cyan);
				Font font = new Font("Jokerman", Font.PLAIN, 35);
				g2.setFont(font);
				if (gameBoard.allHexes.get(i).number != -1) {
					g2.drawString("" + gameBoard.allHexes.get(i).number,
							(int) ((gameBoard.allHexes.get(i).centerX * screenWidth)),
							(int) ((gameBoard.allHexes.get(i).centerY * screenHeight)));
				}

			}
		}

		g2.setStroke(new BasicStroke(20));

		for (int i = 0; i < gameBoard.allPaths.size(); i++) {
			g2.setColor(gameBoard.allPaths.get(i).color);
			g2.drawLine((int) (gameBoard.allPaths.get(i).xLoc1 * screenWidth),
					(int) (gameBoard.allPaths.get(i).yLoc1 * screenHeight),
					(int) (gameBoard.allPaths.get(i).xLoc2 * screenWidth),
					(int) (gameBoard.allPaths.get(i).yLoc2 * screenHeight));
		}

		for (int i = 0; i < gameBoard.allJoints.size(); i++) {
			g2.setColor(gameBoard.allJoints.get(i).color);
			int jointSize = (int) (screenWidth * gameBoard.allJoints.get(i).size);
			if (selectedJoints.contains(i)) {

				if (!gameBoard.allJoints.get(i).city) {
					g2.fillOval((int) (gameBoard.allJoints.get(i).xLoc * screenWidth) - (int) (jointSize * 1.5) / 2,
							(int) (gameBoard.allJoints.get(i).yLoc * screenHeight) - (int) (jointSize * 1.5) / 2,
							(int) (jointSize * 1.5), (int) (jointSize * 1.5));
				} else {
					g2.fillRect((int) (gameBoard.allJoints.get(i).xLoc * screenWidth) - (int) (jointSize * 1.5) / 2,
							(int) (gameBoard.allJoints.get(i).yLoc * screenHeight) - (int) (jointSize * 1.5) / 2,
							(int) (jointSize * 1.5), (int) (jointSize * 1.5));

				}

			} else {
				if (!gameBoard.allJoints.get(i).city) {
					g2.fillOval((int) (gameBoard.allJoints.get(i).xLoc * screenWidth) - jointSize / 2,
							(int) (gameBoard.allJoints.get(i).yLoc * screenHeight) - jointSize / 2, jointSize,
							jointSize);
				} else {

					g2.fillRect((int) (gameBoard.allJoints.get(i).xLoc * screenWidth) - jointSize / 2,
							(int) (gameBoard.allJoints.get(i).yLoc * screenHeight) - jointSize / 2, jointSize,
							jointSize);

				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Paint background

		Graphics2D g2 = (Graphics2D) g;

		JPanel totalGUI = new JPanel();
		totalGUI.setLayout(null);

		// Draw the box
		g2.setColor(gameBoard.getCurrentPlayer().color);
		g2.fillRect(0, 0, screenWidth, screenHeight);

		g2.setColor(Color.black);
		g2.fillRect((int) (screenWidth * .45), (int) (screenHeight * .1), (int) (screenWidth * .5),
				(int) (screenHeight * .8));

		if (seeMap) {

			drawMap(g2);
			// System.out.println("DRAW MAP");
		} else {
			drawBank(g2);
			// System.out.println("DRAW BANK");

		}

		g2.setColor(Color.cyan);
		g2.fill3DRect(0, (int) (screenHeight * .8), (int) (screenWidth * .2), (int) (screenHeight * .2), true);
		g2.setColor(Color.black);

		g2.setFont(font2);
		g2.drawString(buttonAction, (int) (screenWidth * .02), (int) (screenHeight * .88));

		g2.setColor(Color.cyan);
		g2.fill3DRect((int) (screenWidth * .2), (int) (screenHeight * .8), (int) (screenWidth * .2),
				(int) (screenHeight * .2), true);
		g2.setColor(Color.black);

		if (seeMap)
			g2.drawString("Bank", (int) (screenWidth * .22), (int) (screenHeight * .88));
		else
			g2.drawString("Map", (int) (screenWidth * .22), (int) (screenHeight * .88));

		if (gameBoard.currentRoll > 0)
			g2.drawString(gameBoard.getCurrentPlayer().username + " rolls a " + gameBoard.currentRoll,
					(int) (screenWidth * .5), (int) (screenHeight * .05));
		else
			g2.drawString(gameBoard.getCurrentPlayer().username + " place a settlement and road",
					(int) (screenWidth * .5), (int) (screenHeight * .05));

		double yScreen = .02;

		for (int i = 0; i < gameBoard.allPlayers.size(); i++) {

			g2.setColor(gameBoard.allPlayers.get(i).color);
			g2.fillRect((int) (screenWidth * .02), (int) (screenHeight * yScreen - screenHeight * .03),
					(int) (int) (screenWidth * .4), (int) (screenHeight * .2));

			Font font3 = new Font("Jokerman", Font.PLAIN, 15);
			g2.setColor(Color.black);
			g2.setFont(font2);
			g2.drawString(gameBoard.allPlayers.get(i).username, (int) (screenWidth * .02),
					(int) (screenHeight * yScreen));

			// Where we would put development cards and resource cards
			if (gameBoard.allPlayers.get(i).cards.size() < 11) {
				for (int j = 0; j < gameBoard.allPlayers.get(i).cards.size(); ++j) {
					if (gameBoard.allPlayers.get(i).cards.get(j) == ResourceCardType.SHEEPRC) {
						g2.drawImage(sheepResourceCard, (int) (screenWidth * (.02) + (j * .04 * screenWidth)),
								(int) (screenHeight * (yScreen + .01)), (int) (screenWidth * .08),
								(int) (screenHeight * .17), this);
					} else if (gameBoard.allPlayers.get(i).cards.get(j) == ResourceCardType.BRICKRC) {
						g2.drawImage(brickResourceCard, (int) (screenWidth * (.02) + (j * .04 * screenWidth)),
								(int) (screenHeight * (yScreen + .01)), (int) (screenWidth * .08),
								(int) (screenHeight * .17), this);
					} else if (gameBoard.allPlayers.get(i).cards.get(j) == ResourceCardType.FORESTRC) {
						g2.drawImage(forestResourceCard, (int) (screenWidth * (.02) + (j * .04 * screenWidth)),
								(int) (screenHeight * (yScreen + .01)), (int) (screenWidth * .08),
								(int) (screenHeight * .17), this);
					} else if (gameBoard.allPlayers.get(i).cards.get(j) == ResourceCardType.STONERC) {
						g2.drawImage(stoneResourceCard, (int) (screenWidth * (.02) + (j * .04 * screenWidth)),
								(int) (screenHeight * (yScreen + .01)), (int) (screenWidth * .08),
								(int) (screenHeight * .17), this);
					} else if (gameBoard.allPlayers.get(i).cards.get(j) == ResourceCardType.WHEATRC) {
						g2.drawImage(wheatResourceCard, (int) (screenWidth * (.02) + (j * .04 * screenWidth)),
								(int) (screenHeight * (yScreen + .01)), (int) (screenWidth * .08),
								(int) (screenHeight * .17), this);
					}
				}
			} else {
				for (int j = 0; j < gameBoard.allPlayers.get(i).cards.size(); ++j) {
					if (gameBoard.allPlayers.get(i).cards.get(j) == ResourceCardType.SHEEPRC) {
						g2.drawImage(sheepResourceCard, (int) (screenWidth * (.02) + (j * .02 * screenWidth)),
								(int) (screenHeight * (yScreen + .01)), (int) (screenWidth * .04),
								(int) (screenHeight * .085), this);
					} else if (gameBoard.allPlayers.get(i).cards.get(j) == ResourceCardType.BRICKRC) {
						g2.drawImage(brickResourceCard, (int) (screenWidth * (.02) + (j * .02 * screenWidth)),
								(int) (screenHeight * (yScreen + .01)), (int) (screenWidth * .04),
								(int) (screenHeight * .085), this);
					} else if (gameBoard.allPlayers.get(i).cards.get(j) == ResourceCardType.FORESTRC) {
						g2.drawImage(forestResourceCard, (int) (screenWidth * (.02) + (j * .02 * screenWidth)),
								(int) (screenHeight * (yScreen + .01)), (int) (screenWidth * .04),
								(int) (screenHeight * .085), this);
					} else if (gameBoard.allPlayers.get(i).cards.get(j) == ResourceCardType.STONERC) {
						g2.drawImage(stoneResourceCard, (int) (screenWidth * (.02) + (j * .02 * screenWidth)),
								(int) (screenHeight * (yScreen + .01)), (int) (screenWidth * .04),
								(int) (screenHeight * .085), this);
					} else if (gameBoard.allPlayers.get(i).cards.get(j) == ResourceCardType.WHEATRC) {
						g2.drawImage(wheatResourceCard, (int) (screenWidth * (.02) + (j * .02 * screenWidth)),
								(int) (screenHeight * (yScreen + .01)), (int) (screenWidth * .04),
								(int) (screenHeight * .085), this);
					}
				}
			}

			yScreen = yScreen + .2;
		}
	}

	private boolean isJointSelected(double x, double y) {
		for (int i = 0; i < gameBoard.allJoints.size(); i++) {// Add all the
																// joints
																// corresponding
																// to this hex
			double dist = Math.sqrt((x - gameBoard.allJoints.get(i).xLoc) * (x - gameBoard.allJoints.get(i).xLoc)
					+ (y - gameBoard.allJoints.get(i).yLoc) * (y - gameBoard.allJoints.get(i).yLoc));

			if (dist < .02) {// theoretically should only happen once per
								// location
				if (!UserInterface.selectedJoints.contains(i))
					UserInterface.selectedJoints.add(i);// Adds the UserInteface
														// arraylist joint
														// position
				else {
					UserInterface.selectedJoints.remove(i);
				}
				return true;
			}

		}

		return false;

	}

	public void runInterface() {// will probably need to change to a normal
								// method that takes a gameBoard

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				JPanel totalGUI = new JPanel();
				totalGUI.setLayout(null);

				JButton button = new JButton();

				button.setSize(screenWidth, screenHeight);
				button.setLocation(0, 0);
				button.setOpaque(false);
				button.setContentAreaFilled(false);
				button.setBorderPainted(false);
				button.setVisible(true);
				totalGUI.add(button);

				button.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent mC) {

						int xClick = mC.getX();
						int yClick = mC.getY();

						if (seeMap) {

							if (isJointSelected((1.0 * xClick) / screenWidth, (1.0 * yClick) / screenHeight)) {

							} else if (xClick > 0 && xClick < (int) (screenWidth * .2)
									&& yClick > (int) (screenHeight * .8)) {

								if (buttonAction.equals("Place Settlement")) {
									gameBoard.placeSettlementOrCity(selectedJoints);
									
									// TODO Communicate to Server
								} else if (buttonAction.equals("Place Road")) {
									gameBoard.placePath(selectedJoints);
									// TODO Communicate to server
								} else if (buttonAction.equals("Move Robber")) {
									gameBoard.placeRobber(selectedJoints);
									// TODO Communicate to server
								} else {

									if (gameBoard.nextTurn()) {
										// gameBoard.nextTurn();
										// TODO Communicate to server
									} else {
										JOptionPane.showMessageDialog(null,
												"Make sure to place a settlement and a road!");
									}

								}

								emptyBank();
								emptySelectedJoints();

							} else if (xClick > (int) (screenWidth * .2) && xClick < (int) (screenWidth * .4)
									&& yClick > (int) (screenHeight * .8)) {
								UserInterface.emptySelectedJoints();
								seeMap = false;
							} else {

							}

						} else {

							double x = (1.0 * xClick) / screenWidth;
							double y = (1.0 * yClick) / screenHeight;

							if (xClick > 0 && xClick < (int) (screenWidth * .2) && yClick > (int) (screenHeight * .8)) {

								if (buttonAction.equals("Trade With Bank")) {
									gameBoard.tradeWithBank(playerCardSelect, cardSelect);
									// TODO communicate with server.
								} else {
									gameBoard.nextTurn();
									// TODO communicate with server.
								}

								emptyBank();
								emptySelectedJoints();
							} else if (xClick > (int) (screenWidth * .2) && xClick < (int) (screenWidth * .4)
									&& yClick > (int) (screenHeight * .8)) {
								emptyBank();
								emptySelectedJoints();
								seeMap = true;
							}

							if (x > .4 && x < .6 && y > .2 && y < .3) {
								playerCardSelect = 1;
							}
							if (x > .4 && x < .6 && y > .3 && y < .4) {
								playerCardSelect = 2;
							}
							if (x > .4 && x < .6 && y > .4 && y < .5) {
								playerCardSelect = 3;
							}
							if (x > .4 && x < .6 && y > .5 && y < .6) {
								playerCardSelect = 4;
							}
							if (x > .4 && x < .6 && y > .6 && y < .7) {
								playerCardSelect = 5;
							}

							if (x > .8 && x < .9 && y > .2 && y < .3) {
								cardSelect = 1;
							}
							if (x > .8 && x < .9 && y > .3 && y < .4) {
								cardSelect = 2;
							}
							if (x > .8 && x < .9 && y > .4 && y < .5) {
								cardSelect = 3;

							}
							if (x > .8 && x < .9 && y > .5 && y < .6) {
								cardSelect = 4;

							}
							if (x > .8 && x < .9 && y > .6 && y < .7) {
								cardSelect = 5;

							}

						}

						/*
						 * Patrick do these one at a time.
						 * 
						 * Take them out of the comments and copy and paste them
						 * into gameBoard constructor I commented out all of the
						 * old code Search for your name in gameBoard and you
						 * should find it
						 * 
						 */

						/*
						 * //How I printed out all Hex locations... Click in the
						 * center of mass
						 * System.out.println(" allHexes.add(new Hex(("+(1.0*
						 * xClick)/(1.0*screenWidth)+" ),("+(1.0*yClick)/(1.0*
						 * screenHeight)+" )));");
						 */

						// How I printed out the joint locations
						// System.out.println(" allJoints.add(new
						// Joint(("+(1.0*xClick)/(1.0*screenWidth)+"
						// ),("+(1.0*yClick)/(1.0*screenHeight)+" )));");

						/*
						 * //How I printed out the path locations first =
						 * !first; if(first)
						 * System.out.print("allPaths.add(new Path(("+(1.0*
						 * xClick)/(1.0*screenWidth)+" ),("+(1.0*yClick)/(1.0*
						 * screenHeight)+" ) ,"); else
						 * System.out.println("("+(1.0*xClick)/(1.0*screenWidth)
						 * +" ) , ("+(1.0*yClick)/(1.0*screenHeight)+" )  ));");
						 */
						// TODO COME BACK AND FIX THIS
						buttonAction = getAction(selectedJoints, playerCardSelect, cardSelect);

					}

				});

				JPanel panel = new UserInterface();
				panel.setSize(screenWidth, screenHeight);
				panel.setLocation(0, 0);
				panel.setVisible(true);
				totalGUI.add(panel);

				// totalGUI.add(hello);

				frame = new JFrame("Settlers of Farmville");
				frame.setSize(screenWidth, screenHeight);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(totalGUI);
				// frame.pack();
				frame.setVisible(true);

			}
		});

	}

	public String getAction(HashSet<Integer> selectedJoints, int playerCardSelect, int cardSelect) {

		String buttonString = "End Turn";

		if (selectedJoints.isEmpty()) {
			buttonString = gameBoard.getTradeWithBank(playerCardSelect, cardSelect);
			if (!buttonString.equals("End Turn")) {
				return buttonString;
			}
		} else {
			buttonString = gameBoard.getPlaceRobber(selectedJoints);

			if (!buttonString.equals("End Turn")) {
				return buttonString;
			}

			buttonString = gameBoard.getPlacePath(selectedJoints);

			if (!buttonString.equals("End Turn")) {
				return buttonString;
			}

			if (selectedJoints.size() != 1) {
				return "End Turn";
			}

			buttonString = gameBoard.getPlaceSettlementOrCity(selectedJoints);

			if (!buttonString.equals("End Turn")) {
				return buttonString;
			}

		}

		return buttonString;
	}

	public static void emptySelectedJoints() {
		// TODO Auto-generated method stub
		selectedJoints = new HashSet<Integer>();
	}

	public static void emptyBank() {
		// TODO Auto-generated method stub
		playerCardSelect = 0;
		cardSelect = 0;
	}

}

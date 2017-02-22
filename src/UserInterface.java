import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class UserInterface extends JPanel{
	
	//static boolean first = false;
	
	final static int screenWidth = (int)(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width*.8);
	final static int screenHeight = (int)(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height*.8);
	static boolean continueGame = true;
	static GameBoard gameBoard; 
	private static final int UPDATE_RATE = 12;
	static JFrame frame = new JFrame();
	
	public UserInterface() {

	      Thread gameThread = new Thread() {
	         public void run() {
	            while (continueGame) { 
	            
	            
	            	
	               repaint(); // Callback paintComponent()
	               // Delay for timing control and give other threads a chance
	               try {
	                  Thread.sleep(1000 / UPDATE_RATE);  // milliseconds
	               } catch (InterruptedException ex) { }
	            }
	         }
	      };
	      gameThread.start();  // Callback run()
	   }
	
	//paint 
	
	 @Override
	   public void paintComponent(Graphics g) {
	      super.paintComponent(g);    // Paint background
	      
	      Graphics2D g2 = (Graphics2D) g;
	      
	      JPanel totalGUI = new JPanel();
	      totalGUI.setLayout(null);
	  
	      // Draw the box
	      g2.setColor(Color.white);
	      g2.fillRect(0, 0, screenWidth, screenHeight);
	      
	      
		  	BufferedImage img = null;
			try {
				img = ImageIO.read(new File("Extra/forest_hex.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
	    /*  image I used to draw over
	  	BufferedImage img = null;
		try {
			img = ImageIO.read(new File("gamemap.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g2.drawImage(img, 0, 0, screenWidth, screenHeight, this);
	      */
	      
	      for(int i = 0; i < gameBoard.allHexes.size(); i++){
	    if(gameBoard.allHexes.get(i).wasCreated){
	      //g2.setColor(gameBoard.allHexes.get(i).color); set up the colors
	      //g2.fillPolygon(gameBoard.allHexes.get(i).xLocs, gameBoard.allHexes.get(i).yLocs, 6);
	      g2.drawImage(img 
	    		  , (int)((gameBoard.allHexes.get(i).centerX*screenWidth) - ((int)(.08*screenWidth)))
	    		  , (int)((gameBoard.allHexes.get(i).centerY*screenHeight) - ((int)(.095*screenHeight)))
	    		  , (int)(.155*screenWidth)
	    		  , (int)(.195*screenHeight)
	    		  , this);
	      
	      
	      
	    }}
	      
	     g2.setStroke(new BasicStroke(20));
	      
	      for(int i = 0; i < gameBoard.allPaths.size();i++){
	    	  g2.setColor(gameBoard.allPaths.get(i).color);
	    	  g2.drawLine((int)(gameBoard.allPaths.get(i).xLoc1*screenWidth),
	    			  (int)(gameBoard.allPaths.get(i).yLoc1*screenHeight)
	    			  ,(int)( gameBoard.allPaths.get(i).xLoc2* screenWidth)
	    			  ,(int)( gameBoard.allPaths.get(i).yLoc2* screenHeight));
	      }
	      
	      for(int i =0; i < gameBoard.allJoints.size();i++){
	     	  g2.setColor(gameBoard.allJoints.get(i).color);
	    	  g2.fillRect((int)(gameBoard.allJoints.get(i).xLoc*screenWidth) - gameBoard.allJoints.get(i).getSize()/2
	    			  ,(int)(gameBoard.allJoints.get(i).yLoc*screenHeight)- gameBoard.allJoints.get(i).getSize()/2
	    			  , gameBoard.allJoints.get(i).getSize() 
	    			  ,gameBoard.allJoints.get(i).getSize());
	      }
	  

	   }
	

	   public static void main(String[] args) {//will probably need to change to a normal method that takes a gameboard
		  
		   gameBoard = new GameBoard();
		   
		   
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
			     
			      System.out.println(" allHexes.add(new Hex(("+(1.0*xClick)/(1.0*screenWidth)+" ),("+(1.0*yClick)/(1.0*screenHeight)+" )));");					
			      		
			      				
			      	/* How I printed out the joint locations
			      	System.out.println(" allJoints.add(new Joint((int)("+(1.0*xClick)/(1.0*screenWidth)+" *screenWidth),(int)("+(1.0*yClick)/(1.0*screenHeight)+" * screenHeight)));");		
			      		*/				 		
			      				
			      		/* How I printed out the path locations		
			      		 * 	first = !first;
			      		if(first)
			      				System.out.print("allPaths.add(new Path((int)("+(1.0*xClick)/(1.0*screenWidth)+" *screenWidth),(int)("+(1.0*yClick)/(1.0*screenHeight)+" * screenHeight) ,");
			      		else
			      			System.out.println("(int)("+(1.0*xClick)/(1.0*screenWidth)+" *screenWidth) , (int)("+(1.0*yClick)/(1.0*screenHeight)+" * screenHeight)  ));");
			      	*/
			      				
			      	//I deleted the print out for the Hex, contact me if you need me to recreate
			      	   
			      			
			      			
			      			
			      			
			      			}});
		        	 
		 
		 
		 
		 
		        	JPanel panel = new UserInterface();
		        	panel.setSize(screenWidth,screenHeight);
		        	panel.setLocation(0, 0);
		        	panel.setVisible(true);
			    	totalGUI.add(panel);
		        	 
		        	//totalGUI.add(hello);
		      
		        	frame = new JFrame("Settlers of Farmville");
		        	frame.setSize(screenWidth, screenHeight);
		            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		            frame.setContentPane(totalGUI);
		           // frame.pack();
		            frame.setVisible(true);
		        
		            
	
		
		
	

		
		
	
	
		
		         }
		      });
		
		}

		

	   public static void init(GameBoard game) {//will probably need to change to a normal method that takes a gameboard
		  
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
			     
			      System.out.println(" allHexes.add(new Hex(("+(1.0*xClick)/(1.0*screenWidth)+" ),("+(1.0*yClick)/(1.0*screenHeight)+" )));");					
			      		
			      				
			      	/* How I printed out the joint locations
			      	System.out.println(" allJoints.add(new Joint((int)("+(1.0*xClick)/(1.0*screenWidth)+" *screenWidth),(int)("+(1.0*yClick)/(1.0*screenHeight)+" * screenHeight)));");		
			      		*/				 		
			      				
			      		/* How I printed out the path locations		
			      		 * 	first = !first;
			      		if(first)
			      				System.out.print("allPaths.add(new Path((int)("+(1.0*xClick)/(1.0*screenWidth)+" *screenWidth),(int)("+(1.0*yClick)/(1.0*screenHeight)+" * screenHeight) ,");
			      		else
			      			System.out.println("(int)("+(1.0*xClick)/(1.0*screenWidth)+" *screenWidth) , (int)("+(1.0*yClick)/(1.0*screenHeight)+" * screenHeight)  ));");
			      	*/
			      				
			      	//I deleted the print out for the Hex, contact me if you need me to recreate
			      	   
			      			
			      			
			      			
			      			
			      			}});
		        	 
		 
		 
		 
		 
		        	JPanel panel = new UserInterface();
		        	panel.setSize(screenWidth,screenHeight);
		        	panel.setLocation(0, 0);
		        	panel.setVisible(true);
			    	totalGUI.add(panel);
		        	 
		        	//totalGUI.add(hello);
		      
		        	frame = new JFrame("Settlers of Farmville");
		        	frame.setSize(screenWidth, screenHeight);
		            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		            frame.setContentPane(totalGUI);
		           // frame.pack();
		            frame.setVisible(true);
		        
		            
	
		
		
	

		
		
	
	
		
		         }
		      });
		
		}
		     
	   
		private static boolean continueGame() {
			// TODO Auto-generated method stub
			return true;

		}
	
		
		
		
		
		
		
		
		
	}


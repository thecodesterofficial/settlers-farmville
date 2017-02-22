import java.awt.Frame;

import javax.swing.JOptionPane;


public class StartMenu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Frame f = new Frame();
		
		Object[] options = {"Host a Game",
                "Connect to a Host"};
		int n = JOptionPane.showOptionDialog(f,
				"How would you like to start Settlers of Farmville? ",
						"Start Menu",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[1]);
		
	
		
		if(n == 0){
			try {
				Host host = new Host();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(n == 1){
			try {
				Player.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		f.dispose();

	}

}
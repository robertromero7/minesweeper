package cmp168Project2;

public class ShowMS {

	public static void main(String [] args) {
		
		
		javax.swing.SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				MineSweeperGui gui = new MineSweeperGui();
				
			}
			
		});
	}
}

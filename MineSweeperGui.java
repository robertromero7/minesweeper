import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MineSweeperGui extends JFrame{
	
	private JPanel jp;
	private MSBoard board;

	public MineSweeperGui(){
		
		jp = new JPanel();
		jp.setLayout(new BorderLayout());
		
		board = new MSBoard();
		
		jp.add(board, BorderLayout.CENTER);
		
		add(jp);
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	
	
	
	private class MSBoard extends JPanel implements ActionListener{
		private int numRows;
		private int numColumns;
		private int numBombs;
		private int[][] checkGameWon;
		private JButton [][] board;
		Grid grid;
		JOptionPane playAgain;
		boolean [][] bombGrid;
		
		ImageIcon bombImg = new ImageIcon("bomb.png");
		
		ImageIcon bombImgScaled = new ImageIcon(bombImg.getImage().getScaledInstance(40,40,Image.SCALE_FAST));
		
		public MSBoard() {
			
			
			String rows =  JOptionPane.showInputDialog(null,"How many rows do you want?","MineSweeper", JOptionPane.OK_CANCEL_OPTION);
			String columns =  JOptionPane.showInputDialog(null,"How many Columns do you want?","MineSweeper", JOptionPane.OK_CANCEL_OPTION);
			String bombs =  JOptionPane.showInputDialog(null,"How many Bombs do you want?","MineSweeper", JOptionPane.OK_CANCEL_OPTION);
			
			
			 numRows = Integer.parseInt(rows);
			 numColumns = Integer.parseInt(columns);
			 numBombs = Integer.parseInt(bombs);
			 grid = new Grid(numRows,numColumns,numBombs);
			 
			
			 
			setLayout(new GridLayout(numRows,numColumns));
			displayBoard();
			
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
		//	System.out.println(e.toString());
			
			JButton buttonClicked = (JButton) e.getSource();
		
			for(int row=0; row<board.length;row++) {
				for(int col=0;col<board[row].length;col++) {
					if(board[row][col]==buttonClicked) {			// CHECKS IF A BOMB WAS CLICKED
						
						if(grid.isBombAtLocation(row,col) == true){
							
							displayLose();  // LOSE IF BOMB WAS CLICKED
						}else {
						
							buttonClicked.setText("" + grid.getCountAtLocation(row,col));  // REVEALS THE COUNT OF THE BUTTON CLICKED
							checkGameWon[row][col] = 1;
							if(checkGameWon()==false) {
								if(buttonClicked.getText().equals("0")) {
									ifZero(row,col,buttonClicked);  // REVEALS THE COUNT OF ADJACENT CELLS IF ITS A ZERO
								}
							}else {
								displayWin();
									
								
							}
						}
					}
				}
			}
			
			
		}
		
		private void displayWin() {
			int yesno =  JOptionPane.showConfirmDialog(null,"Do You Want To Play Again?","YOU WON!", JOptionPane.YES_NO_OPTION); // ASK IF WANT TO PLAY AGAIn
			if(yesno == JOptionPane.YES_OPTION) { // IF WANTS TO PLAY AGAIN CLEAR BOARD
				for(int row=0; row<board.length;row++) {
					for(int col=0;col<board[row].length;col++) {
						board[row][col].setText("");
						}
					}
				dispose();		// GET RID OF OLD INSTANCE OF THE GAME
				MineSweeperGui gui = new MineSweeperGui();		// CREATE NEW GAME
			}else {  // EXIT THE SYSTEM IF NO
				System.exit(EXIT_ON_CLOSE);
			}
			
		}

		private void ifZero(int row,int col,JButton x) {
			
			
					if(row>=1 && col>=1) {
						if(bombGrid[row-1][col-1]==false && !x.getText().isEmpty()){
							board[row-1][col-1].setText("" + grid.getCountAtLocation(row-1,col-1));
							checkGameWon[row-1][col-1] = 1;
							
						}
					}
					
					
					if(row>=1) {
						if(bombGrid[row-1][col]==false && !x.getText().isEmpty()){
							board[row-1][col].setText("" + grid.getCountAtLocation(row-1,col));
							checkGameWon[row-1][col] = 1;
						}
					}
					
					
					if(row>=1 && col<board[col].length-1 && !x.getText().isEmpty()) {
						if(bombGrid[row-1][col+1]==false){
							board[row-1][col+1].setText("" + grid.getCountAtLocation(row-1,col+1));
							checkGameWon[row-1][col+1] = 1;

						}
					}
					
					if(col>=1) {
						if(bombGrid[row][col-1]==false && !x.getText().isEmpty()){
							board[row][col-1].setText("" + grid.getCountAtLocation(row,col-1));
							checkGameWon[row][col-1] = 1;

						}
					}
					
					if(col<board[col].length-1) {
						if(bombGrid[row][col+1]==false && !x.getText().isEmpty()){
							board[row][col+1].setText("" + grid.getCountAtLocation(row,col+1));
							checkGameWon[row][col+1] = 1;

						}
					}
			
					if(row<board.length-1 && col>=1) {
						if(bombGrid[row+1][col-1]==false && !x.getText().isEmpty()){
							board[row+1][col-1].setText("" + grid.getCountAtLocation(row+1,col-1));
							checkGameWon[row+1][col-1] = 1;

						}
					}
					
					if(row<board.length-1) {
						if(bombGrid[row+1][col]==false && !x.getText().isEmpty()){
							board[row+1][col].setText("" + grid.getCountAtLocation(row+1,col));
							checkGameWon[row+1][col] = 1;

						}
					}
						
					if(row<board.length-1 && col<board[col].length-1 && !x.getText().isEmpty()) {
						if(bombGrid[row+1][col+1]==false){
							board[row+1][col+1].setText("" + grid.getCountAtLocation(row+1,col+1));
							checkGameWon[row+1][col+1] = 1;

						}
					}
			
			
		}

		private boolean checkGameWon() {
//		int numToWin = numRows * numColumns;
//		numToWin = numToWin - numBombs;
//		
//		if (numClicks<numToWin) {
//			return false;
//		}
//		
//		return true;
			for(int row=0; row<board.length;row++) {
				for(int col=0;col<board[row].length;col++) {

				System.out.println(checkGameWon[row][col]);
					}
			}
			System.out.println("--------------------------------------");
			System.out.println("--------------------------------------");
			for(int row=0; row<board.length;row++) {
				for(int col=0;col<board[row].length;col++) {
					if(checkGameWon[row][col]==0) {
	
						return false;
						
					}
				}
			
		}
			return true;
		}

		public void displayLose() {
			
			
			
			for(int row=0; row<board.length;row++) {			//REVEALS COUNT GRID ONCE LOST
				for(int col=0;col<board[row].length;col++) {
					board[row][col].setText("" + grid.getCountAtLocation(row,col));
				}
			}	
			for(int row=0; row<board.length;row++) {			//REVEALS ALL BOMBS ONCE LOST
				for(int col=0;col<board[row].length;col++) {
					if(grid.isBombAtLocation(row,col) == true){
					board[row][col].setIcon(bombImgScaled);
					}
					}
			}
			int yesno =  JOptionPane.showConfirmDialog(null,"Do You Want To Play Again?","YOU LOST!", JOptionPane.YES_NO_OPTION); // ASK IF WANT TO PLAY AGAIn
			if(yesno == JOptionPane.YES_OPTION) { // IF WANTS TO PLAY AGAIN CLEAR BOARD
				for(int row=0; row<board.length;row++) {
					for(int col=0;col<board[row].length;col++) {
						board[row][col].setText("");
						board[row][col].setIcon(null);
						}
					}
				dispose();		// GET RID OF OLD INSTANCE OF THE GAME
				MineSweeperGui gui = new MineSweeperGui();		// CREATE NEW GAME
				
			}else {  // EXIT THE SYSTEM IF NO
				System.exit(EXIT_ON_CLOSE);
			}
			
		}
		
		public void displayBoard() {
			board = new JButton[numRows][numColumns];
			bombGrid = new boolean[numRows][numColumns];
			checkGameWon = new int[numRows][numColumns];
		
			
			for(int row=0; row<board.length;row++) {
				for(int col=0;col<board[row].length;col++) {	//CREATES BOMB GRID, TO KNOW IF USER HAS LOST
					bombGrid[row][col] = grid.getBombGrid()[row][col];			
					}
				}
			
			for(int row=0; row<board.length;row++) {
				for(int col=0;col<board[row].length;col++) {
					checkGameWon[row][col] = 0;		
					
					if(bombGrid[row][col]==true) {		//FILLS BOMBS IN CHECK GAME WON ARRAY 
						checkGameWon[row][col] = 1;		
					}
					
					}
				}
			
			for(int row=0; row<board.length;row++) {
				for(int col=0;col<board[row].length;col++) {
					board[row][col] =  new JButton();
					board[row][col].addActionListener(this);
					board[row][col].setEnabled(true);			//FILLS UP BOARD ARRAY WITH JBUTTONS
					board[row][col].setText("");
					this.add(board[row][col]);			// ADDS JUBTTON TO THE BOARD
					
					
				}
			}
			
			
		}
	
	} // Inner Class End
}// Outer Class End

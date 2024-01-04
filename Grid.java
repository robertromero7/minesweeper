package cmp168Project2;

public class Grid {
	
	private boolean bombGrid [][];
	private int countGrid [][];
	private int numRows;
	private int numColumns;
	private int numBombs;
	
	public Grid(){
		numRows = 10;
		numColumns = 10;
		numBombs = 25;
		createBombGrid();
		createCountGrid();
	}
	public Grid(int rows, int columns) {
		numRows = rows;
		numColumns = columns;
		numBombs = 25;
		createBombGrid();
		createCountGrid();
	}
	public Grid(int rows,int columns,int numBombs) {
		numRows = rows;
		numColumns = columns;
		this.numBombs = numBombs;
		createBombGrid();
		createCountGrid();
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
	public int getNumBombs() {
		return numBombs;
	}
	public boolean[][] getBombGrid() {
		boolean copyOfArray[][] = new boolean[numRows][numColumns];
		for (int i = 0; i < copyOfArray.length; i++){
	         for (int j = 0; j < bombGrid[i].length; j++){
	           copyOfArray[i][j] = bombGrid[i][j];
	         }
	      }
		return copyOfArray;
	}
	public int[][] getCountGrid(){
		
		int copyOfArray[][] = new int[numRows][numColumns];
		for (int i = 0; i < copyOfArray.length; i++){
	         for (int j = 0; j < countGrid[i].length; j++){
	           copyOfArray[i][j] = countGrid[i][j];
	         }
	      }
		return copyOfArray;
	}
	public boolean isBombAtLocation(int row, int column) {
		
		if(bombGrid[row][column] == true) {	
		return true;
		}else {
			return false;
		}
	}
	public int getCountAtLocation(int row, int column) {
		
		return countGrid[row][column];
	}
	private void createBombGrid() {
		int count  = 0;
		
		
		if( numRows > 0 && numColumns > 0 ) {
		bombGrid = new boolean[numRows][numColumns];
		while(count<numBombs) {
			for(int row=0; row<numRows; row++){
				for(int col=0; col<bombGrid[row].length; col++){
					
					int num = 0;
					for (int i = 0; i < 5; i++) {
			            num = (int)(Math.random() * 10) + 1;
			            
			       }
					if((num == 0 || num > 1) && bombGrid[row][col] == false) {
						bombGrid[row][col] = false;
					}
					if(num == 1 && count<numBombs && bombGrid[row][col] != true){
						bombGrid[row][col] = true;
						if(count==numBombs) {
							break;
						}
						count++;
						
					}
				}
		}
		
		}
		}
	}
	private void createCountGrid() {
		int count = 0 ;
		
		countGrid = new int[numRows][numColumns];
		for(int row=0; row<numRows; row++){
			for(int col=0; col<countGrid[row].length-1; col++){
				count=0;
				
				if(row==0 && col ==0) {
					for(int i = col; i <= col+1;i++) {
						if(bombGrid[row][i] == true) {
							count++;
						}
					}
					for(int i = col; i <= col+1;i++) {
						if(bombGrid[row+1][i] == true) {
							count++;
						}
					}
					countGrid[row][col] = count;
				}
				if(row==0 && col>=1 && col<countGrid[row].length-1) {
					for(int i = col-1; i <= col+1;i++) {
						if(bombGrid[row][i] == true) {
							count++;
						}
					}

					for(int i = col-1; i <= col+1;i++) {
						if(bombGrid[row+1][i] == true) {
							count++;
						}
					}
					countGrid[row][col] = count;
				}
				if(row==numRows-1 && col>=1 && col<countGrid[row].length-1) {
					for(int i = col-1; i <= col+1;i++) {
						if(bombGrid[row][i] == true) {
							count++;
						}
					}

					for(int i = col-1; i <= col+1;i++) {
						if(bombGrid[row-1][i] == true) {
							count++;
						}
					}
					countGrid[row][col] = count;
				}
				if(row==numRows-1 && col ==0) {
					for(int i = col; i <= col+1;i++) {
						if(bombGrid[row-1][i] == true) {
							count++;
						}
					}
					for(int i = col; i <= col+1;i++) {
						if(bombGrid[row][i] == true) {
							count++;
						}
					}
					countGrid[row][col] = count;
				}
				if(row>0 && row<numRows-1 && col==0) {
					
					count =0;
					for(int i = col; i <= col+1;i++) {
						if(bombGrid[row-1][i] == true) {
							count++;
						}
					}
					for(int i = col; i <= col+1;i++) {
						if(bombGrid[row][i] == true) {
							count++;
						}
					}
					for(int i = col; i <= col+1;i++) {
						if(bombGrid[row+1][i] == true) {
							count++;
						}
					}
					countGrid[row][col] = count;
				}
				
				if(row>0 && col > 0 && row < numRows-1 && col < numColumns) {
					count =0;
					for(int i = col-1; i <= col+1;i++) {
						if(bombGrid[row-1][i] == true) {
							count++;
						}
					}
					for(int i = col-1; i <= col+1;i++) {
						if(bombGrid[row][i] == true) {
							count++;
						}
					}
					for(int i = col-1; i <= col+1;i++) {
						if(bombGrid[row+1][i] == true) {
							count++;
						}
					}
				}
				countGrid[row][col] = count;
			}
		}
		for(int row=0; row<numRows; row++){
		for(int col=numColumns-1; col<numColumns; col++){
			
			if(row==0 && col == countGrid[row].length-1) {
				count =0;
				for(int i = col-1; i <= col;i++) {
					if(bombGrid[row][i] == true) {
						count++;
					}
				}
				for(int i = col-1; i <= col;i++) {
					if(bombGrid[row+1][i] == true) {
						count++;
					}
				}
				countGrid[row][col] = count;
			}
			if(row==numRows-1 && col == countGrid[row].length-1) {
				count = 0;
				for(int i = col-1; i <= col;i++) {
					if(bombGrid[row-1][i] == true) {
						count++;
					}
				}
				for(int i = col-1; i <= col;i++) {
					if(bombGrid[row][i] == true) {
						count++;
					}
				}
				countGrid[row][col] = count;
			}
		
		if(row>0 && row<numRows-1 && col==countGrid[row].length-1) {
			
			count =0;
			for(int i = col-1; i <= col;i++) {
				if(bombGrid[row-1][i] == true) {
					count++;
				}
			}
			for(int i = col-1; i <= col;i++) {
				if(bombGrid[row][i] == true) {
					count++;
				}
			}
			for(int i = col-1; i <= col;i++) {
				if(bombGrid[row+1][i] == true) {
					count++;
				}
			}
			countGrid[row][col] = count;
		}
	}
		
	}
	}
}

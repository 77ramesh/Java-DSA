package application;

public class SudokuSolver {

	static int[][] board = {
			{0, 4, 9, 0, 0, 3, 0, 0, 0},//i = 0, j = 0
			{0, 2, 0, 5, 4, 0, 3, 0, 0},
			{0, 0, 0, 8, 9, 0, 0, 4, 0},
			{9, 0, 5, 0, 2, 0, 0, 8, 1},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{4, 7, 0, 0, 8, 0, 9, 0, 6},
			{0, 8, 0, 0, 7, 2, 0, 0, 0},
			{0, 0, 6, 0, 5, 8, 0, 1, 0},
			{0, 5, 0, 1, 0, 0, 8, 6, 0}
	};
	
	public static void main(String[] args) {

		solve();
		
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void solve() {
		
		for(int x=0;x<board.length;x++) {
			for(int y=0;y<board.length;y++) {
				
				if(board[y][x] == 0) { //(0, 0)+
					for(int z=1;z<=9;z++) {
						if(possible(y, x, z))
							board[y][x] = z;
					}
				}
			}
		}
	}
	
	static boolean possible(int x, int y, int n) {
		
		for(int i=0;i<9;i++) {
			if(board[x][i] == n)
				return false;
		}
		
		for(int i=0;i<9;i++) {
			if(board[i][y] == n)
				return false;
		}
		
		int x1 = (x/3)*3, y1 = (y/3)*3;
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(board[x1+j][y1+i] == n)
					return false;
			}
		}
		return true;
	}

}

package sudoku;

public class SudokuSolve {

    public int[][] answer=new int[9][9];
    public int[][] solveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {//输入格式不正确
            return answer;
        }
        //有正确结果进行打印
        else if (solve(board, 0, 0)) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    answer[i][j]=board[i][j]-'0';
                    System.out.println(answer[i][j]);
                }
            }
        }
        else if (!solve(board, 0, 0)){
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    answer[i][j] = 0;
                    System.out.println(answer[i][j]);
                }
            }
        }
        return answer;
    }

    public boolean solve(char[][] board, int i, int j) {
        if (j == 9) {
            if (i == 8)
                return true;
            i++;
            j = 0;
        }//当一行已经匹配结束后匹配下一行
        if (board[i][j] != '.') {
            return solve(board, i, j + 1);
        }
        for (char k = '1'; k <= '9'; k++) {
            if (isValid(board, i, j, k)) {
                board[i][j] = k;//不存在这个数字，加入数字进行尝试
                if (solve(board, i, j + 1))//先对行中数字进行匹配
                    return true;//可以匹配，输出最终结果
                else
                    board[i][j] = '.';//不能匹配，回滚
            }
        }
        return false;
    }

    //检查行，列，方格中是否存在这个数字
    public boolean isValid(char[][] board, int row, int col, char c) {
        for (int k = 0; k < 9; k++) {
            if (board[row][k] != '.' && board[row][k] == c)
                return false;//检查一行
            if (board[k][col] != '.' && board[k][col] == c)
                return false;//检查一列
            if (board[row / 3 * 3 + k / 3][col / 3 * 3 + k % 3] != '.'
                    && board[row / 3 * 3 + k / 3][col / 3 * 3 + k % 3] == c)
                return false;//检查3*3小方格
        }
        return true;
    }

}

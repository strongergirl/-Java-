package sudoku;

import java.util.Random;

public class SudokuPuzzleGenerator {
    private Random random = new Random();//新设置random变量
    private static final int MAXTIME = 220;/**最大的次数*/
    private int currentTimes = 0;/**当前的次数*/
    public int[][] generatePuzzleMatrix() {/**生成随机数字的函数*/
        int[][] randomMatrix = new int[9][9];/**生成随机矩阵*/
        for (int row = 0; row < 9; row++) {
            if (row == 0) {
                currentTimes = 0;
                randomMatrix[row] = randomArray();//第一行可以随机生成
            } else {
                int[] tempArray = randomArray();
                for (int col = 0; col < 9; col++) {
                    if (currentTimes < MAXTIME) {
                        //找不到合适的数字就回滚从上一行重新查找
                        if (!isCandidateNmb(randomMatrix, tempArray, row, col)) {
                            rowZero(randomMatrix,row);
                            row -= 1;
                            col = 8;//直接跳出循环
                            tempArray = randomArray();
                        }
                    } else {//如果超出最大次数则重新开始
                        row = -1;
                        col = 8;
                        allZero(randomMatrix);
                        currentTimes = 0;
                    }
                }
            }
        }
        return randomMatrix;
    }

    //将一行赋值成0
    private void rowZero(int[][] matrix, int row)
    {
        for (int j = 0; j < 9; j++) {
            matrix[row][j] = 0;
        }
    }

    //将每一个数字都赋值成0
    private void allZero(int[][] matrix) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                matrix[row][col] = 0;
            }
        }
    }

    //判断是否可以找到候选的符合条件的数字
    private boolean isCandidateNmb(int[][] randomMatrix, int[] randomArray, int row, int col) {
        for (int i = 0; i < 9; i++) {
            randomMatrix[row][col] = randomArray[i];
            if (noConflict(randomMatrix, row, col)) {
                return true;
            }
        }
        return false;
    }

    //判断一行一列以及3*3格是否有冲突
    private boolean noConflict(int[][] candidateMatrix, int row, int col) {
        return noConflictInRow(candidateMatrix, row, col)&&noConflictInColumn(candidateMatrix, row, col) && noConflictInBlock(candidateMatrix, row, col);
    }

    //判断一行没有冲突
    private boolean noConflictInRow(int[][] candidateMatrix, int row, int col) {
        int currentValue = candidateMatrix[row][col];
        for (int colNum = 0; colNum < col; colNum++) {
            if (currentValue == candidateMatrix[row][colNum]) {
                return false;
            }
        }
        return true;
    }

    //判断一列没有冲突
    private boolean noConflictInColumn(int[][] candidateMatrix, int row, int col) {
        int currentValue = candidateMatrix[row][col];
        for (int rowNum = 0; rowNum < row; rowNum++) {
            if (currentValue == candidateMatrix[rowNum][col]) {
                return false;
            }
        }
        return true;
    }

    //判断3*3网格没有冲突
    private boolean noConflictInBlock(int[][] candidateMatrix, int row, int col) {
        int baseRow = row / 3 * 3;//判断属于哪一个网格
        int baseCol = col / 3 * 3;
        for (int rowNum = 0; rowNum < 8; rowNum++) {//获取网格中的数字
            if (candidateMatrix[baseRow + rowNum / 3][baseCol + rowNum % 3] == 0) {
                continue;
            }
            for (int colNum = rowNum + 1; colNum < 9; colNum++) {//进行循环，判断是否网格中有相同的数字
                if (candidateMatrix[baseRow + rowNum / 3][baseCol + rowNum % 3] == candidateMatrix[baseRow
                        + colNum / 3][baseCol + colNum % 3]) {
                    return false;
                }
            }
        }
        return true;
    }

    //返回一个0到9的一维随机数组
    private int[] randomArray() {
        currentTimes++;
        int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int randomInt = 0;
        for (int i = 0; i < 20; i++) {//将数组中数字交换20次，得到一个充分随机的数组
            /**public int nextInt(int n)
             该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n*/
            randomInt = random.nextInt(8) + 1;
            int temp = array[0];
            array[0] = array[randomInt];
            array[randomInt] = temp;//将生成的数字和第一个数字进行交换
        }
        return array;
    }

    public int getCurrentTimes() {
        return currentTimes;
    }

    public void setCurrentTimes(int currentTimes) {
        this.currentTimes = currentTimes;
    }
}

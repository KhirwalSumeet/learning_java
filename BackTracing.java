import java.io.*;
import java.util.*;
import java.util.LinkedList;

class KnightTour {
    int[][] possibleMoves;
    KnightTour() {
        possibleMoves = new int[8][8];
        possibleMoves[0][0] = 1;
        possibleMoves[1][0] = 1;
        possibleMoves[2][0] = -1;
        possibleMoves[3][0] = -1;
        possibleMoves[4][0] = 2;
        possibleMoves[5][0] = 2;
        possibleMoves[6][0] = -2;
        possibleMoves[7][0] = -2;
        possibleMoves[0][1] = 2;
        possibleMoves[1][1] = -2;
        possibleMoves[2][1] = 2;
        possibleMoves[3][1] = -2;
        possibleMoves[4][1] = 1;
        possibleMoves[5][1] = -1;
        possibleMoves[6][1] = 1;
        possibleMoves[7][1] = -1;
    }
    
    public boolean tour(int[][] moves, int moveCount, int currentX, int currentY) {
        if (moveCount == moves.length * moves.length)
            return true;
        int nextX, nextY;
        for (int[] next : possibleMoves) {
            nextX = currentX + next[0];
            nextY = currentY + next[1];
            if (nextX > 0 && nextX < moves.length && nextY > 0 && nextY < moves.length ) {
                if (moves[nextX][nextY] == -1) {
                    moves[currentX][currentY] = moveCount;
                    if (tour(moves, moveCount + 1, nextX, nextY) == true)
                        return true;
                    else 
                        moves[currentX][currentY] = -1;
                }
            }
        }
        return false;
    }
}


class ratMaze {
    int[][] possibleMoves, maze;
    int destX, destY;
    ratMaze(int[][] possible) {
        possibleMoves = new int[possible.length][2];
        for (int i = 0; i < possible.length; i++) {
            possibleMoves[i][0] = possible[i][0];
            possibleMoves[i][1] = possible[i][1];
        }
    }
    
    public void setMaze(int[][] newMaze, int[] dest) {
        maze = newMaze;
        destX = dest[0];
        destY = dest[1];
    }
    
    public boolean traverseMaze(int curX, int curY) {
        int nextX, nextY;
        if (curX == destX && curY == destY)
            return true;
        for (int[] nextMove : possibleMoves) {
            nextX = curX + nextMove[0];
            nextY = curY + nextMove[1];
            if (nextX > -1 && nextY > -1 && nextX < maze.length && nextY < maze[0].length) {
                if (maze[nextX][nextY] != 0 && maze[nextX][nextY] != 2) {
                    maze[nextX][nextY] = 2;
                    if (traverseMaze(nextX, nextY) == true)
                        return true;
                    else
                        maze[nextX][nextY] = 1;
                }
            }
        }
        return false;
    }
    
    public void printMaze() {
        for (int[] row : maze)
            System.out.println(Arrays.toString(row));
    }
    
    public void mazeSolution(int[][] newMaze, int[] src, int[] dest) {
        setMaze(newMaze, dest);
        maze[src[0]][src[1]] = 2;
        if (traverseMaze(src[0], src[1]))
            printMaze();
        else
            System.out.println("No routes found");
        
    }
}

class Nqueen {
    int[][] board;
    int size;
    int[][] moves;
    Nqueen(int s) {
        size = s;
        moves = new int[8][2];
        int[][] temp = moves;
        temp[0][0] = temp[2][1] = temp[4][0] = temp [6][1] = 0;
        temp[0][1] = temp[1][1] = temp[1][0] = temp[2][0] = temp[3][0] = temp[7][1] = 1;
        temp[3][1] = temp[4][1] = temp[5][1] = temp[6][0] = temp[5][0] = temp[7][0] = -1;
    }
    
    public void initialiseBoard() {
        board = new int[size][size];
    }
    
    public boolean canPlaceQueen(int x, int y) {
        if (board[x][y] == 1)
            return false;
        int nextX, nextY;
        for (int[] next : moves) {
            nextX = x + next[0];
            nextY = y + next[1];
            while (nextX > -1 && nextY > -1 && nextX < size && nextY < size) {
                if (board[nextX][nextY] == 1)
                    return false;
                nextX = nextX + next[0];
                nextY = nextY + next[1];
            }
        }
        return true;
    }
    
    public boolean placeAllQueens(int queenCount) {
        if (queenCount == size)
            return true;
        for (int i = 0; i < size; i++) {
            if (canPlaceQueen(queenCount, i) ) {
                board[queenCount][i] = 1;
                if (placeAllQueens(queenCount + 1)) 
                    return true;
                board[queenCount][i] = 0;
            }
        }
        return false;
    }
    
    public void printBoard() {
        for (int[] row : board)
            System.out.println(Arrays.toString(row));
    }
    
    public void nqueenSolution () {
        initialiseBoard();
        System.out.println("Are queens placed succcessfully ? " + placeAllQueens(0));
        printBoard();
        
    }
}

class SubsetSum {
    boolean[] visited;
    int[] set;
    int sum;
    
    SubsetSum(int[] s) {
        set = s;
        visited = new boolean[s.length];
        sum = 0;
    }
    
    public void findSubset(int newSum) {
        System.out.println("Do we have any subset with sum = " + newSum + " ? " + recursiveFind(sum, newSum));
        printSubset();
        
    }
    
    public void printSubset() {
        System.out.println("Subset :");
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == true)
                System.out.print(set[i]+" ");
            visited[i] = false;
        }
    }
    
    public boolean recursiveFind(int curSum, int netSum) {
        if (curSum == netSum)
            return true;
        else if (curSum > netSum)
            return false;
        
        for (int i = 0; i < set.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (recursiveFind(curSum + set[i], netSum) == true )
                    return true;
                else
                    visited[i] = false;
            }
        }
        return false;
    }
}

class BackTracing {
    
    public void ratmaze() {
        int[][] possibleMoves = new int[3][2];
        possibleMoves[0][0] = 1;
        possibleMoves[0][1] = 0;
        possibleMoves[1][0] = 0;
        possibleMoves[1][1] = 1;
        possibleMoves[2][0] = 0;
        possibleMoves[2][1] = -1;
        
        int[][] maze = new int[4][4];
        maze[0][0] = 1;
        maze[0][1] = 1;
        maze[1][1] = 1;
        maze[1][2] = 1;
        maze[1][3] = 1;
        maze[2][2] = 1;
        maze[3][2] = 1;
        maze[3][1] = 1;
        maze[3][3] = 1;
        
        // COnsidered downward direction as positive X and Rightward as positive Y
        int[] src = new int[]{0,0};
        int[] dest = new int[]{3,1};
        ratMaze rm = new ratMaze(possibleMoves);
        rm.mazeSolution(maze, src, dest);        
    }
    
    public void nQueen() {
        int size = 4;
        Nqueen nq = new Nqueen(size);
        nq.nqueenSolution();
        
    }
    
    public void findSubsetSum() {
        int[] set = new int[]{10, 15, 21, 5, 8, 6, 72};
        SubsetSum ss = new SubsetSum(set);
        ss.findSubset(35);
        
    }
    public static void main(String[] args) {
        System.out.println("Compile successfull");
        BackTracing bt = new BackTracing();
        bt.findSubsetSum();
    }
}

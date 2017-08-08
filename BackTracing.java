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

class BackTracing {
    
    public static void main(String[] args) {
        System.out.println("Compile successfull");
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
}

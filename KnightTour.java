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

class DP {
    
    public static void main(String[] args) {
        System.out.println("Compile successfull");
        
        KnightTour kt = new KnightTour();
        
        int[][] moves = new int[8][8];
        for (int[] row: moves)
            Arrays.fill(row, -1);
        int moveCount = 0;
        System.out.println(kt.tour(moves, moveCount, 0, 0));
        for (int[] row: moves)
            System.out.println(Arrays.toString(row));
    }
}

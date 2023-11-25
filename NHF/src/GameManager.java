import java.io.*;
import java.util.*;

public class GameManager {
    BoardBlock[][] board;
    int[] playerScore;
    private int whichPlayer;
    private int nextPlayer;
    private Player P1;
    private Player P2;
    GameManager(BoardBlock[][] board, int[] playerScore, Player P1, Player P2){
       this.board = board;
       this.playerScore = playerScore;
       whichPlayer = 0;
       nextPlayer = 1;
       this.P1 = P1;
       this.P2 = P2;
    }

    GameManager(File gameFile) {
        String wd = System.getProperty("user.dir");
        playerScore = new int[2];
        board = new BoardBlock[9][9];
        String filePath = wd + "/NHF/boards/" + gameFile.getName();

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            String[] playerData = line.split(";");
            playerScore[0] = Integer.parseInt(playerData[0]);
            playerScore[1] = Integer.parseInt(playerData[1]);
            P1 = new Player(playerData[0], 1);
            P2 = new Player(playerData[1], 2);

            int rowCounter = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] boardData = line.split(" ");
                for(int i = 0; i < 9; i++){
                    BoardBlock tmp = new BoardBlock(boardData[i]);
                    board[rowCounter][i] = tmp;
                }
                rowCounter++;
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        whichPlayer = 0;
        nextPlayer = 1;
    }

    public void save(String boardName, BoardBlock[][] saveBoard, int savePlayerScore[]) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(boardName + ".txt"));
            writer.write(savePlayerScore[0] + ";" + savePlayerScore[1]);
            writer.newLine();

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    writer.write(saveBoard[i][j].getState());
                    if (j < saveBoard[i].length - 1) {
                        writer.write(" ");
                    }
                }
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    public BoardBlock[][] getBoard(){
        return board;
    }

    public int[] getPlayerScore(){
        return playerScore;
    }

    public boolean StepPlayer(int x, int y){
        return board[x][y].getState().equals("E");
    }


    public boolean GameOver(Player temp){
        if(temp == P1){
            return P1.getX() == 8;
        }else if(temp == P2){
            return P2.getX() == 0;
        }
        return false;
    }

    //For debugging purposes
    public String findPlayer(Player temp){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j].getState().equals(temp.getName())){
                    return "(" + i + "," + j +")";
                }
            }
        }
        return "";
    }

    public int getNextPlayer() {
        return nextPlayer;
    }
    public int getWhichPlayer(){
        return whichPlayer;
    }
    public Player getPlayer(){
        return whichPlayer == 1 ? P1 : P2;
    }
    public Player getPlayer(int nextPlayer){
        return nextPlayer == 1 ? P1 : P2;
    }

    public void setNextPlayer(int nextPlayer){
        this.nextPlayer = nextPlayer;
    }

    public void setWhichPlayer(int whichPlayer){
        this.whichPlayer = whichPlayer;
    }

    public Player getP1(){
        return P1;
    }

    public Player getP2(){
        return P2;
    }

    public void PlayerPutBlockOnTheBoard(int player){
        if(player == 1){
            playerScore[0] -= 1;
        }else{
            playerScore[1] -= 1;
        }
    }

    public void PlayerPickBlockOffTheBoard(int player){
        if(player == 1){
            playerScore[0] += 2;
        }else{
            playerScore[1] += 2;
        }
    }

    // Nested Pair class to hold two BoardBlock objects
    public class Pair {
        BoardBlock block1;
        BoardBlock block2;

        public Pair(BoardBlock block1, BoardBlock block2) {
            this.block1 = block1;
            this.block2 = block2;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Pair pair = (Pair) obj;
            return block1.equals(pair.block1) && block2.equals(pair.block2);
        }

        @Override
        public int hashCode() {
            return 31 * block1.hashCode() + block2.hashCode();
        }
    }

    // Nested Cell class for A* algorithm
    private class Cell {
        Pair parent;
        double f, g, h;

        public Cell() {
            parent = null;
            f = g = h = Double.MAX_VALUE;
        }

        public Cell(Pair parent, double f, double g, double h) {
            this.parent = parent;
            this.f = f;
            this.g = g;
            this.h = h;
        }
    }

    // Method to check if a BoardBlock is traversable
    private boolean isTraversable(BoardBlock block) {
        return block.getState().equals("E"); // Assuming "E" means empty/traversable
    }

    // Method to check if a given row and column are within the board boundaries
    private boolean isValid(int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }

    // Method to calculate the heuristic value (H) for A*
    private double calculateHValue(int srcRow, int srcCol, int destRow, int destCol) {
        return Math.sqrt(Math.pow((srcRow - destRow), 2) + Math.pow((srcCol - destCol), 2));
    }

    // Method to check if path exists for a player
    public boolean pathExists(Player player) {
        int srcRow = player.getX();
        int srcCol = player.getY();
        int destRow = this.getWhichPlayer() == 1 ? 8 : 0;

        return aStarSearch(srcRow, srcCol, destRow, srcCol);
    }

    // A* Search algorithm
    private boolean aStarSearch(int srcRow, int srcCol, int destRow, int destCol) {
        boolean[][] closedList = new boolean[board.length][board[0].length];
        Cell[][] cellDetails = new Cell[board.length][board[0].length];

        // Initialize starting cell
        cellDetails[srcRow][srcCol] = new Cell(new Pair(board[srcRow][srcCol], null), 0.0, 0.0, 0.0);

        PriorityQueue<Cell> openList = new PriorityQueue<>((cell1, cell2) -> Double.compare(cell1.f, cell2.f));
        openList.add(cellDetails[srcRow][srcCol]);

        while (!openList.isEmpty()) {
            Cell current = openList.poll();
            int i = current.parent.block1.getRow(); // Assuming BoardBlock has getRow() method
            int j = current.parent.block1.getColumn(); // Assuming BoardBlock has getColumn() method

            closedList[i][j] = true;

            // Check all 8 successors of current cell
            for (int addX = -1; addX <= 1; addX++) {
                for (int addY = -1; addY <= 1; addY++) {
                    int newRow = i + addX, newCol = j + addY;

                    if (isValid(newRow, newCol)) {
                        BoardBlock neighborBlock = board[newRow][newCol];

                        if (newRow == destRow && newCol == destCol) {
                            return true; // Destination found
                        }

                        if (!closedList[newRow][newCol] && isTraversable(neighborBlock)) {
                            double gNew = current.g + 1.0;
                            double hNew = calculateHValue(newRow, newCol, destRow, destCol);
                            double fNew = gNew + hNew;

                            if (cellDetails[newRow][newCol] == null || cellDetails[newRow][newCol].f > fNew) {
                                cellDetails[newRow][newCol] = new Cell(new Pair(neighborBlock, current.parent.block1), fNew, gNew, hNew);
                                openList.add(cellDetails[newRow][newCol]);
                            }
                        }
                    }
                }
            }
        }

        return false; // Path not found
    }

}

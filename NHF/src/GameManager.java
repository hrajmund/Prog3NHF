import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

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
        String filePath = wd + "/boards/" + gameFile.getName();

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            String[] playerData = line.split(";");
            playerScore[0] = Integer.parseInt(playerData[0]);
            playerScore[1] = Integer.parseInt(playerData[1]);
            P1 = new Player(playerData[2], 1);
            P2 = new Player(playerData[3], 2);

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

    public boolean canBlockBeChanged(int x, int y, String newState, int playerMakingMove, Player player1, Player player2) {
        // Save the original state to revert back after the check
        String originalState = board[x][y].getState();
        board[x][y].setState(newState);

        boolean pathExists;
        if (newState.equals("B")) {
            // Call isPathToRow with the player who is not making the move
            pathExists = isPathToRow(playerMakingMove, player1, player2);
        } else {
            pathExists = true;
        }

        // Revert the state
        board[x][y].setState(originalState);

        return pathExists;
    }

    public boolean isPathToRow(int playerMakingMove, Player player1, Player player2) {
        int targetRow = playerMakingMove == 1 ? 1 : 9; // Player 1 targets row 9, Player 2 targets row 1
        Player currentPlayer = playerMakingMove == 1 ? player2 : player1; // The opposing player

        boolean[][] visited = new boolean[board.length][board[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{currentPlayer.getX(), currentPlayer.getY()});

        while (!queue.isEmpty()) {
            int[] position = queue.remove();
            int x = position[0];
            int y = position[1];

            if (y == targetRow) {
                return true;
            }

            visited[x][y] = true;

            // Check adjacent positions
            if (x > 0 && board[x - 1][y].getState().equals("E") && !visited[x - 1][y]) {
                queue.add(new int[]{x - 1, y});
            }
            if (x < board.length - 1 && board[x + 1][y].getState().equals("E") && !visited[x + 1][y]) {
                queue.add(new int[]{x + 1, y});
            }
            if (y > 0 && board[x][y - 1].getState().equals("E") && !visited[x][y - 1]) {
                queue.add(new int[]{x, y - 1});
            }
            if (y < board[0].length - 1 && board[x][y + 1].getState().equals("E") && !visited[x][y + 1]) {
                queue.add(new int[]{x, y + 1});
            }
        }

        return false;
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
}

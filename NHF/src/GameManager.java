import java.io.*;

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

    public void P1PutBlockOnTheBoard(){
        playerScore[0] -= 1;
    }
    public void P2PutBlockOnTheBoard(){
        playerScore[1] -= 1;
    }

    public void P1PickBlockOffTheBoard(){
        playerScore[0] += 1;
    }
    public void P2PickBlockOffTheBoard(){
        playerScore[1] += 1;
    }
}

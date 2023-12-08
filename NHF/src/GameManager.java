import java.io.*;
import java.util.*;

public class GameManager {
    BoardBlock[][] board;
    int[] playerScore;
    private int whichPlayer;
    private int nextPlayer;
    private Player P1;
    private Player P2;

    GameManager(BoardBlock[][] board, int[] playerScore, Player P1, Player P2) {
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
            P1 = new Player(playerData[2], 1);
            P2 = new Player(playerData[3], 2);

            int rowCounter = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] boardData = line.split(" ");
                for (int i = 0; i < 9; i++) {
                    BoardBlock tmp = new BoardBlock(boardData[i]);
                    board[rowCounter][i] = tmp;
                }
                rowCounter++;
            }

            bufferedReader.close();
        } catch (IOException e) {
            IllegalMoveWindow illegalMoveWindow = new IllegalMoveWindow(4);
        }
        whichPlayer = 0;
        nextPlayer = 1;
        //System.out.println(findPath(board,P1,P2, 4,3,4,4));
    }

    public void save(String boardName, BoardBlock[][] saveBoard, int savePlayerScore[]) {
        try {
            String wd = System.getProperty("user.dir");
            BufferedWriter writer = new BufferedWriter(new FileWriter(wd + "/NHF/boards/" + boardName + ".txt"));
            writer.write(savePlayerScore[0] + ";" + savePlayerScore[1] + ";" + P1.getName() + ";" + P2.getName());
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
            System.exit(0);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public BoardBlock[][] getBoard() {
        return board;
    }

    public int[] getPlayerScore() {
        return playerScore;
    }

    public int getNextPlayer() {
        return nextPlayer;
    }

    public int getWhichPlayer() {
        return whichPlayer;
    }

    public Player getPlayer() {
        return whichPlayer == 1 ? P1 : P2;
    }

    public Player getPlayer(int nextPlayer) {
        return nextPlayer == 1 ? P1 : P2;
    }

    public void setNextPlayer(int nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public void setWhichPlayer(int whichPlayer) {
        this.whichPlayer = whichPlayer;
    }

    public Player getP1() {
        return P1;
    }

    public Player getP2() {
        return P2;
    }
    public boolean StepPlayer(int x, int y) {
        if(x == -1 || y == -1 || x == 9 || y == 9){
            return false;
        }
        return board[x][y].getState().equals("E");
    }

    public boolean GameOver(Player temp) {
        if (temp == P1) {
            return P1.getX() == 8;
        } else if (temp == P2) {
            return P2.getX() == 0;
        }
        return false;
    }

    //For debugging purposes
    public String findPlayer(Player temp) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j].getState().equals(temp.getName())) {
                    return "(" + i + "," + j + ")";
                }
            }
        }
        return "";
    }

    public void PlayerPutBlockOnTheBoard(int player) {
        if (player == 1) {
            playerScore[0] -= 1;
        } else {
            playerScore[1] -= 1;
        }
    }

    public void PlayerPickBlockOffTheBoard(int player) {
        if (player == 1) {
            playerScore[0] += 2;
        } else {
            playerScore[1] += 2;
        }
    }

    class Graph {

        private Map<BoardBlock, Map<BoardBlock, Integer>> graphMap;
        private BoardBlock[][] copyOfBoard;

        public Graph(BoardBlock[][] boardBlocks){
            copyOfBoard = new BoardBlock[9][9];

            for(int i = 0; i < 9; i++)
                for (int j = 0; j < 9; j++)
                    copyOfBoard[i][j] = boardBlocks[i][j];


            graphMap = new HashMap<>();
            for (int i = 0; i < boardBlocks.length; i++){
                for(int j = 0; j < boardBlocks[i].length; j++){
                    BoardBlock current = boardBlocks[i][j];
                    Map<BoardBlock, Integer> neighbors = new HashMap<>();
                    if(i>0) neighbors.put(boardBlocks[i-1][j],1);
                    if(i < boardBlocks.length-1) neighbors.put(boardBlocks[i+1][j], 1);
                    if(j > 0) neighbors.put(boardBlocks[i][j-1], 1);
                    if(j < boardBlocks.length-1) neighbors.put(boardBlocks[i][j+1],1);

                    graphMap.put(current, neighbors);
                }
            }
        }

        public Map<BoardBlock, Integer> getNeighbors(BoardBlock block){
            return graphMap.getOrDefault(block, new HashMap<>());
        }

        public List<BoardBlock> dijkstra(Graph g, int startingPointX, int startingPointY, int targetRow){
            Map<BoardBlock, Integer> distances = new HashMap<>();
            Map<BoardBlock, BoardBlock> predecessors = new HashMap<>();
            PriorityQueue<BoardBlock> queue = new PriorityQueue<>(Comparator.comparing(distances::get));

            for(BoardBlock block : graphMap.keySet()){
                if(g.graphMap.containsKey(block) && (block.getRow() == startingPointX && block.getColumn() == startingPointY)){
                    distances.put(block,0);
                }else{
                    distances.put(block, Integer.MAX_VALUE);
                }
            }

            queue.add(g.copyOfBoard[startingPointX][startingPointY]);

            while(!queue.isEmpty()){
                BoardBlock currentBlock = queue.poll();
                int currentDistance = distances.get(currentBlock);

                if(currentBlock.getRow() == targetRow){
                    break;
                }

                for(Map.Entry<BoardBlock, Integer> neigborEntry : g.getNeighbors(currentBlock).entrySet()){
                    BoardBlock neighbor = neigborEntry.getKey();
                    int weight = neigborEntry.getValue();
                    int distanceThroughCurrent = currentDistance + weight;
                    if(distanceThroughCurrent <= distances.get(neighbor)){
                        distances.put(neighbor, distanceThroughCurrent);
                        predecessors.put(neighbor, currentBlock);
                        queue.add(neighbor);
                    }
                }
            }

            LinkedList<BoardBlock> path = new LinkedList<>();
            BoardBlock current = null;


            for(int i = 0; i < 9; i++){
                if(predecessors.containsKey(g.copyOfBoard[targetRow][i]) && g.copyOfBoard[targetRow][i].getState().equals("E")){
                    current = g.copyOfBoard[targetRow][i];
                    break;
                }
            }

            while(current != null){
                path.addFirst(current);
                current = predecessors.get(current);
            }

            List<BoardBlock> optimizedPath = new ArrayList<>();
            if(!path.isEmpty()){
                optimizedPath.add(path.get(0));
                for(int j = 1; j < path.size()-1;j++){
                    BoardBlock previous = path.get(j-1);
                    BoardBlock currentBlock = path.get(j);
                    BoardBlock next = path.get(j+1);

                    if(previous.getRow() != currentBlock.getRow() || currentBlock.getRow() != next.getRow()){
                        optimizedPath.add(currentBlock);
                    }
                }
                optimizedPath.add(path.get(path.size() - 1));
            }
            return optimizedPath;
        }
    }

    public boolean findPath(BoardBlock[][] boardBlocks, Player one, Player two, int firstChangedBlockX, int firstChangedBlockY, int secondChangedBlockX, int secondChangedBlockY){
        BoardBlock[][] tmp = new BoardBlock[9][9];
        for(int i = 0; i < 9; i++){
            for(int k = 0; k < 9; k++){
                tmp[i][k] = boardBlocks[i][k];
            }
        }
        tmp[firstChangedBlockX][firstChangedBlockY].setState("B");
        tmp[secondChangedBlockX][secondChangedBlockY].setState("B");
        Graph graph = new Graph(tmp);
        List<BoardBlock> pathforP1 = graph.dijkstra(graph, one.getX(), one.getY(), 8);
        List<BoardBlock> pathforP2 = graph.dijkstra(graph, two.getX(), two.getY(), 0);
        if(pathforP1.isEmpty() && pathforP2.isEmpty()){
            tmp[firstChangedBlockX][firstChangedBlockY].setState("E");
            tmp[secondChangedBlockX][secondChangedBlockY].setState("E");
            return false;
        }
        return true;
    }
}
public class Player {
    private String name;
    private int numberOfThePlayer;
    private int x, y;
    Player(String name, int x, int y, int numberOfThePlayer){
        this.name = name;
        this.numberOfThePlayer = numberOfThePlayer;
        this.x = x;
        this.y = y;
    }

    public String getLocation(){
        return x + ";" + y;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    /*public void Step(){
        if(numberOfThePlayer == 1){
            if()
        }
    }*/
}

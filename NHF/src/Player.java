public class Player {
    private String name;
    private int numberOfThePlayer;
    private int x, y;
    Player(String name, int numberOfThePlayer){
        this.name = name;
        this.numberOfThePlayer = numberOfThePlayer;
        x = y = 0;
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

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void moveLeft(){
        y -= 1;
    }
    public void moveRight(){
        y += 1;
    }
    public void moveUp(){
        x += 1;
    }
    public void moveDown(){
        x -= 1;
    }
}

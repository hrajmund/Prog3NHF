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
        System.out.println(this.getY());
        y -= 1;
        System.out.println(this.getY());
    }
    public void moveRight()
    {
        x--;
        y++;
    }
    public void moveUp(int whichPlayer){
        x--;
        y--;
    }
    public void moveDown(int whichPlayer){
        switch (whichPlayer){
            case 1: {
                if(this.y != 0){
                    x++;
                    y++;
                }
                break;
            }
            case 2: {
                if(this.y != 8){
                    x++;
                    y++;
                }
                break;
            }
        }
        /*
        System.out.println(this.getX());
        x -= 1;
        System.out.println(this.getX());*/
    }
}

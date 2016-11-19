import java.awt.*;
import objectdraw.*;

public class Coordinate extends ActiveObject {
  private static final int TILE_SIZE = 50;
  private int y;
  private int x;

  public Coordinate (int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int x(){return x;}
  public int y(){return y;}
  public void setX(int x){this.x = x;}
  public void setY(int y){this.y = y;}
  public Location actualLocation(){return new Location(x * TILE_SIZE, y * TILE_SIZE);}
  public int actualX(){return x * TILE_SIZE;}
  public int actualY(){return y * TILE_SIZE;}


} //end of File

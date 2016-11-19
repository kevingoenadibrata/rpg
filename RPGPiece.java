import java.awt.*;
import objectdraw.*;

public class RPGPiece extends ActiveObject {

  //constants
  private static final int SIZE = 50;
  private Location loc;
  private VisibleImage piece;
  private int[] locID;
  private boolean[][] locBool;
  private RPGWorld world;

  public RPGPiece ( Location paramLoc, Image image, DrawingCanvas canvas, RPGWorld world) {
    loc = paramLoc;
    piece = new VisibleImage(image, loc, canvas);
    this.world = world;
    locID = new int[2];
    locID[0] = (int)loc.getX()/SIZE;
    locID[1] = (int)loc.getY()/SIZE;
    world.addObject(locID[0], locID[1]);
  }

  public int getID(int i){
    if(i == 0){return locID[0];}
    else if(i == 1){return locID[1];}
    else{return 0;}
  }
} //end of File

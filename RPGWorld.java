import java.awt.*;
import objectdraw.*;

public class RPGWorld extends ActiveObject {

  private boolean[][] accessBool, objectBool;
  private VisibleImage bg;

  public RPGWorld (Image img, DrawingCanvas canvas) {
    bg = new VisibleImage(img, 0, 0, canvas);

    accessBool = new boolean[16][11];
    objectBool = new boolean[16][11];

    initializeBool(accessBool, true);
    initializeBool(objectBool, false);
  }

  public void initializeBool(boolean[][] bool, boolean value){
    for(int i = 0; i < bool.length; i++){
      for(int j = 0; j < bool[0].length; j++){
        bool[i][j] = value;
      }
    }
  }

  public void printBool(boolean[][] bool){
    System.out.println();
    for(int i = 0; i < bool[0].length; i++){
      for(int j = 0; j < bool.length; j++){
        if(bool[j][i])
          System.out.print("O");
        else
          System.out.print("X");
      }
      System.out.println();
    }
  }

  public boolean isAccessable(int col, int row){
    if(col < 0 || col > 15 || row < 0 || row > 10){return false;}
    else {return accessBool[col][row];}
  }
  public boolean isObject(int col, int row){
    if(col < 0 || col > 15 || row < 0 || row > 10){return false;}
    else {return objectBool[col][row];}
  }

  public void addObject(int col, int row){accessBool[col][row] = false; objectBool[col][row] = true;}
  public void removeObject(int col, int row){accessBool[col][row] = true; objectBool[col][row] = false;}
}

import java.awt.*;
import objectdraw.*;
import java.io.*;
import java.util.Scanner;

public class RPGWorld extends ActiveObject {

  private boolean[][] accessBool, objectBool;
  private VisibleImage bg;

  public RPGWorld (Image img, String blockLoc, DrawingCanvas canvas) {
    bg = new VisibleImage(img, 0, 0, canvas);

    accessBool = new boolean[16][11];
    objectBool = new boolean[16][11];

    initializeBool(accessBool, true);
    initializeBool(objectBool, false);
    readAccess(blockLoc);
    printBool(accessBool);
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

  public void readAccess(String blockLoc){
    try (Scanner scanner = new Scanner(new File("blocked/" + blockLoc))) {
        while (scanner.hasNext()) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            accessBool[x][y] = false;
        }
    }
    catch(IOException e){
      System.out.println("Error with File IO.");
      System.exit(1);
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
  public void addSecretObject(int col, int row){objectBool[col][row] = true;}
  public void removeObject(int col, int row){accessBool[col][row] = true; objectBool[col][row] = false;}
}

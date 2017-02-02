import java.awt.*;
import objectdraw.*;
import java.io.*;
import java.util.Scanner;

public class RPGWorld extends ActiveObject {

  private boolean[][] accessBool, objectBool;
  private VisibleImage bg;
  private int horFactor, verFactor, howMuch;
  private RPGController controller;
  private boolean running = false;

  public RPGWorld (Image img, String blockLoc, DrawingCanvas canvas, RPGController controller) {
    bg = new VisibleImage(img, 0, 0, canvas);
    this.controller = controller;

    accessBool = new boolean[16][11];
    objectBool = new boolean[16][11];

    initializeBool(accessBool, true);
    initializeBool(objectBool, false);
    readAccess(blockLoc);
    // printBool(accessBool);
    start();
  }

  public void loadWorld(){

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

  //dev only
  public void unblockWorld(){
    for(int i = 0; i < 16; i++){
      for(int j = 0; j < 11; j++){
        accessBool[i][j] = true;
      }
    }
  }
  //
  public void moveMap(String dir){
    horFactor = 0;
    verFactor = 0;
    howMuch = 0;

    if(dir == "UP"){
      verFactor = 1;
      howMuch = 11*50;
      controller.setcharID(1, 10);
    }
    else if(dir == "DOWN"){
      verFactor = -1;
      howMuch = 11*50;
      controller.setcharID(1, 0);
    }
    else if(dir == "LEFT"){
      horFactor = 1;
      howMuch = 16*50;
      controller.setcharID(0, 15);
    }
    else if(dir == "RIGHT"){
      horFactor = -1;
      howMuch = 16*50;
      controller.setcharID(0, 0);
    }
    running = true;
  }

  public void run(){
    while(true){
      while(running){
        for(int i = 0; i < howMuch; i++){
          controller.setMode("moving");
          bg.move(horFactor, verFactor);
          controller.moveObj(horFactor, verFactor);
          pause(2);
        }
        controller.moveObj(horFactor * -50, verFactor * -50);
        controller.setMode("walking");
        horFactor = 0;
        verFactor = 0;
        howMuch = 0;
        running = false;
      }
      pause(1);
    }
  }

}

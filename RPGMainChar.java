import java.awt.*;
import objectdraw.*;

public class RPGMainChar extends ActiveObject {

  //constants
  private static final int SIZE = 50;
  private Location loc;
  private Color color;
  private VisibleImage piece;
  private int[] locID;
  private boolean[][] locBool;
  private RPGWorld world;
  private RPGText textbox;
  private RPGController controller;
  private int dx, dy;
  private boolean talk = false;
  private Image[] sprites;
  private String facing = new String("DOWN");

  public RPGMainChar ( Location paramLoc, Image[] paramSprites, DrawingCanvas canvas, RPGWorld world, RPGText textbox, RPGController controller) {
    loc = paramLoc;
    sprites = paramSprites;
    this.world = world;
    this.textbox = textbox;
    this.controller = controller;

    piece = new VisibleImage(sprites[1], loc, canvas);

    locID = new int[2];
    locID[0] = (int)loc.getX()/SIZE;
    locID[1] = (int)loc.getY()/SIZE;

  }

  public void changeFacing(String direction){facing = direction;}
  public void printFacing(){System.out.println(facing);}
  public void isMainChar(){world.removeObject(locID[0], locID[1]);}

  public void interact(){
    int col = 0;
    int row = 0;
    if(facing == "UP"){col = locID[0]; row = locID[1] -1;}
    if(facing == "DOWN"){col = locID[0]; row = locID[1] +1;}
    if(facing == "LEFT"){col = locID[0]-1; row = locID[1];}
    if(facing == "RIGHT"){col = locID[0]+1; row = locID[1];}
    if(world.isObject(col, row)){
      for(int i = 0; i < controller.pieces.size(); i++){
        int pieceCol = controller.pieces.get(i).getID(0);
        int pieceRow = controller.pieces.get(i).getID(1);
        if(pieceCol == col && pieceRow == row){
          textbox.addText(controller.pieces.get(i).getPieceMessage());
        }
      }
      // textbox.addText();
      talk = true;
      controller.setTalk(talk);
    }
  }
  public void continueText(){
    textbox.removeText();
    talk = false;
    controller.setTalk(talk);
  }
  public void up(){
  piece.setImage(sprites[0]);
    if(world.isAccessable(locID[0], locID[1]-1)){
      piece.move(0,-SIZE);
      locID[1]--;
    }
  }
  public void down(){
  piece.setImage(sprites[1]);
    if(world.isAccessable(locID[0], locID[1]+1)){
      piece.move(0,SIZE);
      locID[1]++;
    }
  }
  public void left(){
    piece.setImage(sprites[2]);
    if(world.isAccessable(locID[0]-1, locID[1])){
      piece.move(-SIZE,0);
      locID[0]--;
    }
  }
  public void right(){
    piece.setImage(sprites[3]);
    if(world.isAccessable(locID[0]+1, locID[1])){
      piece.move(SIZE,0);
      locID[0]++;
    }
  }
  public int getID(int i){
    if(i == 0){return locID[0];}
    else if(i == 1){return locID[1];}
    else{return 0;}
  }
} //end of File

import java.awt.*;
import objectdraw.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class RPGController extends WindowController implements ActionListener, KeyListener{
  //constants
  private static final int CANVAS_WIDTH = 800;
  private static final int CANVAS_HEIGHT = 600;
  private static final Color blue = new Color(53, 135, 176);

  //variables
  private RPGPiece potion;
  private RPGMainChar mainChar;
  private RPGWorld world1;
  private JButton getid, exit;
  private ArrayList<Coordinate> blockedWorld1;
  private Image[] mainCharSprites;

  //main
  public static void main( String[] args ) {
    new Acme.MainFrame(new RPGController(), args, CANVAS_WIDTH,
      CANVAS_HEIGHT);
  }




  //methods
  public void begin(){
    GUISetup();
    getSprites();

    world1 = new RPGWorld( getImage("images/map1.png"), canvas);
    mainChar = new RPGMainChar(grid(9,6), mainCharSprites, canvas, world1);
    potion = new RPGPiece(grid(8,10), getImage("images/potion.png"), canvas, world1);

    blockedWorld1 = new ArrayList<>();


    this.requestFocus();
    this.requestFocusInWindow();
  }






  public void actionPerformed(ActionEvent evt) {
    if(evt.getSource() == getid){
      getID(mainChar);
    }
    else if(evt.getSource() == exit){
      System.exit(1);
    }
  }

  public void keyPressed(KeyEvent e){
    int keyCode = e.getKeyCode();
    if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W){
      mainChar.up();
      mainChar.changeFacing("UP");
    }
    else if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S){
      mainChar.down();
      mainChar.changeFacing("DOWN");
    }
    else if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A){
      mainChar.left();
      mainChar.changeFacing("LEFT");
    }
    else if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D){
      mainChar.right();
      mainChar.changeFacing("RIGHT");
    }
    else if(keyCode == KeyEvent.VK_SPACE){
      mainChar.interact();
    }
    this.requestFocus();
    this.requestFocusInWindow();

  }
  public void keyReleased(KeyEvent e){}
  public void keyTyped(KeyEvent e){}

  public void GUISetup(){
    JPanel southPanel = new JPanel();
    getid = new JButton("GET ID");
    exit = new JButton("EXIT GAME");
    getid.addActionListener(this);
    exit.addActionListener(this);
    this.addKeyListener(this);
    canvas.addKeyListener(this);
    southPanel.add(getid);
    southPanel.add(exit);
    this.add(southPanel, BorderLayout.SOUTH);
  }

  public Location grid(int col, int row){
    Location position = new Location(50*(col-1), 50*(row-1));
    return position;
  }

  public void getID(RPGMainChar piece){
    System.out.println("ID: " + piece.getID(0) + ", " + piece.getID(1));
  }

  public void getSprites(){
    mainCharSprites = new Image[4];
    mainCharSprites[0] = getImage("images/snorlax-up.png");
    mainCharSprites[1] = getImage("images/snorlax-down.png");
    mainCharSprites[2] = getImage("images/snorlax-left.png");
    mainCharSprites[3] = getImage("images/snorlax-right.png");
  }
} //addded something

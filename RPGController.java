import java.awt.*;
import objectdraw.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

public class RPGController extends WindowController implements ActionListener, KeyListener{
  //constants
  private static final int CANVAS_WIDTH = 1050;
  private static final int CANVAS_HEIGHT = 750;
  private static final int TILE = 50;
  private static final Color blue = new Color(53, 135, 176);

  //variables
  private RPGMainChar mainChar;
  private RPGWorld world1;
  private RPGText textbox;
  private VisibleImage sidebar;
  private JButton getid, exit;
  private JLabel id;
  private Image[] mainCharSprites;
  private Font f;
  private String mode = "walking";
  private Boolean talking = false;
  public ArrayList<RPGPiece> pieces = new ArrayList<RPGPiece>();

  //main
  public static void main( String[] args ) {
    new Acme.MainFrame(new RPGController(), args, CANVAS_WIDTH,
      CANVAS_HEIGHT);
  }


  //methods
  public void begin(){
    GUISetup();
    ((JDrawingCanvas) canvas).setBackground(new Color(0, 127, 123));
    importElements();
    this.requestFocus();
    this.requestFocusInWindow();
  }


  public void actionPerformed(ActionEvent evt) {
    if(evt.getSource() == exit){
      System.exit(1);
    }
  }

  public void keyPressed(KeyEvent e){
    int keyCode = e.getKeyCode();
    if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W){
      if(mode.equals("walking")){
        mainChar.up();
        mainChar.changeFacing("UP");
        getID(mainChar);
      }
    }
    else if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S){
      if(mode.equals("walking")){
        mainChar.down();
        mainChar.changeFacing("DOWN");
        getID(mainChar);
      }
    }
    else if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A){
      if(mode.equals("walking")){
        mainChar.left();
        mainChar.changeFacing("LEFT");
        getID(mainChar);
      }
    }
    else if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D){
      if(mode.equals("walking")){
        mainChar.right();
        mainChar.changeFacing("RIGHT");
        getID(mainChar);
      }
    }
    else if(keyCode == KeyEvent.VK_SPACE){
      if(mode.equals("walking")){
        mainChar.interact();
      }
      else if(mode.equals("talking")){
        mainChar.continueText();
        mode = "walking";
      }
    }
    else if(keyCode == KeyEvent.VK_Z){
      //develop purposes
      world1.unblockWorld();
    }
    this.requestFocus();
    this.requestFocusInWindow();

  }
  public void keyReleased(KeyEvent e){}
  public void keyTyped(KeyEvent e){}

  public void GUISetup(){
    JPanel southPanel = new JPanel();
    exit = new JButton("EXIT GAME");
    id = new JLabel("( 0 , 0 )");
    exit.addActionListener(this);
    this.addKeyListener(this);
    canvas.addKeyListener(this);
    southPanel.add(id);
    southPanel.add(exit);
    this.add(southPanel, BorderLayout.SOUTH);
  }

  public Location grid(int col, int row){
    Location position = new Location(TILE*(col), TILE*(row));
    return position;
  }

  public void getID(RPGMainChar piece){
    id.setText("( " + piece.getID(0) + " , " + piece.getID(1) + " )");
    this.requestFocus();
    this.requestFocusInWindow();
  }

  public void getSprites(){
    mainCharSprites = new Image[4];
    mainCharSprites[0] = getImage("images/snorlax-up.png");
    mainCharSprites[1] = getImage("images/snorlax-down.png");
    mainCharSprites[2] = getImage("images/snorlax-left.png");
    mainCharSprites[3] = getImage("images/snorlax-right.png");
  }

  public void setMode(String mode){
    this.mode = mode;
  }

  public String getMode(){
    return this.mode;
  }

  public void moveObj(int dx, int dy){
    mainChar.moveObj(dx,dy);
  }
  public void setcharID(int i, int val){
    mainChar.setID(i,val);
  }


  public void importElements(){
    try {f = Font.createFont( Font.TRUETYPE_FONT, new FileInputStream("assets/font.ttf"));}
    catch(IOException|FontFormatException e){
      System.out.println("Error with File IO.");
      System.exit(1);
    }

    getSprites();
    // world1 = new RPGWorld( getImage("images/map1.png"), "blockedWorld1.txt", canvas, this);
    world1 = new RPGWorld( getImage("images/mapTest.png"), "blockedWorld1.txt", canvas, this);
    textbox = new RPGText(f, getImage("images/textbox.png"), getImage("images/contIcon.gif"), canvas);
    mainChar = new RPGMainChar(grid(8,5), mainCharSprites, canvas, world1, textbox, this);
    sidebar = new VisibleImage(getImage("images/sidebar.png"), grid(16,0), canvas);

    pieces.add(new RPGPiece("potion", "This is a potion", grid(7,7), getImage("images/potion.png"), canvas, world1));
    pieces.add(new RPGPiece("potion", "text", grid(8,9), getImage("images/potion.png"), canvas, world1));
  }
}

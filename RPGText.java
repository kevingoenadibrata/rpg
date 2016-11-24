import java.awt.*;
import objectdraw.*;
import java.io.*;
import java.util.Scanner;

public class RPGText extends ActiveObject {

  private boolean[][] accessBool, objectBool;
  private Location loc = new Location(0, 550);
  private Text text;
  private VisibleImage textbox, contIcon;
  private Image contImage;
  private DrawingCanvas canvas;
  private Font f;

  public RPGText (Font f, Image image, Image contImage, DrawingCanvas canvas) {
    this.f = f;
    this.canvas = canvas;
    this.contImage = contImage;
    textbox = new VisibleImage(image, loc, canvas);
  }

  public void addText(String message){
    text = new Text(message, loc.getX() + 25, loc.getY() + 25, canvas);
    text.setFont(f);
    text.setFontSize(40);
    text.setColor(new Color(49, 56, 47));
    contIcon = new VisibleImage(contImage ,loc.getX() + 15 * 50, loc.getY() + 2 * 50, canvas);

  }
  public void removeText(){
    text.removeFromCanvas();
    contIcon.removeFromCanvas();
  }//text


}

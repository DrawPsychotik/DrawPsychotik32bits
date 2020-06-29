import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import controlP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class DrawPsychotik extends PApplet {

int minR =100, maxR=255, minG=100, maxG=200, minB=50, maxB=200;
float Stroke= 0 ;
boolean saveFrame = false; 

public void settings() {
  size(700, 700);
}
public void setup() {
  surface.setTitle("Draw Psychotik");
  cf = new ControlFrame(this, 600, 400, "Controls");
  surface.setLocation(420, 10);
  background(0);
  strokeWeight(4);
  mouseReleased();
}

public void draw() {

  translate(width/2, height/2);
  if (mousePressed) {
    calcularStrokeW();

    for (int i=0; i<base; i++) {

      rotate( TWO_PI/base);
      if (tipo) {
        line(mouseX-width/2, mouseY-height/2, pmouseX-width/2, pmouseY-height/2);
        line(width/2-mouseX, mouseY-height/2, width/2-pmouseX, pmouseY-height/2);
      } else
      {
        ellipse(mouseX-width/2, mouseY-height/2, Stroke, Stroke );
        ellipse(width/2-mouseX, mouseY-height/2, Stroke, Stroke);
      }
    }
  }
  if (saveFrame) {
    saveFrame("bajadas/####.jpg");
    saveFrame =  false;
  }
}
public void calcularStrokeW() {
  if (auto) {
    float d = 20/(max(1, dist(mouseX, mouseY, pmouseX, pmouseY)));
    wActual= lerp (wActual, d, 0.2f);

    Stroke = wActual;
    strokeWeight(Stroke);
  } else {
    float d = dist(mouseX, mouseY, pmouseX, pmouseY);
    Stroke= d;
    strokeWeight(Stroke);
  }
}
public void mousePressed() {

  stroke(random(minR, maxR), random(minG, maxG), random(minB, maxB));
}

ControlFrame cf;
float speed;
float pos;
float c0, c1, c2, c3;
boolean auto, tipo;
int base=5;
float wActual = 20;



class ControlFrame extends PApplet {
  int w, h;
  PApplet parent;
  ControlP5 cp5;
  Range rangeR, rangeG, rangeB;
  public ControlFrame(PApplet _parent, int _w, int _h, String _name) {
    super();   
    parent = _parent;
    w=_w;
    h=_h;
    PApplet.runSketch(new String[]{this.getClass().getName()}, this);
  }
  public void settings() {
    size(w, h);
  }
  public void setup() {
    surface.setTitle("Draw Psychotik - Configuracion");
    surface.setLocation(10, 10);
    cp5 = new ControlP5(this);

    rangeR = cp5.addRange("rangeControllerRojo")
      // disable broadcasting since setRange and setRangeValues will trigger an event
      .setBroadcast(false) 
      .setPosition(25, 125)
      .setSize(350, 40)
      .setHandleSize(20)
      .setRange(0, 255)
      .setRangeValues(minR, maxR)
      // after the initialization we turn broadcast back on again
      .setBroadcast(true)
      .setColorForeground(color(255, 0, 0))
      .setColorBackground(color(255, 0, 0, 50))   
      ;

    rangeG = cp5.addRange("rangeControllerVerde")
      // disable broadcasting since setRange and setRangeValues will trigger an event
      .setBroadcast(false) 
      .setPosition(25, 200)
      .setSize(350, 40)
      .setHandleSize(20)
      .setRange(0, 255)
      .setRangeValues(minG, maxG)
      // after the initialization we turn broadcast back on again
      .setBroadcast(true)
      .setColorForeground(color(0, 255, 0))
      .setColorBackground(color(0, 255, 0, 50))  
      ;

    rangeB = cp5.addRange("rangeControllerAzul")
      // disable broadcasting since setRange and setRangeValues will trigger an event
      .setBroadcast(false) 
      .setPosition(25, 275)
      .setSize(350, 40)
      .setHandleSize(20)
      .setRange(0, 255)
      .setRangeValues(minB, maxB)
      // after the initialization we turn broadcast back on again
      .setBroadcast(true)
      .setColorForeground(color(0, 0, 255))
      .setColorBackground(color(0, 0, 255, 50))  
      ;

    cp5.addSlider("base")
      .plugTo(parent, "base")
      .setRange(2, 12)
      .setValue(0.01f)
      .setPosition(25, 350)
      .setSize(350, 40);

    cp5.addToggle("auto")
      .plugTo(parent, "auto")
      .setPosition(25, 50)
      .setSize(50, 50)
      .setValue(true);

    cp5.addToggle("Tipo")
      .plugTo(parent, "tipo")
      .setPosition(125, 50)
      .setSize(50, 50)
      .setValue(true);

    cp5.addBang("Bajar")
      .plugTo(parent, "bajar")
      .setLabel("Bajar Archivo")
      .setTriggerEvent(Bang.RELEASE)
      .setPosition(175, 50)
      .setSize(50, 50).setId(0);


  }
  public void draw() {
    background(120);
  }

  public void controlEvent(ControlEvent theControlEvent) {
    if (theControlEvent.isFrom("rangeControllerRojo")) {
      // min and max values are stored in an array.
      // access this array with controller().arrayValue().
      // min is at index 0, max is at index 1.
      minR = PApplet.parseInt(theControlEvent.getController().getArrayValue(0));
      maxR = PApplet.parseInt(theControlEvent.getController().getArrayValue(1));
    }



    if (theControlEvent.isFrom("rangeControllerVerde")) {
      // min and max values are stored in an array.
      // access this array with controller().arrayValue().
      // min is at index 0, max is at index 1.
      minG = PApplet.parseInt(theControlEvent.getController().getArrayValue(0));
      maxG = PApplet.parseInt(theControlEvent.getController().getArrayValue(1));
    }

    if (theControlEvent.isFrom("rangeControllerAzul")) {
      // min and max values are stored in an array.
      // access this array with controller().arrayValue().
      // min is at index 0, max is at index 1.
      minB = PApplet.parseInt(theControlEvent.getController().getArrayValue(0));
      maxB = PApplet.parseInt(theControlEvent.getController().getArrayValue(1));
    }
   if (theControlEvent.getController().getName().equals("Bajar")) {
    saveFrame = true;
    println("lo que?");
     }
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "DrawPsychotik" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

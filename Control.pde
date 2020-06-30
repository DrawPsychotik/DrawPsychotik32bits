import controlP5.*;
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
      .setValue(0.01)
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
  void draw() {
    background(120);
  }

  void controlEvent(ControlEvent theControlEvent) {
    if (theControlEvent.isFrom("rangeControllerRojo")) {
      // min and max values are stored in an array.
      // access this array with controller().arrayValue().
      // min is at index 0, max is at index 1.
      minR = int(theControlEvent.getController().getArrayValue(0));
      maxR = int(theControlEvent.getController().getArrayValue(1));
    }



    if (theControlEvent.isFrom("rangeControllerVerde")) {
      // min and max values are stored in an array.
      // access this array with controller().arrayValue().
      // min is at index 0, max is at index 1.
      minG = int(theControlEvent.getController().getArrayValue(0));
      maxG = int(theControlEvent.getController().getArrayValue(1));
    }

    if (theControlEvent.isFrom("rangeControllerAzul")) {
      // min and max values are stored in an array.
      // access this array with controller().arrayValue().
      // min is at index 0, max is at index 1.
      minB = int(theControlEvent.getController().getArrayValue(0));
      maxB = int(theControlEvent.getController().getArrayValue(1));
    }
   if (theControlEvent.getController().getName().equals("Bajar")) {
    saveFrame = true;
    println("lo que?");
     }
  }
}

int minR =100, maxR=255, minG=100, maxG=200, minB=50, maxB=200;
float Stroke= 0 ;
boolean saveFrame = false; 

void settings() {
  size(700, 700);
}
void setup() {
  surface.setTitle("Draw Psychotik");
  cf = new ControlFrame(this, 600, 400, "Controls");
  surface.setLocation(420, 10);
  background(0);
  strokeWeight(4);
  mouseReleased();
}

void draw() {

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
void calcularStrokeW() {
  if (auto) {
    float d = 20/(max(1, dist(mouseX, mouseY, pmouseX, pmouseY)));
    wActual= lerp (wActual, d, 0.2);

    Stroke = wActual;
    strokeWeight(Stroke);
  } else {
    float d = dist(mouseX, mouseY, pmouseX, pmouseY);
    Stroke= d;
    strokeWeight(Stroke);
  }
}
void mousePressed() {

  stroke(random(minR, maxR), random(minG, maxG), random(minB, maxB));
}

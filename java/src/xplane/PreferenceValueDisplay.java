package xplane;

//import scala.Predef;

import java.awt.*;

public class PreferenceValueDisplay {
    public double warnLow, warnHigh, doNotWarnLow, doNotWarnHigh;
    public int sensor;
    public int x,y;

    public void paint(Graphics g){
        switch(sensor){
            case 0: x = 1050;
                    y = 100;
                    break;
            case 1: x = 1450;
                    y=100;
                    break;
            case 2: x =1850;
                    y =100;
                    break;

        }
        g.drawString("Warn Low", x, 100);
        g.drawString(String.format("%.2f",warnLow), x, 120);
        g.drawString("Do Not Warn Low", x+150, 100);
        g.drawString(String.format("%.2f",doNotWarnLow), x+150, 120);
        g.drawString("Warn High", x, 150);
        g.drawString(String.format("%.2f",warnHigh), x, 170);
        g.drawString("Do Not Warn High", x+150, 150);
        g.drawString(String.format("%.2f",doNotWarnHigh), x+150, 170);
        g.fillRect(x-20, 80, 10, 100);
        g.fillRect(x+320, 80, 10, 100);
        g.fillRect(x+120, 80, 5, 100);
        g.fillRect(x-20, 70, 350, 10);
        g.fillRect(x-20, 180, 350, 10);
        g.fillRect(x-20, 125, 350, 5);
    }

}

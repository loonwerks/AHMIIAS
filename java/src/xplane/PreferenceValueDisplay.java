package xplane;

import scala.Predef;

import java.awt.*;

public class PreferenceValueDisplay {
    public double warnLow, warnHigh, doNotWarnLow, doNotWarnHigh;

    public void paint(Graphics g){
        g.drawString("Warn Low", 1050, 100);
        g.drawString(String.format("%.2f",warnLow), 1050, 120);
        g.drawString("Do Not Warn Low", 1200, 100);
        g.drawString(String.format("%.2f",doNotWarnLow), 1200, 120);
        g.drawString("Warn High", 1050, 150);
        g.drawString(String.format("%.2f",warnHigh), 1050, 170);
        g.drawString("Do Not Warn High", 1200, 150);
        g.drawString(String.format("%.2f",doNotWarnHigh), 1200, 170);
        g.fillRect(1030, 80, 10, 100);
        g.fillRect(1370, 80, 10, 100);
        g.fillRect(1170, 80, 5, 100);
        g.fillRect(1030, 70, 350, 10);
        g.fillRect(1030, 180, 350, 10);
        g.fillRect(1030, 125, 350, 5);
    }

}

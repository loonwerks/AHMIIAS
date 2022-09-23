package xplane;

import java.awt.*;
import util.button;


public class SensorChangeLearningOptionDisplay {
    public double learningModeInfo, authorityToChangeInfo;

    public void paint(Graphics g){
        g.drawString("Current Learning Mode : ", 520, 450);
        g.drawString(String.format("%.2f",learningModeInfo), 800, 450);
        g.drawString("Update Learning Mode ", 520, 500);
        g.drawString("Current Authority to Change Sensor", 520, 620);
        g.drawString(String.format("%.2f",authorityToChangeInfo), 900, 620);
        g.drawString("Update Authority to Change Sensor", 520, 670);

//        g.fillRect(530, 350, 10, 200);
//        g.fillRect(640, 390, 10, 200);
//        g.fillRect(540, 350, 350, 10);
//        g.fillRect(540, 390, 350, 10);
//        g.fillRect(560, 400, 10, 100);
//        g.fillRect(1170, 80, 5, 100);

    }

}

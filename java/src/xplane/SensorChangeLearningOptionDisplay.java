package xplane;

import java.awt.*;

public class SensorChangeLearningOptionDisplay {
    public double learningMode, authorityToChange;

    public void paint(Graphics g){
        g.drawString("Learning Mode ", 1050, 100);
        g.drawString(String.format("%.2f",learningMode), 1050, 120);
        g.drawString("Authority to Change Sensor", 1200, 100);
        g.drawString(String.format("%.2f",authorityToChange), 1200, 120);
        g.fillRect(1030, 80, 10, 100);
        g.fillRect(1370, 80, 10, 100);
        g.fillRect(1170, 80, 5, 100);

    }

}

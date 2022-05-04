package us.hiai.util;

import java.awt.*;

public class RadioButton {
    public String[] options;

    private String text = "";
    int mx=0,my=0, xpos,ypos;
    public RadioButton(String text[], int x, int y){
        xpos = x;
        ypos = y;
        options = text.clone();
    }
    public void update(int mx, int my){
        this.mx = mx;
        this.my = my;
    }

    public void draw(Graphics g){
        int x, y;
        for(int i=0;i< options.length;i++) {
            x = xpos;
            y = ypos + i * 40;

            if (distanceSquare(mx,my,x,y) <= 100) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.LIGHT_GRAY);
            }
            g.fillOval(x-10,y-10,20,20);
            g.setColor(Color.BLACK);
            g.drawOval(x-10,y-10,20,20);
            g.drawString(options[i], x+30, y+8);
        }
    }
    public int getSelectedOption(int mx, int my){
        int x,y;
        for(int i=0;i< options.length;i++) {
            x = xpos;
            y = ypos + i * 40;

            if (distanceSquare(mx,my,x,y) <= 100) {
                return i;
            }
        }
        return -1;
    }

    private int distanceSquare(int x1, int y1, int x2, int y2){
        return (int)(Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2));

    }
}

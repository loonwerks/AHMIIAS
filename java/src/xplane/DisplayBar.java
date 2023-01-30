package xplane;

import java.awt.Color;
import java.awt.Graphics;

public class DisplayBar {
	public double warnLow, warnHigh, doNotWarnLow, doNotWarnHigh;
	public double error;
	public DisplayBar(){		
		
		
	}
	
	public void draw(Graphics g){
		g.setColor(Color.red);
		g.fillRect(0, 0, 100, 100);

		g.setColor(Color.green);
		g.fillRect(0, 300, 100, 100);

		g.setColor(Color.white);
		g.fillRect(0, 100, 100, 100);
		g.setColor(Color.white);
		g.fillRect(0, 200, 100, 100);
		Color c = Color.white;
		if(Math.abs(warnHigh-doNotWarnHigh) < 1.0 ){
			if(warnHigh > doNotWarnHigh){
				c = new Color(255, 0, 0, (int)(255*Math.max(warnHigh-doNotWarnHigh, 0)));

			}else{
				c = new Color(0, 255, 0, (int)(255*Math.max(-warnHigh+doNotWarnHigh, 0)));
			}
			g.setColor(c);
			g.fillRect(0, 100, 100, 100);
		}

		if(Math.abs(warnLow-doNotWarnLow) < 1.0){
			if(warnLow > doNotWarnLow){
				c = new Color(255, 0, 0, (int)(255*Math.max(warnLow-doNotWarnLow, 0)));

			}else{
				c = new Color(0, 255, 0, (int)(255*Math.max(-warnLow+doNotWarnLow, 0)));
			}
			g.setColor(c);
			g.fillRect(0, 200, 100, 100);
		}


		g.setColor(Color.black);
		if(error > 8.0) {
			g.fillRect(0, 400 - ((int) ((error - 7.0) * 100)), 100, 2);
			g.drawString(String.format("%.2f",error),100, 400 - ((int) ((error - 7.0) * 100)));
		}
		
		g.setColor(Color.black);
		g.drawRect(0, 0, 100, 400);
		for(int i=0;i<4;i++) {
			g.drawRect(0,100+(i*100),100,1);
			g.drawString(" "+(10-i), 100,100+(i*100));
			g.drawString(" "+(10-i), 100,100+(i*100));
		}
		
		
	}
	
}

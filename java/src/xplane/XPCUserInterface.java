package xplane;


import util.RadioButton;
import util.button;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;

public class XPCUserInterface extends JFrame implements Runnable{



    private Image imageBuffer;
    private Graphics imageBufferGraphics;
    public float positions[][] = new float[4][2];
    public double distanceToTarget = 0.0;
    private Font textFont1 = new Font("Courier", Font.BOLD,25);
    private Font textFont2 = new Font("Courier", Font.PLAIN,20);
    private String sensorNames[] = {"GPS", "Lidar", "IMU", "Target Waypoint"};
    private double targetLat, targetLon;
    public int selectedSensor = 0;
    private float errorLat=0, errorLon=0;
    private boolean errorInGPS = false,errorInIMU=false,errorInLIDAR=false, errorIsIncremental = false;
    public int SensorPossiblyUnreliable = -1;
    public int SensorUnreliable = -1;
    public String faultySensorName = "none";
    public String pilotDecision = "nil";
    public boolean displayLandingButton = false, startedLandingProcedure = false, abortLanding = false;
    public int reroutedLandingZone = -1;
    public boolean learningModeUpdate = false;
    public boolean authorityToChangeUpdate = false;
    public int planeAirspeed=0, planeAltitude=0;
    private int mx=0,my=0;
    private boolean displayWarning = false;

    private button  InduceErrorButton,InduceIncrementalErrorButton, AcknowledgeErrorButton, DenyErrorButton, LandButton, AbortLandingButton;
    private RadioButton learningMode, authorityToChange,landingOptions;

    public PreferenceValueDisplay displayObj;
    public SensorChangeLearningOptionDisplay sensorChangeLearningObj;
    public DisplayBar barObj,barObjOld;

    public void displayWarning(String faultySensorName){
        this.faultySensorName = faultySensorName;
        SensorPossiblyUnreliable = 0;
        displayWarning = true;
    }

    public void injectError(){

        errorInGPS = true;
        if (new Random().nextBoolean()) {
            errorLat = 0.06f;
            errorLon = 0.06f;
        }else{
            errorLat = 0.065f;
            errorLon = 0.065f;
        }

    }
    public void injectIncrementalError(){

        errorInGPS = true;
        //errorInIMU = true;
        //errorInLIDAR = true;
        errorIsIncremental = true;
        //errorLat = 0.1f;
        //errorLon = 0.1f;

    }
    public void acknowledgeError(){
        SensorUnreliable = SensorPossiblyUnreliable;
        /*if(selectedSensor == SensorUnreliable)
            selectedSensor = (selectedSensor + 1) % 3;*/
        pilotDecision = "yes";
    }
    public void denyError(){
        SensorPossiblyUnreliable = -1;
        pilotDecision = "no";
    }
    public void reset(){
        displayWarning = false;
        errorInGPS = false;
        errorIsIncremental = false;
        errorLat=0;
        errorLon=0;
        SensorPossiblyUnreliable = -1;
        SensorUnreliable = -1;
        faultySensorName = "none";
        pilotDecision = "nil";
    }


    public void setGPSValue(float xPos, float yPos) {
        if (!errorInGPS) {
            positions[0][0] = xPos;
            positions[0][1] = yPos;
        } else {
            positions[0][0] = xPos + errorLat;
            positions[0][1] = yPos + errorLon;
        }
    }
    public void setLidarValue(float xPos, float yPos) {
        if (!errorInLIDAR) {
            positions[2][0] = xPos;
            positions[2][1] = yPos;
        } else {
            positions[2][0] = xPos + errorLat;
            positions[2][1] = yPos + errorLon;
        }
    }

    public void setIMUValue(float xPos, float yPos) {
        if (!errorInIMU) {
            positions[1][0] = xPos;
            positions[1][1] = yPos;
        } else {
            positions[1][0] = xPos + errorLat;
            positions[1][1] = yPos + errorLon;
        }
    }

    public void setTargetWaypoint(float xPos, float yPos) {
        positions[3][0] = xPos;
        positions[3][1] = yPos;
    }

    public void setDistanceToTarget(double distance){
        distanceToTarget = distance;
    }
    public double getGPSLat(){
        return positions[0][0];
    }
    public double getGPSLon(){
        return positions[0][1];
    }

    public double getLidarLat(){
        return positions[1][0];
    }
    public double getLidarLon(){
        return positions[1][1];
    }

    public double getIMULat(){
        return positions[2][0];
    }
    public double getIMULon(){
        return positions[2][1];
    }

    @Override
    public void run() {
        displayObj = new PreferenceValueDisplay();
        barObj = new DisplayBar();
        barObjOld = new DisplayBar();
        sensorChangeLearningObj = new SensorChangeLearningOptionDisplay();
        String learningModeOptionText[] = {"On", "Off"};
        learningMode = new RadioButton(learningModeOptionText, 530, 530);
        String authorityToChangeOptionText[] = {"On", "Off"};
        authorityToChange = new RadioButton(authorityToChangeOptionText, 530, 700);
        InduceErrorButton = new button("Jump Error",580, 160, 320, 30);
        InduceIncrementalErrorButton = new button("Incremental Error",580, 200, 320, 30);
        AcknowledgeErrorButton = new button("Acknowledge Error",80, 660, 320, 30);
        DenyErrorButton = new button("Deny Error",80, 720, 320, 30);
        LandButton = new button("Initiate Landing",80, 820, 320, 30);
        AbortLandingButton = new button("Abort Landing",80, 820, 320, 30);
        String optionText[] = {"Nellis AFB(KLSV)", "[H] Gilbert Development Corp (NV61)", "Las Vegas (VEGAS)"};
        landingOptions = new RadioButton(optionText,530, 450);
        this.setSize(1500, 900);
        this.setResizable(false);
        this.setTitle("Sensor Output:");
        this.setLocation(400,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KeyboardListener());
        this.addMouseListener(new MouseEventListener());
        this.addMouseMotionListener(new MouseMoveListener());
        this.setVisible(true);


    }


    public void paint(Graphics g){

        imageBuffer= createImage(getWidth(),getHeight());
        imageBufferGraphics=imageBuffer.getGraphics();
        try {
            draw(imageBufferGraphics);
            g.drawImage(imageBuffer,0,0,null);
            if(errorIsIncremental && errorLon < 0.1f && errorLat < 0.1f){
                errorLon += 0.1/(60.0*15.0*1.5);
                errorLat += 0.1/(60.0*15.0*1.5);
            }


            Thread.sleep(16);

        }catch(Exception e){
            System.err.println("Error in draw method!");
        }
        repaint();

    }
    public void draw(Graphics g){
        for(int i = 0;i < 4 ;i++) {

            if(i == selectedSensor) {
                g.setColor(new Color(102,255,102));
            }else{
                g.setColor(Color.LIGHT_GRAY);

            }

            g.fillRoundRect(50, 50 + (i * 150), 400, 100, 50, 50);
            g.setColor(Color.BLACK);


            g.drawRoundRect(50, 50 + (i * 150), 400, 100, 50, 50);
            g.setColor(Color.BLACK);

            g.setFont(textFont1);
            g.drawString(sensorNames[i]+":", 62, 70 + (i * 150));
            g.setFont(textFont2);

            g.setColor(Color.BLACK);

            g.drawString(String.format("Position:%s, %s", formatPosition(positions[i][0]), formatPosition(positions[i][1])), 75, 110 + (i * 150));
            if(i==3){
                g.drawString(String.format("Distance to target: %.4f Km", distanceToTarget), 75, 580);
            }
        }
        g.setFont(textFont2);

        if(!errorInGPS) {
            InduceErrorButton.draw(g);
            InduceIncrementalErrorButton.draw(g);
        }else if(SensorPossiblyUnreliable != -1 && SensorUnreliable == -1){
            g.drawString(sensorNames[SensorPossiblyUnreliable]+" may be unreliable!", 75, 630);

            AcknowledgeErrorButton.draw(g);
            DenyErrorButton.draw(g);
        }else if(SensorUnreliable != -1){
            g.setColor(Color.BLACK);
            g.drawString(sensorNames[SensorUnreliable]+" error acknowledged.", 75, 680);
            g.drawString("Switched to "+sensorNames[selectedSensor]+" sensor.", 75, 700);
        }
        if(!startedLandingProcedure && displayLandingButton){
            LandButton.draw(g);
        }else if(startedLandingProcedure && !abortLanding){
            AbortLandingButton.draw(g);
        }

        drawTestUI(g);
        if(abortLanding){
            g.setFont(textFont1);
            g.drawString("Alternative Landing Options:", 550, 410);
            g.setFont(textFont2);
            landingOptions.draw(g);
        }
        learningMode.draw(g);
        authorityToChange.draw(g);
        displayObj.paint(g);
        sensorChangeLearningObj.paint(g);
        BufferedImage barImg = new BufferedImage(500,500, BufferedImage.TYPE_INT_ARGB);
        barObj.draw(barImg.getGraphics());
        barObj.warnHigh = displayObj.warnHigh;
        barObj.warnLow = displayObj.warnLow;
        barObj.doNotWarnHigh = displayObj.doNotWarnHigh;
        barObj.doNotWarnLow = displayObj.doNotWarnLow;
        g.drawImage(barImg, 1200,300, null);
        BufferedImage barImgOld = new BufferedImage(500,500, BufferedImage.TYPE_INT_ARGB);
        barObjOld.draw(barImgOld.getGraphics());
        /*barObjOld.warnHigh = displayObj.warnHigh;
        barObjOld.warnLow = displayObj.warnLow;
        barObjOld.doNotWarnHigh = displayObj.doNotWarnHigh;
        barObjOld.doNotWarnLow = displayObj.doNotWarnLow;*/
        g.drawImage(barImgOld, 1050,300, null);
        g.drawString("Previous", 1050,725);
        g.drawString("Present", 1200,725);
        barObjOld.error = barObj.error;

    }
    private void drawTestUI(Graphics g){

        g.setFont(textFont1);
        g.setColor(Color.BLACK);
        g.drawString("Testing UI:", 675, 70);
        g.setFont(textFont2);
        g.drawString("Altitude: "+planeAltitude+"ft above Sea Level", 550,260);
        g.drawString("Airspeed: "+planeAirspeed+"kn", 550,280);
        g.fillRect(500,0,10,1200);
        g.fillRect(500,350,500,10);
        g.fillRect(1000,0,10,1200);
    }
    private String formatPosition(double pos){
        String ans = "";

        ans += (int)pos + "°";
        if(pos <0) {
            pos *= -1;

        }
        ans += (int)(pos*60)%60 +"\'";
        ans += (int)(pos*3600)%60+"\"";
        return ans;
    }

    private class KeyboardListener implements KeyListener{


        @Override
        public void keyTyped(KeyEvent keyEvent) {
        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {

        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    }
    private  class MouseMoveListener implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            mx = mouseEvent.getX();
            my = mouseEvent.getY();
            InduceErrorButton.update(mx,my);
            InduceIncrementalErrorButton.update(mx,my);
            AcknowledgeErrorButton.update(mx,my);
            DenyErrorButton.update(mx,my);
            LandButton.update(mx,my);
            AbortLandingButton.update(mx,my);
            landingOptions.update(mx,my);
            authorityToChange.update(mx,my);
            learningMode.update(mx,my);
        }
    }

    private  class MouseEventListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if(!errorInGPS && InduceErrorButton.button.intersects(new Rectangle(mx,my,1,1))){
                injectError();
            }else if(!errorInGPS && InduceIncrementalErrorButton.button.intersects(new Rectangle(mx,my,1,1))){
                injectIncrementalError();
            }else if(SensorUnreliable == -1 && SensorPossiblyUnreliable != -1  && AcknowledgeErrorButton.button.intersects(new Rectangle(mx,my,1,1))){
                acknowledgeError();
            }else if(SensorPossiblyUnreliable != -1 && DenyErrorButton.button.intersects(new Rectangle(mx,my,1,1))) {
                denyError();
            }else if(!startedLandingProcedure && LandButton.button.intersects(new Rectangle(mx,my,1,1))){
                //initiate landing
                startedLandingProcedure = true;
            }else if(startedLandingProcedure && AbortLandingButton.button.intersects(new Rectangle(mx,my,1,1))){
                abortLanding = true;
            }else if(landingOptions.getSelectedOption(mx,my) != -1) {
                reroutedLandingZone = landingOptions.getSelectedOption(mx, my);
            }else if(learningMode.getSelectedOption(mx,my) != -1) {
                learningModeUpdate = true;
            }else if(authorityToChange.getSelectedOption(mx,my) != -1) {
                authorityToChangeUpdate = true;
            }
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }

}

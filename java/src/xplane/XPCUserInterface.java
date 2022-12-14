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
    public int activeSensor = 0;
    private float errorLat=0, errorLon=0;
    private boolean errorInGPS = false,errorInIMU=false,errorInLIDAR=false, errorIsIncremental = false;
    public int SensorPossiblyUnreliable = -1;
    public int SensorUnreliable = -1;
    public String faultySensorName = "none";
    public String pilotDecision = "nil";
    public String pilotDecisionToChange ="nil";
    public boolean ErrorSensor = false;
    public boolean errorReseted = false;
    public boolean displayLandingButton = false, initiateLanding = false, abortLanding = false, finishedTakeoff = false;
    public int reroutedLandingZone = -1;
    public int learningModeUpdate = -1;
    public int authorityToChangeUpdate = -1;
    public int planeAirspeed=0, planeAltitude=0;
    private int mx=0,my=0;
    private boolean displayWarning = false;

    private button  InduceErrorButton,InduceIncrementalErrorButton, AcknowledgeErrorButton, DenyErrorButton, AcknowledgeChangeButton, DenyChangeButton, LandButton, AbortLandingButton;
    private button InduceLowErrorButton, InduceHighErrorButton, InduceSafetyErrorButton;
    private RadioButton learningMode, authorityToChange,landingOptions;

    public PreferenceValueDisplay GPSdisplayObj, IMUdisplayObj, LIDARdisplayObj;
    public SensorChangeLearningOptionDisplay sensorChangeLearningObj;
    public DisplayBar GPSbarObj,GPSbarObjOld, IMUbarObj,IMUbarObjOld, LIDARbarObj,LIDARbarObjOld;

    public void displayWarning(String faultySensorName){
        this.faultySensorName = faultySensorName;
        SensorPossiblyUnreliable = 0;
        displayWarning = true;
    }
    public void injectLowError(){
        errorInGPS = true;
        errorLat = 0.06f;
        errorLon = 0.06f;
    }
    public void injectHighError(){
        errorInGPS = true;
        errorLat = 0.065f;
        errorLon = 0.065f;
    }
    public void injectSafetyError(){
        errorInGPS = true;
        errorLat = 0.08f;
        errorLon = 0.08f;
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
    public void acknowledgeForError(){
        pilotDecision = "yes";
    }
    public void denyForError(){
        pilotDecision = "no";
    }
    public void acknowledgeChange(){
        SensorUnreliable = SensorPossiblyUnreliable;
        if(activeSensor == SensorUnreliable)
            activeSensor = (activeSensor + 1) % 3;
        pilotDecisionToChange = "yes";
    }
    public void denyChange(){
        SensorPossiblyUnreliable = -1;
        pilotDecisionToChange = "no";
    }
    public void reset(){
        displayWarning = false;
        errorInGPS = false;
        errorInLIDAR = false;
        errorInIMU = false;
        errorIsIncremental = false;
        errorLat=0;
        errorLon=0;
        SensorPossiblyUnreliable = -1;
        SensorUnreliable = -1;
        faultySensorName = "none";
        pilotDecision = "nil";
    }
    public void errorReset(){
        errorReseted = true;
        reset();
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
        GPSdisplayObj = new PreferenceValueDisplay();
        IMUdisplayObj = new PreferenceValueDisplay();
        LIDARdisplayObj = new PreferenceValueDisplay();
        GPSbarObj = new DisplayBar();
        GPSbarObjOld = new DisplayBar();
        IMUbarObj = new DisplayBar();
        IMUbarObjOld = new DisplayBar();
        LIDARbarObj = new DisplayBar();
        LIDARbarObjOld = new DisplayBar();
        sensorChangeLearningObj = new SensorChangeLearningOptionDisplay();
        String learningModeOptionText[] = {"On", "Off"};
        learningMode = new RadioButton(learningModeOptionText, 530, 530);
        String authorityToChangeOptionText[] = {"On", "Off"};
        authorityToChange = new RadioButton(authorityToChangeOptionText, 530, 700);
        InduceErrorButton = new button("Jump Error",580, 160, 320, 30);
        InduceIncrementalErrorButton = new button("Incremental Error",580, 200, 320, 30);
        InduceLowErrorButton = new button("Low Jump Error",580, 120, 320, 30);
        InduceHighErrorButton = new button("High Jump Error",580, 160, 320, 30);
        InduceSafetyErrorButton = new button("Safety Jump Error",580, 200, 320, 30);
        AcknowledgeErrorButton = new button("Acknowledge Error",80, 660, 320, 30);
        DenyErrorButton = new button("Deny Error",80, 720, 320, 30);
        AcknowledgeChangeButton = new button("Acknowledge Sensor Change",80, 660, 360, 30);
        DenyChangeButton = new button("Deny Sensor Change",80, 720, 360, 30);
        LandButton = new button("Initiate Landing",80, 820, 320, 30);
        AbortLandingButton = new button("Abort Landing",80, 820, 320, 30);
        String optionText[] = {"Nellis AFB(KLSV)", "[H] Gilbert Development Corp (NV61)", "Las Vegas (VEGAS)"};
        landingOptions = new RadioButton(optionText,530, 450);
        this.setSize(2200, 900);
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

            if(i == activeSensor) {
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
        if(!errorInGPS && sensorChangeLearningObj.learningModeInfo) {
            InduceErrorButton.draw(g);
            InduceIncrementalErrorButton.draw(g);
        } else if (!errorInGPS && !sensorChangeLearningObj.learningModeInfo && finishedTakeoff) {
            InduceLowErrorButton.draw(g);
            InduceHighErrorButton.draw(g);
            InduceSafetyErrorButton.draw(g);
        } else if(SensorPossiblyUnreliable != -1 && SensorUnreliable == -1 && sensorChangeLearningObj.authorityToChangeInfo){
            g.drawString(sensorNames[SensorPossiblyUnreliable]+" may be unreliable!", 75, 630);
            AcknowledgeChangeButton.draw(g);
            DenyChangeButton.draw(g);
        }else if(SensorUnreliable != -1 && sensorChangeLearningObj.authorityToChangeInfo && errorInGPS){
            g.setColor(Color.BLACK);
            g.drawString(sensorNames[SensorUnreliable]+" error acknowledged.", 75, 680);
            g.drawString("Switched to "+sensorNames[activeSensor]+" sensor.", 75, 700);
        }
        if(ErrorSensor == true){
            AcknowledgeErrorButton.draw(g);
            DenyErrorButton.draw(g);
        }
        if(!initiateLanding && displayLandingButton){
            LandButton.draw(g);
        }else if(initiateLanding && !abortLanding){
            AbortLandingButton.draw(g);
        }
        drawTestUI(g);
        if(abortLanding){
            g.setFont(textFont1);
            g.drawString("Alternative Landing Options:", 550, 410);
            g.setFont(textFont2);
            landingOptions.draw(g);
        }
        if(!initiateLanding && finishedTakeoff) {
            learningMode.draw(g);
            sensorChangeLearningObj.paintLearningMode(g);
            if(!sensorChangeLearningObj.learningModeInfo) {
                sensorChangeLearningObj.paintAuthorityToChange(g);
                authorityToChange.draw(g);
            }
        }
        GPSdisplayObj.sensor=0;
        GPSdisplayObj.paint(g);
        BufferedImage GPSbarImg = new BufferedImage(500,500, BufferedImage.TYPE_INT_ARGB);
        GPSbarObj.draw(GPSbarImg.getGraphics());
        GPSbarObj.warnHigh = GPSdisplayObj.warnHigh;
        GPSbarObj.warnLow = GPSdisplayObj.warnLow;
        GPSbarObj.doNotWarnHigh = GPSdisplayObj.doNotWarnHigh;
        GPSbarObj.doNotWarnLow = GPSdisplayObj.doNotWarnLow;
        g.drawImage(GPSbarImg, 1200,300, null);
        BufferedImage GPSbarImgOld = new BufferedImage(500,500, BufferedImage.TYPE_INT_ARGB);
        GPSbarObjOld.draw(GPSbarImgOld.getGraphics());
        /*GPSbarObjOld.warnHigh = GPSdisplayObj.warnHigh;
        GPSbarObjOld.warnLow = GPSdisplayObj.warnLow;
        GPSbarObjOld.doNotWarnHigh = GPSdisplayObj.doNotWarnHigh;
        GPSbarObjOld.doNotWarnLow = GPSdisplayObj.doNotWarnLow;*/
        g.drawImage(GPSbarImgOld, 1050,300, null);
        g.drawString("Previous", 1050,725);
        g.drawString("Present", 1200,725);
        g.drawString("GPS Sensor", 1100, 775);
        GPSbarObjOld.error = GPSbarObj.error;

        IMUdisplayObj.sensor=1;
        IMUdisplayObj.paint(g);
        BufferedImage IMUbarImg = new BufferedImage(500,500, BufferedImage.TYPE_INT_ARGB);
        IMUbarObj.draw(IMUbarImg.getGraphics());
        IMUbarObj.warnHigh = IMUdisplayObj.warnHigh;
        IMUbarObj.warnLow = IMUdisplayObj.warnLow;
        IMUbarObj.doNotWarnHigh = IMUdisplayObj.doNotWarnHigh;
        IMUbarObj.doNotWarnLow = IMUdisplayObj.doNotWarnLow;
        g.drawImage(IMUbarImg, 1600,300, null);
        BufferedImage IMUbarImgOld = new BufferedImage(500,500, BufferedImage.TYPE_INT_ARGB);
        IMUbarObjOld.draw(IMUbarImgOld.getGraphics());
        /*IMUbarObjOld.warnHigh = IMUdisplayObj.warnHigh;
        IMUbarObjOld.warnLow = IMUdisplayObj.warnLow;
        IMUbarObjOld.doNotWarnHigh = IMUdisplayObj.doNotWarnHigh;
        IMUbarObjOld.doNotWarnLow = IMUdisplayObj.doNotWarnLow;*/
        g.drawImage(IMUbarImgOld, 1450,300, null);
        g.drawString("Previous", 1450,725);
        g.drawString("Present", 1600,725);
        g.drawString("IMU Sensor", 1500, 775);
        IMUbarObjOld.error = IMUbarObj.error;

        LIDARdisplayObj.sensor=2;
        LIDARdisplayObj.paint(g);
        BufferedImage LIDARbarImg = new BufferedImage(500,500, BufferedImage.TYPE_INT_ARGB);
        LIDARbarObj.draw(LIDARbarImg.getGraphics());
        LIDARbarObj.warnHigh = LIDARdisplayObj.warnHigh;
        LIDARbarObj.warnLow = LIDARdisplayObj.warnLow;
        LIDARbarObj.doNotWarnHigh = LIDARdisplayObj.doNotWarnHigh;
        LIDARbarObj.doNotWarnLow = LIDARdisplayObj.doNotWarnLow;
        g.drawImage(LIDARbarImg, 2000,300, null);
        BufferedImage LIDARbarImgOld = new BufferedImage(500,500, BufferedImage.TYPE_INT_ARGB);
        LIDARbarObjOld.draw(LIDARbarImgOld.getGraphics());
        /*LIDARbarObjOld.warnHigh = LIDARdisplayObj.warnHigh;
        LIDARbarObjOld.warnLow = LIDARdisplayObj.warnLow;
        LIDARbarObjOld.doNotWarnHigh = LIDARdisplayObj.doNotWarnHigh;
        LIDARbarObjOld.doNotWarnLow = LIDARdisplayObj.doNotWarnLow;*/
        g.drawImage(LIDARbarImgOld, 1850,300, null);
        g.drawString("Previous", 1850,725);
        g.drawString("Present", 2000,725);
        g.drawString(" LIDAR Sensor", 1900, 775);
        LIDARbarObjOld.error = LIDARbarObj.error;


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
            InduceLowErrorButton.update(mx,my);
            InduceHighErrorButton.update(mx,my);
            InduceSafetyErrorButton.update(mx,my);
            InduceIncrementalErrorButton.update(mx,my);
            AcknowledgeChangeButton.update(mx,my);
            DenyChangeButton.update(mx,my);
            AcknowledgeErrorButton.update(mx,my);
            DenyErrorButton.update(mx,my);
            LandButton.update(mx,my);
            AbortLandingButton.update(mx,my);
            landingOptions.update(mx,my);
            learningMode.update(mx,my);
            authorityToChange.update(mx,my);

        }
    }

    private  class MouseEventListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if(!errorInGPS && InduceLowErrorButton.button.intersects(new Rectangle(mx, my, 1, 1))){
                injectLowError();
            }else if(!errorInGPS && InduceHighErrorButton.button.intersects(new Rectangle(mx, my, 1, 1))){
                injectHighError();
            }else if(!errorInGPS && InduceSafetyErrorButton.button.intersects(new Rectangle(mx, my, 1, 1))){
                injectSafetyError();
            }
            if(!errorInGPS && InduceErrorButton.button.intersects(new Rectangle(mx,my,1,1))){
                injectError();
            }else if(learningMode.getSelectedOption(mx,my) != -1) {
                if (learningMode.getSelectedOption(mx,my) == 0){
                    learningModeUpdate = 0;
                    authorityToChangeUpdate = -1;;
                }else {
                    learningModeUpdate = learningMode.getSelectedOption(mx, my);
                }
            }else if(authorityToChange.getSelectedOption(mx,my) != -1) {
                authorityToChangeUpdate = authorityToChange.getSelectedOption(mx,my);
            }else if(SensorUnreliable == -1 && SensorPossiblyUnreliable != -1  && AcknowledgeChangeButton.button.intersects(new Rectangle(mx,my,1,1))){
                acknowledgeChange();
            }else if(SensorPossiblyUnreliable != -1 && DenyChangeButton.button.intersects(new Rectangle(mx,my,1,1))) {
                denyChange();
            }else if(!errorInGPS && InduceIncrementalErrorButton.button.intersects(new Rectangle(mx,my,1,1))){
                injectIncrementalError();
            }else if(!initiateLanding && LandButton.button.intersects(new Rectangle(mx,my,1,1))){
                //initiate landing
                initiateLanding = true;
            }else if(initiateLanding && AbortLandingButton.button.intersects(new Rectangle(mx,my,1,1))){
                abortLanding = true;
            }else if(landingOptions.getSelectedOption(mx,my) != -1) {
                reroutedLandingZone = landingOptions.getSelectedOption(mx, my);
            }else if(ErrorSensor && AcknowledgeErrorButton.button.intersects(new Rectangle(mx,my,1,1))){
                acknowledgeForError();
            } else if (ErrorSensor && DenyErrorButton.button.intersects(new Rectangle(mx,my,1,1))) {
                denyForError();

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

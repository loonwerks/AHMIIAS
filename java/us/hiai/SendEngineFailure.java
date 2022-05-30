package us.hiai;

import gov.nasa.xpc.XPlaneConnect;

import java.io.IOException;

/**
 * Created by icislab on 10/18/2016.
 */
public class SendEngineFailure
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        XPlaneConnect xpc = new XPlaneConnect();
        String dref = "sim/operation/failures/rel_engfai1";
        //String dref = "sim/operation/failures/rel_depres_fast";
        float[] value = {6.0F};
        xpc.sendDREF(dref, value);
    }
}
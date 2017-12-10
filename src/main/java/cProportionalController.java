import com.cyberbotics.webots.controller.DistanceSensor;

import java.util.Vector;

public class cProportionalController extends ProportionalSuperController {

    private static int TIME_STEP = 20;
    private DistanceSensor[] sensors;
    private double speedLeft;
    private double speedRight;
    double[][] k = {{1, 1},{0.2, 0.2},{0.4, 0.4}};
    private double maxspeed = 800;



    public cProportionalController() {
        super();
        // get distance sensors and save them in array
        sensors = new DistanceSensor[]{getDistanceSensor("ps7"), getDistanceSensor("ps0"), 
        getDistanceSensor("ps2"), getDistanceSensor("ps3"),
        getDistanceSensor("ps4"), getDistanceSensor("ps5")};
        for (DistanceSensor l : sensors) {
            l.enable(10);
        }
    }

    public void run() {
        while (step(TIME_STEP) != -1) {
            evaluateSpeed();
            speedLeft = 400 + speedLeft;
            speedRight = 400 + speedRight;
            if(speedLeft > maxspeed){
               speedLeft = maxspeed;
            }
            if(speedRight > maxspeed){
              speedRight = maxspeed;
             }
            drive(speedLeft, speedRight);
        }
    }

    private void evaluateSpeed() {
        speedLeft = k[0][1] * sensors[1].getValue()  + k[1][1] * sensors[2].getValue()  + k[2][1] * sensors[3].getValue() ;
        speedRight = k[0][0] * sensors[0].getValue()  + k[1][0] * sensors[5].getValue()  + k[2][0] * sensors[4].getValue() ;
        System.out.println("speedLeft" + speedLeft);
        System.out.println("speedRight" + speedRight);   
    }
    

    public static void main(String[] args) {
        cProportionalController controller = new cProportionalController();
        controller.run();
    }
}

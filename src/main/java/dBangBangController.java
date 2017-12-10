import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;

public class dBangBangController extends bangBangSuperController {

    private static int TIME_STEP = 15;
    private static int SENSOR_VALUE = 1900;
    private DistanceSensor[] sensors;
    private static int S_FRONT_LEFT = 0; // Sensor front left
    private static int S_FRONT_RIGHT = 1; // Sensor front right
    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 1000; // max. motor speed


    public dBangBangController() {
        super();
        // get distance sensors and save them in array
        sensors = new DistanceSensor[]{getDistanceSensor("ps7"), getDistanceSensor("ps0")};
        for (int i = 0; i < 2; i++){
            sensors[i].enable(10);
            }
    }


    public void run() {
        while (step(TIME_STEP) != -1) {
         if (sensors[S_FRONT_LEFT].getValue() < SENSOR_VALUE && sensors[S_FRONT_RIGHT].getValue() < SENSOR_VALUE) {
		driveForward();
		System.out.println("Forward");
    	} else if (sensors[S_FRONT_LEFT].getValue() < sensors[S_FRONT_RIGHT].getValue()) {
		driveRight();
		System.out.println("Right");
	} else {
		driveLeft();
		System.out.println("Left");
	}
	//System.out.println("ps7: " + sensors[S_FRONT_LEFT].getValue());
	//System.out.println("ps0: " + sensors[S_FRONT_RIGHT].getValue());
	}
    }


    public static void main(String[] args) {
        dBangBangController controller = new dBangBangController();
        controller.run();
    }
}


import com.cyberbotics.webots.controller.DifferentialWheels;

public abstract class bangBangSuperController extends DifferentialWheels {

    private static int TIME_STEP = 15;

    private static int MAX_SENSOR_VALUE = 200;

    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 1000; // max. motor speed

    private void driveRight() {
        setSpeed(MAX_SPEED, MIN_SPEED);
    }

    private void driveLeft() {
        setSpeed(MIN_SPEED, MAX_SPEED);
    }

    private void driveForward() {
        setSpeed(MAX_SPEED, MAX_SPEED);
    }

    public abstract void run();


}

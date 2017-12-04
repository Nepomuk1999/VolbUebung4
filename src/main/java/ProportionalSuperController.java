
import com.cyberbotics.webots.controller.DifferentialWheels;

public abstract class ProportionalSuperController extends DifferentialWheels {


    private static int MAX_SENSOR_VALUE = 200;

    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 1000; // max. motor speed

    protected void driveForward(double speedLeft, double speedRight) {
        setSpeed(speedLeft, speedRight);
    }

    protected void driveBackward() {
        setSpeed(-MAX_SPEED, -MAX_SPEED);
    }

    protected void turnRight() {
        setSpeed(MAX_SPEED, MIN_SPEED);
    }

    protected void turnLeft() {
        setSpeed(MIN_SPEED, MAX_SPEED);
    }

    public abstract void run();
}

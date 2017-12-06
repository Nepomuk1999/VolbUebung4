
import com.cyberbotics.webots.controller.DifferentialWheels;

public abstract class bangBangSuperController extends DifferentialWheels {


    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 1000; // max. motor speed

    protected void driveRight() {
        setSpeed(MAX_SPEED, MIN_SPEED);
    }

    protected void driveLeft() {
        setSpeed(MIN_SPEED, MAX_SPEED);
    }

    protected void driveForward() {
        setSpeed(MAX_SPEED, MAX_SPEED);
    }

    protected void turnRight() {
        setSpeed(MAX_SPEED, -MAX_SPEED);
    }

    protected void turnLeft() {
        setSpeed(-MAX_SPEED, MAX_SPEED);
    }

    protected void stop() {
        setSpeed(0, 0);
    }

    public abstract void run();


}

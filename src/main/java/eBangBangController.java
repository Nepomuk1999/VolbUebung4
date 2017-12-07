import com.cyberbotics.webots.controller.DistanceSensor;

public class eBangBangController extends bangBangSuperController {

    private static int TIME_STEP = 15;
    private DistanceSensor[] sensors;
    private double LIMIT = 500;
    private static final int FRONT = 0;
    private static final int LEFT = 1;
    private static final int CORNER = 2;


    public eBangBangController() {
        super();
        sensors = new DistanceSensor[]{getDistanceSensor("ps7"), getDistanceSensor("ps5"), getDistanceSensor("ps6")};
        for (DistanceSensor ds : sensors) {
            ds.enable(10);
        }
    }

    @Override
    public void run() {
        while (step(TIME_STEP) != -1) {
            double[] sensorValues = getSensorValues();
            if (sensorValues[LEFT] < LIMIT && sensorValues[FRONT] < LIMIT) {
                driveForward();
            } else if (sensorValues[CORNER] > LIMIT) {
                driveRight();
            } else if (sensorValues[LEFT] > LIMIT) {
                driveLeft();
            } else if (sensorValues[FRONT] > LIMIT) {
                driveRight();
            }

        }
    }

    private double[] getSensorValues() {
        return new double[]{sensors[FRONT].getValue(), sensors[LEFT].getValue(), sensors[CORNER].getValue()};
    }

    public static void main(String[] args) {
        eBangBangController controller = new eBangBangController();
        controller.run();
    }

}

import com.cyberbotics.webots.controller.DistanceSensor;

public class eProportionalController extends ProportionalSuperController {

    private static int TIME_STEP = 15;
    private DistanceSensor[] sensors;
    private static final int FRONT_LEFT = 0;
    private static final int FRONT_RIGHT = 1;
    private static final int LEFT = 2;
    private static final int CORNER_LEFT = 3;
    private static final int CORNER_RIGHT = 4;
    private double speedLeft;
    private double speedRight;
    double[][] k = {{1}, {1}, {0.7}, {1}, {0.7}};
    double c = 200;


    public eProportionalController() {
        super();
        sensors = new DistanceSensor[]{getDistanceSensor("ps7"), getDistanceSensor("ps0"), getDistanceSensor("ps5"),
                getDistanceSensor("ps6"), getDistanceSensor("ps1")};
        for (DistanceSensor ds : sensors) {
            ds.enable(10);
        }
    }

    @Override
    public void run() {
        while (step(TIME_STEP) != -1) {
            evaluateSpeed();
            drive(speedLeft, speedRight);
        }
    }

    private void evaluateSpeed() {
        double[] sensorValues = new double[]{((sensors[FRONT_LEFT].getValue() / 2800 / 3) * 1000),
                ((sensors[FRONT_RIGHT].getValue() / 2800 / 3) * 1000), ((sensors[LEFT].getValue() / 2800 / 3) * 1000),
                ((sensors[CORNER_LEFT].getValue() / 2800 / 3) * 1000), ((sensors[CORNER_RIGHT].getValue() / 2800 / 3) * 1000)};

        speedLeft = c + sensorValues[FRONT_LEFT] * k[FRONT_LEFT][0] + sensorValues[FRONT_RIGHT] * k[FRONT_RIGHT][0] + sensorValues[CORNER_LEFT] * k[CORNER_LEFT][0] + sensorValues[CORNER_RIGHT] * k[CORNER_RIGHT][0];
        speedRight = c + sensorValues[LEFT] * k[LEFT][0];

    }

    public static void main(String[] args) {
        eProportionalController controller = new eProportionalController();
        controller.run();
    }
}

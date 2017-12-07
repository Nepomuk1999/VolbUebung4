import com.cyberbotics.webots.controller.LightSensor;

public class bProportionalController extends ProportionalSuperController {

    private static int TIME_STEP = 20;
    private LightSensor[] lightSensors;
    private double speedLeft;
    private double speedRight;
    double[][] k = {{1, 1}, {0.5, 0.5}, {-1, -1}};
    private double maxspeed;


    public bProportionalController() {
        super();
        lightSensors = new LightSensor[]{getLightSensor("ls0"), getLightSensor("ls7"),
                getLightSensor("ls2"), getLightSensor("ls5"),
                getLightSensor("ls3"), getLightSensor("ls4")};
        for (LightSensor l : lightSensors) {
            l.enable(10);
        }
    }

    public void run() {
        while (step(TIME_STEP) != -1) {
            evaluateSpeed();
            if (lightFound()) {
                speedLeft = 0;
                speedRight = 0;
            }
            drive(speedLeft, speedRight);
        }

    }

    private boolean lightFound() {
        double[] values = getLightSensorValues();
        for (double v : values) {
            if (v == 0) {
                return true;
            }
        }
        return false;
    }

    private void evaluateSpeed() {
        double[] sensorValues = new double[lightSensors.length];
        for (int i = 0; i < sensorValues.length; i++) {
            //Werte zwischen 0.0 und 1000.0 skalieren  4150 max wert des simulators nach start ohne licht
            sensorValues[i] = (1 - (lightSensors[i].getValue() / (4150 / 3))) * (1000 / 3);
            System.out.println("sensV" + sensorValues[i]);
        }
        speedLeft = (k[0][1] * sensorValues[0]) + (k[1][1] * sensorValues[2]) + (k[2][1] * sensorValues[2]);
        speedRight = (k[0][0] * sensorValues[1]) + (k[1][0] * sensorValues[3]) + (k[2][0] * sensorValues[2]);
        System.out.println("r" + speedRight);
        System.out.println("l" + speedLeft);
    }

    private double[] getLightSensorValues() {
        double[] sensorValues = new double[lightSensors.length];
        for (int i = 0; i < sensorValues.length; i++) {
            //Werte zwischen 0.0 und 1000.0 skalieren  4150 max wert des simulators nach start ohne licht
            sensorValues[i] = lightSensors[i].getValue();
        }
        return sensorValues;
    }


    public static void main(String[] args) {
        bProportionalController controller = new bProportionalController();
        controller.run();
    }
}

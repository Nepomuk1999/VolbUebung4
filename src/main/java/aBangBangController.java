

import com.cyberbotics.webots.controller.LightSensor;

public class aBangBangController extends bangBangSuperController {

    private static int TIME_STEP = 15;
    private LightSensor[] lightSensors;
    boolean left = false;
    boolean right = false;

    public aBangBangController() {
        super();
        lightSensors = new LightSensor[]{getLightSensor("ls0"), getLightSensor("ls1"),
                getLightSensor("ls2"), getLightSensor("ls3"), getLightSensor("ls4"),
                getLightSensor("ls5"), getLightSensor("ls6"), getLightSensor("ls7")};
        for (LightSensor ls : lightSensors) {
            ls.enable(10);
        }
    }


    public void run() {
        while (step(TIME_STEP) != -1) {
            double[] values = getSensorValues();
            checkForLight(values);
            if (left == right) {
                driveForward();
            } else if (right == true) {
                driveRight();
            } else {
                driveLeft();
            }

            resetbool();
        }
    }

    private void resetbool() {
        left = false;
        right = false;
    }

    private void checkForLight(double[] lightSensorValues) {
        for (int i = 0; i < lightSensorValues.length; i++) {
            if (i < 4) {
                if (lightSensorValues[i] < 150) {
                    right = true;
                }
            } else {
                if (lightSensorValues[i] < 150) {
                    left = true;
                }
            }

        }
    }

    private double[] getSensorValues() {
        double[] sensorValues = new double[8];
        for (int i = 0; i < sensorValues.length; i++) {
            sensorValues[i] = lightSensors[i].getValue();
        }
        return sensorValues;
    }

    public static void main(String[] args) {
        aBangBangController controller = new aBangBangController();
        controller.run();
    }
}

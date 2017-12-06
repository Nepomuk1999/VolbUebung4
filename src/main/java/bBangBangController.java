import com.cyberbotics.webots.controller.LightSensor;

public class bBangBangController extends bangBangSuperController {
    private static int TIME_STEP = 15;
    private LightSensor[] lightSensors;
    double left = 0;
    double right = 0;

    public bBangBangController() {
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
            if (lightReached()) {
                stop();
            } else if (left == right) {
                driveForward();
            } else if (right < left) {
                driveRight();
            } else {
                driveLeft();
            }
            resetbool();
        }
    }

    private boolean lightReached() {
        if (lightSensors[0].getValue() < 50 && lightSensors[7].getValue() < 50) {
            return true;
        } else {
            return false;
        }
    }

    private void resetbool() {
        left = 0;
        right = 0;
    }

    private void checkForLight(double[] lightSensorValues) {
        for (int i = 0; i < lightSensorValues.length; i++) {
            if (i < 4) {
                right += lightSensorValues[i];
            } else {
                left += lightSensorValues[i];
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
        bBangBangController controller = new bBangBangController();
        controller.run();
    }
}

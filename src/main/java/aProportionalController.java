import com.cyberbotics.webots.controller.LightSensor;

public class aProportionalController extends ProportionalSuperController {

    private static int TIME_STEP = 15;
    private LightSensor[] lightSensors;
    //left 0,0 right 1,0
    double[][] lightIntensity;

    public aProportionalController() {
        super();
        lightSensors = new LightSensor[]{getLightSensor("ls0"), getLightSensor("ls1"),
                getLightSensor("ls3"), getLightSensor("ls4"), getLightSensor("ls5"),
                getLightSensor("ls6"), getLightSensor("ls7")};
        for (LightSensor ls : lightSensors) {
            ls.enable(10);
        }
    }


    public void run() {
        while (step(TIME_STEP) != -1) {
            readSensoreValues();
            driveForward(lightIntensity[0][0], lightIntensity[1][0]);
            resetValues();
        }

    }

    private void resetValues() {
        lightIntensity[0][0] = 0;
        lightIntensity[1][0] = 0;
    }

    private void readSensoreValues() {
        for (int i = 0; i <= 7; i++) {
            if (i < 4) {
                lightIntensity[1][0] += lightSensors[i].getValue();
            } else {
                lightIntensity[0][0] += lightSensors[i].getValue();
            }
        }
    }

    public static void main(String[] args) {
        aProportionalController controller = new aProportionalController();
        controller.run();
    }
}



import com.cyberbotics.webots.controller.LightSensor;

public class aBangBangController extends bangBangSuperController {

    private LightSensor[] lightSensors;

    public aBangBangController() {
        super();
        lightSensors = new LightSensor[]{getLightSensor("ls0"), getLightSensor("ls1"),
                getLightSensor("ls3"), getLightSensor("ls4"), getLightSensor("ls5"),
                getLightSensor("ls6"), getLightSensor("ls7")};
        for (LightSensor ls : lightSensors) {
            ls.enable(10);
        }
    }


    public void run() {

    }
}

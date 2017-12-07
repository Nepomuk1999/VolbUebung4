import com.cyberbotics.webots.controller.DistanceSensor;

public class eProportionalController extends ProportionalSuperController {

    private double[][] matrix;
    private DistanceSensor[] sensors;

    public eProportionalController() {
        super();
        sensors = new DistanceSensor[8];
        for (int i = 0; i < sensors.length; i++) {
            sensors[i] = getDistanceSensor("ps" + i);
            sensors[i].enable(10);
        }
        // Inizialize Matrix
        matrix = new double[][]{{0.8, 0.7},
                {0.4, 0.3},
                {1.0, 0.9},
                {0.6, 0.5}};
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        eProportionalController controller = new eProportionalController();
        controller.run();
    }
}

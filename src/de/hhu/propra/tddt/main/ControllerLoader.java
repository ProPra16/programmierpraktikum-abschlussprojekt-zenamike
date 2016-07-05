import javafx.scene.Scene;

/**
 * Created by Nadine on 03.07.16.
 */
public abstract class ControllerLoader {

    protected CustomController controller;
    protected Scene scene;

    public abstract CustomController controller() throws Exception;

    public abstract Scene scene();

}

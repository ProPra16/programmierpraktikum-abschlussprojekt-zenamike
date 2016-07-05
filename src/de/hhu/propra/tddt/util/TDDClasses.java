import java.util.ArrayList;

/**
 * Created by Nadine on 04.07.16.
 */
public class TDDClasses {

    private String name;
    private ArrayList<String> classes;
    private ArrayList<String> tests;

    private boolean BabySteps;
    private boolean Tracking;

    public TDDClasses(String name,
                      ArrayList<String> classes,
                      ArrayList<String> tests,
                      boolean BabySteps,
                      boolean Tracking){

        this.name = name;
        this.classes = classes;
        this.tests = tests;
        this.BabySteps = BabySteps;
        this.Tracking = Tracking;
    }

    public boolean isTracking() {
        return Tracking;
    }

    public boolean isBabySteps() {
        return BabySteps;
    }

    public ArrayList<String> getTests() {
        return tests;
    }

    public ArrayList<String> getClasses() {
        return classes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClasses(ArrayList<String> classes) {
        this.classes = classes;
    }

    public void setTests(ArrayList<String> tests) {
        this.tests = tests;
    }

    public void setBabySteps(boolean babySteps) {
        BabySteps = babySteps;
    }

    public void setTracking(boolean tracking) {
        Tracking = tracking;
    }

    @Override
    public String toString() {
        return "TDDClasses{" +
                "name='" + name + '\'' +
                ", classes=" + classes +
                ", tests=" + tests +
                ", BabySteps=" + BabySteps +
                ", Tracking=" + Tracking +
                '}';
    }
}

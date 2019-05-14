import java.util.List;

public class Step {

    private String name;

    private List<String> parameters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public List<String> getParameters() {
        return parameters;
    }
}

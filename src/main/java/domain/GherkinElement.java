package domain;

public abstract class GherkinElement {

    private final Location location;

    private final String text;

    protected GherkinElement(Location location, String text) {
        this.location = location;
        this.text = text;
    }

    public Location getLocation() {
        return location;
    }

    public String getText() {
        return text;
    }
}

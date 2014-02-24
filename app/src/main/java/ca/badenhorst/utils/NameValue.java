package ca.badenhorst.utils;

/**
 * Created by wade on 22/02/14.
 */
public class NameValue {
    String name, value;

    @Override
    public String toString() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public NameValue(String name, String value) {
        this.name = name;
        this.value = value;
    }
}

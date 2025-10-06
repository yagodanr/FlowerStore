package ucu.edu.ua.apps;

public enum FlowerColor {
    RED("#FF0000"), GREEN("#008000"), WHITE("#FFFFFF");

    private String hexColor;
    FlowerColor(String color) {
        hexColor = color;
    }

    @Override
    public String toString() {
        return hexColor;
    }
}

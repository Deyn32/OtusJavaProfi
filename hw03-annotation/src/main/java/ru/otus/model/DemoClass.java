package ru.otus.model;

public class DemoClass {
    private int x;
    private int y;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int sum() {
        return x + y;
    }

    public int area() {
        return x * y;
    }

    public int minus() {
        return y - x;
    }

    @Override
    public String toString() {
        return "Тест DemoClass {\n" +
                "x=" + x +
                "\n, y=" + y +
                "\n}";
    }
}

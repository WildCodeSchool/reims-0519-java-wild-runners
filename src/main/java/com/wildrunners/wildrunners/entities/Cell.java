package com.wildrunners.wildrunners.entities;

public class Cell {
    private String name;
    private boolean isObstacle;
    private boolean isDiscovered;

    public Cell(String name, boolean isObstacle) {
        this.name = name;
        this.isObstacle = isObstacle;
        this.isDiscovered = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isObstacle() {
        return isObstacle;
    }

    public void setObstacle(boolean isObstacle) {
        this.isObstacle = isObstacle;
    }

    public boolean isDiscovered() {
        return isDiscovered;
    }

    public void setDiscovered(boolean isDiscovered) {
        this.isDiscovered = isDiscovered;
    }
}
package com.wildrunners.wildrunners.entities;

public class Cell {
    private String name;
    private boolean isObstacle;

    public Cell(String name, boolean isObstacle) {
        this.name = name;
        this.isObstacle = isObstacle;
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
}
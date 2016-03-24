package edu.nsu.ir.Cases;

public class CCase {
    private String name;
    private boolean status;

    public CCase() {
    }

    public CCase(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CCase{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}

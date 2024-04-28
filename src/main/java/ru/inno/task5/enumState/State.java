package ru.inno.task5.enumState;

public enum State {
    CLOSE("Закрыт"),
    OPEN("Открыт"),
    RESERV("Зарезервирован"),
    DELETE("Удален");

    private String name;

    State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
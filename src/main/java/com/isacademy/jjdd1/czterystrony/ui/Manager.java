package com.isacademy.jjdd1.czterystrony.ui;

public class Manager {
    private Command command;
    public Manager(Command command) {
        this.command = command;
    }

    public static void commandExecute(Command command) {
        command.execute();
    }




}

package com.isacademy.jjdd1.czterystrony.ui;

public class ExitCommand implements Command {

    @Override
    public void execute() {
        System.exit(1);
    }
}

package com.Puj0.RPGSpringBoot.domain.command;

public class CommandDispatcher {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
        execute();
    }

    private void execute() {
        command.execute();
    }
}

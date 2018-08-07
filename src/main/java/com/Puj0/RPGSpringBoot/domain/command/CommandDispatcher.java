package com.Puj0.RPGSpringBoot.domain.command;

public class CommandDispatcher {

    private ICommand command;

    public void setCommand(ICommand command) {
        this.command = command;
        execute();
    }

    private void execute() {
        command.execute();
    }
}

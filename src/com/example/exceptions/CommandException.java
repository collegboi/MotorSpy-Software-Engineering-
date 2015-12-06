package com.example.exceptions;

public class CommandException extends Exception{

	public CommandException() {
    }

    public CommandException(String aMessage) {
        super(aMessage);
    }
}


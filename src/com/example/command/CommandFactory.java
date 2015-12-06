package com.example.command;

import com.example.exceptions.CommandException;

public class CommandFactory {

    private static CommandFactory factory = null;

    private CommandFactory() {
    }

    public synchronized static CommandFactory getInstance() {
        if (factory == null) {      // first time

            factory = new CommandFactory();
        }
        return factory;
    }
    

   public synchronized Command createCommand(String commandStr) throws CommandException {

    	Command command = null;
    	String packageName = "com.example.command.";    	
    	
        try {
            commandStr = packageName + commandStr + "Command";

            //...
            Class<?> theClass = Class.forName(commandStr);
            
            //...
            Object theObject = theClass.newInstance();

            command = (Command) theObject;
        } catch (InstantiationException e) {
            throw new CommandException("CommandFactory: " + e);
        } catch (IllegalAccessException e) {
            throw new CommandException("CommandFactory: " + e);
        } catch (ClassNotFoundException e) {
            throw new CommandException("CommandFactory: " + e);
        }

    	
    	return command;	

    }
}
    
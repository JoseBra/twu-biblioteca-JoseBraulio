package twu.biblioteca.agents;

import twu.biblioteca.commands.Command;
import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Library;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInteractionAgent {
    private Library chosenLibrary;
    private List<Command> availableCommands;

    public UserInteractionAgent() {
        this.availableCommands = new ArrayList<>();
    }

    public void showWelcomeMessage() {
        System.out.print("Welcome to the new brand Biblioteca System!\n");
    }

    public void showMainMenu() {
        System.out.print("Here is a list of all the available commands:\n");
        availableCommands.forEach(command -> System.out.println("\t" + command.getUsageExplanation()));
    }

    public void setAvailableCommands(List<Command> availableCommands) {
        this.availableCommands = availableCommands;
    }
    public void setChosenLibrary(Library library){
        this.chosenLibrary = library;
    }

    public List<Command> getAvailableCommands() {
        return availableCommands;
    }

    public void awaitUserInput() {
        Class commandClass = ConsoleInputParser.getCommandClassFromString(new Scanner(System.in).nextLine());
        if (commandClass == null){
            printInvalidOptionMessage();
        }else{
            executeInputChosenCommand(commandClass);
        }
    }

    private void executeInputChosenCommand(Class commandClass) {
        for(Command command : availableCommands){
            if (command.getClass().equals(commandClass)){
                printStringToUser(command.execute(chosenLibrary));
                return;
            }
        }
        printInvalidOptionMessage();
    }

    private void printInvalidOptionMessage(){
        printStringToUser("Select a valid option!");
    }

    private void printStringToUser(String message){
        System.out.println(message);
    }
}

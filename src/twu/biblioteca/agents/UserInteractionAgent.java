package twu.biblioteca.agents;

import twu.biblioteca.commands.Command;
import twu.biblioteca.environment.Library;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<String> parsedUserInput = new ArrayList<>();
        try{
            parsedUserInput = extractCommandAndArgumentsFromInputLine(new Scanner(System.in).nextLine());
            Class commandClass = ConsoleInputParser.getCommandClassFromString(parsedUserInput.get(0));
            executeInputChosenCommand(commandClass, parsedUserInput.get(1));
        }catch (Exception e){
            printInvalidOptionMessage();
        }
    }

    private List<String> extractCommandAndArgumentsFromInputLine(String inputLine) {
        ArrayList<String> parsedLine = new ArrayList<>(Arrays.asList(inputLine.split(" ", 2)));
        if (parsedLine.size() < 2) parsedLine.add("");
        return parsedLine;
    }

    private void executeInputChosenCommand(Class commandClass, String arguments) {
        for(Command command : availableCommands){
            if (command.getClass().equals(commandClass)){
                printStringToUser(command.execute(chosenLibrary, arguments));
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

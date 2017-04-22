package twu.biblioteca.agents;

import twu.biblioteca.commands.Command;
import twu.biblioteca.environment.Library;
import twu.biblioteca.environment.User;

import java.util.*;

public class UserInteractionAgent {
    private Library chosenLibrary;
    private List<Command> availableCommands;
    private LoginUserManager loginUserManager;

    public UserInteractionAgent() {
        this.loginUserManager = LoginUserManager.getInstance();
        this.availableCommands = new ArrayList<>();
    }

    public UserInteractionAgent(Library chosenLibrary, List<Command> availableCommands) {
        this.chosenLibrary = chosenLibrary;
        this.availableCommands = availableCommands;
        this.loginUserManager = LoginUserManager.getInstance();
    }

    public void showWelcomeMessage() {
        System.out.print("Welcome to the new brand Biblioteca System!\n");
    }

    public void showMainMenu() {
        System.out.print("Here is a list of all the available commands:\n");
        User loggedUser = loginUserManager.getLoggedUser();
        availableCommands.stream().filter(command -> !command.isRequireLogin() || command.isRequireLogin() && loginUserManager.isUserLogged()
                                          && loggedUser.getUserRoles().contains(command.getRequiredRole()))
                                  .forEach(command -> System.out.println("\t" + command.getUsageExplanation()));
    }

    public void setAvailableCommands(List<Command> availableCommands) {
        this.availableCommands = availableCommands;
    }
    public void setChosenLibrary(Library library){
        this.chosenLibrary = library;
    }

    public void awaitUserInput() {
        List<String> parsedUserInput = new ArrayList<>();
        try{
            parsedUserInput = extractCommandAndArgumentsFromInputLine(new Scanner(System.in).nextLine());
            Map.Entry<Class, Class> commandAndTargetClass = ConsoleInputParser.getCommandAndTargetClassFromString(parsedUserInput.get(0));
            executeInputChosenCommand(commandAndTargetClass, parsedUserInput.get(1));
        }catch (Exception e){
            printInvalidOptionMessage();
        }
    }

    private List<String> extractCommandAndArgumentsFromInputLine(String inputLine) {
        ArrayList<String> parsedLine = new ArrayList<>(Arrays.asList(inputLine.split(" ", 2)));
        if (parsedLine.size() < 2) parsedLine.add("");
        return parsedLine;
    }

    private void executeInputChosenCommand(Map.Entry<Class, Class> commandAndTargetClass, String arguments) {
        for(Command command : availableCommands){
            if (command.getClass().equals(commandAndTargetClass.getKey())){
                printStringToUser(command.execute(chosenLibrary, arguments, commandAndTargetClass.getValue()));
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

    public void startSystem() {
        showWelcomeMessage();
        showMainMenu();
        awaitUserInput();
    }
}

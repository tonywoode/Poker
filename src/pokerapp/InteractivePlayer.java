package pokerapp;

import pokerapp.console.Console;
import pokerapp.console.ExchangeCardsInterpreter;
import pokerapp.console.ExchangeSetting;
import pokerapp.utils.textformat.FormatStringException;
import pokerapp.utils.textformat.IllegalFormatCodeException;

/**
 * @author Steve
 * @author Ari
 * @author Sam
 * @version 1.0
 */
public class InteractivePlayer extends Player {

  private final String VALID_INPUT = "(([12345]\\s){0,2}[12345])|^$";
  private final int MAXPOS = 5;


  public InteractivePlayer(String name) {
    setPlayerName(name);
  }

  public InteractivePlayer() {
    this("");
  }

  public String getPlayerNameFromUser(Console console) throws FormatStringException, IllegalFormatCodeException {
    console.writeMessage("What's your name?");
    setPlayerName(console.readLine());
    console.writeMessage("Hello " + getPlayerName() + ", and welcome to pokerapp.");
    return getPlayerName();
  }

  @Override
  public void playTurn(Console console, Deck deck, ExchangeSetting exchangeSetting) throws FormatStringException, IllegalFormatCodeException {

    for (int numberExchanges = 0; numberExchanges < exchangeSetting.getNumTimes(); ++numberExchanges) {

      //    console.writeMessage("Your hand is: " + getHand().toUserString());
      console.writeMessage("Your hand is: " + getHand().toFancyUserString());
      console.writeMessage("Would you like me to sort your hand?\n"); //TODO:sort the hand
      console.writeMessage("You can change between 0 and " + exchangeSetting.getNumCards() + " cards.");
      console.writeMessage("Write the position of each card you want to exchange, " +
          "separated by spaces. Then press enter");

      String command = console.readString();

      while (!command.matches(VALID_INPUT) || !isUniquePos(command)) {
        console.writeMessage("Please enter valid input. Then press enter");
        command = console.readString();
      }

      ExchangeCardsInterpreter interpreter = new ExchangeCardsInterpreter(command);
      interpreter.execute(getHand(), deck);

      //    console.writeMessage("Your new hand is: " + getHand().toUserString());
      console.writeMessage("Your new hand is: " + getHand().toFancyUserString());
    }
  }
  /* Designed using variant of the code suggested here: http://stackoverflow
  .com/questions/3951547/java-array-finding-duplicates
  */

  private boolean isUniquePos(String command) {
    if (command.matches(VALID_INPUT) && !command.isEmpty()) {
      boolean[] bitmap = new boolean[MAXPOS + 1];
      java.util.Arrays.fill(bitmap, false);
      for (String strPos : command.split(" ")) {
        int pos = Integer.parseInt(strPos);
        if (!bitmap[pos])
          bitmap[pos] = true;
        else
          return false;
      }
    }
    return true;
  }


}
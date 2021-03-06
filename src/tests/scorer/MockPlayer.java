package tests.scorer;

import pokerapp.*;
import pokerapp.console.Console;
import pokerapp.console.ExchangeSetting;
import pokerapp.utils.textformat.FormatStringException;
import pokerapp.utils.textformat.IllegalFormatCodeException;

import java.io.IOException;

/**
 * <p>
 *   A stub player for testing, where we can provide a hand
 * </p>
 *
 * @author Steve
 * @version 1
 */
public class MockPlayer extends Player {

  public MockPlayer(Hand hand, int n) {
    this.hand = hand;
    setPlayerName("Player #" + n);
  }

  @Override
  public void visit(PlayerVisitor visitor) {

  }
}

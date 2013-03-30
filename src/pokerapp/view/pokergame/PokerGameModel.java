package pokerapp.view.pokergame;

import lombok.Getter;
import pokerapp.*;
import pokerapp.console.Console;
import pokerapp.console.ExchangeSetting;
import pokerapp.console.StandardConsole;
import pokerapp.scorer.PokerGameEvaluator;
import pokerapp.scorer.scoredhands.ScoredHand;
import pokerapp.utils.textformat.StringFormatter;

import java.io.IOException;

/**
 * Sets the basic parameters for a generic pokergame - players and dealer and setting a winner
 * @author Steve
 * @version 1
 */
public class PokerGameModel {
  private final Dealer dealer;
  @Getter
  private final Player interactivePlayer;

  private final ComputerPlayer computerPlayer;
  private final Players players;
  private final PokerGameEvaluator pokerGameEvaluator;

  public PokerGameModel(Dealer dealer, InteractivePlayer interactivePlayer, ComputerPlayer computerPlayer,
                        Players players, PokerGameEvaluator pokerGameEvaluator) {
    this.dealer = dealer;
    this.interactivePlayer = interactivePlayer;
    this.computerPlayer = computerPlayer;
    this.players = players;
    this.pokerGameEvaluator = pokerGameEvaluator;
    this.players.register(interactivePlayer, computerPlayer);
  }

  public Player getComputerPlayer() { return computerPlayer; }

  /**
   * Getter for the dealer dishing out a hand
   */
  public void dealCards() {
    dealer.dealCards(5, players);
  }

  public GameResult evaluate() {
    return pokerGameEvaluator.evaluate(players.getPlayers());
  }

  public void letComputerPlayerPlay() throws IOException {
    ExchangeSetting exchangeSettings = new ExchangeSetting(3, 1);

    ScoredHand scoredHand = pokerGameEvaluator.score(getComputerPlayer().getHand());

    computerPlayer.playTurn(dealer, scoredHand, exchangeSettings);
  }
}

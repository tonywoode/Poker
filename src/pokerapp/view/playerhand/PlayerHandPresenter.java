package pokerapp.view.playerhand;

import pokerapp.Card;
import pokerapp.Dealer;
import pokerapp.Hand;
import pokerapp.Player;
import pokerapp.utils.textformat.FormatStringException;
import pokerapp.utils.textformat.IllegalFormatCodeException;
import pokerapp.view.Displayable;
import pokerapp.view.events.EventSource;

import javax.swing.*;
import java.io.IOException;

/**
 * PlayerHand presenter MVP pattern: presenter will communicate actions from and to pokerapp.view
 * @author Tony
 */
public class PlayerHandPresenter implements PlayerHandViewActionListener, Displayable {

  private final PlayerHandView plHandView;
  private Player player;
  private Hand hand;

  private final Dealer dealer;

  private final EventSource eventSource;

  public PlayerHandPresenter(EventSource eventSource, PlayerHandView plHandView, Dealer dealer) {
    this.eventSource = eventSource;
    this.plHandView = plHandView;
    this.plHandView.addListener(this);
    this.dealer = dealer;
  }

  public void init(Player player, Hand hand) throws FormatStringException, IOException, IllegalFormatCodeException {
    this.player = player;
    this.hand = hand;
    this.plHandView.setHand(hand);
  }

  public void onHold() throws IOException {
	  plHandView.setHand(hand);

	  eventSource.fire(new TurnCompletedEvent());
  }

  public void onExchange() throws IOException {
    for (Card card : plHandView.getSelectedCards()) {
      Card newCard = dealer.exchangeCard(hand, card);
    }
    plHandView.setHand(hand);
    eventSource.fire(new TurnCompletedEvent());
  }

  @Override
  public JComponent getView() {
    return plHandView.getView();
  }
}


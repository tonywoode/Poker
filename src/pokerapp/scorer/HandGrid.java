package pokerapp.scorer;

import pokerapp.Card;
import pokerapp.Hand;

/**
 *
 * @author Ari
 *
 * represents the hand as a matrix
 */
public class HandGrid {

  public int[][] matrix = new int[Card.NUM_SUITS + 1][HandRanks.HIST_SIZE];

  public HandGrid(Hand other_hand) {
    Hand hand = other_hand;

    for (Card c : hand) {
      ++matrix[c.getSuit().getNumber()][c.getRank().getNumber()];
    }
  }
}

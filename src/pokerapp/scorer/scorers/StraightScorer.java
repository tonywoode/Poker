package pokerapp.scorer.scorers;

import pokerapp.Card;
import pokerapp.Hand;
import pokerapp.scorer.HandRanks;
import pokerapp.scorer.scoredhands.ScoredHand;
import pokerapp.scorer.scoredhands.StraightScoredHand;
import pokerapp.utils.Constants;

/**
 *
 * @author Steve
 * @author Ari
 *
 * checks if the hand is a straight and assigns a rank
 * the rank is the highest card in the straight
 */
public class StraightScorer extends HandScorer {

  protected ScoredHand resolveCore(Hand hand) {
    HandRanks rh = new HandRanks(hand);
    int rank = 0;

    if (rh.toString().contains(Constants.LOW_STRAIGHT_SIGNATURE)||
        rh.toString().contains(Constants.HIGH_STRAIGHT_SIGNATURE)) {
      for (Card c : hand) {
        if (c.getRank() > rank)
          rank = c.getRank();
      }
      return new StraightScoredHand(getHandNumber(), hand, rank);
    }
    else return null;
  }
}

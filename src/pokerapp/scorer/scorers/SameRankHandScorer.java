package pokerapp.scorer.scorers;

import pokerapp.Hand;
import pokerapp.Rank;
import pokerapp.scorer.HandRanks;
import pokerapp.scorer.domain.SameRankCards;
import pokerapp.scorer.scoredhands.SameRankScoredHand;
import pokerapp.scorer.scoredhands.ScoredHand;

/**
 * Created with IntelliJ IDEA.
 * User: steve
 * Date: 06/03/13
 * Time: 01:25
 * To change this template use File | Settings | File Templates.
 */
public class SameRankHandScorer extends HandScorer {
  private int rankValue, rankCount;
  private String name;

  public SameRankHandScorer(String name, int rankValue, int rankCount) {
    this.name = name;
    this.rankValue = rankValue;
    this.rankCount = rankCount;
  }

  @Override
  protected ScoredHand resolveCore(Hand hand) {
    HandRanks hr = new HandRanks(hand);

    if (hr.countMultiple(rankValue) == rankCount)
      return createHandCategory(hand, hr);
    else
      return null;
  }

  // Pattern: Factory Method
  protected ScoredHand createHandCategory(Hand hand, HandRanks hr) {
    Rank rank = Rank.from(hr.getRankOfMultiple(rankValue));
    SameRankCards sequence = createSameRankSequence(hand, rank);
    return new SameRankScoredHand(getHandNumber(), hand, name, sequence, hand.getKickers(rank));
  }

  protected SameRankCards createSameRankSequence(Hand hand, Rank... rank) {
    return new SameRankCards(hand.cardsOfRank(rank));
  }
}

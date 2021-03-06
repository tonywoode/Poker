package pokerapp.scorer;

import pokerapp.scorer.scorers.HandScorer;

import java.util.List;

/**
 * Initialises the chain of responsibility based
 * HandScorers.
 * <p/>
 * The HandScorers are injected using DI.
 * <p/>
 * Implements the Factory and Builder patterns.
 *
 * @author Steve
 * @version 1.0
 */
public class HandScorerFactory {
  private static final int FIRST_SCORER = 0;
  private final HandScorer scorer;

  /**
   * Initialises the Chain of Responsibility for the HandScorers
   * <p/>
   * The chain's order is based solely on the list's order
   *
   * @param scorers ordered list of the HandScorers for the Chain of Responsibility
   */
  public HandScorerFactory(List<HandScorer> scorers) {
    if (scorers.size() < 1)
      throw new IllegalArgumentException("Must have at least one scorer");

    this.scorer = scorers.get(FIRST_SCORER);

    // avoid running off the end of the list in the following loop
    scorers.add(null);

    HandScorer currentScorer = this.scorer;

    for (int iter = 0; iter != scorers.size() - 1; ++iter) {
      HandScorer nextScorer = scorers.get(iter + 1);
      currentScorer.setNextScorer(nextScorer);
      currentScorer.setHandNumber(scorers.size() - iter);
      currentScorer = nextScorer;
    }
  }

  /**
   * @return The first HandScorer in the Chain of Responsibility
   */
  public HandScorer getScorer() {
    return scorer;
  }
}
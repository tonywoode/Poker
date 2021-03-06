package tests.scorer.resolvers;

import org.junit.Test;
import pokerapp.scorer.scoredhands.ScoredHand;
import pokerapp.scorer.scorers.SameRankHandScorer;
import pokerapp.scorer.scorers.TwoPairScorer;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Ari
 * Date: 23/02/13
 * Time: 14:31
 * To change this template use File | Settings | File Templates.
 */
public class TwoPairHandScorerTest extends HandScorerTestFixtureBase {

    public TwoPairHandScorerTest() {
      super(new TwoPairScorer());
    }

    @Test
    public void HandIsTwoPair_first4() throws FileNotFoundException {
      ScoredHand hand = resolveHand("D4", "S4", "D3", "C3", "C5");

      assertEquals("Is two", "Two pair", hand.getName());
    }

    @Test
    public void HandIsNotTwoPair() {
      ScoredHand hand = resolveHand("D4", "S4", "C2", "C3", "C5");

      assertEquals("Is not two", null, hand);
    }
}

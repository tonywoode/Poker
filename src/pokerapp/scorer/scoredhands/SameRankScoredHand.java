package pokerapp.scorer.scoredhands;

import lombok.Getter;
import pokerapp.Card;
import pokerapp.Hand;
import pokerapp.Rank;
import pokerapp.scorer.domain.SameRankCards;
import pokerapp.scorer.domain.Spares;
import pokerapp.scorer.typetag.SameRank;
import pokerapp.utils.Comparator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents one of (2,3,4) of a kind.
 *
 * @author Steve
 * @version 1.0
 */
public class SameRankScoredHand extends AbstractScoredHand<SameRankScoredHand> {

  private String name;
  @Getter private SameRankCards sameRankCards;
  @Getter private Spares spares;

  public SameRankScoredHand(int handNumber, Hand hand, String name, SameRankCards sameRankCards, Spares spares) {
    super(handNumber, hand, new SameRank(sameRankCards.getCount()));
    this.name = name;
    this.sameRankCards = sameRankCards;
    this.spares = spares;
//    ApplicationContext appContext = new ClassPathXmlApplicationContext("/scorer-application-context.xml");
//    this.handType = (SameRank)appContext.getBean(Integer.toString(handNumber));
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Rank getHandRank() {
    return sameRankCards.getRank();  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public List<Card> getRelevantCards() {
    List<Card> relevantArray = new ArrayList<Card>();
    Iterator<Card> iter = sameRankCards.iterator();

    while (iter.hasNext())
      relevantArray.add(iter.next());

    return relevantArray;
  }

  public Rank getRank() {
    return sameRankCards.getRank();
  }

  @Override
  protected int compareEqualCategories(SameRankScoredHand rhs) {
    return Comparator
        .begin(sameRankCards, rhs.sameRankCards)
        .next(spares, rhs.spares)
        .compare();
  }
}

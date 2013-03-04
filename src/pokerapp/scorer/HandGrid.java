package pokerapp.scorer;

import pokerapp.Card;
import pokerapp.Hand;
import pokerapp.utils.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: Ari
 * Date: 28/02/13
 * Time: 12:32
 * To change this template use File | Settings | File Templates.
 */
public class HandGrid {

    private Hand hand;

    public int [][] matrix = new int[Constants.NUM_SUITS + 1][Constants.NUM_RANKS + 1];

    public HandGrid(Hand other_hand){
        this.hand = other_hand;

        for(Card c: hand){
            ++matrix[c.getSuit().getNumber()][c.getRank()];
        }
    }

    //TODO: create Rank & Flush Histograms here?  maybe use facade?

}
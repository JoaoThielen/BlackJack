import java.util.Arrays;
import java.util.Collections;

public class Deck {
	
	private Card[] deck = new Card[52];
	private int i;

	public Deck() {
		for (i = 1; i < 14; i++) {
			deck[i-1] = new Card("Hearts", i);
		}
		for (; i < 27; i++) {
			deck[i-1] = new Card("Spades", i);
		}
		for (; i < 40; i++) {
			deck[i-1] = new Card("Clubs", i);
		}
		for (; i < 53; i++) {
			deck[i-1] = new Card("Diamonds", i);
		}
	}
	
	public void showAllCards(){
		for (int j = 0; j < 52; j++){
			System.out.print(deck[j].getRank() + " of ");
			System.out.print(deck[j].getSuit());
			System.out.println();
		}
	}
	
	public Card getCard(int index){
		return deck[index];
	}
	
	public String showCard(int index){
		return deck[index].toString();
	}
	
	public void shuffle(){
		Collections.shuffle(Arrays.asList(deck));
	}
	
	public int size(){
		return deck.length;
	}

}
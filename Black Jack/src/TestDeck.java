import java.util.Collections;
import java.util.Random;

public class TestDeck {

	public static void main(String[] args) {
		Random random = new Random();
		Deck one = new Deck();
		Player player1 = new Player("Joe");
		
		
		//one.showAllCards();
		System.out.println(one.showCard(40));
		
		
		Card test = one.getCard(37);
		System.out.println(test);
		System.out.println(test.getValue());
		System.out.println(one.getCard(12));
		System.out.println(one.getCard(12).getValue());
		one.shuffle();
		//player1.takeCard(one.getCard((int)Math.random()+ 11));
		//player1.takeCard(one.getCard(random.nextInt(12)));
		player1.takeCard(one.getCard(1));
		player1.showHand();

	}

}

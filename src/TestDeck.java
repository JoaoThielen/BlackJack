
public class TestDeck {

	public static void main(String[] args) {
		Deck one = new Deck();
		
		//one.showAllCards();
		System.out.println(one.showCard(40));
		
		
		Card test = one.getCard(37);
		System.out.println(test);
		System.out.println(test.getValue());

	}

}

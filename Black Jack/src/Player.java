import java.util.Stack;

public class Player {
	private String name;
	private Stack<Card> hand = new Stack<>();
	private static int nmbrOfPlyrs = 0;
	private boolean inGame = true;
	
	public Player(){
		this.name = "Player " + ++nmbrOfPlyrs;
	}
	
	public Player(String name){
		this.name = name;
		nmbrOfPlyrs++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int handSize(){
		return hand.size();
	}
	
	public Card popCard(){
		Card temp = this.hand.pop();
		return temp;
	}
	
	public void takeCard (Card card){
		hand.add(card);
	}
	
	public Card showCard(int index){
		return hand.get(index);
	}
	
	public void showHand (){
		System.out.println(name + " has " + hand);
	}
	
	public int handValue(){
		int total = 0;
		for (int i = 0;i < hand.size(); i++){
			total += hand.get(i).getValue();
		}
		return total;
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}
	
	public String toString (){
		return this.name;
	}
}

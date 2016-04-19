
public class Card {
	private int value;
	private String suit;
	private String rank;

	public Card(String suit, int value) {
		int rank = value % 13;
		switch (rank) {
		case 1:
			this.rank = "A";
			break;
		case 2:
			this.rank = "2";
			break;
		case 3:
			this.rank = "3";
			break;
		case 4:
			this.rank = "4";
			break;
		case 5:
			this.rank = "5";
			break;
		case 6:
			this.rank = "6";
			break;
		case 7:
			this.rank = "7";
			break;
		case 8:
			this.rank = "8";
			break;
		case 9:
			this.rank = "9";
			break;
		case 10:
			this.rank = "10";
			break;
		case 11:
			this.rank = "J";
			break;
		case 12:
			this.rank = "Q";
			break;
		case 0:
			this.rank = "K";
			break;
		}
		this.suit = suit;
		if(value <= 13) {
			this.value = value;
		}else if(value <= 26){
			value -= 13;
			this.value = value;
		}else if(value <= 39){
			value -= 26;
			this.value = value;
		}else {
			value -= 39;
			this.value = value;
		}

	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String toString() {
		return this.getRank() + " of " + this.getSuit();
	}
}

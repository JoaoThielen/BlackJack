
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class BlackJack {
	// Creation of the main deck
	static Deck[] decks = { new Deck(), new Deck(), new Deck(), new Deck() };
	static Stack<Card> mainDeck = new Stack<>();

	// Creation of players and dealer
	static Player p1 = new Player();
	static Player p2 = new Player();
	static Player p3 = new Player();
	static Player p4 = new Player();
	static Player p5 = new Player();
	static Player dealer = new Player("Dealer");

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		char newGame = 'N';
		// Puts the cards from decks into mainDeck
		for (int i = 0; i < decks.length; i++) {
			for (int j = 0; j < decks[i].size(); j++) {
				mainDeck.push(decks[i].getCard(j));
			}
		}
		// Gives the user the option to change the names of the players
		changeName(p1);
		changeName(p2);
		changeName(p3);
		changeName(p4);
		changeName(p5);

		// Game main loop
		do {
			Collections.shuffle(mainDeck);
			round1();
			checkFirstBlackJack(p1);
			checkFirstBlackJack(p2);
			checkFirstBlackJack(p3);
			checkFirstBlackJack(p4);
			checkFirstBlackJack(p5);
			checkFirstBlackJack(dealer);
			// If the dealer is the only one that has 21 in the first round, the
			// match ends
			if (!dealer.isInGame()
					&& (p1.isInGame() || p2.isInGame() || p3.isInGame() || p4.isInGame() || p5.isInGame())) {
				System.out.println("The dealer won!");

			} else {
				System.out.println("Game continues!");
				// Following lines are the plays of each player
				if (p1.isInGame()) {
					play(p1);
				}
				if (p2.isInGame()) {
					play(p2);
				}
				if (p3.isInGame()) {
					play(p3);
				}
				if (p4.isInGame()) {
					play(p4);
				}
				if (p5.isInGame()) {
					play(p5);
				}
				// Defines who won and displays to user
				whoWins(p1, p2, p3, p4, p5, dealer);

			}

			// Check if player wants to play again
			System.out.println("Do you want to play again? (Y/N)");
			newGame = input.next().trim().charAt(0);
			// If player chooses to play again, this will put the cards back
			// into the deck
			if (newGame == 'Y' || newGame == 'y')
				restart(p1, p2, p3, p4, p5, dealer);
		} while (newGame == 'Y' || newGame == 'y');

		System.out.println("Thanks for playing!");
	}

	// Method for running first round of the game
	static void round1() {
		p1.takeCard(mainDeck.pop());
		p2.takeCard(mainDeck.pop());
		p3.takeCard(mainDeck.pop());
		p4.takeCard(mainDeck.pop());
		p5.takeCard(mainDeck.pop());
		dealer.takeCard(mainDeck.pop());
		p1.showHand();
		p2.showHand();
		p3.showHand();
		p4.showHand();
		p5.showHand();
		System.out.println("Dealer has [X]");
		p1.takeCard(mainDeck.pop());
		p2.takeCard(mainDeck.pop());
		p3.takeCard(mainDeck.pop());
		p4.takeCard(mainDeck.pop());
		p5.takeCard(mainDeck.pop());
		dealer.takeCard(mainDeck.pop());
		p1.showHand();
		p2.showHand();
		p3.showHand();
		p4.showHand();
		p5.showHand();
		System.out.println("Dealer has [X, " + dealer.showCard(1) + "]");
	}

	// Method to check in any player got 21 after first round
	static boolean checkFirstBlackJack(Player player) {
		int value1 = player.showCard(0).getValue();
		int value2 = player.showCard(1).getValue();
		if (value1 == 1 && value2 == 10) {
			player.setInGame(false);
			System.out.println("WINNER! " + player.getName() + " has Blackjack!");
			return true;
		} else if (value2 == 1 && value1 == 10) {
			player.setInGame(false);
			System.out.println("WINNER! " + player.getName() + " has Blackjack!");
			return true;
		} else {
			player.setInGame(true);
			return false;
		}
	}

	// Method to run remaining rounds
	static void play(Player player) {
		char keepPlaying;
		player.showHand();
		System.out.println(player.getName() + ", do you want to hit(H) or stand(S)?");
		keepPlaying = input.next().trim().charAt(0);
		while (keepPlaying == 'h' || keepPlaying == 'H') {
			System.out.println(mainDeck.peek());
			player.takeCard(mainDeck.pop());
			if (player.handValue() > 21) {
				System.out.println("Bust!" + player.getName() + " got " + player.handValue() + " total");
				player.setInGame(false);
				break;
			} else {
				System.out.println(player.getName() + ", do you want to hit(H) or stand(S)?");
				keepPlaying = input.next().trim().charAt(0);
			}
		}
	}

	// Method for controlling dealer's round
	static void dealerPlay(Player dealer) {
		dealer.showHand();
		while (dealer.handValue() < 17) {
			System.out.println("Dealer Hits");
			System.out.println(mainDeck.peek());
			dealer.takeCard(mainDeck.pop());
		}
		if (dealer.handValue() > 22) {
			System.out.println("Dealer busted!");
			dealer.setInGame(false);
		}
	}

	// Method that defines who won or tied
	static void whoWins(Player p1, Player p2, Player p3, Player p4, Player p5, Player dealer) {
		ArrayList<Player> winners = new ArrayList<>();
		if (p1.isInGame()) {
			if (p1.handValue() >= p2.handValue() || p1.handValue() >= p3.handValue() || p1.handValue() >= p4.handValue()
					|| p1.handValue() >= p5.handValue() || p1.handValue() >= dealer.handValue()) {
				winners.add(p1);
			}

		}
		if (p2.isInGame()) {
			if (p2.handValue() >= p1.handValue() || p2.handValue() >= p3.handValue() || p2.handValue() >= p4.handValue()
					|| p2.handValue() >= p5.handValue() || p2.handValue() >= dealer.handValue()) {
				winners.add(p2);
			}
		}
		if (p3.isInGame()) {
			if (p3.handValue() >= p1.handValue() || p3.handValue() >= p2.handValue() || p3.handValue() >= p4.handValue()
					|| p3.handValue() >= p5.handValue() || p3.handValue() >= dealer.handValue()) {
				winners.add(p3);
			}
		}
		if (p4.isInGame()) {
			if (p4.handValue() >= p1.handValue() || p4.handValue() >= p3.handValue() || p4.handValue() >= p3.handValue()
					|| p3.handValue() >= p5.handValue() || p4.handValue() >= dealer.handValue()) {
				winners.add(p4);
			}
		}
		if (p5.isInGame()) {
			if (p5.handValue() >= p1.handValue() || p5.handValue() >= p2.handValue() || p5.handValue() >= p3.handValue()
					|| p5.handValue() >= p4.handValue() || p5.handValue() >= dealer.handValue()) {
				winners.add(p5);
			}
		}
		if (dealer.isInGame()) {
			if (dealer.handValue() >= p2.handValue() || dealer.handValue() >= p3.handValue()
					|| dealer.handValue() >= p4.handValue() || dealer.handValue() >= p5.handValue()
					|| dealer.handValue() >= p1.handValue()) {
				winners.add(dealer);
			}

		}
		if (winners.size() == 1) {
			System.out.println(winners + " is the winner!!!");
		} else {
			System.out.println(winners + " have tied!!!");
		}

	}

	// Method to check if name is to be changed, and changes it
	static void changeName(Player player) {
		char answer;
		System.out.println("Do you want to change " + player.getName() + "'s name?(y/n)");
		answer = input.next().charAt(0);
		if (answer == 'Y' || answer == 'y') {
			System.out.println("Please type the new name:");
			player.setName(input.next());
		}
	}

	// Method that takes the cards back
	static void restart(Player p1, Player p2, Player p3, Player p4, Player p5, Player dealer) {
		int handSize = p1.handSize();
		for (int i = 0; i < handSize; i++) {
			mainDeck.add(p1.popCard());
		}
		handSize = p2.handSize();
		for (int i = 0; i < handSize; i++) {
			mainDeck.add(p2.popCard());
		}
		handSize = p3.handSize();
		for (int i = 0; i < handSize; i++) {
			mainDeck.add(p3.popCard());
		}
		handSize = p4.handSize();
		for (int i = 0; i < handSize; i++) {
			mainDeck.add(p4.popCard());
		}
		handSize = p5.handSize();
		for (int i = 0; i < handSize; i++) {
			mainDeck.add(p5.popCard());
		}
		handSize = dealer.handSize();
		for (int i = 0; i < handSize; i++) {
			mainDeck.add(dealer.popCard());
		}
	}
}

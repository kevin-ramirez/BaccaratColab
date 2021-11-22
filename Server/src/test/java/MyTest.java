import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class MyTest {

	BaccaratInfo info = new BaccaratInfo();
	BaccaratGame game = new BaccaratGame();
	BaccaratGameLogic gameLogic = new BaccaratGameLogic();
	BaccaratDealer dealer =  new BaccaratDealer();
	ArrayList<Card> deck = new ArrayList<>();

	double myBet = 20.0;
	String betChoice = "Player";
	ArrayList<Card> playerHand = new ArrayList<>();
	ArrayList<Card> bankerHand = new ArrayList<>();

	Card player1 = new Card("Clubs", 8);
	Card player2 = new Card("Hearts", 12);
	Card banker1 = new Card("Spades", 4);
	Card banker2 = new Card("Hearts", 2);
	Card myCard = new Card("Hearts", 8);


	//All different test cases below
	//Two per method
	@BeforeAll
	void beforeAll(){
		playerHand.add(player1);
		playerHand.add(player2);
		bankerHand.add(banker1);
		bankerHand.add(banker2);
	}

	@Test
	void generateDeck1(){
		dealer.generateDeck();
		assertEquals(dealer.deckSize(), 52);
	}

	@Test
	void generateDeck2(){
		dealer.generateDeck();
		dealer.drawOne();
		assertEquals(dealer.deckSize(), 51);
	}

	@Test
	void dealHand1(){
		dealer.generateDeck();
		dealer.dealHand();
		assertEquals(dealer.deckSize(), 50);
	}

	@Test
	void dealHand2(){
		dealer.generateDeck();
		dealer.dealHand();
		dealer.dealHand();
		assertEquals(dealer.deckSize(), 48);
	}

	@Test
	void drawOne1(){
		dealer.generateDeck();
		dealer.dealHand();
		assertEquals(dealer.deckSize(), 50);
	}

	@Test
	void drawOne2(){
		dealer.generateDeck();
		dealer.drawOne();
		dealer.drawOne();
		assertEquals(dealer.deckSize(), 50);
	}

	@Test
	void shuffleDeck(){
		dealer.generateDeck();
		dealer.shuffleDeck();
		assertEquals(dealer.deckSize(), 52);
	}

	@Test
	void shuffleDeck2(){
		dealer.generateDeck();
		dealer.shuffleDeck();
		dealer.shuffleDeck();
		assertEquals(dealer.deckSize(), 52);
	}

	@Test
	void deckSize1(){
		dealer.generateDeck();
		assertEquals(dealer.deckSize(), 52);
	}

	@Test
	void deckSize2(){
		dealer.generateDeck();
		dealer.shuffleDeck();
		dealer.shuffleDeck();
		dealer.drawOne();
		assertEquals(dealer.deckSize(), 51);
	}

	@Test
	void card1(){
		Card myCard = new Card("Hearts", 9);
		assertEquals(myCard.suite, "Hearts");
	}

	@Test
	void card2(){
		Card myCard = new Card("Hearts", 9);
		assertEquals(myCard.value, 9);
	}

	@Test
	void handTotal1(){
		assertEquals(BaccaratGameLogic.handTotal(bankerHand),6 );
	}

	@Test
	void handTotal2(){
		assertEquals(BaccaratGameLogic.handTotal(playerHand),8 );
	}

	@Test
	void evaluateBankerDraw1(){
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(bankerHand, playerHand.get(1)));
	}

	@Test
	void evaluateBankerDraw2(){
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(bankerHand, playerHand.get(0)));
	}

	@Test
	void evaluatePlayerDraw1(){
		assertFalse(BaccaratGameLogic.evaluatePlayerDraw(playerHand));
	}

	@Test
	void evaluatePlayerDraw2(){
		assertFalse(BaccaratGameLogic.evaluatePlayerDraw(bankerHand));
	}

	@Test
	void whoWon1(){
		assertEquals(BaccaratGameLogic.whoWon(playerHand, bankerHand), "Player");
	}

	@Test
	void whoWon2(){
		playerHand.add(myCard);
		assertEquals(BaccaratGameLogic.whoWon(playerHand, bankerHand), "Draw");
	}

	@Test
	void evaluateWinnings1(){
		String betOutcome = game.outCome(betChoice, "Player", 4, 8);
		game.betOutcome = betOutcome;

		assertEquals(game.evaluateWinnings(), 0);
	}

	@Test
	void evaluateWinnings2(){
		String betOutcome = game.outCome(betChoice, "Player", 7, 8);
		game.betOutcome = betOutcome;

		assertEquals(game.evaluateWinnings(), 0);
	}




















}

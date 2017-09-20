import static org.junit.Assert.*;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Ignore // rewrote later
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();			
	}
	
	//check initial score
	@Test
	public void testTennisGame_game_starts_at_00() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		String score = game.getScore() ;
		assertEquals("Starting code isn't right", "love - love", score);		
	}	
	//check that player 1 scores update and are printed correctly
	@Test
	public void testTennisGame_player1_scores_update() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();

		game.player1Scored();
		String score = game.getScore() ;
		assertEquals("Player1 score doesn't update", "15 - love", score);
		game.player1Scored();
		score = game.getScore() ;
		assertEquals("Player1 score doesn't update", "30 - love", score);
		game.player1Scored();
		score = game.getScore() ;
		assertEquals("Player1 score doesn't update", "40 - love", score);		
	}

	//check that player 2 scores update and are printed correctly
	@Test
	public void testTennisGame_player2_scores_update() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();

		game.player2Scored();
		String score = game.getScore() ;
		assertEquals("Player2 score doesn't update", "love - 15", score);
		game.player2Scored();
		score = game.getScore() ;
		assertEquals("Player2 score doesn't update", "love - 30", score);
		game.player2Scored();
		score = game.getScore() ;
		assertEquals("Player2 score doesn't update", "love - 40", score);		
	}
	
	//check that advantage is printed correctly
	@Test
	public void testTennisGame_advantages() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();

		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//advantage player 1
		game.player1Scored();
		String score = game.getScore() ;
		assertEquals("Player1 advantage doesn't work", "player1 has advantage", score);	
				
		game.player2Scored();
		//make sure deuce works
		score = game.getScore() ;
		assertEquals("Deuce doesn't work", "deuce", score);
		game.player2Scored();		
		//player2 advantage
		score = game.getScore() ;
		assertEquals("Player2 advantage doesn't work", "player2 has advantage", score);
		
	}
	
	//check that the game ends
	@Test
	public void testTennisGame_game_ends() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();

		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();		
		String score = game.getScore() ;
		score = game.getScore() ;
		assertEquals("50-0 doesn't end the game", "player1 wins", score);
		
		//second game
		TennisGame game2 = new TennisGame();

		game2.player2Scored();
		game2.player2Scored();
		game2.player2Scored();
		game2.player2Scored();		
		score = game2.getScore() ;
		assertEquals("0-50 doesn't end the game", "player2 wins", score);		
			
		//60-40
		TennisGame game3 = new TennisGame();

		game3.player2Scored();
		game3.player2Scored();
		game3.player2Scored();
		game3.player1Scored();
		game3.player1Scored();
		game3.player1Scored();
		
		game3.player1Scored();
		game3.player1Scored();		
		
		score = game3.getScore() ;
		assertEquals("60-40 doesn't end the game", "player1 wins", score);	
		
		//40-60
		TennisGame game4 = new TennisGame();

		game4.player2Scored();
		game4.player2Scored();
		game4.player2Scored();
		game4.player1Scored();
		game4.player1Scored();
		game4.player1Scored();
		
		game4.player2Scored();
		game4.player2Scored();		
		
		score = game4.getScore() ;
		assertEquals("40-60 doesn't end the game", "player2 wins", score);		
		
	}		
	
	//Scores update1
	@Test
	public void testTennisGame_scores_update1() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();

		game.player1Scored();
		game.player2Scored();
		String score = game.getScore() ;
		assertEquals("15 - 15 doesn't work", "15 - 15", score);
		
		game.player1Scored();
		score = game.getScore() ;
		assertEquals("30 - 15 doesn't work", "30 - 15", score);	
		
		game.player2Scored();
		score = game.getScore() ;
		assertEquals("30 - 30 doesn't work", "30 - 30", score);		
		
		game.player1Scored();
		score = game.getScore() ;
		assertEquals("40 - 30 doesn't work", "40 - 30", score);	
		
		
	}	

	//Scores update12. same as the last but scores are updated in different order
	@Test
	public void testTennisGame_scores_update2() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();

		game.player1Scored();
		game.player2Scored();
		String score = game.getScore() ;
		assertEquals("15 - 15 doesn't work", "15 - 15", score);
		
		game.player2Scored();
		score = game.getScore() ;
		assertEquals("15-30 doesn't work", "15 - 30", score);	
		
		game.player1Scored();
		score = game.getScore() ;
		assertEquals("30 - 30 doesn't work", "30 - 30", score);		
		
		game.player2Scored();
		score = game.getScore() ;
		assertEquals("30 - 40 doesn't work", "30 - 40", score);	
		
		
	}

	
	
	
}

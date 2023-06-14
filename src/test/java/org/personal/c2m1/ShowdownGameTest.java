package org.personal.c2m1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.personal.c2m1.Card.Rank;
import org.personal.c2m1.Card.Suit;

class ShowdownGameTest {
			ShowdownGame subject;
			List<Player> playerList ;

			@BeforeEach
			void init() {
			  subject = new ShowdownGame();
				playerList = subject.createPlayer(4);

			}

			@Test
			void test_exchangeHands() {



			}
}

package org.personal.c2m1;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

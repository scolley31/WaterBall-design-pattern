import ShowDown.ShowDown;
import TemplateGame.Deck;
import TemplateGame.Game;
import ShowDown.ShowDownAI;
import UNO.UNOAI;
import UNO.UNOHuman;
import UNO.UNO;

public class GameLauncher {
    public static void main(String[] args) {
        Game UNO = new UNO(new UNOHuman(), new UNOAI(), new UNOAI(), new UNOAI(), 5);
        UNO.start();
        Game showDown = new ShowDown(new ShowDownAI(), new ShowDownAI(), new ShowDownAI(), new ShowDownAI(), 13);
        showDown.start();
    }
}
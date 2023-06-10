package ShowDown;

public class ShowDownAI extends Player {
    @Override
    protected void nameHimself() {
        name = String.valueOf(this.hashCode());
        System.out.println("AI ShowDown Player name himself, name is " + name);
    }

    @Override
    public Card showCard() {
        int num = (int) (Math.random() * (getHands().size()));
        Card showCard =  getHands().getHandCards().stream().skip(num).findFirst().orElse(null);
        this.showCard = showCard;
        getHands().getHandCards().remove(showCard);
        System.out.println("name "+name+" 手上還有 "+getHands().size()+" 張牌");
        return showCard;
    }
}

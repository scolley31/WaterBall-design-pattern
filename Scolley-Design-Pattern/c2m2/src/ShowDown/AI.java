package ShowDown;

public class AI extends Player {
    @Override
    protected void nameHimself() {
        name = String.valueOf(this.hashCode());
        System.out.println("AI UNO.Player name himself, name is " + name);
    }

    @Override
    public Card showCard() {
        int num = (int) (Math.random() * (getHands().size()));
        System.out.println("name "+name+" 手上還有 "+getHands().size()+" 張牌");
        Card showCard =  getHands().getHandCards().stream().skip(num).findFirst().orElse(null);
        getHands().getHandCards().remove(showCard);
        return showCard;
    }
}

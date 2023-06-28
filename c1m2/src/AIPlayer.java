public class AIPlayer extends Player {
    protected AIPlayer(String name) {
        super(name);
    }
    public void nameHimself() {
       setName(this.getName());
    }
    @Override
    public Card showCard() {
        int index = (int) (Math.random() * this.getHand().getCards().size());
        Card showCard = this.getHand().getCards().get(index);
        this.getHand().getCards().remove(index);
        return showCard;
    }
}


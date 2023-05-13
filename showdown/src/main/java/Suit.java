public enum Suit {
    C('1'), D('2'), H('3'), S('4'); //SPADE,HEART,DIAMOND,CLUB

    private char value;

    Suit(char i) {
        this.value = i;
    }

    public char getValue() {
        return this.value;
    }
}
import q2.CountNumberOfWaterballs;
import q2.SearchEmptyMessageIndex;
import q2.SearchLongestMessage;
import q2.SearchTemplate;

public class Main {
    public static void main(String[] args) {
        SearchTemplate searchTemplate = new CountNumberOfWaterballs();
        System.out.println("--------CountNumberOfWaterballs First--------");
        System.out.println(searchTemplate.search(new String[]{"test", "tsedt2", "tes", "", "123", "55", "Waterball", "Waterball", "Waterball"}));

        System.out.println("--------CountNumberOfWaterballs Second--------");
        System.out.println(searchTemplate.search(new String[]{"123", "Waterball", "tes", "", "Waterball", "55", "", "Waterball", "Waterball"}));

        searchTemplate = new SearchLongestMessage();
        System.out.println("--------SearchLongestMessage First--------");
        System.out.println(searchTemplate.search(new String[]{"tesasdasdasdt", "tsedt2", "tes", "", "123", "55", "Waterball", "Waterball", "Waterball"}));

        System.out.println("--------SearchLongestMessage Second--------");
        System.out.println(searchTemplate.search(new String[]{"123", "Waterball", "tessdas", "", "Waterball", "55", "", "Waterball", "Waterball"}));

        searchTemplate = new SearchEmptyMessageIndex();
        System.out.println("--------SearchEmptyMessageIndex First--------");
        System.out.println(searchTemplate.search(new String[]{"test", "tsedt2", "tes", "", "123", "55", "Waterball", "Waterball", "Waterball"}));

        System.out.println("--------SearchEmptyMessageIndex Second--------");
        System.out.println(searchTemplate.search(new String[]{"123", "Waterball", "tes", "", "Waterball", "55", "", "Waterball", "Waterball"}));
    }
}
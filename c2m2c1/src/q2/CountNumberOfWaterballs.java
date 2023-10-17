package q2;

public class CountNumberOfWaterballs extends SearchTemplate<Integer> {

    @Override
    protected Integer defaultValue() {
        return 0;
    }

    @Override
    protected Integer updateResult(Integer result, String message, int index) {
        return "Waterball".equals(message) ? result+1 : result;
    }

//    public int count(String[] messages) {
//        int count = 0;
//        for (String message : messages) {
//            if ("Waterball".equals(message)) {
//                count ++;
//            }
//            System.out.println(message);
//        }
//        return count;
//    }

}

package q2;

public class SearchEmptyMessageIndex extends SearchTemplate<Integer> {

    @Override
    protected Integer defaultValue() {
        return 0;
    }

    @Override
    protected boolean stopCondition(String message) {
        return message.isEmpty();
    }

    @Override
    protected Integer updateResult(Integer result, String message, int index) {
        return message.isEmpty() ? index : -1;
    }

//    @Override
//    protected Integer returnWithDefaultValue(Integer result) {
//        return result == null ? -1 : result;
//    }

//    public int search(String[] messages) {
//        int index = 0;
//        while (index < messages.length && !messages[index].isEmpty()) {
//            System.out.println(messages[index]);
//            index ++;
//        }
//        return index >= messages.length ? -1 : index;
//    }

//    private int currentIndex = -1;
//    private int index = -1;
//    @Override
//    protected Integer updateResult(String message) {
//        currentIndex++;
//        if(index ==-1 && message.isEmpty()){
//            index = currentIndex;
//        }
//        return index;
//    }
}

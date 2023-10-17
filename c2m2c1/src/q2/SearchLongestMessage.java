package q2;

public class SearchLongestMessage extends SearchTemplate<String> {

    @Override
    protected String defaultValue() {
        return "";
    }

    @Override
    protected String updateResult(String result, String message, int index) {
        return result.length() > message.length() ? result : message;
    }

//    public String search(String[] messages) {
//        String maxLengthMessage = "";
//        for (String message : messages) {
//            if (message.length() > maxLengthMessage.length()) {
//                maxLengthMessage = message;
//            }
//            System.out.println(message);
//        }
//        return maxLengthMessage;
//    }
}

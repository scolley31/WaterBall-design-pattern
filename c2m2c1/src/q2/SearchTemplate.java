package q2;

public abstract class SearchTemplate<Result> {
    public Result search(String[] messages){
        Result result = defaultValue();
        for (int i = 0; i < messages.length; i++) {
            result = updateResult(result,messages[i], i);
            System.out.println(messages[i]);
            if(stopCondition(messages[i])){
                break;
            }
        }
        return result;
    }

    protected Result defaultValue() {
        return null;
    }

    protected boolean stopCondition(String message) {
        return false;
    };
    protected abstract Result updateResult(Result result, String message, int index);
}

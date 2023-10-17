package q1;

public abstract class Template {
    public void p(int[] u) {
        int n = u.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (criteria(u[j], u[j+1])) {
                    int mak = u[j];
                    u[j] = u[j + 1];
                    u[j + 1] = mak;
                }
            }
        }
    }

    protected abstract boolean criteria(int v1, int v2);
}

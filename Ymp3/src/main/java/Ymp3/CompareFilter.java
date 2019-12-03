package Ymp3;

public class CompareFilter implements Filter {
    private final String value;

    public CompareFilter(String value) {
        this.value = value;
    }

    @Override
    public boolean apply(String value) {
        return this.value.compareTo(value) > 0;
    }
}

package Ymp3;

public class EqualsFilter implements Filter{
    private final String value;

    public EqualsFilter(String value) {
        this.value = value;
    }

    @Override
    public boolean apply(String value) {
        return this.value.equals(value);
    }
}

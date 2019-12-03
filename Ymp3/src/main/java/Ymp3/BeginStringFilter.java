package Ymp3;

public class BeginStringFilter implements Filter {
    private final String value;

    public String getValue() {
        return value;
    }


    public BeginStringFilter(String value) {
        this.value = value;

    }

    @Override
    public boolean apply(String value) {

        if (value == null || "".equals(value)) {
            return false;
        }
        return value.toLowerCase().startsWith(this.value.toLowerCase());
    }

}

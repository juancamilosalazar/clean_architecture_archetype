package co.com.flypass.utils;

public class TextUtil {
    private TextUtil() {
    }

    public static String capitalizeFirstLetter(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}

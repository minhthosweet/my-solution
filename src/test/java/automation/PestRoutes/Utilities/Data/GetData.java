package automation.PestRoutes.Utilities.Data;

import automation.PestRoutes.Utilities.Deprecated;
import org.apache.commons.lang3.*;

import java.util.*;

public class GetData {
    public static String removeFirstAndLastCharacter(String value) {
        return value.substring(1, value.length() -1);
    }

    public static int removeSpecialChars(String needAttribute) {
        String cases = Deprecated.getElementTextValue(needAttribute);

        int result = Integer.parseInt(cases.replaceAll("[@ $,.]", ""));

        return result / 100;
    }

    public static String transformName(String name) {
        String firstName = name.substring(0, name.indexOf(" "));
        String lastName = name.substring(name.indexOf(" ")).trim();
        String last_first = lastName + ", " + firstName;
        return last_first;
    }

    public static String generateRandomString(int needLength) {
        return RandomStringUtils.random(needLength, true, true);
    }

    // Author: Aditya
    public static double generateRandomInteger(int needLength) {
        double m = Math.pow(10, needLength - 1);
        m = m + new Random().nextInt((int) (9 * m)) + new Random().nextDouble();
        double b = Math.round(m * 100.0) / 100.0;
        return b;
    }

    public static int generateRandomNumber(int needLength) {
        int m = (int) Math.pow(10, needLength - 1);
        return m + new Random().nextInt(9 * m);
    }
}

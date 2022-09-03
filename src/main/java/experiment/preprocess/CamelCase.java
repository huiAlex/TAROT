package experiment.preprocess;

import java.util.ArrayList;

public class CamelCase {

    public static String split(String input) {
        String words[] = input.split(" ");

        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            for (String cc : splitUppercase(word)) {
                sb.append(cc);
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    public static ArrayList<String> splitUppercase(String str) {
        char[] list = str.toCharArray();
        str = "A" + str + "A";
        char[] tempList = str.toCharArray();

        for (int i = 1, j = 0; i < tempList.length - 1; i++, j++) {
            if (tempList[i] >= 'A' && tempList[i] <= 'Z') {
                if (tempList[i - 1] >= 'A' && tempList[i - 1] <= 'Z') {
                    if (tempList[i + 1] >= 'A' && tempList[i + 1] <= 'Z') {
                        list[j] = (char) (tempList[i] - 'A' + 'a');
                    } else if (tempList[i + 1] == 's' && tempList[i + 2] >= 'A') { // 大写复数
                        list[j] = (char) (tempList[i] - 'A' + 'a');
                        list[j + 1] = ' ';
                    } else {
                        list[j] = tempList[i];
                    }
                }
            }
        }

        str = new String(list);
        ArrayList<String> result = new ArrayList<String>();
        int startPositionOfSubstring = 0;
        str = str + 'A';
        for (int endPositionOfSubstring = 0; endPositionOfSubstring < str.length(); endPositionOfSubstring++) {
            if (str.charAt(endPositionOfSubstring) >= 'A' && str.charAt(endPositionOfSubstring) <= 'Z') {
                // to exclude initial up case letter
                if (str.substring(startPositionOfSubstring, endPositionOfSubstring).length() > 0) {
                    // to lower case
                    result.add(str.substring(startPositionOfSubstring, endPositionOfSubstring).toLowerCase());
                    startPositionOfSubstring = endPositionOfSubstring;
                }
            }
        }
        return result;
    }

}

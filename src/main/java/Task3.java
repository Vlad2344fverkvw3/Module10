import java.io.*;
import java.util.*;

public class Task3 {
    public String counterIdenticaline() {
        File file = new File("words.txt");
        Map<String, Integer> unique = new HashMap<>();
        try (InputStream inputStream = new FileInputStream(file);
             Scanner scanner = new Scanner(inputStream)) {
            if (file.exists()) {
                while (scanner.hasNext()) {
                    String[] spliterLine = scanner.nextLine().split(" ");

                    for (String str : spliterLine) {
                        unique.put(str, (unique.get(str) == null ? 1 : (unique.get(str) + 1)));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Comparator<String> comparator = (o1, o2) -> {
            Integer value1 = unique.get(o1);
            Integer value2 = unique.get(o2);
            return value1.compareTo(value2) > 0 ? 1 : -1;
        };
        Map<String, Integer> result = new TreeMap<>(comparator.reversed());
        result.putAll(unique);

        System.out.println(" New Value: " + result);

        return unique.toString().replaceAll("[{},]", " ");

    }
}

class Test3 {
    public static void main(String[] args) {
        Task3 task3 = new Task3();
        System.out.println(task3.counterIdenticaline());

    }
}






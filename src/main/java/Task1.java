import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    public void removed() {
        File file = new File("file.txt");
        StringBuilder stringBuilder = new StringBuilder();
        if (file.exists()) {
            try (InputStream inputStream = new FileInputStream(file);
                 Scanner scanner = new Scanner(inputStream)) {
                while (scanner.hasNext()) {
                    stringBuilder.append(scanner.nextLine());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Pattern pattern = Pattern.compile("(\\d{3})-(\\d{3})-(\\d{4})|(\\(\\d{3}\\) \\d{3}-\\d{4})");
        Matcher matcher = pattern.matcher(stringBuilder);
        while (matcher.find()) {
            System.out.println(matcher.group());

        }
    }
}

class Test1 {
    public static void main(String[] args)  {
        Task1 task1 = new Task1();
        task1.removed();
    }
}

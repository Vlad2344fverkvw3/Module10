import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {
    public String readDateFromFile()  {
        File file = new File("text.txt");
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
        return stringBuilder.toString();
    }

    public List<User> getFromString(String line) {
        List<User> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("([A-Za-zА-Яа-я]+) (\\d+)");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {

            list.add(new User(matcher.group(1), Byte.parseByte(matcher.group(2))));
        }
        return list;
    }

    public void saveToJson(String jsonString, Path whereSave) {
        try {
            Files.write(whereSave, jsonString.getBytes());
        } catch (IOException e) {
            System.out.println("Some problems during write to file.txt");
        }
    }
}

class User {
    private final String name;
    private final byte ege;

    public User(String name, byte ege) {
        this.name = name;
        this.ege = ege;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", ege=" + ege +
                '}';
    }
}

class Test2 {
    public static void main(String[] args)  {
        Task2 task2 = new Task2();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonList = gson.toJson(task2.getFromString(task2.readDateFromFile()));
        System.out.println("jsonList = " + jsonList);
        task2.saveToJson(jsonList, Path.of("users.json"));

    }

}




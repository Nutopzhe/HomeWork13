import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UtilClass {
    static final File FILE_FIO = new File("src/main/resources/fio.txt");
    static final File FILE_QUESTIONS = new File("src/main/resources/questions.txt");

    public static void main(String[] args) {
        List<String> listFio = new ArrayList<>();
        List<String> listQuestions = new ArrayList<>();

        methodReader(listFio, FILE_FIO);
        methodReader(listQuestions, FILE_QUESTIONS);

    }

    public static void methodReader(List<String> list, File filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UtilClass {
    static final File FILE_FIO = new File("src/main/resources/fio.txt");
    static final File FILE_QUESTIONS = new File("src/main/resources/questions.txt");
    static final File QUESTIONS_AND_FIO = new File(String.format("src/main/resources/вопросы-%s", LocalDate.now()));

    public static void main(String[] args) {
        List<String> listFio = new ArrayList<>();
        List<String> listQuestions = new ArrayList<>();

        methodReader(listFio, FILE_FIO);
        methodReader(listQuestions, FILE_QUESTIONS);

        methodGenerationFileAndWrite(listFio, listQuestions, QUESTIONS_AND_FIO);
    }

    //метод чтения и запись данных в списки
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

    //метод генерации учеников и вопросов
    //после генерации идет запись в файл
    public static void methodGenerationFileAndWrite(List<String> listFio, List<String> listQuestions, File filePath) {
        List<String> result = new ArrayList<>();
        //генерация зависит от количества человек в listFio
        //после добавления, вопрос удаляется из списка
        for (String s : listFio) {
            int random = new Random().nextInt(listQuestions.size());
            result.add(s + " - " + listQuestions.get(random));
            listQuestions.remove(random);
            //если лист вопросов пустой, то выйти из генерации
            if (listQuestions.isEmpty()) {
                break;
            }
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : result) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

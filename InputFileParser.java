import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputFileParser {

  private ArrayList<Integer> days;
  private ArrayList<String> operation;

  public InputFileParser(String filePath) throws FileNotFoundException {
    File file = new File(filePath);
    if (!file.exists()) {
      throw new FileNotFoundException();
    }

    days = new ArrayList<>();
    operation = new ArrayList<>();

    Scanner input = new Scanner(file);
    String line = "";
    int lineNum = 0;
    while (input.hasNextLine()) {
      line = input.nextLine();
      String[] tokens = line.split(": ");
      days.add(Integer.parseInt(tokens[0]));
      operation.add(tokens[1]);
    }
    input.close();
  }

  public ArrayList<Integer> getDays() {
    return days;
  }

  public ArrayList<String> getOperations() {
    return operation;
  }

}
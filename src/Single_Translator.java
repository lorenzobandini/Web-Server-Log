import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Single_Translator implements Runnable {

    private String logFile;

    public Single_Translator(String logFile){
        this.logFile = logFile;
    }

    @Override
    public void run() {
        File file = new File(logFile);
        try (Scanner scanner = new Scanner(file)) {
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        

    }
}
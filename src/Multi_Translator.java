import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.concurrent.Executors;


public class Multi_Translator implements Runnable{

    private final int NUM_LINES = 5;
    private String logFile;

    public Multi_Translator(String logFile){
        this.logFile = logFile;
    }

    @Override
    public void run() {
        System.out.println("Multi Translator Thread Started");
        long startTime = System.currentTimeMillis();
        List<String> lines = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(logFile));
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        int numLines = lines.size();
        int numThreads = (int)Math.floor((numLines / NUM_LINES)/2);

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);


        for(int i = 0; i<lines.size(); i+=NUM_LINES){
            int start = i;
            int end = i+NUM_LINES;
            executor.execute(new Worker_Traslator_Multi(start, end, lines));
        }

        executor.shutdown();
        while(!executor.isTerminated()){try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}

        long endTime = System.currentTimeMillis();
        System.out.println("Multi Translator Thread Finished in " + (endTime - startTime) + "ms");
    }
}
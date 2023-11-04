import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Single_Translator implements Runnable {

    private String logFile;

    public Single_Translator(String logFile){
        this.logFile = logFile;
    }

    @Override
    public void run() {
        System.out.println("Single Translator Thread Started");
        long startTime = System.currentTimeMillis();
        File file = new File(logFile);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                int index = line.indexOf(" ");
                String ip = line.substring(0, index);
                String[] ipArr = ip.split("\\.");
                byte[] ipByte = new byte[4];

                for(int i = 0; i < 4; i++){
                    ipByte[i] = (byte) Integer.parseInt(ipArr[i]);
                }

                InetAddress address = InetAddress.getByAddress(ipByte);
                String hostName = address.getHostName();
                System.out.println(hostName + line.substring(index));
            }
            sc.close();
        } catch (UnknownHostException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Single Translator Thread Finished in " + (endTime - startTime) + "ms"); 
    }
}
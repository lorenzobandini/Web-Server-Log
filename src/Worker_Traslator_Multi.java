import java.util.List;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class Worker_Traslator_Multi implements Runnable {
    
    private final int start;
    private final int end;
    private final List<String> lines;

        public Worker_Traslator_Multi(int start, int end, List<String> lines){
            this.start = start;
            this.end = end;
            this.lines = lines;
        }

    @Override   
    public void run(){
        int i = start;
        while(i < end && i<lines.size()){
            String line = lines.get(i);
            int index = line.indexOf(" ");
            String ip = line.substring(0, index);
            String[] ipArr = ip.split("\\.");
            byte[] ipByte = new byte[4];

            for(int j = 0; j < 4; j++){
                ipByte[j] = (byte) Integer.parseInt(ipArr[j]);
            }

            try {
                InetAddress address = InetAddress.getByAddress(ipByte);
                String hostName = address.getHostName();
                System.out.println(hostName + line.substring(index));
            } catch (UnknownHostException e){
                e.printStackTrace();
            }
            i++;
        }
    }
}

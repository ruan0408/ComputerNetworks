import java.io.*;
import java.net.*;
import java.util.*;

public class PingClient
{
 
   public static void main(String[] args) throws Exception
   {

      if (args.length != 2) {
         System.out.println("Required arguments: host port");
         return;
      }
      
      String host = args[0];
      int port = Integer.parseInt(args[1]);

      DatagramSocket socket = new DatagramSocket();
      socket.setSoTimeout(900);
      DatagramPacket request;
      InetAddress serverHost = InetAddress.getByName(host);
      byte[] buf;
      String line;
      long timeWaited = 0;
      
      long sendTime;
      
      for(int i = 0; i < 10; i++) {
    	  
    	  sendTime = System.currentTimeMillis();
    	  line = "PING "+i+" "+sendTime+"\r\n";
    	  buf = line.getBytes();
    	  request = new DatagramPacket(buf, buf.length, serverHost, port);
    	  socket.send(request);
    	  
    	  try {
    		  socket.receive(request);
    		  buf = request.getData();
        	  ByteArrayInputStream bais = new ByteArrayInputStream(buf);
        	  InputStreamReader isr = new InputStreamReader(bais);
        	  BufferedReader br = new BufferedReader(isr);
        	  line = br.readLine();
        	  timeWaited = System.currentTimeMillis()-sendTime;
        	  System.out.println("PING "+ i + " "+timeWaited+"\r");
    	  } catch(SocketTimeoutException e) {
    		  timeWaited = 900;
    	  } finally {
    		  Thread.sleep(1000 - timeWaited);
    	  }
    	  
      }
      socket.close();
   }
}
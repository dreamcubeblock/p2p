import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendSMG extends Thread {
    PPfriend friend;
    String msg=null;
    String name;
    public SendSMG(PPfriend friend) {
	super();
	this.friend = friend;
    }
    public void run(){
//    	System.out.println("msg run..............."+msg);
    	while(true){
    		msg=friend.myw.pb.msg.getText();
    		
        	msg=SocketStatus.RECIVE_ALL_MSG+":"+msg+":"+this.friend.name+" ";
    		while(friend.send){
        		if(msg!=null){
            		try {
        				DatagramPacket packet=new DatagramPacket(msg.getBytes(), msg.getBytes().length,InetAddress.getByName(friend.ips),3333);
        				DatagramSocket socket=new DatagramSocket();
        				socket.send(packet);
        				socket.close();
        				sleep(1000);
//        				System.out.println("msg...send............."+msg);
//        				synchronized (this) {
//        					this.wait();
//        					notify();
//        				}
        			} catch (Exception e) {
        				e.printStackTrace();
        			}
            	}
        		friend.send=false;
        	}
    	}
    	
    }
}

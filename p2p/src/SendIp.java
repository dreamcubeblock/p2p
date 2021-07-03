import java.io.*;

import java.net.*;


public class SendIp implements Runnable{
	 String ip;
	 
	PPfriend friend;
    public SendIp( PPfriend friend) {
			super();
			this.friend = friend;
	}
    
    public String getFriendName() {
    	return this.friend.name;
    	
    }
    public void run(){
    	try {
			ip = InetAddress.getLocalHost().getHostAddress();
			friend.localip=ip;
			ip=SocketStatus.RECIVE_IP+":"+ip+":"+this.friend.name;
			System.out.println(ip);
		} catch (UnknownHostException e) {
			
		//	name=SocketStatus.RECIVE_NAME+":"+friend.name+"";
			 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true){
		      try {
			      //构造数据报包，用来将长度为 length 偏移量为 offset 的包发送到指定主机上的指定端口号
			      DatagramPacket packet=new DatagramPacket(ip.getBytes(), ip.getBytes().length,InetAddress.getByName(friend.ips) ,3333);
			     // DatagramPacket packet2=new DatagramPacket(name.getBytes(), name.getBytes().length,InetAddress.getByName(friend.ips) ,3333);
			      DatagramSocket socket=new DatagramSocket();
			      socket.send(packet);
			      //socket.send(packet2);
			      socket.close();
			      Thread.sleep(2000);
//			      System.out.println("send...........");
		          } catch (Exception e) {
			         e.printStackTrace();
		            }
                 }
    }
}


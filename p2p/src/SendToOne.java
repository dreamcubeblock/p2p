

import java.io.OutputStream;
import java.net.*;


public class SendToOne extends Thread  {
  DatagramSocket datagramSocket;
  InetAddress ia;
  
  PPfriend friend;
  //一对一发送的信息
  String sendtooneMSG=null;
  //用来判断是发送的信息还是建立连接。。true 代表一对一的消息
  boolean isMSG;
  public SendToOne(PPfriend friend,boolean isMSG,String sendtooneMSG){
	  this.friend=friend;
	  this.isMSG=isMSG;
	  this.sendtooneMSG=sendtooneMSG;
	
	  try {
		Thread.sleep(1000);
		//DatagramPacket packet=new DatagramPacket(inbuf, inbuf.length);
		//this.friend.sendip;
		String[] ips=friend.sendIp.split(":"); 
		System.out.println(ips[0]);
		ia=InetAddress.getByName(ips[0]);
		 Socket socket=new Socket(ia,9999);
		 OutputStream out=socket.getOutputStream();
		 System.out.println(this.sendtooneMSG);
		 System.out.println("111111"+friend.sendIp);
		 byte[] bs=new byte[1024];
		 if(isMSG && sendtooneMSG!=null){
			 sendtooneMSG=SocketStatus.RECIVE_ONE_TO_ONE_MSG+":"+friend.sendIp.split("\\s")[0]+":"+this.sendtooneMSG;
			 bs=sendtooneMSG.getBytes();
		 }else{
			 System.out.println("test link....");
			 String msg=SocketStatus.RECIVE_TEST_MSG+":";
			 System.out.println("sendtoneMSG"+msg);
			 bs=msg.getBytes();
		 }
		  //构造数据报包，用来将长度为 length 的包发送到指定主机上的指定端口号。
		 out.write(bs);
		 out.flush();
		 out.close();
		  //从此套接字发送数据报包。
		  //socket.send(reques);
	} catch (Exception e) {

		e.printStackTrace();
	}
	  }
  
  public void run(){
	  
  }
}

import java.net.*;

public class ReceiveIp extends Thread{
    public ReceiveIp(PPfriend friend) {
		super();
		this.friend = friend;
		
	}
	PPfriend friend;
	SocketStatus socketStatus=new SocketStatus();
   // Vector vectorip=new Vector();
    String msg;
    String sendip="";
    boolean socket=false;
	public void run(){
    	byte[] inbuf=new byte[50];//某认缓冲的大小
		//此类表示用来发送和接收数据报包的套接字
		  DatagramSocket socket;
		  try {
			socket = new DatagramSocket(3333);
			 while(true){
					try {
						//此类表示数据报包。  构造 DatagramPacket，用来接收长度为 length 的数据包
						DatagramPacket packet=new DatagramPacket(inbuf, inbuf.length);
						synchronized (socket) {
							try {
//								System.out.println("11111111111111111");
								//从此套接字接收数据报包
								socket.receive(packet);
//								System.out.println("2222222222");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						//接受数据
					  msg=new String(packet.getData());
					  for (int i = 0; i < inbuf.length; i++) {
						inbuf[i]=(byte)0;
					}
					  sendip=packet.getAddress().toString();
					  sendip=sendip.substring(1);
//					  System.out.println("sendip"+sendip);
					  System.out.println("msg"+msg);

					  String[] status = msg.split(":");

					  switch (Integer.parseInt(status[0])) {
					case 0x01:
						String result = msg;

						    	 System.out.println("xxxx"+result);
						    	 result=result.substring(2);

						    	 if(friend.listset.indexOf(result)==-1){
						    		 friend.listset.add(result);
						    		 friend.myw.pa.aPanel2.list.add(result);
						    	 }//else if(result[x].split(":")[0]!=friend.listset.get(friend.listset.indexOf(result[x])).split(":")[0]) {
						   
						break;
					case 0x02:
						
						 if(msg!=null){
							msg=status[1];
							String[] result2 = status[2].split("\\s");
							System.out.println(result2[0]+sendip+"说"+msg);
							//String name = status[1].substring(1);
							  friend.myw.pa.chat.append("用户："+result2[0]+" "+sendip+"说"+":"+msg);
							  friend.myw.pa.chat.append("\n");
							 // ac.play();
				         }
						break;
					default:
						break;
					}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			  }
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		 
    }
} 

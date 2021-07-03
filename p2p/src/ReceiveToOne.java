
import java.io.InputStream;
import java.net.*;
import javax.swing.JOptionPane;

public class ReceiveToOne extends Thread {
    DatagramSocket datagramSocket;
    public String getip=null;
    PPfriend friend;
    int num=0;
    String msg;
    public ReceiveToOne(PPfriend friend){
    	super();
    	this.friend=friend;
    }
    public void run(){
    	
    	try{
    		 byte[] inbuf=new byte[256];
    		 ServerSocket server=new ServerSocket(9999);
    		//创建数据报套接字并将其绑定到本地主机上的指定端口。用UDP可以接收广播
    		//Dialog qq=new Dialog("一对一聊天");
    		while(true) {

    		Socket socket=server.accept();
        	System.out.println("recive to one......");
        	//数据报包用来实现无连接包投递服务。 构造 DatagramPacket，用来接收长度为 length 的数据包
        	
        		InputStream in=socket.getInputStream();
    		byte [] bytes = new byte[1024];
            int count = in.read(bytes);
        	//接受数据
            String content = new String(bytes,0,count);
			  //返回某台机器的 IP 地址，此数据报将要发往该机器或者是从该机器接收到的。
//			  if(msg!=null){
				  getip=socket.getInetAddress().getHostAddress();
//			  }
				  System.out.println(content);
          	  if(getip!=null && content!=null){
        		  //把缓冲区清空
    			  for (int i = 0; i < inbuf.length; i++) {
    				inbuf[i]=(byte)0;
    			}
    			  System.out.println("msg........"+content);
    			  String[] status = content.split(":");
    			  System.out.println(status[0]);
    			  switch (Integer.parseInt(status[0])) {
    			case 0x03:
    				friend.receivetooneMSG=status[3];
    				JOptionPane.showMessageDialog(null, getip+"发消息给您，请双击它的ip以便接收",null,JOptionPane.OK_OPTION);
    				if(num==1) {
    				friend.qq.chat.append("用户："+getip+"说"+":"+status[3]+"\n");
    				}
    				num=1;
    				break;
                case 0x04:
//                	if(friend.qq!=null){
//                		JOptionPane.showMessageDialog(null, getip+"发文件给您，请按接收按钮接收",null,JOptionPane.OK_OPTION);
//                		friend.qq.refile.setEnabled(true);
//                	}else{
                		JOptionPane.showMessageDialog(null, getip+"发文件给您，请双击它的ip以便接收",null,JOptionPane.OK_OPTION);
//                	}
                		System.out.println("recive....sucess.....");
    				break;
    			default:
    				break;
    			}
          	  }
          	  in.close();
        	}
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	
    	}
    
}

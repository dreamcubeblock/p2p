import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JOptionPane;

public class SendFile extends Thread {
   public static final int PORT=4444;
   PPfriend friend;
   private String filepath=null;
   SendToOne sendToOne;
   public SendFile(PPfriend friend,String filepath){
	   this.filepath=filepath;
	   this.friend=friend;
   }
   public void doserver(){
	   int length;
	   sendToOne=new SendToOne(friend,false,null);
	   File file=new File(filepath);
	   Socket socket;
	   if(filepath!=null){
		   try{
				//通过打开一个到实际文件的连接来创建一个 FileInputStream，该文件通过文件系统中的 File 对象 file 指定
				
				  System.out.println("filepath"+filepath);
				   FileInputStream fos=new FileInputStream(file);
				   // 创建绑定到特定端口的服务器套接字。
				   System.out.println("sendfile here........");
				   ServerSocket s=new ServerSocket(PORT);
				   System.out.println("sendfile here11111111111");
				   socket = s.accept();
				try {
					   try{
						   InputStream netin=socket.getInputStream();
						   System.out.println("sendfile here.11111111111111111111.");
						   InputStream in=new DataInputStream(new BufferedInputStream(netin));
						   OutputStream netout=socket.getOutputStream();
						   OutputStream doc=new DataOutputStream(new BufferedOutputStream(netout));
						   // 返回此抽象路径名的绝对路径名形式。
						   file.getAbsoluteFile();
						   byte[] buf=new byte[2048];
						   length=fos.read(buf);
						   while(length!=(-1)){//是否讀完
							   doc.write(buf, 0, length);//把問價數據送到緩衝區
							   doc.flush();//刷新緩衝區把數據寫網客戶端
							   length=fos.read(buf);//繼續從文件讀取數據
						   }
						   fos.close();
						   doc.close();
						   }finally{
							   JOptionPane.showMessageDialog(null, "文件传输成功", null, JOptionPane.OK_OPTION);
							   socket.close();}
						   }finally{
							   s.close();
						   }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	 
	   }
  
	  
   }
   public void  run() {
	this.doserver();
}
}

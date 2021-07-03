
import java.io.*;

import java.net.*;
import javax.swing.JOptionPane;


public class Receive extends Thread {
    private String filepath=null;
    PPfriend friend=null;
    InetAddress addr;
    public Receive(PPfriend friend,String savepath){
    	this.friend=friend;
    	filepath=savepath;
    }
    public void doclient(){
    	int length;
    	File file=new File(filepath);
    	//创建一个向指定 File 对象表示的文件中写入数据的文件输出流。
    	FileOutputStream fileOutputStream;
    	Socket socket;
		try {
			fileOutputStream = new FileOutputStream(file);
			if(friend.receivetoone.getip!=null){
	    		try {
	    			//在给定主机名的情况下确定主机的 IP 地址
					addr=InetAddress.getByName(friend.receivetoone.getip);
					try {
						//此类实现客户端套接字创建一个流套接字并将其连接到指定 IP 地址的指定端口号
						socket = new Socket(addr,4444);
						try{
							//向文本输出流打印对象的格式化表示形式,刷新输出缓冲区 
							//BufferedWriter:将文本写入字符输出流，缓冲各个字符，从而提供单个字符、数组和字符串的高效写入
					    	PrintWriter out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
					        InputStream netin=socket.getInputStream();
					        InputStream in=new DataInputStream(new BufferedInputStream(netin));
					        OutputStream netout=socket.getOutputStream();
					        OutputStream doc=new DataOutputStream(netout);
					        byte[] buf=new byte[2048];
					        //从输入流中读取一定数量的字节，并将其存储在缓冲区数组 buf中。
					        length=in.read(buf);
					        while(length!=(-1)){//是否读完所有的数据
					        	//将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此文件输出流
					        	fileOutputStream.write(buf, 0, length);
					        	length=in.read(buf);
					        }
					        in.close();
					        fileOutputStream.close();
					    	}finally{
					    		JOptionPane.showMessageDialog(null, "文件传输成功！",null,JOptionPane.OK_OPTION);
					    		socket.close();
					    	}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
	    	}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    } 
    public void run(){
    	this.doclient();
    }
}

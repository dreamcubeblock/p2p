import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.net.*;
import java.util.List;
import javax.swing.*;

public class PPfriend extends JFrame {
	public Mywindow myw ;
    public ReceiveToOne recevietoone;
    SocketStatus socketStatus=new SocketStatus();
    public Dialog qq=null;

  
	String ips=null;
    public String sendIp=null;
    static public volatile String name=null;
    //本地的ip
    public String localip=null;
    boolean send=false;
    boolean isstart=false;
    int num=0;
    boolean isbegin=false;
	String sendfilepath=null;
    //接收文件的路径
    String savefilepath=null;
    String recivename=null;
    File files;
    //一对一发送的信息
    String sendtooneMSG=null;
    //一对一接收的消息
    String receivetooneMSG=null;
    //所有在线用户的ip
     HashSet listhastset=new HashSet();
    boolean packFrame = false;
    List listset = new ArrayList();
    ReceiveToOne receivetoone=new ReceiveToOne(this);
    //RecevieSMG recevieSMG=new RecevieSMG(this);
    SendSMG sendSMG=new SendSMG(this);
    SendIp sendip=new SendIp(this);
    //Register getname=new Register(qq,this); 
    
    boolean issendfile=false;
    SendFile sendFile ;
    Receive receive;
//    SendThread sendThread
    boolean flag=true;

//	}
    public String getName() {
    	return name;
    	
    }
	public PPfriend() throws SocketException, UnknownHostException{
		 myw=new Mywindow();
		 this.inputips(this);
		 this.ips=getip();
		 this.actionPerformed(this);
		 this.refresh(this);

		if(flag){
			//发送自己的ip
				
			 //接收所有在线用户的ip
			 ReceiveIp receiveIp=new ReceiveIp(this);
			 receiveIp.start();
		}
				 receivetoone=new ReceiveToOne(this);
	             receivetoone.start();
		flag=false;
		 		 if (packFrame) {
	        	//调整此窗口的大小，以适合其子组件的首选大小和布局
	        	myw.pack();
	        } else {
	        	//验证此容器及其所有子组件。
	        	myw.validate();
	        }
		 // Center the window
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        Dimension frameSize = myw.getSize();
	        if (frameSize.height > screenSize.height) {
	            frameSize.height = screenSize.height;
	        }
	        if (frameSize.width > screenSize.width) {
	            frameSize.width = screenSize.width;
	        }
	        myw.setLocation((screenSize.width - frameSize.width) / 2,
	                          (screenSize.height - frameSize.height) / 2);

		myw.setVisible(true);
	}
	
	
	public void actionPerformed(PPfriend friend) {
		final PPfriend pfriend;
		pfriend=friend;
		
		//为“发生”按键添加按键触发
		myw.pb.button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myw.pa.chat.setText("");
			}
		});
		 myw.pb.button1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(isstart==false){
						 sendSMG.start();
						 isstart=true;
					}
					send=true;
					System.out.println("111111111num"+sendSMG.getState());

				}
			});	
		 myw.pa.aPanel2.list.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 System.out.println("list.........");
				 qq=new Dialog("一对一聊天");
				 pfriend.sendIp=myw.pa.aPanel2.list.getSelectedItem();
				// Register register=new Register(qq,friend);
				// register.start();
				 
				 System.out.println(sendIp.split(":")[0]);
				 if(num==0) {
				 if(receivetoone.getip!=null) {
					 qq.refile.setEnabled(true);
				 }
				 
				 if(pfriend.receivetooneMSG!=null) {
					 System.out.println(sendIp);
					 qq.chat.append("用户："+sendIp.split(":")[0]+"说"+":"+receivetooneMSG+"\n");
					 num=1;
				 }
				 }
				 qq.sbutton.addActionListener(new ActionListener() {
					 public void actionPerformed(ActionEvent e) {
						 sendtooneMSG=qq.sendchat.getText();
						 qq.chat.append("用户："+friend.localip+"说"+":"+sendtooneMSG+"\n");
						 SendToOne sendToOne=new SendToOne(pfriend,true,sendtooneMSG);
						 sendToOne.start();
					 }
				 });
				 qq.fbutton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							System.out.println("发送文件.............");
							JFileChooser jFileChooser=new JFileChooser();
							jFileChooser.setMultiSelectionEnabled(false);
						    jFileChooser.showOpenDialog(qq);
						    files =jFileChooser.getSelectedFile();
						    sendfilepath=files.getAbsolutePath();
						    jFileChooser.setToolTipText(sendfilepath);
						    //开始发送文件
						    System.out.println("sendfilepath.........."+sendfilepath);
						    issendfile=true;
						    sendFile=new SendFile(pfriend, sendfilepath);
						    sendFile.start();
//						    flag=false;
//						   new PPfriend(sendfilepath);
						}
					});
					//为接收文件添加触发按钮
//					 if(qq!=null){
//						 System.out.println("recive.......button ./.........");
						 qq.refile.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									System.out.println("jiejie..........");
									JFileChooser jFileChooser=new JFileChooser();
									File savefile;
									jFileChooser.showSaveDialog(qq);
									savefile=jFileChooser.getSelectedFile();
									if(savefile!=null){
										savefilepath=savefile.getAbsolutePath();
										System.out.println("savepath......"+savefilepath);
									}
									receive=new Receive(pfriend, savefilepath);
									receive.start();
								}
							});

			 }
		 });
	
		 
	}
		 void inputips(PPfriend friend) {
				final PPfriend pfriend;
				pfriend=friend;
				//为“发生”按键添加按键触发
				//inputips(PPfriend)
				
				 myw.pa.aPanel2.aPanel3.button1.addActionListener(new ActionListener() {
						@Override
						
						public void actionPerformed(ActionEvent e) {
							
							name=friend.myw.pa.aPanel2.aPanel3.msg.getText();
							new Thread(sendip).start();
							
							//String ID = myw.pa.aPanel2.aPanel3.msg.getText();
							//	pfriend.name=ID;

						}
					});	
		
	}
		 public void refresh(PPfriend friend) {
				final PPfriend pfriend;
				pfriend=friend;
				//为“发生”按键添加按键触发
				 myw.pb.button3.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							listset.clear();
							myw.pa.aPanel2.list.clear();
						}
					});	
			}
		 
	String getip() throws SocketException, UnknownHostException{
	InetAddress Address=InetAddress.getLocalHost();
	NetworkInterface netInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
	String ips =null;
	if (!netInterface.isLoopback() && netInterface.isUp()) {
	List<InterfaceAddress> interfaceAddresses = netInterface.getInterfaceAddresses();
	
	for (InterfaceAddress interfaceAddress : interfaceAddresses) {
		//只有 IPv4 网络具有广播地址，因此对于 IPv6 网络将返回 null。 
		if(interfaceAddress.getBroadcast()!= null){
			ips =interfaceAddress.getBroadcast().getHostAddress();
			}
		}
	}
	return ips;	
}
//	public  void a(){
//		SendFile sendFile=new SendFile(this, se ndfilepath);
//	}
	public static void main(String[] args){
		

        SwingUtilities.invokeLater(new Runnable() {		//多线程异步执行
            public void run() {
                try {

                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                try {
					new PPfriend();
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
	}

}

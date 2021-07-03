import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

//用程序可以将其他组件放在面板提供的空间内
class Apanel extends Panel{
	TextArea chat;
	APanel2 aPanel2;
	public Apanel(){
		//创建个只读文本
		chat=new TextArea(40,43);
		chat.setEditable(false);
		aPanel2=new APanel2();
		//布置容器的边框布局
		setLayout(new BorderLayout());
		add("West",chat);
		add("East",aPanel2);
	}
}
class APanel2 extends Panel{
	java.awt.List list;
	APanel3 aPanel3;
	APanel2(){
		//List 组件为用户提供了一个可滚动的单选文本项列表,
		list=new List(25,false);
		aPanel3=new APanel3();
		setLayout(new BorderLayout());
		add("Center",list);
		add("North",aPanel3);
		
		add("East",new Label());
		add("South",new Label("双击一个人的昵称可以进行交谈"));
		
	}
}
class APanel3 extends Panel{
	JTextArea msg;
	Button button1;
	APanel3(){
		msg=new JTextArea(1,10);
		button1=new Button("提交");
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(new JLabel("输入用户名"));
		add(msg);
		add(button1);
	}
}
class BPanel extends Panel{
	JTextArea msg;
	Button button1,button2,button3,button4,button5;
	public BPanel(){
		msg=new JTextArea(5,30);
		button1=new Button("发送");
		button2=new Button("刷新谈话区");
		button3=new Button("刷新在线用户");
		Dimension preferredSize = new Dimension(100,80);
		button1.setPreferredSize(preferredSize );
       // button2.addActionListener(new Myw_reflash_msg_adapter());
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(new JLabel("<html>发<br>送<br>的<br>话<html>"));
		add(msg);
        add(button1);
        add(button2);
        add(button3);

    }
}
public class Mywindow extends JFrame{
	//PPfriend friend=new PPfriend();
       Label person;
       Apanel pa;
       BPanel pb;
       
       public Mywindow(){
    	   super();
    	   person=new Label("聊天记录");
    	   pa=new Apanel();
    	   pb=new BPanel();
    	   setLayout(new BorderLayout());
    	   setBackground(Color.YELLOW);
    	   setSize(650, 550);
    	   setLocation(550, 200);
    	   setResizable(true);
    	   add("North",person);
    	   add("Center",pa);
    	   add("South",pb);
  		 	ImageIcon imageIcon=new ImageIcon("./src/1.png");
	        this.setIconImage(imageIcon.getImage());
    	   //此类存在的目的是方便创建侦听器对象
    	   addWindowListener(new WindowAdapter() {
    		   public void windowClosing(WindowEvent e){
    			   System.exit(1);
    		   }
    	   });
    	   this.setTitle("p2p");
    	   setVisible(true);
       }
       
}

class Myw_send_adapter implements ActionListener {
     SocketStatus status=new SocketStatus();
//	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("fasonfaoononon.......");
	  status.setStatus(SocketStatus.RECIVE_ALL_MSG);
	}
   
}

class Myw_reflash_msg_adapter implements ActionListener {
	SocketStatus status=new SocketStatus();
//	@Override
	public void actionPerformed(ActionEvent e) {
		status.status=SocketStatus.RECIVE_ALL_MSG;
	}
   
}

class Myw_reflash_people_adapter implements ActionListener {
	SocketStatus status=new SocketStatus();
//	@Override
	
	public void actionPerformed(ActionEvent e) {
		
		status.status=SocketStatus.RECIVE_IP;
	}
   
}

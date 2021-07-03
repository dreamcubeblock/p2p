import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;

public class Dialog extends javax.swing.JFrame {
	TextArea chat;
	TextField sendchat;
	Button sbutton,fbutton,refile;
	public Dialog(String name) {
		super(name);
		Label title=new Label("密函:");
		//创建个只读文本，用来显示别人发过来的消息
		chat=new TextArea(20,20);
		chat.setEditable(false);
		//创建个允许编辑的文本，用来发送一对一消息
		sendchat=new TextField(25);
//		sendchat.setEditable(true);
		sbutton=new Button("发送");
		fbutton=new Button("发送文件");
		refile=new Button("接收文件");
		refile.setEnabled(false);
		Panel p=new Panel(new FlowLayout(FlowLayout.LEFT));
		//setLayout(new BorderLayout());
		setBackground(Color.YELLOW);
		setTitle("一对一聊天");
 	    setSize(400, 200);
 	    setLocation(450, 100);
 	    setResizable(false);
 	    add("North",title);
		add("Center",chat);
		p.add(sendchat);
		p.add(sbutton);
		p.add(fbutton);
		p.add(refile);
		add("South",p);
//		add("East",sendchat);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosed(WindowEvent e){
				e.getWindow().dispose();
			}
		});
		setVisible(true);
	}
}

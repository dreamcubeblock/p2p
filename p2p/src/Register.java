import java.util.ArrayList;
import java.util.List;

public class Register extends Thread{
	Dialog qq;
	PPfriend friend;
	List listset = new ArrayList();
	public Register(Dialog qq,PPfriend friend) {
		super();
		this.qq=qq;
		
	}
	public void run() {
		while(true) {
			if(listset.indexOf(friend.receivetooneMSG)==-1) {
			qq.chat.append("用户："+friend.sendIp.split(":")[0]+"说"+":"+friend.receivetooneMSG+"\n");
			listset.add(friend.receivetooneMSG);
			}
			}

	}
}
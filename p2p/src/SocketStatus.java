

public class SocketStatus {
	//当前程序的状态
    int status=0x01;
    public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public final static int RECIVE_IP = 0x01; //接收在线用户的IP

    public final static int RECIVE_ALL_MSG = 0x02; //接收群消息

    public final static int RECIVE_ONE_TO_ONE_MSG = 0x03; //接收某个人的消息

    public final static int RECIVE_TEST_MSG = 0x04; //测试是不是能够建立TCP连接
    public final static int RECIVE_NAME = 0x05;
//
//    public final static int FILE_TRANS_STATUS_FAIL = 0x05; //文件传输失败
}


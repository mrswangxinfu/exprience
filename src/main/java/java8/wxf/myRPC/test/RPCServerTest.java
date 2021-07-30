package java8.wxf.myRPC.test;

		import java8.wxf.myRPC.server.HelloService;
		import java8.wxf.myRPC.server.HelloServiceImpl;
		import java8.wxf.myRPC.server.Server;
		import java8.wxf.myRPC.server.ServerCenter;

public class RPCServerTest {
	public static void main(String[] args) {
		//开启一个线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				//服务中心
				Server server = new ServerCenter(9999);
				//将HelloService接口及实现类 注册到 服务中心
				server.register(HelloService.class, HelloServiceImpl.class);
				server.start();
			}
		}).start();//start()
	}
}

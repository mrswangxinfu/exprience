package java8.wxf.myRPC.test;

import java8.wxf.myRPC.client.Client;
import java8.wxf.myRPC.server.HelloService;

import java.net.InetSocketAddress;



public class RPCClientTest {
	public static void main(String[] args) throws ClassNotFoundException {
		HelloService service = Client.getRemoteProxyObj(
				/**初始化HelloService*/
				Class.forName("java8.wxf.myRPC.server.HelloService") ,
				new InetSocketAddress("127.0.0.1", 9999));
		System.out.println( service.sayHi("RPC")  ) ;
		System.out.println(service.sayHi("1324165"));
	}
}

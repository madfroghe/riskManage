package httpIvk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;

import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.protocol.HttpContext;

/**
 * 
 * @author chenchong@rong360.com
 * @date [2016年10月24日]
 */
public class MyPlainSocketFactory extends PlainConnectionSocketFactory {

	@Override
	public Socket createSocket(final HttpContext context) throws IOException {
		InetSocketAddress socksAddr = (InetSocketAddress) context.getAttribute("socks.address");
		if (socksAddr != null) {
			Proxy proxy = new Proxy(Proxy.Type.SOCKS, socksAddr);
			return new Socket(proxy);
		}
		return new Socket();
	}
}

package baic.obd.gateway.socket;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class Server {

	private static final Logger LOG = Logger.getLogger(Server.class);
	private static final int PORT = 8081;

	public static void main(String[] args) throws Exception {
		LOG.info("Socket Server starting up, the port is: " + PORT);
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.setHandler(new Handler());
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 60);
		acceptor.setDefaultLocalAddress(new InetSocketAddress(PORT));
		acceptor.bind();
	}
}

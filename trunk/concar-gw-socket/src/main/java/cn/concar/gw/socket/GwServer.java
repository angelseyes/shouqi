/**
 * 
 */
package cn.concar.gw.socket;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import cn.concar.gw.jms.InitJms;
import cn.concar.gw.util.Constants;

/**
 * @author lihao
 *
 */
public class GwServer {
	
	private static final Logger LOG = Logger.getLogger(GwServer.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Runtime.getRuntime().addShutdownHook(new GwServerShutdownHook());
		
		
	    // Create the acceptor  
        IoAcceptor acceptor = new NioSocketAcceptor();  
          
        // Add two filters : a logger and a codec  
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new CodecFactory()));
        acceptor.getFilterChain().addLast("logger", new LoggingFilter() );  
        
        // Attach the business logic to the server  
        acceptor.setHandler(new MessageHandler());  


     

  
        // Configurate the buffer size and the iddle time  
        acceptor.getSessionConfig().setReadBufferSize(Constants.READ_BUFFER);  
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, Constants.IDEL_TIME);  
          
        // And bind !  
        try {
			acceptor.bind( new InetSocketAddress(Constants.PORT) );
		} catch (Exception e) {
			LOG.error("Failed to bind port: " + Constants.PORT, e);
			throw new RuntimeException(e.getMessage());
		}
        
        InitJms.startJMSContainer();
        LOG.info("GW Server listen to " + Constants.HOST + ":" + Constants.PORT);
        LOG.info("GW Server started in cluster " + Constants.CLUSTER_MODE + " mode.");

	}

}

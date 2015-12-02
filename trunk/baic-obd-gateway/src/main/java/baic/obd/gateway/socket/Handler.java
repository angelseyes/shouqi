package baic.obd.gateway.socket;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import baic.obd.gateway.controller.BaseController;
import baic.obd.gateway.controller.ServiceController;
import baic.obd.gateway.terminal.Terminal;
import baic.obd.gateway.terminal.TerminalFactory;
import baic.obd.protocol.model.ModelPackage;
import baic.obd.protocol.parser.Parser;

public class Handler extends IoHandlerAdapter {

	private static final Logger LOG = Logger.getLogger(Handler.class);

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		LOG.info("Inside messageReceived.");
		IoBuffer ioBuffer = (IoBuffer) message;
		byte[] buffer = new byte[ioBuffer.remaining()];
		ioBuffer.get(buffer);
		String messageStr = new String(buffer);
		LOG.info("Received message: " + messageStr);

		Terminal terminal = (Terminal) session.getAttribute("terminal");
		if (terminal == null) {
			terminal = new Terminal();
			terminal.setSession(session);
			session.setAttribute("terminal", terminal);
			TerminalFactory.addTerminal(terminal);
		}
		terminal.setMessageStr(messageStr);
		String completeMessage = terminal.getMessageStr();
		if (completeMessage != null) {

			ModelPackage mp = null;
			try {
				mp = Parser.parse(messageStr);
				LOG.info("Parse successfully!");
			} catch (Exception e) {
				LOG.error("Parse error: " + e.getMessage());
			}
			if (mp != null && mp.getModelList().size() > 0) {
				BaseController serviceController = new ServiceController(mp);
				Thread serviceThread = new Thread(serviceController);
				serviceThread.start();
			}
		}
	}
}
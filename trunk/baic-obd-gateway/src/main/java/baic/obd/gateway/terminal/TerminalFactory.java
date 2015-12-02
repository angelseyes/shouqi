package baic.obd.gateway.terminal;

import java.util.HashMap;
import java.util.Map;

public class TerminalFactory {
	
	private static Map<String, Terminal> terminalMap = new HashMap<String, Terminal>();

	public static Terminal getTerminal(String terminalNo) {
		return terminalMap.get(terminalNo);
	}

	public static void addTerminal(Terminal terminal) {
		if (getTerminal(terminal.getTerminalNo()) == null) {
			terminalMap.put(terminal.getTerminalNo(), terminal);
		}
	}
}

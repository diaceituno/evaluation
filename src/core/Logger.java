package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

	private IOControl ioControl;
	
	//Singleton Pattern
	public static Logger logger = null;
	private Logger() {
		ioControl = new IOControl();
	}
	public static Logger getInstance() {
		if(logger == null) {
			logger = new Logger();
		}
		return logger;
	}
	
	public void logError(Exception exception) {
		
		DateFormat dateFormat = new SimpleDateFormat("[dd/MM/yyyy - HH:mm]");
		String toLog = dateFormat.format(new Date()) + "\n";
		StringWriter sw = new StringWriter();
		exception.printStackTrace(new PrintWriter(sw));
		toLog += "[TRACE]" + sw.toString() + "\n";
		
		try {
			ioControl.save("log.txt", toLog, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

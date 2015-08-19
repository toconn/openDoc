/*
 * Created on Dec 30, 2004
 */
package test.unit;

import java.text.MessageFormat;

import ua.core.service.message.MessageService;
import ua.core.exceptions.ExceptionRuntime;
import ua.core.exceptions.ExceptionBase;
import ua.core.util.Message;
import ua.core.util.StringList;
import ua.core.util.StringUtils;

/**
 * @author TOCONNEL
 */
public class TestBase {

	public static String		CLASS_NAME		= "BaseTest";

	private static final int	TEXT_PADDING	= 24;


	public static String getFormattedMessage (Message message) {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		MessageFormat messageFormat = null;

		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		messageFormat = new MessageFormat (message.getFormat());

		return messageFormat.format (message.getParameters());

	}


	public static int getTextPaddingSize() {

		return TEXT_PADDING;
	}


	public static void print() {

		System.out.println ("");
	}


	public static void print (Exception e, String exceptionType) {

		print ("Exception", exceptionType + ": " + e.getMessage());

	}


	public static void print (Exception e, String className, String methodName, String comment) {

		print ("Exception", e.getClass().getName());
		print ("   Message", e.getMessage());
		print ("   Class", className);
		print ("   Method", methodName);

		if (comment != null) {
			print ("   comment", comment);
		}

		print ("");
	}


	public static void print (ExceptionBase e) {

		// /////////////////////////////////////////
		// Declarations:
		// /////////////////////////////////////////

		final String METHOD_NAME = "print (ExceptionBase)";

		StringList messageList = null;

		// /////////////////////////////////////////
		// Code:
		// /////////////////////////////////////////

		try {

			print ("Exception", e.getExceptionType().getFormat());

			if (e.getMessageList() != null) {

				messageList = MessageService.getFormattedMessageList (null, e.getMessageList());

				for (String text : messageList) {

					print ("", text);
				}
			}
		}

		// /////////////////////////////////////////
		// Code:
		// /////////////////////////////////////////

		catch (Exception eNew) {

			print (eNew, CLASS_NAME + ":" + METHOD_NAME + ": Exception while printing validation messages");
		}

	}


	public static void print (ExceptionBase e, String exceptionType) {

		print ("ExceptionBase", exceptionType + ": " + e.getExceptionType().getFormat());

	}


	public static void print (ExceptionRuntime e) {

		// try {

		if (e.getOriginalException() != null) {

			print ("Exception :", e.getOriginalException().getClass().getName());
		}
		else {

			print ("Exception :");
		}

		print ("   Class   :", e.getClassName());
		print ("   Method  :", e.getMethodName());

		for (Message message : e.getMessageList()) {

			print (getFormattedMessage (message));
		}

		print ("");

	}


	public static void print (Object obj) {

		System.out.println (obj);
	}


	public static void print (String text) {

		System.out.println (text);
	}


	public static void print (String text1, String text2) {

		System.out.println (StringUtils.padString (text1, " ", getTextPaddingSize()) + ": " + text2);
	}
}

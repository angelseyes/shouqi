package cn.concar.service.util;

import static java.util.Arrays.asList;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.CharacterIterator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.Collection;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: StringUtils Function: Strings understands utility functions
 * associated with Strings. Date: 2013-6-24
 *
 * @author haoli
 * @version 1.0
 */
public final class StringUtils {

	/**
	 * REPLACER:replacer field.
	 */
	private static final Map<Character, String> REPLACER = replacer();

	/**
	 * Creates a new instance of StringUtils.
	 *
	 */
	private StringUtils() {
		// utility class
	}

	/**
	 * nullValue.
	 * 
	 * @param input
	 *            input
	 * @param ifNull
	 *            ifNull
	 * @return String
	 */
	public static String nullValue(String input, String ifNull) {
		return (input != null) ? input : ifNull;
	}

	/**
	 * join Strings.
	 * 
	 * @param token
	 *            token
	 * @param strings
	 *            strings
	 * @return String
	 * @throws Exception
	 */
	public static String join(String token, String... strings) throws Exception {
		return join(token, asList(strings));
	}

	/**
	 * join: join a list of strings together into a single string using ", " as
	 * the token. assertEquals("A, B, C", join(asList("A", "B", "C"))).
	 * 
	 * @param strings
	 *            strings
	 * @return String
	 * @throws Exception
	 */
	public static String join(List<String> strings) throws Exception {
		return join(", ", strings);
	}

	/**
	 * truncateWithDefault.
	 * 
	 * @param value
	 *            value
	 * @param size
	 *            size
	 * @param defaultValue
	 *            defaultValue
	 * @return String
	 */
	public static String truncateWithDefault(String value, int size, String defaultValue) {
		if (isEmpty(value)) {
			return defaultValue;
		}
		if (value.length() > size) {
			return defaultValue;
		}
		return value;
	}

	/**
	 * join:(Describe the usage of this method).
	 * 
	 * @param token
	 *            token
	 * @param strings
	 *            strings
	 * @return String
	 * @throws Exception
	 */
	public static String join(String token, Collection<String> strings) throws Exception {
		return join(token, strings, false);
	}

	/**
	 * joinMaybe.
	 * 
	 * @param token
	 *            token
	 * @param strings
	 *            strings
	 * @return String
	 * @throws Exception
	 */
	public static String joinMaybe(String token, String... strings) throws Exception {
		return join(token, asList(strings), true);
	}

	/**
	 * join.
	 * 
	 * @param token
	 *            token
	 * @param strings
	 *            strings
	 * @param ignoreEmpty
	 *            ignoreEmpty
	 * @return String String
	 * @throws Exception
	 *             Exception
	 */
	private static String join(String token, Collection<String> strings, boolean ignoreEmpty) throws Exception {
		StringBuilder b = new StringBuilder();
		for (String s : strings) {
			if (!ignoreEmpty && s == null) {
				throw new Exception("Can't join null elements!");
			}
			if (isEmpty(s) && ignoreEmpty) {
				continue;
			}
			b.append(s);
			b.append(token);
		}
		if (b.length() == 0) {
			return "";
		}
		b.delete(b.length() - token.length(), b.length());
		return b.toString();
	}

	/**
	 * paren: return a balanced parenthesized string. assertEquals("(A)",
	 * paren("A"));
	 * 
	 * @param s
	 *            string
	 * @return String
	 */
	public static String paren(String s) {
		return "(" + s + ")";
	}

	/**
	 * unParen.
	 * 
	 * @param clause
	 *            clause
	 * @return String
	 */
	public static String unParen(String clause) {
		return clause.replaceAll("\\(", "").replaceAll("\\)", "");
	}

	/**
	 * paren.
	 * 
	 * @param clauses
	 *            clauses
	 * @return String[]
	 */
	public static String[] paren(String[] clauses) {
		String[] result = new String[clauses.length];
		int index = 0;
		for (String s : clauses) {
			result[index++] = paren(s);
		}
		return result;
	}

	/**
	 * whether or not the string is null or consists only of whitespace.
	 * 
	 * @param s
	 * @return boolean
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}

	/**
	 * format.
	 * 
	 * @param input
	 *            input
	 * @param replace
	 *            replace
	 * @return String
	 */
	public static String format(String input, String... replace) {
		String str = "";
		Formatter formatter = new Formatter();
		try {
			str = formatter.format(input, (Object[]) replace).toString();
		} finally {
			formatter.close();
		}
		return str;
	}

	/**
	 * capitalize.
	 * 
	 * @param s
	 *            String
	 * @return String
	 */
	public static String capitalize(String s) {
		if (isEmpty(s)) {
			return s;
		}
		return Character.toTitleCase(s.charAt(0)) + s.substring(1);
	}

	/**
	 * zeroPad.
	 * 
	 * @param count
	 *            count
	 * @param num
	 *            num
	 * @return String
	 */
	public static String zeroPad(int count, long num) {
		return String.format("%0" + count + "d", num);
	}

	/**
	 * lastNCharacters.
	 * 
	 * @param s
	 *            s
	 * @param n
	 *            n
	 * @return String
	 */
	public static String lastNCharacters(String s, int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		int start = ((s.length() - n) < 0) ? 0 : (s.length() - n);
		return s.substring(start);
	}

	/**
	 * hasContent.
	 * 
	 * @param string
	 *            string
	 * @return boolean
	 */
	public static boolean hasContent(String string) {
		return !isEmpty(string);
	}

	/**
	 * isAtLeast.
	 * 
	 * @param minSize
	 *            minSize
	 * @param s
	 *            s
	 * @return boolean
	 */
	public static boolean isAtLeast(String s, int minSize) {
		if (minSize < 0 || s == null) {
			throw new IllegalArgumentException();
		}
		if (isEmpty(s) && minSize > 0) {
			return false;
		}
		return s.length() >= minSize;
	}

	/**
	 * capitalizeWords.
	 * 
	 * @param s
	 *            s
	 * @return String
	 */
	public static String capitalizeWords(String s) {
		if (s == null) {
			return null;
		}
		String[] tokens = s.split(" ");
		StringBuilder buf = new StringBuilder();
		for (String x : tokens) {
			buf.append(capitalize(x) + " ");
		}

		return buf.substring(0, buf.length() - 1);
	}

	/**
	 * escapeHtml.
	 * 
	 * @param aTagFragment
	 *            aTagFragment
	 * @return String
	 */
	public static String escapeHtml(String aTagFragment) {
		final StringBuffer result = new StringBuffer();
		final StringCharacterIterator i = new StringCharacterIterator(aTagFragment);
		char character = i.current();
		while (character != CharacterIterator.DONE) {
			result.append(translate(character));
			character = i.next();
		}
		return result.toString();
	}

	/**
	 * Map contain strings code.
	 * 
	 * @return Map
	 */
	private static Map<Character, String> replacer() {
		HashMap<Character, String> replacer = new HashMap<Character, String>();
		replacer.put('<', "&lt;");
		replacer.put('>', "&gt;");
		replacer.put('\"', "&quot;");
		replacer.put('\'', "&#039;");
		replacer.put('\\', "&#092;");
		replacer.put('&', "&amp;");
		return replacer;
	}

	/**
	 * translate character to string.
	 * 
	 * @param character
	 *            character
	 * @return string
	 */
	private static String translate(char character) {
		if (REPLACER.containsKey(character)) {
			return REPLACER.get(character);
		}
		return String.valueOf(character);
	}

	/**
	 * add bracket to string's 2 sides.
	 * 
	 * @param value
	 *            value
	 * @return string
	 */
	public static String bracket(String value) {
		return "[" + value + "]";
	}

	/**
	 * check string1 is equal to string2.
	 * 
	 * @param string1
	 *            string1
	 * @param string2
	 *            string2
	 * @return boolean
	 */
	public static boolean match(String string1, String string2) {
		if (string1 == null && string2 == null) {
			return true;
		}
		if (string1 == null) {
			return false;
		}
		return string1.equals(string2);
	}

	/**
	 * isDigit.
	 * 
	 * @param value
	 *            value
	 * @return boolean
	 */
	public static boolean isDigit(String value) {
		if (value == null || value.length() == 0) {
			return false;
		}
		return value.matches("\\p{Digit}+");
	}

	/**
	 * stringToCharacter.
	 * 
	 * @param s
	 *            s
	 * @return Character
	 */
	public static Character stringToCharacter(String s) {
		if (!isEmpty(s) && s.length() == 1) {
			return Character.valueOf(s.charAt(0));
		} else {
			throw new RuntimeException("Invalid Input String");
		}
	}

	/**
	 * stringToDate.
	 * 
	 * @param str
	 *            str
	 * @param formatStr
	 *            formatStr
	 * @return Date
	 * @throws ParseException
	 */
	public static Date stringToDate(String str, String formatStr) throws ParseException {
		DateFormat format = new SimpleDateFormat(formatStr);
		return format.parse(str);
	}

	/**
	 * dateToString.
	 * 
	 * @param date
	 *            date
	 * @param formatStr
	 *            formatStr
	 * @return String
	 */
	public static String dateToString(Date date, String formatStr) {
		DateFormat format = new SimpleDateFormat(formatStr);
		return format.format(date);
	}

	/**
	 * getStackTrace:(Describe the usage of this method). Get string of
	 * exception stack trace.
	 * 
	 * @param aThrowable
	 *            Throwable exception.
	 * @return String String of exception stack trace.
	 */
	public static String getStackTrace(Throwable aThrowable) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		return result.toString();
	}

	/**
	 * getStackTrace:(Describe the usage of this method). Get string of
	 * exception stack trace.
	 * 
	 * @author haoli
	 * @param aThrowable
	 *            Throwable exception.
	 * @param length
	 *            Length limit of the stack trace.
	 * @return String of exception stack trace.
	 */
	public static String getStackTrace(Throwable aThrowable, int length) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		if (result.toString().length() <= length) {
			return result.toString();
		} else {
			return result.toString().substring(0, length - 1);
		}
	}

	/**
	 * Transfer an object in. 1. if the object is not null, transfer the object
	 * to string and return it; 2. if the object is null, return null directly.
	 * 
	 * @return String or Null
	 * @author zxu1
	 * @param obj
	 */
	public static String toStringOrNull(Object obj) {
		if (obj == null) {
			return null;
		} else {
			return String.valueOf(obj);
		}
	}

	/**
	 * Check if mainStr contains subStr.
	 * 
	 * @return boolean
	 * @author qili
	 * @param String
	 *            mainStr
	 * @param String
	 *            subStr
	 */
	public static boolean contains(String mainStr, String subStr) {
		if (isEmpty(mainStr)) {
			return false;
		}
		if (isEmpty(subStr)) {
			return true;
		}
		return mainStr.toLowerCase().contains(subStr.toLowerCase());
	}

}

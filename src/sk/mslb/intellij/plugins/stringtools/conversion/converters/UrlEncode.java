package sk.mslb.intellij.plugins.stringtools.conversion.converters;

import sk.mslb.intellij.plugins.stringtools.conversion.ConversionResult;
import sk.mslb.intellij.plugins.stringtools.conversion.Converter;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class UrlEncode implements Converter {


	private static final String NOT_ESCAPED="-_.!~*'()";

	private String toHex(int ch) {
		return "%"+ Integer.toHexString(ch);
	}

	@Override
	public ConversionResult convert(String input) {

		StringBuilder sbuf = new StringBuilder();
		int len = input.length();
		for (int i = 0; i < len; i++) {
			int ch = input.charAt(i);
			if (Character.isDigit(ch)) {
				sbuf.append((char) ch);
			} else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
				sbuf.append((char) ch);
			} else if (NOT_ESCAPED.indexOf(ch) != -1) {
				sbuf.append((char) ch);
			} else if (ch == ' ') {			// space
				sbuf.append("%20");
			} else if (ch <= 0x007f) {        // other ASCII
				sbuf.append(toHex(ch));
			} else if (ch <= 0x07FF) {		// non-ASCII <= 0x7FF
				sbuf.append(toHex(0xc0 | (ch >> 6)));
				sbuf.append(toHex(0x80 | (ch & 0x3F)));
			} else {					// 0x7FF < ch <= 0xFFFF
				sbuf.append(toHex(0xe0 | (ch >> 12)));
				sbuf.append(toHex(0x80 | ((ch >> 6) & 0x3F)));
				sbuf.append(toHex(0x80 | (ch & 0x3F)));
			}
		}

		return new ConversionResult().withResult(sbuf.toString());
	}

	public static void main(String[] args) {
		System.out.println(new UrlEncode().convert("abc<>123 ľšč"));

	}
}

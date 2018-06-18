package sk.mslb.intellij.plugins.stringtools.conversion.converters;

import sk.mslb.intellij.plugins.stringtools.conversion.ConversionResult;
import sk.mslb.intellij.plugins.stringtools.conversion.Converter;

/**
 *  @author: Bert Bos <bert@w3.org>
 *  changed and formatted code from https://www.w3.org/International/unescape.java
 */
public class UrlDecode implements Converter {

	private static String unescape(String s) {
		StringBuilder sbuf = new StringBuilder();
		int sumb = 0;
		int b;

		for (int i = 0, more = -1; i < s.length(); i++) {
			int ch = s.charAt(i);
			switch (ch) {
				case '%':
					ch = s.charAt(++i);
					int hb = (Character.isDigit((char) ch) ? ch - '0' : 10 + Character.toLowerCase((char) ch) - 'a') & 0xF;
					ch = s.charAt(++i);
					int lb = (Character.isDigit((char) ch) ? ch - '0' : 10 + Character.toLowerCase((char) ch) - 'a') & 0xF;
					b = (hb << 4) | lb;
					break;
				default:
					b = ch;
			}

			if ((b & 0xc0) == 0x80) {                // 10xxxxxx (continuation byte)
				sumb = (sumb << 6) | (b & 0x3f);
				if (--more == 0) {
					sbuf.append((char) sumb);
				}
			} else if ((b & 0x80) == 0x00) {        // 0xxxxxxx (yields 7 bits)
				sbuf.append((char) b);
			} else if ((b & 0xe0) == 0xc0) {        // 110xxxxx (yields 5 bits)
				sumb = b & 0x1f;
				more = 1;
			} else if ((b & 0xf0) == 0xe0) {        // 1110xxxx (yields 4 bits)
				sumb = b & 0x0f;
				more = 2;
			} else if ((b & 0xf8) == 0xf0) {        // 11110xxx (yields 3 bits)
				sumb = b & 0x07;
				more = 3;
			} else if ((b & 0xfc) == 0xf8) {        // 111110xx (yields 2 bits)
				sumb = b & 0x03;
				more = 4;
			} else {                                // 1111110x (yields 1 bit)
				sumb = b & 0x01;
				more = 5;                            // Expect 5 more bytes
			}

		}
		return sbuf.toString();
	}

	@Override
	public ConversionResult convert(String input) {
		return new ConversionResult().withResult(unescape(input));
	}
}

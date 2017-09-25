package sk.mslb.intellij.plugins.stringfunctions.conversion;

import java.nio.charset.Charset;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class StringToHexConversion implements Conversion {

	private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

	@Override
	public String convert(String input) {
		return asHex(input.getBytes(Charset.defaultCharset()));
	}

	private String asHex(byte[] buf) {
		char[] chars = new char[2 * buf.length];
		for (int i = 0; i < buf.length; ++i) {
			chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
			chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
		}
		return new String(chars);
	}
}

package sk.mslb.intellij.plugins.stringfunctions.conversion;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class HexToStringConversion implements Conversion {

	@Override
	public String convert(String input) {
		return asString2(input);
	}

	private String asString2(String hexString) {
		String tmpStr = hexString.length() % 2 == 0 ? hexString: hexString.substring(0, hexString.length() - 1);

		StringBuilder sb = new StringBuilder();
		Arrays.stream(tmpStr.split("(?<=\\G.{2})"))
				.forEach(s -> sb.append((char) Integer.parseInt(s, 16))
				);
		return sb.toString();
	}
	
}

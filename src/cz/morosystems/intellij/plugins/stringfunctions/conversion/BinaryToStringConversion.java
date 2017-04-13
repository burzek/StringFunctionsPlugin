package cz.morosystems.intellij.plugins.stringfunctions.conversion;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class BinaryToStringConversion implements Conversion {

	@Override
	public String convert(String input) {
		return asString(input);
	}

	private String asString(String binString) {
			String tmpStr = binString.length() % 8 == 0 ? binString: binString.substring(0, 8 * (binString.length() / 8));

		StringBuilder sb = new StringBuilder();
		Arrays.stream(tmpStr.split("(?<=\\G.{8})"))
				.forEach(s -> sb.append((char) Integer.parseInt(s, 2))
		);
		return sb.toString();
	}
}

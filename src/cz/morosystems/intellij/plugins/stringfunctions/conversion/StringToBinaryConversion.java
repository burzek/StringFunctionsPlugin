package cz.morosystems.intellij.plugins.stringfunctions.conversion;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class StringToBinaryConversion implements Conversion {

	@Override
	public String convert(String input) {
		return asBinary(input);
	}

	private String asBinary(String input) {
		StringBuilder str = new StringBuilder();
		for (char c : input.toCharArray()) {
			for (int bit = 7; bit >= 0; bit--) {
				byte tstBit = (byte) (1 << bit);
				str.append((c & tstBit) == 0 ? '0' : '1');
			}
		}
		return str.toString();
	}
}
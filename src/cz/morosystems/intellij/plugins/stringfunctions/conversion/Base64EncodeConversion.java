package cz.morosystems.intellij.plugins.stringfunctions.conversion;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class Base64EncodeConversion implements Conversion {

	@Override
	public String convert(String input) {
		return "BASE64_ENCODE";
	}
}
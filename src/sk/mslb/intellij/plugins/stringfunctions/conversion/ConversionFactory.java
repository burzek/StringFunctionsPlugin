package sk.mslb.intellij.plugins.stringfunctions.conversion;

import sk.mslb.intellij.plugins.stringfunctions.data.Operation;

/**
 * @author boris.brinza 13-Apr-2017.
 */
public class ConversionFactory {

	public Conversion getTransformationAction(Operation transformation) {
		switch (transformation) {
			case STRING_TO_BIN:
				return new StringToBinaryConversion();
			case BIN_TO_STRING:
				return new BinaryToStringConversion();
			case STRING_TO_HEX:
				return new StringToHexConversion();
			case HEX_TO_STRING:
				return new HexToStringConversion();
			case BASE_64_DECODE:
				return new Base64DecodeConversion();
			case BASE_64_ENCODE:
				return new Base64EncodeConversion();
			case URL_DECODE:
				return new UrlDecodeConversion();
			case URL_ENCODE:
				return new UrlEncodeConversion();
			case HTML_ENCODE:
				return new HtmlEncodeConversion();
			case HTML_DECODE:
				return new HtmlDecodeConversion();
			default:
				throw new IllegalStateException("Invalid transformation:" + transformation);

		}
	}
}

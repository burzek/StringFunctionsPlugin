package sk.mslb.intellij.plugins.stringfunctions.conversion;

import sk.mslb.intellij.plugins.stringfunctions.conversion.converters.Base64Decode;
import sk.mslb.intellij.plugins.stringfunctions.conversion.converters.Base64Encode;
import sk.mslb.intellij.plugins.stringfunctions.conversion.converters.BinaryToString;
import sk.mslb.intellij.plugins.stringfunctions.conversion.converters.HexToString;
import sk.mslb.intellij.plugins.stringfunctions.conversion.converters.HtmlDecode;
import sk.mslb.intellij.plugins.stringfunctions.conversion.converters.HtmlEncode;
import sk.mslb.intellij.plugins.stringfunctions.conversion.converters.StringToBinary;
import sk.mslb.intellij.plugins.stringfunctions.conversion.converters.StringToHex;
import sk.mslb.intellij.plugins.stringfunctions.conversion.converters.UrlDecode;
import sk.mslb.intellij.plugins.stringfunctions.conversion.converters.UrlEncode;
import sk.mslb.intellij.plugins.stringfunctions.data.Operation;

/**
 * @author boris.brinza 13-Apr-2017.
 */
public class ConversionFactory {

	public Converter getConverter(Operation transformation) {
		switch (transformation) {
			case STRING_TO_BIN:
				return new StringToBinary();
			case BIN_TO_STRING:
				return new BinaryToString();
			case STRING_TO_HEX:
				return new StringToHex();
			case HEX_TO_STRING:
				return new HexToString();
			case BASE_64_DECODE:
				return new Base64Decode();
			case BASE_64_ENCODE:
				return new Base64Encode();
			case URL_DECODE:
				return new UrlDecode();
			case URL_ENCODE:
				return new UrlEncode();
			case HTML_ENCODE:
				return new HtmlEncode();
			case HTML_DECODE:
				return new HtmlDecode();
			default:
				throw new IllegalStateException("Invalid transformation:" + transformation);

		}
	}
}

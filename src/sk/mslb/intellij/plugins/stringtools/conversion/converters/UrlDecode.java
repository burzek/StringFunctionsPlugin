package sk.mslb.intellij.plugins.stringtools.conversion.converters;

import java.nio.charset.Charset;
import java.util.Base64;

import sk.mslb.intellij.plugins.stringtools.conversion.ConversionResult;
import sk.mslb.intellij.plugins.stringtools.conversion.Converter;
import sk.mslb.intellij.plugins.stringtools.gui.i18n.ResourceKey;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class UrlDecode implements Converter {
	@Override
	public ConversionResult convert(String input) {
		try {
			return new ConversionResult().withResult(new String(Base64.getUrlDecoder().decode(input), Charset.defaultCharset()));
		} catch (IllegalArgumentException e) {
			return new ConversionResult().withResult("???").withError(ResourceKey.ERR_INVALID_INPUT);
		}

	}
}

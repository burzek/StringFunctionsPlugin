package sk.mslb.intellij.plugins.stringtools.conversion;

import sk.mslb.intellij.plugins.stringtools.data.ConversionData;
import sk.mslb.intellij.plugins.stringtools.data.DataProvider;

/**
 * @author boris.brinza 13-Apr-2017.
 */
public class ConversionProcessor {

	private DataProvider dataProvider;

	public ConversionProcessor(DataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}

	public ConversionData doConversion() {
		ConversionData data = dataProvider.getConversionData();
		Converter converter = new ConversionFactory().getConverter(data.getOperation());
		ConversionResult conversionResult = converter.convert(data.getOriginalText());
		data.setConvertedText(conversionResult.getResult());
		if (conversionResult.isError()) {
			data.setErrorMessage(conversionResult.getErrorResourceKey());
		}

		return data;
	}
}

package sk.mslb.intellij.plugins.stringtools.conversion.converters;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class HexToString extends NumBaseToString {


	@Override
	protected int getBase() {
		return 16;
	}


	@Override
	protected int getNumberDigits() {
		return 2;
	}
}

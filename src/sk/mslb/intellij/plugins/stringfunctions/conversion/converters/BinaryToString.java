package sk.mslb.intellij.plugins.stringfunctions.conversion.converters;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class BinaryToString extends NumBaseToString {

	@Override
	protected int getBase() {
		return 2;
	}

	@Override
	protected int getNumberDigits() {
		return 8;
	}


}

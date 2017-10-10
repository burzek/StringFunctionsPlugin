package sk.mslb.intellij.plugins.stringfunctions.conversion.converters;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class OctToString extends NumBaseToString {

	@Override
	protected int getBase() {
		return 8;
	}

	@Override
	protected int getNumberDigits() {
		return 3;
	}

}

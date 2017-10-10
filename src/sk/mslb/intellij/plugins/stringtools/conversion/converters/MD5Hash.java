package sk.mslb.intellij.plugins.stringtools.conversion.converters;

/**
 * @author boris.brinza 10-Oct-2017.
 */
public class MD5Hash extends HashConverter {
	@Override
	protected HASH_TYPE getHashType() {
		return HASH_TYPE.MD5;
	}


}
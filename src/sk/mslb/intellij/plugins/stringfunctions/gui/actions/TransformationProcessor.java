package sk.mslb.intellij.plugins.stringfunctions.gui.actions;

import sk.mslb.intellij.plugins.stringfunctions.data.TransformationData;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public interface TransformationProcessor {

	TransformationData getTransformationData();
	void updateData(TransformationData document);
	void updateStatus(String status);
}

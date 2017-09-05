package cz.morosystems.intellij.plugins.stringfunctions.gui.actions;

import cz.morosystems.intellij.plugins.stringfunctions.data.TransformationData;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public interface TransformationProcessor {

	TransformationData getTransformationData();
	void updateData(TransformationData document);
}

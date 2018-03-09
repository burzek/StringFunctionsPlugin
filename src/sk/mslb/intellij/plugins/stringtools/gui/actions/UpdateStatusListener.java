package sk.mslb.intellij.plugins.stringtools.gui.actions;

import sk.mslb.intellij.plugins.stringtools.gui.i18n.ResourceKey;

/**
 * @author boris.brinza 28-Sep-2017.
 */
public interface UpdateStatusListener {

	void updateStatus(ResourceKey status);
	void updateErrorStatus(ResourceKey status);
}

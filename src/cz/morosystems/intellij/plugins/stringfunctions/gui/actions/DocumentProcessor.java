package cz.morosystems.intellij.plugins.stringfunctions.gui.actions;

import cz.morosystems.intellij.plugins.stringfunctions.data.Document;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public interface DocumentProcessor {

	Document getDocument();
	void updateDocument(Document document);
}

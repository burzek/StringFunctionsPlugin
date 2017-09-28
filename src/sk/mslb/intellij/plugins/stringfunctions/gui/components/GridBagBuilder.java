package sk.mslb.intellij.plugins.stringfunctions.gui.components;

import java.awt.*;
import com.intellij.util.ui.JBUI;

/**
 * @author boris.brinza 28-Sep-2017.
 */
public class GridBagBuilder {

	private GridBagConstraints templateGBC = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH,
			GridBagConstraints.HORIZONTAL, JBUI.insetsRight(5), 0, 0);

	private GridBagConstraints currentGbc;

	public GridBagBuilder() {
		currentGbc = (GridBagConstraints) templateGBC.clone();
	}

	public GridBagBuilder withPos(int gridX, int gridY) {
		currentGbc.gridx = gridX;
		currentGbc.gridy = gridY;
		return this;
	}

	public GridBagBuilder withAnchor(int anchor) {
		currentGbc.anchor = anchor;
		return this;
	}

	public GridBagBuilder withGridWidth(int width) {
		currentGbc.gridwidth = width;
		return this;
	}

	public GridBagBuilder withFill(int fill) {
		currentGbc.fill = fill;
		return this;
	}


	public GridBagConstraints toGBC() {
		return currentGbc;
	}


}

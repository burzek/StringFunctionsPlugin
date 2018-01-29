package sk.mslb.intellij.plugins.stringtools.gui.components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;

import com.intellij.icons.AllIcons.General;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.JBColor;

import sk.mslb.intellij.plugins.stringtools.gui.actions.ActionsRequestListener;

/**
 * @author boris.brinza
 */
public class InputTextEditor extends EditorTextField {
	private boolean showWarning;

	public InputTextEditor(ActionsRequestListener trl) {
		super();
		initializeGUI();
		getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void documentChanged(DocumentEvent event) {
				trl.transformationRequested();
			}
		});
	}

	private void initializeGUI() {
		setOneLineMode(false);
		setPreferredSize(new Dimension(350, 100));
		setMinimumSize(new Dimension(350, 100));
	}

	public void showWarning(boolean showWarning) {
		this.showWarning = showWarning;
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (showWarning) {
			Icon icon = new IconWithTooltip(General.Error);
			icon.paintIcon(this, g, getWidth() - icon.getIconWidth() - 5, getHeight() - icon.getIconHeight() - 5);

		}
	}

	private class IconWithTooltip implements Icon {

		private boolean showTooltip;
		private Icon icon;

		public IconWithTooltip(Icon icon) {
			this.icon = icon;
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) {
					showTooltip = false;
					if (e.getX() >= getX() &&
						e.getX() <= getX() + icon.getIconWidth() &&
						e.getY() >= getY() &&
						e.getY() <= getY() + icon.getIconWidth()) {
						showTooltip = true;
						repaint();
					}
				}
			});
		}

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			icon.paintIcon(c, g, x, y);
			if (showTooltip) {
				int xStart = c.getX() + 60;
				int yStart = c.getY() + 40;
				int xEnd = xStart + 170;
				int yEnd = yStart + 40;

				g.setColor(JBColor.yellow.brighter());
				g.fillRect(xStart, yStart, xEnd - xStart, yEnd - yStart);
				g.setColor(JBColor.black);
				g.drawLine(xStart, yStart, xEnd, yStart);
				g.drawLine(xEnd, yStart, xEnd, yEnd);
				g.drawLine(xEnd, yEnd, xStart, yEnd);
				g.drawLine(xStart, yEnd, xStart, yStart);
				g.drawString("xxxxxxxxxxsfkjdskljkt ekrjtg kdjfgkldjfgkfjdg dfsklgjdfkl", xStart + 5, yStart + 10);
			}
		}

		@Override
		public int getIconWidth() {
			return icon.getIconWidth();
		}

		@Override
		public int getIconHeight() {
			return icon.getIconHeight();
		}
	}
}

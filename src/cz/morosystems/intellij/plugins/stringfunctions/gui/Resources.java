package cz.morosystems.intellij.plugins.stringfunctions.gui;

import java.io.IOException;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import com.intellij.ide.plugins.PluginManager;
import org.jetbrains.java.generate.exception.PluginException;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class Resources {
	private static final String RESOURCE_PATH = "/ui.properties";

	private ResourceBundle resourceBundle;


	public Resources() {
		loadResource();
	}

	private void loadResource() {
		try {
			resourceBundle = new PropertyResourceBundle(getClass().getResourceAsStream(RESOURCE_PATH));
		} catch (IOException e) {
			PluginManager.getLogger().error("Cannot load resource " + RESOURCE_PATH);
			throw new PluginException("Cannot load resource " + RESOURCE_PATH, e);
		}
	}

	public String getText(ResourceKeys resourceKey) {
		try {
			return resourceBundle.getString(resourceKey.getResourceKey());
		} catch (MissingResourceException e) {
			PluginManager.getLogger().error("Cannot find resource with key:" + resourceKey.getResourceKey());
			return "???" + resourceKey.getResourceKey() + "???";
		}
	}
}

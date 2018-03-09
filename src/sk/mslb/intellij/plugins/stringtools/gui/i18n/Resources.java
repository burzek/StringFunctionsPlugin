package sk.mslb.intellij.plugins.stringtools.gui.i18n;

import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.swing.Icon;

import org.jetbrains.java.generate.exception.PluginException;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.util.IconLoader;

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

	public String getText(ResourceKey resourceKey) {
		String text;
		if (resourceBundle.containsKey(resourceKey.getResourceKey())) {
			text =  resourceBundle.getString(resourceKey.getResourceKey());
			for (String param : resourceKey.getParameterNames()) {
				text = text.replaceAll("\\$" + param, resourceKey.getParameterValueFor(param));
			}
		} else {
			PluginManager.getLogger().error("Cannot find resource with key:" + resourceKey.getResourceKey());
			text = "???" + resourceKey.getResourceKey() + "???";
		}
		return text;
	}

	public Icon getIcon(ResourceKey resourceKey) {
		Icon ret = null;
		String key = resourceKey.getResourceKey() + ".icon";
		if (resourceBundle.containsKey(key)) {
			String iconPath = resourceBundle.getString(key);
			ret = IconLoader.getIcon(iconPath);
		}
		return ret;
	}

	public Character getMnemonic(ResourceKey resourceKey) {
		Character ret = null;
		String key = resourceKey.getResourceKey() + ".mnemonic";
		if (resourceBundle.containsKey(key)) {
			String mnemonicStr = resourceBundle.getString(key);
			if (mnemonicStr.length() > 0) {
				ret = mnemonicStr.charAt(0);
			}
		}

		return ret;
	}
}

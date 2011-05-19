import net.GUIpsp.GuiBot.BasePlugin;
import net.GUIpsp.GuiBot.GuiBot;
import net.GUIpsp.GuiBot.Main;

public class SetPrefix extends BasePlugin {

	public void main() throws Throwable {
		registerCmd(
				"setpref",
				getClass().getMethod("setPref", String.class, String.class,
						String.class, String.class, String.class), this);

	}

	public String version() {

		return "V1";
	}

	public String pluginName() {

		return "SetPrefix";
	}

	public String author() {

		return "GUIpsp";
	}

	public void setPref(String channel, String sender, String login,
			String hostname, String message) {
		if (message.trim() == "") {
			Main.bot.sendMessage(
					channel,
					"USAGE: "
							+ GuiBot.pref
							+ "setpref \"<prefix>\" - sets the command prefix to <prefix>");
		} else {
			GuiBot.pref = message.replaceAll("\"", "");
			Main.bot.sendMessage(channel,
					"Prefix set to \"" + message.replaceAll("\"", "") + "\"");
		}
	}
}

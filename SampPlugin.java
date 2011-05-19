import net.GUIpsp.GuiBot.*;

public class SampPlugin extends BasePlugin {

	public void main() throws Throwable {
		registerCmd(
				"test",
		 		getClass().getMethod("fart", String.class, String.class,
						String.class, String.class, String.class), this);

	}

	public String version() {
		return "V1";
	}

	public String pluginName() {
		return "TestPlugin";
	}

	public String author() {
		return "GUIpsp";
	}

	public void fart(String channel, String sender, String login,
			String hostname, String message) {
		Main.bot.sendMessage(channel, "yup.wav");
	}

}

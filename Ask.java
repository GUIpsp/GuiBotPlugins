import java.util.*;

import net.GUIpsp.GuiBot.*;

public class Ask extends BasePlugin {

	public void main() throws Throwable {
		registerCmd(
				"ask",
				getClass().getMethod("ask", String.class, String.class,
						String.class, String.class, String.class), this,
				"USAGE: " + GuiBot.pref + "ask <question> - asks " + Main.nick
						+ " a question");
	}

	public String version() {
		return "V1";
	}

	public String pluginName() {

		return "Ask";
	}

	public String author() {

		return "GUIpsp";
	}

	public void ask(String channel, String sender, String login,
			String hostname, String message) {
		Random random = new Random();
		random.setSeed(message.hashCode());
		List<String> list = new ArrayList<String>();
		list.add("As I see it, yes");
		list.add("It is certain");
		list.add("It is decidedly so");
		list.add("Most likely");
		list.add("Outlook good");
		list.add("Signs point to yes");
		list.add("Without a doubt");
		list.add("Yes");
		list.add("Yes, definitely");
		list.add("You may rely on it");
		list.add("Don't count on it");
		list.add("My reply is no");
		list.add("My sources say no");
		list.add("Outlook not so good");
		list.add("Very doubtful");
		list.add("Don't lose hope");
		list.add("It may happen");
		list.add("Maybe");
		if (!(message.trim() == "")) {
			Main.bot.sendMessage(channel, list.get(random.nextInt(list.size())));
		}
	}
}
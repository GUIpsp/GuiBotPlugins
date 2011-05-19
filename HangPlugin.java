import java.io.*;
import java.util.*;
import net.GUIpsp.GuiBot.*;

public class HangPlugin extends BasePlugin {

	public void main() throws Throwable {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(
						"/home/guipsp/Desktop/GuiBot/plugins/wordlist.txt")));
		String tmp;
		tmp = reader.readLine();
		while (tmp != null) {
			choices.add(tmp);
			tmp = reader.readLine();

		}
		reader.close();
		registerCmd(
				"guess_start",
				getClass().getMethod("start", String.class, String.class,
						String.class, String.class, String.class), this);
		registerCmd(
				"guess",
				getClass().getMethod("guess", String.class, String.class,
						String.class, String.class, String.class), this);
	}

	public String version() {
		return "V1";
	}

	public String pluginName() {
		return "Stupid Hangman without the man";
	}

	public String author() {
		return "Rotten194";
	}

	public void start(String channel, String sender, String login,
			String hostname, String message) {
		if (isRunning == true) {
			Main.bot.sendMessage(channel, "A game is already running!");
			return;
		} else {
			newGame();
			Main.bot.sendMessage(channel, "Started a game! " + print());
		}
	}

	public void guess(String channel, String sender, String login,
			String hostname, String message) {
		if (isRunning)
			guess(message, channel);
		if (!isRunning) {
			Main.bot.sendMessage(channel, "Guessing where? No game running");
		} else if (!won() && isRunning == true) {
			isRunning = false;
			Main.bot.sendMessage(channel, "The word was \"" + word + "\", "
					+ sender + " got it!");
		} else {

			Main.bot.sendMessage(channel, "Status: " + print());
		}

	}

	public boolean isRunning;

	public String word;
	public String mask;
	public ArrayList<String> choices = new ArrayList<String>();

	public void newGame() {
		isRunning = true;
		word = choices.get(new Random().nextInt(choices.size()));
		mask = "";
		for (int i = 0; i < word.length(); i++)
			mask += "*";
	}

	public void guess(String guess, String channel) {
		if (guess.length() > 1)
			Main.bot.sendMessage(channel, "Can't guess a word!");
		else {
			int cor = 0;
			for (int i = 0; i < word.length(); i++) {
				if (word.charAt(i) == guess.charAt(0)) {
					cor++;
					mask = mask.substring(0, (i)) + " " + mask.substring(i + 1);
				}
			}
			Main.bot.sendMessage(channel, (cor > 0 ? "You got " + cor
					+ " characters!" : "Didn't find anything"));
		}
	}

	public String print() {
		String s = "";
		for (int i = 0; i < (word.length()); i++) {
			if (mask.charAt(i) == '*') {
				s += "*";
			} else {
				s += word.charAt(i);
			}
		}
		return s;
	}

	public boolean won() {

		return mask.contains("*");
	}
}

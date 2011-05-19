import java.io.*;
import java.net.*;
import java.util.regex.*;

import net.GUIpsp.GuiBot.*;

public class Title extends BasePlugin {

	public void main() throws Throwable {
		registerCmd(
				"title",
				getClass().getMethod("getTitle", String.class, String.class,
						String.class, String.class, String.class), this);

	}

	public String version() {

		return "V1";
	}

	public String pluginName() {

		return "Title";
	}

	public String author() {

		return "GUIpsp";
	}

	public void getTitle(String channel, String sender, String login,
			String hostname, String message) throws Throwable {
		if (message.trim() == "") {
			Main.bot.sendMessage(channel, "USAGE: " + GuiBot.pref
					+ "title <url> - gets <url>'s title");
		} else {
			if (!message.toLowerCase().trim().startsWith("http://")) {
				message = "http://" + message;
			}

			HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(
					message).openConnection();
			httpUrlConnection.setRequestMethod("HEAD");
			httpUrlConnection.connect();
			httpUrlConnection.setReadTimeout(3000);
			if (httpUrlConnection.getHeaderField("Content-Type").toLowerCase()
					.matches(".*html.*")) {
				try {
					URLConnection urlConnection = new URL(message)
							.openConnection();

					BufferedReader reader = new BufferedReader(
							new InputStreamReader(
									urlConnection.getInputStream()));
					String html = "", tmp = "";
					while ((tmp = reader.readLine()) != null) {
						html += " " + tmp;
					}

					reader.close();

					html = html.replaceAll("\\s+", " ");
					Pattern p = Pattern.compile("<title>(.*?)</title>");
					Matcher m = p.matcher(html);
					while (m.find() == true) {
						Main.bot.sendMessage(channel, sender + ": "
								+ m.group(1).trim());
					}
				} catch (UnknownHostException e) {
					Main.bot.sendMessage(channel, sender + ": No.");
				}

			} else {
				Main.bot.sendMessage(
						channel,
						sender
								+ ": "
								+ message
								+ " is not HTML, but it's content type is "
								+ httpUrlConnection
										.getHeaderField("Content-Type")
								+ " and it's content length is "
								+ httpUrlConnection
										.getHeaderField("Content-Length"));
			}
		}
	}
}
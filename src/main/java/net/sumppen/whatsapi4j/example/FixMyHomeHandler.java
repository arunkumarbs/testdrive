package net.sumppen.whatsapi4j.example;



import net.sumppen.whatsapi4j.EventManager;
import net.sumppen.whatsapi4j.MessageProcessor;
import net.sumppen.whatsapi4j.WhatsApi;
import net.sumppen.whatsapi4j.WhatsAppException;

public class FixMyHomeHandler {

	private enum WhatsAppCommand {
		send, read
	}
	public static boolean running = true;

	public static void send() {
		boolean loggedIn = false;

		String username = "919886576701";
		String password = "lTB0zsidjn2Hhua+u5VzfHY9GCc=";
		String identity = "911106558791086";
		//String identity = "351751048266335";
		String nickname = "FixMyHome";

		WhatsApi wa = null;
		try {
			wa = new WhatsApi(username, identity, nickname);

			EventManager eventManager = new ExampleEventManager();
			wa.setEventManager(eventManager );
			MessageProcessor mp = new ExampleMessageProcessor();
			wa.setNewMessageBind(mp);
			wa.connect();
			if(password != null) {
				wa.loginWithPassword(password);
				loggedIn = true;
			}
			//String cmd = "sendText";
			//			ExampleMessagePoller poller = new ExampleMessagePoller(wa);
			//			poller.start();
			System.out.print("$ ");
			while(running) {
				try {
					WhatsAppCommand wac = WhatsAppCommand.valueOf("send");
					switch(wac) {
					case send:
						if(loggedIn) {
							String to = "919880066934";
							String message = "Hi There 1";
							String returnMsg = sendTextMessage(wa, message, to);
							if(returnMsg!=null && !("".equals(returnMsg)) )
							{
								running = false;
							}
						} else {
							System.out.println("Not logged in!");
						}
						//break;
					default: 
						System.out.println("Unknown command: ");
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
				System.out.print("$ ");
			}
			//			poller.setRunning(false);
			System.out.println("Done! Logging out");
			//wa.disconnect();
		} catch (Exception e) {
			System.out.println("Caught exception: "+e.getMessage());
			e.printStackTrace();
			if(wa != null) {
				wa.disconnect();
			}
			//System.exit(1);
		}

	}

	public static void read() {
		boolean loggedIn = false;

		String username = "919886576701";
		String password = "lTB0zsidjn2Hhua+u5VzfHY9GCc=";
		String identity = "911106558791086";
		//String identity = "351751048266335";
		String nickname = "FixMyHome";

		WhatsApi wa = null;
		try {
			wa = new WhatsApi(username, identity, nickname);

			EventManager eventManager = new ExampleEventManager();
			wa.setEventManager(eventManager );
			MessageProcessor mp = new ExampleMessageProcessor();
			wa.setNewMessageBind(mp);
			wa.connect();
			if(password != null) {
				wa.loginWithPassword(password);
				loggedIn = true;
			}
			//String cmd = "sendText";
			//			ExampleMessagePoller poller = new ExampleMessagePoller(wa);
			//			poller.start();
			System.out.print("$ ");
			while(running) {
				try {
					WhatsAppCommand wac = WhatsAppCommand.valueOf("read");
					switch(wac) {
					case read:
						if(loggedIn) {
							/*String to = "919880066934";
							String message = "Hi There 1";
							String returnMsg = sendTextMessage(wa, message, to);
							if(returnMsg!=null && !("".equals(returnMsg)) )
							{
								running = false;
							}*/
							wa.pollMessages();
						} else {
							System.out.println("Not logged in!");
						}
						//break;
					default: 
						System.out.println("Unknown command: ");
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
				System.out.print("$ ");
			}
			//			poller.setRunning(false);
			System.out.println("Done! Logging out");
			//wa.disconnect();
		} catch (Exception e) {
			System.out.println("Caught exception: "+e.getMessage());
			e.printStackTrace();
			if(wa != null) {
				wa.disconnect();
			}
			//System.exit(1);
		}

	}

	private static String sendTextMessage(WhatsApi wa, String message, String to) throws WhatsAppException {
		System.out.print("To: ");

		if(to == null || to.length() == 0) {
			return null;
		}
		wa.sendMessageComposing(to);
		System.out.print("Message: ");

		wa.sendMessagePaused(to);
		if(message == null || message.length() == 0) {
			return null;
		}
		String res = wa.sendMessage(to, message);
		System.out.println("HELLO"+res);
		return res;
	}

}

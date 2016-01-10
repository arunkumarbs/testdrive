package net.sumppen.whatsapi4j.example;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;

import net.sumppen.whatsapi4j.MessageProcessor;
import net.sumppen.whatsapi4j.ProtocolNode;
import net.sumppen.whatsapi4j.message.Message;
import net.sumppen.whatsapi4j.message.TextMessage;

import com.google.common.io.Files;

public class ExampleMessageProcessor implements MessageProcessor {

	public void processMessage(ProtocolNode message) {
		String from = message.getAttribute("from");
		if(message.getAttribute("type").equals("text")) {
			ProtocolNode body = message.getChild("body");
			String hex = new String(body.getData());
			String participant = message.getAttribute("participant");
			if(participant != null && !participant.isEmpty()) {
				//Group message
				System.out.println(participant+"("+from+") ::: "+hex);
			} else {
				//Private message
				System.out.println(from+" ::: "+hex);
			}
		}
		if(message.getAttribute("type").equals("media")) {
			ProtocolNode media = message.getChild("media");
			String type = media.getAttribute("type");
			if(type.equals("location")) {
				System.out.println(from+" ::: ("+media.getAttribute("longitude")+","+media.getAttribute("latitude")+")");
			} else if (type.equals("image")) {
				String caption = media.getAttribute("caption");
				if(caption == null)
					caption = "";
				String pathname = "preview-image-"+(new Date().getTime())+".jpg";
				System.out.println(from+" ::: "+caption+"(image): "+media.getAttribute("url"));
				byte[] preview = media.getData();
				writePreview(pathname, preview);
			} else if (type.equals("video")) {
				String caption = media.getAttribute("caption");
				if(caption == null)
					caption = "";
				String pathname = "preview-video-"+(new Date().getTime())+".jpg";
				System.out.println(from+" ::: "+caption+"(video): "+media.getAttribute("url"));
				byte[] preview = media.getData();
				writePreview(pathname, preview);
			} else {
				System.out.println(from+" ::: media/"+type);
			}
			
		}
	}

	private void writePreview(String pathname, byte[] preview) {
		File path = new File(pathname);
		try {
			Files.write(preview, path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Preview: "+path.getAbsolutePath());
	}

	public void processMessage(Message message) {
		//TODO add all supported message types
		switch(message.getType()) {
		case TEXT:
			TextMessage msg = (TextMessage)message;
			if(msg.getGroupId() != null && !msg.getGroupId().isEmpty()) {
				//Group message
				System.out.println(msg.getDate()+" :: "+msg.getFrom()+"("+msg.getGroupId()+"): "+msg.getText());
			} else {
				//Private message
				System.out.println(msg.getDate()+" :(=): "+msg.getFrom()+"@ExampleMessageProcessor : "+msg.getText());
				// write custom logic to process the message for FixMyHome
				publishMessage(msg.getFrom(), msg.getText());
			}
			break;
		default:
			processMessage(message.getProtocolNode());
		}
	}
	
	private void publishMessage(String from, String message)
	{
		System.out.println("inside example message processor's publishMessage");
		try{
			String urlParams = "whatsappMessage=" + URLEncoder.encode(message, "UTF-8") +
					"&mobileIdentifier=" + URLEncoder.encode(from, "UTF-8");
			
			//URLConnection conn = new URL("http://6-dot-affable-ring-fbo.appspot.com/fmh_frontend?method=processWhatsAppMessage").openConnection();
			URLConnection conn = new URL("http://localhost:8888/fmh_frontend?method=processWhatsAppMessage").openConnection();
			
			conn.setDoInput(true);
			//conn.setDoOutput(false);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(urlParams);
			wr.flush();
		} catch (Exception e)
		{
			System.out.println("inside exception publishMessage:"+e.getMessage());
		}
	}
}

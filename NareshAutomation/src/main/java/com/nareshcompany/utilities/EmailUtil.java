package com.nareshcompany.utilities;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.nareshcompany.config.Configurations;

public class EmailUtil {

	public static void sendEmailWithDetails(String subject, String body, File file,Configurations config)
	{
		if(!config.EMAIL_FLAG.equals("true"))
			return;
		String host = "mailhost.prod.ch3.s.com";
		String from = "naresh.automation@searshc.com";
		String[] cc = {""};
		try {

			String emailRecipients =  config.EMAIL_RECEPIENTS;
			String emailRecipientsCC =  config.EMAIL_RECEPIENTS_CC;

			String[] toList = emailRecipients.split(",");

			if(emailRecipientsCC!=null)
			{
				cc = emailRecipientsCC.split(",");
			}

			sendMailUsingJavaMail(host, from, toList, cc,subject , body, file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param host SMTP host
	 * @param from From 
	 * @param to To list
	 * @param cc CC list
	 * @param subject Subject of email
	 * @param body Body text
	 * @param file File to attach
	 * @return
	 */
	public static int sendMailUsingJavaMail(String host, String from,  String[] to, String[] cc,  String subject, String body, File... files)
	{
		final Properties props = new Properties();

		props.put("mail.smtp.host", host);
		props.put("mail.debug", "false");

		final Session session = Session.getInstance(props);

		try 
		{
			final Message msg = new MimeMessage(session);

			if(from.endsWith(";") || from.endsWith(","))
			{
				from = from.substring(0, from.length() - 1);
			}

			msg.setFrom(new InternetAddress(from));

			final InternetAddress[] toAddress = new InternetAddress[to.length];

			for (int count = 0; count < to.length; count++) 
			{
				if(to[count] != null && !to[count].trim().equals(""))
				{
					toAddress[count] = new InternetAddress(to[count]);
				}
			}

			final InternetAddress[] ccAddress = new InternetAddress[cc.length];

			for (int count = 0; count < cc.length; count++) 
			{
				if(cc[count] != null && !cc[count].trim().equals(""))
				{
					ccAddress[count] = new InternetAddress(cc[count]);
				}
			}

			if(toAddress.length != 0 && toAddress[0] != null)
			{
				msg.setRecipients(Message.RecipientType.TO, toAddress);
			}

			if(ccAddress.length != 0 && ccAddress[0] != null)
			{
				msg.setRecipients(Message.RecipientType.CC, ccAddress);
			}

			msg.setSubject(subject);
			msg.setSentDate(new Date());

			Multipart multipart = new MimeMultipart("related");

			BodyPart mbp1 = new MimeBodyPart();
			mbp1.setContent(body, "text/html");			
			multipart.addBodyPart(mbp1);

			if(files!=null)
			{
				int size = files.length;

				for (int i = 0; i < size; i++) 
				{
					File file = files[i];

					if(file != null && file.exists())
					{
						long fileLen = getFileSizeInMb(file);
						System.out.println("attachement file size... : "+fileLen);
						if(fileLen<7)
						{
							MimeBodyPart mbp2 = new MimeBodyPart();
							FileDataSource fds = new FileDataSource(file);
							mbp2.setDataHandler(new DataHandler(fds));
							mbp2.setFileName(fds.getName());

							multipart.addBodyPart(mbp2);
						}
					}
				}
			}
			msg.setContent(multipart);

			Transport.send(msg);

			System.out.println("Mail sent sucessfully.");

			return 1;

		} catch (Exception ex) 
		{			
			ex.printStackTrace();
			return 0;
		}
	}
	/**
	 * Returns file size in MB
	 * @param fileName
	 * @return
	 */
	public static long getFileSizeInMb(String fileName)
	{
		try {
			return (new File(fileName).length()/(1024*1024));
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Returns file size in MB
	 * @param fileName
	 * @return
	 */
	public static long getFileSizeInMb(File file)
	{
		try {
			return (file.length()/(1024*1024));
		} catch (Exception e) {
			return 0;
		}
	}


}

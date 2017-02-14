/**
 * 
 */
package com.thravvel.core.utils.Exceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
public abstract class MessageBasedException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String fMessageId;
	protected Object[] fObjects;
	protected String catalogFileName;

	protected String humanMessage;
	protected String message;
	private String detail_technique = "DETAIL TECHNIQUE :";

	public static final String PROTOCOL_SEPARATOR = ":\\";

	/**
	 * Initializes a new instance of custom exception
	 * 
	 * @see MessageException
	 * @param catalogFileName
	 *            name of catalog file
	 * @param fMessageId
	 *            message id
	 */
	public MessageBasedException(String catalogFileName, String fMessageId) {

		fObjects = null;
		this.fMessageId = fMessageId;
		this.catalogFileName = catalogFileName;
		this.humanMessage = parseMessage(catalogFileName, fMessageId, fObjects);
		this.message = humanMessage;
	}

	/**
	 * Initializes a new instance of custom exception
	 * 
	 * @see MessageException
	 * @param catalogFileName
	 *            name of catalog file
	 * @param fMessageId
	 *            message id
	 * @param aobj
	 *            table of message arguments
	 * 
	 */
	public MessageBasedException(String catalogFileName, String fMessageId, Object[] aobj) {
		this.fMessageId = fMessageId;
		fObjects = aobj;
		this.catalogFileName = catalogFileName;
		this.humanMessage = parseMessage(catalogFileName, fMessageId, fObjects);
		this.message = humanMessage;
	}

	/**
	 * Initializes a new instance of custom exception
	 * 
	 * @param catalogFileName
	 *            name of catalog file
	 * @param fMessageId
	 *            message id
	 * @param aobj
	 *            table of message arguments
	 * @param strDetailTechnique
	 *            cause of exception
	 * 
	 */
	public MessageBasedException(String catalogFileName, String fMessageId, Object[] aobj, String strDetailTechnique) {
		this.fMessageId = fMessageId;
		fObjects = aobj;
		this.catalogFileName = catalogFileName;
		this.humanMessage = parseMessage(catalogFileName, fMessageId, fObjects);
		this.message = humanMessage + detail_technique + strDetailTechnique;
	}

	/**
	 * This constructor is use only for Serialization. Don't use it in your
	 * program.
	 */
	public MessageBasedException() {

	}

	/**
	 * Gets the file system resource without protocol
	 * 
	 * @param filePath
	 *            file path
	 * @return file resource
	 */
	private static String getFileSystemResourceWithoutProtocol(String filePath) {
		int pos = filePath.indexOf(PROTOCOL_SEPARATOR);
		if (pos == -1) {
			return filePath;
		} else {
			if (filePath.length() > pos + PROTOCOL_SEPARATOR.length()) {
				while (filePath.charAt(++pos) == '/') {
					;
				}
			}
			return filePath.substring(pos);
		}

	}

	/**
	 * Gets the configuration as XML document.
	 * 
	 * @param resourcePath
	 * @return
	 * @throws Exception
	 */
	private Document GetConfigAsXmlDocument(String resourcePath) throws Exception {
		Document config = null;
		resourcePath = getFileSystemResourceWithoutProtocol(resourcePath);

		try {
			SAXBuilder sxb = new SAXBuilder();
			// if the resourcepath is like
			// classpath:messages/DAOMessageError.xml
			// the resourcePath to use will be message/DAOMessageError.xml
			// without the prefix classpath
			if (resourcePath.startsWith("classpath")) {
				resourcePath = resourcePath.substring(resourcePath.indexOf(":") + 1);
				config = sxb.build(getClass().getClassLoader().getResourceAsStream(resourcePath));
			} else {
				config = sxb.build(new File(resourcePath));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception("File not found :" + resourcePath + ". Cause:" + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Error when opening the file :" + resourcePath + ". Cause:" + e.getMessage());
		} catch (JDOMException e) {
			e.printStackTrace();
			throw new Exception("Unable to load the file " + resourcePath + ". Cause" + e.getMessage());

		}

		return config;

	}

	/**
	 * Parses the message
	 * 
	 * @param catalogFileName
	 *            catalog file name
	 * @param fMessageId
	 *            message id
	 * @param aobj
	 *            table of message's arguments
	 * @return error message
	 */
	private String parseMessage(String catalogFileName, String fMessageId, Object[] aobj) {
		String messageValue = null;
		Element elt = null;
		try {
			Document messageErrorFile = GetConfigAsXmlDocument(catalogFileName);
			Element racine = messageErrorFile.getRootElement();
			XPath xpa = XPath.newInstance("//error[@key='" + fMessageId + "']");
			elt = (Element) xpa.selectSingleNode(racine);

			if (elt == null)
				return "Message not found : MessageId = " + fMessageId;
			xpa = XPath.newInstance("./@value");
			messageValue = xpa.valueOf(elt);

			// formatage avec prise en compte des parametres qui sont dans le
			// message
			messageValue = MessageFormat.format(messageValue, aobj);

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return messageValue;
	}

	/**
	 * @return human message.
	 */
	public String getHumanMessage() {
		return humanMessage;
	}

	/**
	 * Obtains current exception's message
	 * 
	 * @return error message explained cause of exception or empty string
	 */
	@Override
	public String getMessage() {
		return message;

	}

	/**
	 * set the HumanMessage. Please don't use it. It is use for serialization
	 * 
	 * @param humanMessage
	 */
	public void setHumanMessage(String humanMessage) {
		this.humanMessage = humanMessage;
	}

	/**
	 * set the Message. Please don't use it. It is use for serialization
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}

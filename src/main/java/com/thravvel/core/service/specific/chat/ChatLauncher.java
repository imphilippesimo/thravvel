/**
 * 
 */
package com.thravvel.core.service.specific.chat;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.thravvel.core.dao.contract.IUserDao;
import com.thravvel.core.data.entities.ChatObject;
import com.thravvel.core.service.CommonService;
import com.thravvel.core.utils.Exceptions.ThravvelCoreException;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 * 
 */
@Service
@Transactional
public class ChatLauncher extends CommonService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	IUserDao userDao;

	private Logger logger = Logger.getLogger(ChatLauncher.class);

	final SocketIOServer server = null;

	public ChatLauncher() {

	}

	public void launch() throws ThravvelCoreException {
		try {
			Configuration config = new Configuration();
			// for local environment
			// config.setHostname("localhost");
			config.setHostname("ec2-52-89-54-132.us-west-2.compute.amazonaws.com");
			config.setPort(9092);
			// config.setJsonSupport(jsonSupport);

			final SocketIOServer server = new SocketIOServer(config);
			server.addEventListener("chatevent", ChatObject.class, new DataListener<ChatObject>() {

				@Override
				public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) {
					// logger.info("==========" + data.getSender().getUserName()
					// + "==========");
					// broadcast messages to all clients
					server.getBroadcastOperations().sendEvent("chatevent", data);
					// logger.info("==========" + data.getContent() +
					// "==========");
				}

			});

			server.start();
			logger.info("Listenning on port 9092...");

			// Thread.sleep(Integer.MAX_VALUE);

			// } catch (ThravvelCoreException tce) {
			// throw tce;

		} catch (Exception e) {
			ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCORECHATLAUNCHERERROR-001");
			logger.error(coreException.getMessage(), e);
			throw coreException;
		}
	}

	public void shutdown() {
		server.stop();
	}

}

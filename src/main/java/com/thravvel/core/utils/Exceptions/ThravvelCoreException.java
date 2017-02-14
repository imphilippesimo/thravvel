/**
 * 
 */
package com.thravvel.core.utils.Exceptions;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
public class ThravvelCoreException extends MessageBasedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String BASE_PATH_SERVER_MESSAGE = "";

	protected String getBasePathCatologName() {
		return BASE_PATH_SERVER_MESSAGE;
	}

	public ThravvelCoreException(String catalogFileName, String messageId) {
		super(catalogFileName, messageId);
	}

	public ThravvelCoreException(String catalogFileName, String messageId, Object[] aobj) {
		super(catalogFileName, messageId, aobj);
	}

	public ThravvelCoreException(String catalogFileName, String messageId, Object[] aobj, String detailTechnique) {
		super(catalogFileName, messageId, aobj, detailTechnique);
	}

}

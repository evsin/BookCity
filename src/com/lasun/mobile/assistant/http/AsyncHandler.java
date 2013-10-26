package com.lasun.mobile.assistant.http;


public interface AsyncHandler {
	/**
	 * Invoked when an unexpected exception occurs during the processing of the
	 * response
	 * 
	 * @param ta {@link Throwable}
	 */
	void onThrowable(Throwable t, Object cookie);

	/**
	 * Invoked once the HTTP response processing is finished.
	 * <p/>
	 * 
	 * Gets always invoked as last callback method.
	 * 
	 */
	void onCompleted(int statusCode, String Content, Object cookie);
	
}

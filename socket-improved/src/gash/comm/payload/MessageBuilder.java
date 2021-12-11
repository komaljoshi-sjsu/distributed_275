package gash.comm.payload;

import java.util.Date;
import java.util.List;

import gash.comm.extra.Message;

public abstract class MessageBuilder {

	public enum MessageType {
		subscribeTopic, leave
	}

	private boolean _verbose;
	private String _incompleteBuffer;

	public MessageBuilder() {
		_verbose = false;
		_incompleteBuffer = null;
	}

	public abstract String encode(MessageType type, String msgId, String source, String body, Date received);

	public abstract List<Message> decode(byte[] raw) throws Exception;

	public void reset() {
		_incompleteBuffer = null;
	}

	public boolean isComplete() {
		return (_incompleteBuffer == null);
	}

	public boolean isVerbose() {
		return _verbose;
	}

	public void setVerbose(boolean on) {
		this._verbose = on;
	}

}
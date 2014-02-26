package org.java_websocket.drafts;

import java.nio.channels.ByteChannel;

public interface IChannelAccessDraft extends IDraft{

	public void setChannel( ByteChannel channel );
	public ByteChannel getChannel();

}

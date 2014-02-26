package org.java_websocket.drafts;

import java.nio.ByteBuffer;
import java.util.List;

import org.java_websocket.WebSocket.Role;
import org.java_websocket.drafts.Draft.CloseHandshakeType;
import org.java_websocket.drafts.Draft.HandshakeState;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.exceptions.LimitExedeedException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.Framedata.Opcode;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.util.Charsetfunctions;

public interface IDraft {

	public static final byte[] FLASH_POLICY_REQUEST = Charsetfunctions.utf8Bytes( "<policy-file-request/>\0" );

	public abstract HandshakeState acceptHandshakeAsClient( ClientHandshake request, ServerHandshake response ) throws InvalidHandshakeException;

	public abstract HandshakeState acceptHandshakeAsServer( ClientHandshake handshakedata ) throws InvalidHandshakeException;

	public abstract ByteBuffer createBinaryFrame( Framedata framedata ); // TODO Allow to send data on the base of an Iterator or InputStream

	public abstract List<Framedata> createFrames( ByteBuffer binary, boolean mask );

	public abstract List<Framedata> createFrames( String text, boolean mask );

	public abstract List<Framedata> continuousFrame( Opcode op, ByteBuffer buffer, boolean fin );

	public abstract void reset();

	public abstract List<ByteBuffer> createHandshake( Handshakedata handshakedata, Role ownrole );

	public abstract List<ByteBuffer> createHandshake( Handshakedata handshakedata, Role ownrole, boolean withcontent );

	public abstract ClientHandshakeBuilder postProcessHandshakeRequestAsClient( ClientHandshakeBuilder request ) throws InvalidHandshakeException;

	public abstract HandshakeBuilder postProcessHandshakeResponseAsServer( ClientHandshake request, ServerHandshakeBuilder response ) throws InvalidHandshakeException;

	public abstract List<Framedata> translateFrame( ByteBuffer buffer ) throws InvalidDataException;

	public abstract CloseHandshakeType getCloseHandshakeType();

	/**
	 * Drafts must only be by one websocket at all. To prevent drafts to be used more than once the Websocket implementation should call this method in order to create a new usable version
	 * of a given draft instance.<br>
	 * The copy can be safely used in conjunction with a new websocket connection.
	 * */
	public abstract IDraft copyInstance();

	public abstract Handshakedata translateHandshake( ByteBuffer buf ) throws InvalidHandshakeException;

	public abstract int checkAlloc( int bytecount ) throws LimitExedeedException , InvalidDataException;

	public abstract void setParseMode( Role role );

	public abstract Role getRole();

}
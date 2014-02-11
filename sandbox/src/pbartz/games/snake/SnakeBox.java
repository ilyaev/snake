package pbartz.games.snake;

import java.util.List;

import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.games.GamesClient;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.Participatable;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RealTimeReliableMessageSentListener;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

public class SnakeBox extends BaseGameActivity implements OnTouchListener, RoomUpdateListener, RealTimeMessageReceivedListener, RoomStatusUpdateListener, OnInvitationReceivedListener, RealTimeReliableMessageSentListener {

	public SnakeBoxSurface aSurfaceView;
	
	private static final boolean ENABLE_DEBUG = true;
    private static final String TAG = "SnakeSurvival";
    public String nextAction = "";

	private String mIncomingInvitationId;
	final static int RC_WAITING_ROOM = 10002;

	// are we already playing?
	boolean mPlaying = false;

	public String mRoomId;

	public Room mRoom;

	private GameSession gSession;

	// at least 2 players required for our game
	final static int MIN_PLAYERS = 2;

	public static final String CMD_MOVE = "MOV";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		enableDebugLog(ENABLE_DEBUG, TAG);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		aSurfaceView = new SnakeBoxSurface(this);
		aSurfaceView.setOnTouchListener(this);
		setContentView(aSurfaceView);		
	}
	
	@Override
    protected void onStart() {
        super.onStart();
    }

	@Override
	public void onBackPressed() {
		if (aSurfaceView.board == aSurfaceView.gameBoard) {
			aSurfaceView.board.isPaused = false;
			aSurfaceView.setBoard(aSurfaceView.startBoard);
			aSurfaceView.startBoard.reselect();
		} else {
			if (!aSurfaceView.board.backAction()) {
				super.onBackPressed();
			}
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		aSurfaceView.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		aSurfaceView.resume();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		aSurfaceView.board.processTouch(event);
		return true;
	}
	
	public void showLeaderBoard() {
		if (this.isSignedIn()) {
			startActivityForResult(getGamesClient().getLeaderboardIntent(getString(R.string.leaderboard_id)), 1);
		} else {
			nextAction = "leaderboard";
			beginUserInitiatedSignIn();
		}
	}

	@Override
	public void onSignInFailed() {
		// TODO Auto-generated method stub
		Log.v(TAG, "FAILED!");
		nextAction = "";
	}
	
	private RoomConfig.Builder makeBasicRoomConfigBuilder() {
	    return RoomConfig.builder(this)
	            .setMessageReceivedListener(this)
	            .setRoomStatusUpdateListener(this);
	}
	
	boolean shouldStartGame(Room room) {
		mRoomId = room.getRoomId();
		mRoom = room;
	    int connectedPlayers = 0;
	    for (Participant p : room.getParticipants()) {
	        if (p.isConnectedToRoom()) ++connectedPlayers;
	    }
	    return connectedPlayers >= MIN_PLAYERS;
	}

	// Returns whether the room is in a state where the game should be cancelled.
	boolean shouldCancelGame(Room room) {
		mRoomId = room.getRoomId();
		mRoom = room;
	    // TODO: Your game-specific cancellation logic here. For example, you might decide to 
	    // cancel the game if enough people have declined the invitation or left the room.
	    // You can check a participant's status with Participant.getStatus().
	    // (Also, your UI should have a Cancel button that cancels the game too)
		return false;
	}

	@Override
	public void onSignInSucceeded() {
		// TODO Auto-generated method stub
		Log.v(TAG, "SUCCESS!");
		if (nextAction == "leaderboard") {
			nextAction = "";
			showLeaderBoard();
		}
		
		if (nextAction == "uploadscore") {
			nextAction = "";
			uploadScore();
		}
		
		if (nextAction == "quickgame") {
			nextAction = "";
			startQuickGame();
		}	
		
	}

	public void uploadScore() {
		// TODO Auto-generated method stub
		if (this.isSignedIn()) {
			getGamesClient().submitScore(getString(R.string.leaderboard_id), GameScore.getScore(GameSnakeBoard.GAMEMODE_SOLO) + GameScore.getScore(GameSnakeBoard.GAMEMODE_BATTLE) + GameScore.getScore(GameSnakeBoard.GAMEMODE_SURVIVAL));
		} else {
			nextAction = "uploadscore";
			beginUserInitiatedSignIn();
		}
	}

	@Override
	public void onJoinedRoom(int statusCode, Room room) {
		mRoomId = room.getRoomId();
		mRoom = room;
		Log.v(TAG, "Room Joined!");
		if (statusCode != GamesClient.STATUS_OK) {
	       // display error
	       return;  
	    }

	    // get waiting room intent
	    Intent i = getGamesClient().getRealTimeWaitingRoomIntent(room, Integer.MAX_VALUE);
	    startActivityForResult(i, RC_WAITING_ROOM);		
	}

	@Override
	public void onLeftRoom(int statusCode, String roomId) {
		// TODO Auto-generated method stub
		Log.v(TAG, "Room Left!");
	}

	@Override
	public void onRoomConnected(int statusCode, Room room) {
		mRoomId = room.getRoomId();
		mRoom = room;
		// TODO Auto-generated method stub
		Log.v(TAG, "onRoom Connected!");
	}

	@Override
	public void onRoomCreated(int statusCode, Room room) {
		if (statusCode != GamesClient.STATUS_OK) {
	        // display error
			showAlert("Error durung room creation");
	        return;
	    }
		
		mRoomId = room.getRoomId();
		mRoom = room;
	    

	    // get waiting room intent
	    Intent i = getGamesClient().getRealTimeWaitingRoomIntent(room, Integer.MAX_VALUE);
	    startActivityForResult(i, RC_WAITING_ROOM);
	}

	@Override
	public void onConnectedToRoom(Room room) {
		mRoomId = room.getRoomId();
		mRoom = room;
		// TODO Auto-generated method stub
		Log.v(TAG, "Room Connected!");
	}

	@Override
	public void onDisconnectedFromRoom(Room room) {
		mRoomId = room.getRoomId();
		mRoom = room;
		// TODO Auto-generated method stub
		Log.v(TAG, "Room DiSConnected!");
	}

	@Override
	public void onP2PConnected(String participantId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onP2PDisconnected(String participantId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPeerDeclined(Room room, List<String> peers) {
		mRoomId = room.getRoomId();
		mRoom = room;
		// peer declined invitation -- see if game should be cancelled
	    if (!mPlaying && shouldCancelGame(room)) {
	        getGamesClient().leaveRoom(this, room.getRoomId());
	        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	    }
		
	}

	@Override
	public void onPeerInvitedToRoom(Room arg0, List<String> arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPeerJoined(Room arg0, List<String> arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPeerLeft(Room room, List<String> peers) {
		mRoomId = room.getRoomId();
		mRoom = room;
		// peer left -- see if game should be cancelled
	    if (!mPlaying && shouldCancelGame(room)) {
	        getGamesClient().leaveRoom(this, room.getRoomId());
	        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	    }
		
	}

	@Override
	public void onPeersConnected(Room room, List<String> peers) {
		Log.v(TAG, "Peer Connected!");
		mRoomId = room.getRoomId();
		mRoom = room;
		if (mPlaying) {
	        // add new player to an ongoing game
	    }
	    else if (shouldStartGame(room)) {
	        // start game!
	    	Log.v(TAG, "Shoudl Start GAME!");
	    }
		
	}

	@Override
	public void onPeersDisconnected(Room room, List<String> arg1) {
		mRoomId = room.getRoomId();
		mRoom = room;
		Log.v(TAG, "Peer Disconnected");
		if (mPlaying) {
	        // do game-specific handling of this -- remove player's avatar 
	        // from the screen, etc. If not enough players are left for
	        // the game to go on, end the game and leave the room.
	    }
	    else if (shouldCancelGame(room)) {
	        // cancel the game
	        getGamesClient().leaveRoom(this, room.getRoomId());
	        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	    }
		
	}

	@Override
	public void onRoomAutoMatching(Room room) {
		mRoomId = room.getRoomId();
		mRoom = room;
		// TODO Auto-generated method stub
		Log.v(TAG, "Room AutoMatching!");
	}

	@Override
	public void onRoomConnecting(Room room) {
		mRoomId = room.getRoomId();
		mRoom = room;
		// TODO Auto-generated method stub
		Log.v(TAG, "Room Connecting!");
	}

	

	@Override
	public void onInvitationReceived(Invitation invitation) {
		// TODO Auto-generated method stub
		mIncomingInvitationId = invitation.getInvitationId();
	}	
	
	public void startQuickGame() {
	    // automatch criteria to invite 1 random automatch opponent.  
	    // You can also specify more opponents (up to 3). 
		
		if (this.isSignedIn()) {
			 Bundle am = RoomConfig.createAutoMatchCriteria(1, 1, 0);

		    // build the room config:
		    RoomConfig.Builder roomConfigBuilder = makeBasicRoomConfigBuilder();
		    roomConfigBuilder.setAutoMatchCriteria(am);
		    RoomConfig roomConfig = roomConfigBuilder.build();

		    // create room:
		    getGamesClient().createRoom(roomConfig);

		    // prevent screen from sleeping during handshake
		    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		} else {
			nextAction = "quickgame";
			beginUserInitiatedSignIn();
		}
	}
	
	public void nextNetPlay() {
		this.gSession = new GameSession(this);
    	this.gSession.initialize();
	}
	
	@Override
	public void onActivityResult(int request, int response, Intent intent) {
	    if (request == RC_WAITING_ROOM) {
	        if (response == Activity.RESULT_OK) {
	        	Log.v(TAG, "Start Game HERE");
	        	
	        	nextNetPlay();
	        	
	            // (start game)
	        }
	        else if (response == Activity.RESULT_CANCELED) {
	            // Waiting room was dismissed with the back button. The meaning of this
	            // action is up to the game. You may choose to leave the room and cancel the
	            // match, or do something else like minimize the waiting room and
	            // continue to connect in the background.

	            // in this example, we take the simple approach and just leave the room:
	            getGamesClient().leaveRoom(this, mRoomId);
	            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	        }
	        else if (response == GamesActivityResultCodes.RESULT_LEFT_ROOM) {
	            // player wants to leave the room.
	            getGamesClient().leaveRoom(this, mRoomId);
	            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	        }
	    }
	}

	public void pushCommand(String cmd, int lastGivenCommand) {
		if (this.gSession != null) {
			this.gSession.pushCommand(cmd, Integer.toString(lastGivenCommand));
		}
	}

	@Override
	public void onRealTimeMessageSent(int statusCode, int tokenId,
			String recipientParticipantId) {
		// TODO Auto-generated method stub
		Log.v(TAG, "RTM Sent to: " + recipientParticipantId);
	}
	
	public void showAlert(String message) {
        (new AlertDialog.Builder(this)).setMessage(message)
                .setNeutralButton(android.R.string.ok, null).create().show();
    }
	
	@Override
	public void onRealTimeMessageReceived(RealTimeMessage message) {
		// TODO Auto-generated method stub
		
		byte[] b = message.getMessageData();
		String str = new String(b);
		
		if (this.gSession != null) {
			this.gSession.processCommand(str);
		}
		
		Log.v(TAG, "RTM Received: " + str);		
		
	}
	

}

package demo.cdroom.data;

import java.util.HashMap;

/**
 * Represents the database of all users' information
 * @author circuitcoder
 *
 */
public class UserData {
	
	/**
	 * Represents a User and its saved information
	 * @author circuitcoder
	 *
	 */
	public class User {
		public String userName;
		public String realName;
		public long accessLevel;
	}
	
	public enum AuthStatus {
		SUCCESSED,USERNOTFOUND,WRONGPASSWD;
	}
	
	/**
	 * The UserName-Password pair
	 */
	private HashMap<String,String> users; 
	//TODO: Shadow password
	
	/**
	 * The SessionID-UserName Pair
	 */
	private HashMap<String,String> sessions;
	
	/**
	 * The UserName-UserInfo Pair
	 */
	private HashMap<String,User> info;
	
	/**
	 * The unique overall instance
	 */
	public static UserData instance;
	
	/**
	 * The constructor<br/>
	 * Will read the saved password file and create databases in the RAM
	 */
	public UserData() {
		//TODO: Read config file
		User dan=new User();
		
		dan.realName="He Zhixian";
		dan.userName="dan";
		dan.accessLevel=10000;
		
		users.put("dan","1234");
		info.put("dan", dan);
	}
	
	/**
	 * Check if the password matches the username<br/>
	 * And put the session ID and username pair in the database
	 * @param username The Inputed UserName
	 * @param password The Inputed Password
	 * @param SessionID The session ID of the request
	 * @return The login status
	 */
	public AuthStatus login(String username,String password,String SessionID) {
		if(!users.containsKey(username)) return AuthStatus.USERNOTFOUND;
		else if(users.get(username)!=password) return AuthStatus.WRONGPASSWD;
		sessions.put(SessionID, username);
		return AuthStatus.SUCCESSED;
	}
	
	/**
	 * Remove the session ID and Username from logined user
	 * @param SessionID the Session ID
	 * @return <strong>true</strong> if the user has successfully logouted<br/>
	 * <strong>false</strong> if the user is not currently logined
	 */
	public boolean logout(String SessionID) {
		if(!sessions.containsKey(SessionID)) return false;
		sessions.remove(sessions);
		return true;
	}
	
	/**
	 * Get the user info that matches the Session ID
	 * @param SessionID The Session ID
	 * @return The User Info or
	 * <strong>null</strong> if this session ID is not login
	 */
	public User getUserInfo(String SessionID) {
		if(!sessions.containsKey(SessionID)) return null;
		return info.get(sessions.get(SessionID));
	}
	
	/**
	 * Return or create the unique instance
	 * @return the instance
	 */
	//TODO: add password file
	public static UserData getData() {
		if(instance!=null) return instance;
		instance=new UserData();
		return instance;
	}
}

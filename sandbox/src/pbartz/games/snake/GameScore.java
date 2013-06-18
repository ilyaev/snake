package pbartz.games.snake;

import android.content.SharedPreferences;

public class GameScore {
	
	public static SharedPreferences prefs;
	public static int lastUnlockedLevel = 1;
	
	public static void initialize(SharedPreferences tPrefs) {
		prefs = tPrefs;
		for(int i = 0 ; i < SnakeLevels.levels.length ; i++) {
			if (!isLevelLocked(i+1)) {
				lastUnlockedLevel = i + 1;
			}
		}
	}
	
	public static boolean updateScore(int mode, int level, int score) {
		if (score > GameScore.getScore(mode, level)) {
			SharedPreferences.Editor editor = prefs.edit();
			String key = "score_" + Integer.toString(mode) + "_" + Integer.toString(level);
			editor.putInt(key, score);		
			editor.commit();
			return true;
		}
		return false;
	}
	
	public static int getScore(int mode, int level) {
		String key = "score_" + Integer.toString(mode) + "_" + Integer.toString(level);
		return prefs.getInt(key, 0);
	}
	
	public static boolean updateControlType(int mode) {
		SharedPreferences.Editor editor = prefs.edit();
		String key = "controlType";
		editor.putInt(key, mode);		
		editor.commit();
		return true;
	}
	
	public static int getControlType() {
		String key = "controlType";
		return prefs.getInt(key, 3);
	}
	
	public static void clearCache() {
		
	}

	public static boolean isLevelLocked(int level) {
		if (prefs == null) return false;
		if (level == 1) return false;
		return prefs.getBoolean("level_locked_" + Integer.toString(level), true);
	}

	public static boolean unlockLevel(int level) {
		if (isLevelLocked(level)) {
			SharedPreferences.Editor editor = prefs.edit();
			String key = "level_locked_" + Integer.toString(level);
			editor.putBoolean(key, false);		
			editor.commit();
			initialize(prefs);
			return true;
		}
		return false;
	}

	public static void setSelectedLevel(int gameMode, int level) {
		SharedPreferences.Editor editor = prefs.edit();
		String key = "level_selected_" + Integer.toString(gameMode);
		editor.putInt(key, level);		
		editor.commit();
	}
	
	public static int getSelectedLevel(int gameMode) {
		String key = "level_selected_" + Integer.toString(gameMode);
		return prefs.getInt(key, 1);
	}

}
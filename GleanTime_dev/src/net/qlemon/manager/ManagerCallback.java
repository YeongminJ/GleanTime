package net.qlemon.manager;

public interface ManagerCallback {
	
	//callback Type 
	public static enum RESULT_TYPE {
		ADD_DONE,
		DELETE_DONE
	};
	
	public void processDone(RESULT_TYPE result, Object data);
}

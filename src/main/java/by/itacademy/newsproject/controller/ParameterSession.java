package by.itacademy.newsproject.controller;

/**
 * Defines Session attributes.
 * 
 * CURRENT_COMMAND - uses to keep Command, which was active before Localization or 
 * other Command executed. It needs to know what page return after next command executed.
 * 
 * 
 * @author akurlovich
 *
 */
public class ParameterSession {
	public static final String CURRENT_COMMAND = "current_command";
	public static final String CURRENT_COMMAND_DEFAULT = ParameterCommand.MAIN_PAGE;

	public static final String KEY_EMPTY_NEWS_TABLE = "emptyNewsTable";



	public static final String RESULT_OPERATION = "result_operation";

	public static final String RESULT_OPERATION_MSG_CREATE_SUCCESS = "News added successfully";
	public static final String RESULT_OPERATION_MSG_CREATE_FAILED = "Opps, no news added, something wrong. Some fields are empty or overlength";
	public static final String RESULT_OPERATION_MSG_DELETE_SUCESS = "News deleted successfully";
	public static final String RESULT_OPERATION_MSG_GROUP_DELETE_SUCESS = "Group news deleted successfully";
	public static final String RESULT_OPERATION_MSG_UPDATE_SUCESS = "News updated successfully";

	private ParameterSession() {
	}
}
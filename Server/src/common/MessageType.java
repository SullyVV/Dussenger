package common;

public interface MessageType {
	String connect_success = "-1";
	String default_type = "0";
	String login_success = "1";
	String login_fail = "2";
	String register_sucess = "3";
	String register_fail = "4";	
	String logout = "5";
	String login_fail_usr_online = "6";
	String onLine_Friend = "7";
    String offLine_Friend = "8";
    String getRelation = "9";
    String returnRelation = "10";
    String createGroup = "11";
    String createSuccess = "12";
    String createFail = "13";
    
    String getGroup = "14";
    String returnGroup = "15";
    String groupForward = "16";
    
    String groupMessage = "17";
    String deleteGroup = "18";
    String change_passwd = "19";
    
    String add_friend = "20";
    String add_request = "21";
    
    String add_friend_not_exist = "22";
    
    String accept_add_request = "23";
    String denny_add_request = "24";
    String delete_friend = "25";
    String update_group = "26";
    String send_file_req = "27";    
    String receive_file = "28";
 
    String deny_file = "29";
    
}

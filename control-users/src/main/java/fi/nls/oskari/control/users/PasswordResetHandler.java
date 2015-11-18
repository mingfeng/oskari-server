package fi.nls.oskari.control.users;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import fi.nls.oskari.annotation.OskariActionRoute;
import fi.nls.oskari.control.ActionException;
import fi.nls.oskari.control.ActionHandler;
import fi.nls.oskari.control.ActionParameters;
import fi.nls.oskari.control.users.model.Email;
import fi.nls.oskari.control.users.service.IbatisEmailService;
import fi.nls.oskari.util.ResponseHelper;

@OskariActionRoute("UserPasswordReset")
public class PasswordResetHandler extends ActionHandler {

    @Override
    public void handleAction(ActionParameters params) throws ActionException {
        // TODO should only handle POST requests (but best to do that last, it is easier to develop/debug with GET requests)
        // TODO parse email address from params
        // TODO create password reset token for the email, store it in the database and send a reset link to the address (only if such address is in database)
        // Return SUCCESS status. Do this even if nothing was sent because client should not be allowed to know whether the
        // provided email address exists in the database.
    	
    	String requestEmail = params.getRequest().getParameter("email");
    	
    	Email email = new Email();
    	email.setEmail(requestEmail);
    	email.setScreenname("Test");
    	email.setUuid(UUID.randomUUID().toString());
    	email.setExpiryTimestamp(createExpiryTime());
    	
    	IbatisEmailService emailService = new IbatisEmailService();
    	emailService.addEmail(email);
    	
        JSONObject result = new JSONObject();
        try {
            result.put("status", "SUCCESS");
        } catch (JSONException e) {
            throw new ActionException("Could not construct JSON", e);
        }
        ResponseHelper.writeResponse(params, result);
    }
    
    public Timestamp createExpiryTime(){
    	Calendar calender = Calendar.getInstance();
        Timestamp currentTime = new java.sql.Timestamp(calender.getTime().getTime());
        calender.setTime(currentTime);
        calender.add(Calendar.DAY_OF_MONTH, 2);
        Timestamp expiryTime = new java.sql.Timestamp(calender.getTime().getTime());
        return expiryTime;
    }


}

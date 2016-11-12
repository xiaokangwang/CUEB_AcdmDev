package org.kkdev.cueb.miscellaneous;

import org.kkdev.cueb.entities.Session;
import org.kkdev.cueb.entities.User;
import org.kkdev.cueb.entities.User_Token;
import org.kkdev.cueb.universal.TokenType;

public class TokenType_INSECURE_password_plain implements TokenType {

	@Override
	public String Name() {
		return "INSECURE_password_plain";
	}

	@Override
	public Boolean DoVerify(Session currs, User_Token tk, String InputScript) {
		return tk.getType().equals(this.Name())&&tk.getScript().equals(InputScript);
	}

	@Override
	public String GenScript(User us, String InputScript) {
		// TODO Auto-generated method stub
		return InputScript;
	}

}

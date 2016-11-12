package org.kkdev.cueb.universal;


import org.kkdev.cueb.entities.Session;
import org.kkdev.cueb.entities.User;
import org.kkdev.cueb.entities.User_Token;
import org.kkdev.cueb.miscellaneous.TokenType_INSECURE_password_plain;

public class TokenVerifier {
	public static boolean Verify(Session currentSession,User proposedUser,String inputScript) {
		User_Token token=proposedUser.getToken();
		TokenType tokenType=GetTokenTypeByName(token.getType());
		return tokenType.DoVerify(currentSession, token, inputScript);
	}
	public static TokenType GetTokenTypeByName(String name) {
		switch (name) {
		case "INSECURE_password_plain":
			return new TokenType_INSECURE_password_plain();

		default:
			return null;
		}
	}
	public static String GenerateScript(User proposedUser,String type,String inputScript) {
		TokenType tokenType=GetTokenTypeByName(type);
		return tokenType.GenScript(proposedUser, inputScript);
	}
}

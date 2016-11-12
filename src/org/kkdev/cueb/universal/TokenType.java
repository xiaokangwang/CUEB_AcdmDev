package org.kkdev.cueb.universal;

import org.kkdev.cueb.entities.Session;
import org.kkdev.cueb.entities.User;
import org.kkdev.cueb.entities.User_Token;

public interface TokenType {
String Name();
Boolean DoVerify(Session currs,User_Token tk,String InputScript);
String  GenScript(User us,String InputScript);
}

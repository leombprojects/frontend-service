package org.leombprojects.frontend.services;

public interface UserService {
    boolean validateAccess(String user, String password);
}

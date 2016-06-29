//
// Copyright (c) 2013-$Date$ TIBCO Software Inc.
// All rights reserved.
// Confidential & Proprietary.
//
package com.tibco.as.jdbc;

import com.tibco.as.space.security.AuthenticationCallback;
import com.tibco.as.space.security.AuthenticationInfo;
import com.tibco.as.space.security.UserPwdCredential;
import com.tibco.as.space.security.X509V3Credential;

public class SimpleAuthenticate implements AuthenticationCallback
{
    String username;
    String password;
    String domain;
    String keyFile;

    public SimpleAuthenticate (String domain, String username, String password) throws SecurityException
    {
        this.domain = domain;
        this.username = username;
        this.password = password;
    }

    public SimpleAuthenticate (String keyfile, String password) throws SecurityException
    {
        this.keyFile = keyfile;
        this.password = password;
    }

    @Override
    public void createUserCredential (AuthenticationInfo info)
    {
        switch (info.getAuthenticationMethod())
        {
            case USERPWD:
            {
                UserPwdCredential userPwdCredential = (UserPwdCredential) info.getUserCredential();
                if (username == null || password == null)
                {
                    throw new SecurityException("username/password not provided");
                }

                userPwdCredential.setUserName(username);
                userPwdCredential.setPassword(password.toCharArray());
                userPwdCredential.setDomain(domain);

                break;
            }
            case X509V3:
            {
                X509V3Credential x509V3Credential = (X509V3Credential) info.getUserCredential();
                if (keyFile == null)
                {
                    throw new SecurityException("key file not provided");
                }
                x509V3Credential.setKeyFile(keyFile);

                if (password != null)
                {
                    x509V3Credential.setPassword(password.toCharArray());
                }
                break;
            }
            case UNKNOWN:
                break;
        }
    }

    @Override
    public void onCleanup ()
    {
        // let values get cached for possible reauthentication
    }
}


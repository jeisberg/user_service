package com.lockerz.service.user.models;

public class TokenAuthModel
{
    private Long uid;
    private String apiKey;
    private String token;
    
    public Long getUid()
    {
        return uid;
    }
    public void setUid(Long uid)
    {
        this.uid = uid;
    }
    public String getApiKey()
    {
        return apiKey;
    }
    public void setApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }
    public String getToken()
    {
        return token;
    }
    public void setToken(String token)
    {
        this.token = token;
    }
    
}

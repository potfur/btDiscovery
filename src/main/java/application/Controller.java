package application;

import java.util.Map;

public interface Controller
{
    public String handle(Map<String, String> args) throws Exception;
}

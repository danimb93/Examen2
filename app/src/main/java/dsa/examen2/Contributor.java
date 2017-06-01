package dsa.examen2;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Dani on 31/05/2017.
 */

public class Contributor {

    private String login;
    private int Repos;
    private String follower;
    private int following, followers;

    public String getLogin() {
        return login;
    }

    public int getRepos() {
        return Repos;
    }

    public String getFollower() {
        return follower;
    }

    public int getFollowing() {
        return following;
    }

    public int getFollowers() {
        return followers;
    }
}
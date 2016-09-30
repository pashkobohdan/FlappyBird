package com.pashkobohdan.flappybird.googlePlayServices;

/**
 * Created by Bohdan Pashko on 30.09.16.
 */
public interface PlayServices
{
    void signIn();
    void signOut();
    void rateGame();
    void unlockAchievement();
    void submitScore(int highScore);
    void showAchievement();
    void showScore();
    boolean isSignedIn();
}

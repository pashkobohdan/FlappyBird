package com.pashkobohdan.flappybird.library.button.core.interfaces;

import com.pashkobohdan.flappybird.library.button.Button;

/**
 * Created by Bohdan Pashko on 14.09.16.
 */
public interface ClickListener {

    /**
     *
     * @param button
     * @param x
     * @param y
     */
    void oneClick(Button button, int x, int y);

    void postClick(Button button, int x, int y);

    void doubleClick(Button button, int x, int y);
}

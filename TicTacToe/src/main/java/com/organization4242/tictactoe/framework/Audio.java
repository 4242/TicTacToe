package com.organization4242.tictactoe.framework;

import java.io.IOException;

public interface Audio {
    Music newMusic(String fileName) throws IOException;

    Sound newSound(String fileName) throws IOException;
}

package io.agore.openvcall.model;

public class CurrentUserSettings {
    public int mEncryptionModeIndex;

    public String mEncryptionKey;

    public String mChannelName;

    public CurrentUserSettings() {
        reset();
    }

    public void reset() {
    }
}

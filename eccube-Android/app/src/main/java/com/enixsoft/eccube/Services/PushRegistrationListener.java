package com.enixsoft.eccube.Services;

/**
 * PUSH通知の登録に成功した場合のコールバックです。
 */
public interface PushRegistrationListener {

    /**
     * 成功した場合に通知されます。
     */
    void onSuccess();

    /**
     * 失敗した場合に通知されます。
     */
    void onFailure();

}

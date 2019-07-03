package com.enixsoft.eccube.Services;

import android.app.IntentService;
import android.content.Intent;

/**
 * 非同期で開封通知を送信するクラスです。
 * pushId（Config.KEY_PUSH_ID）をIntent経由で渡してください。
 */
public class OpenMessageService extends IntentService {

    /**
     * コンストラクタ
     */
    public OpenMessageService() {
        super("OpenMessageService");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        System.out.println("openMessageの開始");

    }
}
package com.enixsoft.eccube.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.enixsoft.eccube.Config;
import com.enixsoft.eccube.R;
import com.enixsoft.eccube.Services.PushRegistrationFragment;
import com.enixsoft.eccube.Services.PushRegistrationListener;

/**
 * スプラッシュ画面を表示するActivityです。
 * GCMの処理を行います。
 */
public class SplashActivity extends AppCompatActivity implements PushRegistrationListener {

    /**
     * スプラッシュの表示を終えたかフラグ
     */
    private boolean mIsSplashEnd;

    /**
     * GCMに登録されているフラグ
     */
    private boolean mIsGcmRegistered;

    /**
     * CountDownTimer
     */
    private CountDownTimer mTimer;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mIsSplashEnd = false;
        mIsGcmRegistered = false;

        // GCMの処理開始
        if (savedInstanceState == null) {
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(new PushRegistrationFragment(), "registration").commit();
        }

        mTimer = new CountDownTimer(Config.SPLASH_TIME_MILLIS, Config.SPLASH_TIME_MILLIS) {
            @Override
            public void onTick(long millisUntilFinished) {
                // do nothing
            }

            @Override
            public void onFinish() {
                mIsSplashEnd = true;
                startNextActivity();
            }
        }.start();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDestroy() {
        mIsSplashEnd = false;
        mTimer.cancel();
        super.onDestroy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSuccess() {
        mIsGcmRegistered = true;
        startNextActivity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFailure() {
        // add by KKEN
        if (Config.DEBUG) {
            mIsGcmRegistered = true;
            Toast.makeText(this, R.string.error_splash, Toast.LENGTH_LONG).show();
            startNextActivity();
        } else {
            mIsGcmRegistered = false;
            Toast.makeText(this, R.string.error_splash, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 次の画面に遷移します。
     */
    private void startNextActivity() {
        if (mIsGcmRegistered && mIsSplashEnd) {
            final Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}

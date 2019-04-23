package com.business.electr.clothes.ui.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.DataEvent;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.fragment.home.MacroscopicFragment;
import com.business.electr.clothes.ui.fragment.home.SelfSelectFragment;
import com.business.electr.clothes.ui.fragment.home.MineFragment;
import com.business.electr.clothes.view.MainBottomView;
import com.sankuai.waimai.router.annotation.RouterUri;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

import static com.business.electr.clothes.bean.DataEvent.TYPE_CHANGE_INFO_DOT;

@RouterUri(path = {RouterCons.CREATE_MAIN})
public class MainActivity extends BaseActivity implements MainBottomView.HomeBottomClick {

    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.mbv_home)
    MainBottomView mbvHome;

    private SelfSelectFragment selfSelectFragment;
    private MacroscopicFragment macroscopicFragment;
    private MineFragment mineFragment;

    private FragmentTransaction fragmentTransaction;

    private boolean isExit = false;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        selfClick();
        mbvHome.setHomeBottomClick(this);
    }

    private void hide(FragmentTransaction transaction) {
        if (selfSelectFragment != null) {
            transaction.hide(selfSelectFragment);
        }
        if (macroscopicFragment != null) {
            transaction.hide(macroscopicFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }


    @Override
    public void selfClick() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hide(fragmentTransaction);
        if (selfSelectFragment == null) {
            selfSelectFragment = new SelfSelectFragment();
            fragmentTransaction.add(R.id.frame_layout, selfSelectFragment);
        } else {
            fragmentTransaction.show(selfSelectFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void macroscopicClick() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hide(fragmentTransaction);
        if (macroscopicFragment == null) {
            macroscopicFragment = new MacroscopicFragment();
            fragmentTransaction.add(R.id.frame_layout, macroscopicFragment);
        } else {
            fragmentTransaction.show(macroscopicFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void mineClick() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hide(fragmentTransaction);
        if (mineFragment == null) {
            mineFragment = new MineFragment();
            fragmentTransaction.add(R.id.frame_layout, mineFragment);
        } else {
            fragmentTransaction.show(mineFragment);
        }
        fragmentTransaction.commit();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataEvent(DataEvent event) {
        switch (event.getType()) {
            case TYPE_CHANGE_INFO_DOT:
                Boolean isVisible = (Boolean) event.getData();
                mbvHome.showSelfDot(isVisible);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            toastMessage(R.string.again_pass_logout_app);
            //利用handler延迟发送更改状态信息
            handler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }
}

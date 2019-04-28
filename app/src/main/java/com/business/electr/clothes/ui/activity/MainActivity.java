package com.business.electr.clothes.ui.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.fragment.home.ElectFragment;
import com.business.electr.clothes.ui.fragment.home.HistoryFragment;
import com.business.electr.clothes.ui.fragment.home.PresentationFragment;
import com.business.electr.clothes.ui.fragment.home.MineFragment;
import com.business.electr.clothes.ui.fragment.home.TaskFragment;
import com.business.electr.clothes.view.MainBottomView;
import com.sankuai.waimai.router.annotation.RouterUri;

import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;


@RouterUri(path = {RouterCons.CREATE_MAIN})
public class MainActivity extends BaseActivity implements MainBottomView.HomeBottomClick {

    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.mbv_home)
    MainBottomView mbvHome;

    private HistoryFragment historyFragment;
    private PresentationFragment presentationFragment;
    private ElectFragment electFragment;
    private TaskFragment taskFragment;
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
        electClick();
        mbvHome.setHomeBottomClick(this);
    }

    private void hide(FragmentTransaction transaction) {
        if (historyFragment != null) {
            transaction.hide(historyFragment);
        }
        if (presentationFragment != null) {
            transaction.hide(presentationFragment);
        }
        if (electFragment != null) {
            transaction.hide(electFragment);
        }
        if (taskFragment != null) {
            transaction.hide(taskFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }


    @Override
    public void historyClick() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hide(fragmentTransaction);
        if (historyFragment == null) {
            historyFragment = new HistoryFragment();
            fragmentTransaction.add(R.id.frame_layout, historyFragment);
        } else {
            fragmentTransaction.show(historyFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void presentation() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hide(fragmentTransaction);
        if (presentationFragment == null) {
            presentationFragment = new PresentationFragment();
            fragmentTransaction.add(R.id.frame_layout, presentationFragment);
        } else {
            fragmentTransaction.show(presentationFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void electClick() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hide(fragmentTransaction);
        if (electFragment == null) {
            electFragment = new ElectFragment();
            fragmentTransaction.add(R.id.frame_layout, electFragment);
        } else {
            fragmentTransaction.show(electFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void taskClick() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hide(fragmentTransaction);
        if (taskFragment == null) {
            taskFragment = new TaskFragment();
            fragmentTransaction.add(R.id.frame_layout, taskFragment);
        } else {
            fragmentTransaction.show(taskFragment);
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

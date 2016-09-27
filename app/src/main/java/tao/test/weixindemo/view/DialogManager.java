package tao.test.weixindemo.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tao.test.weixindemo.R;

/**
 * Created by lisongtao on 2016/9/27 15:44.
 * desc：Dialog的管理者
 */
public class DialogManager {


    ImageView mRecorderDialogIcon;

    ImageView mRecorderDialogVoice;

    TextView mRecorderDialogLabel;
    private Dialog mDialog;

    private Context mContext;

    public DialogManager(Context context) {

        this.mContext = context;
    }

    /**
     * 显示dialog
     */
    public void showDialog() {

        mDialog = new Dialog(mContext, R.style.Theme_AudioRecorderDialog);
        View view = View.inflate(mContext, R.layout.dialog_recorder, null);
        mDialog.setContentView(view);

        mRecorderDialogIcon = (ImageView) view.findViewById(R.id.recorder_dialog_icon);
        mRecorderDialogVoice = (ImageView) view.findViewById(R.id.recorder_dialog_voice);
        mRecorderDialogLabel = (TextView) view.findViewById(R.id.recorder_dialog_label);

        mDialog.show();

    }

    public void recording() {

        if (mDialog != null && mDialog.isShowing()) {
            mRecorderDialogIcon.setVisibility(View.VISIBLE);
            mRecorderDialogVoice.setVisibility(View.VISIBLE);
            mRecorderDialogLabel.setVisibility(View.VISIBLE);

            mRecorderDialogIcon.setImageResource(R.drawable.recorder);
            mRecorderDialogLabel.setText(R.string.uptocancel);

        }
    }

    /**
     * 要取消录音
     */
    public void wantToCancel() {

        if (mDialog != null && mDialog.isShowing()) {
            mRecorderDialogIcon.setVisibility(View.VISIBLE);
            mRecorderDialogVoice.setVisibility(View.GONE);
            mRecorderDialogLabel.setVisibility(View.VISIBLE);

            mRecorderDialogIcon.setImageResource(R.drawable.cancel);
            mRecorderDialogLabel.setText(R.string.cancel);
        }


    }

    /**
     * 录音时间太短
     */
    public void tooShort() {

        if (mDialog != null && mDialog.isShowing()) {
            mRecorderDialogIcon.setVisibility(View.VISIBLE);
            mRecorderDialogVoice.setVisibility(View.GONE);
            mRecorderDialogLabel.setVisibility(View.VISIBLE);

            mRecorderDialogIcon.setImageResource(R.drawable.voice_to_short);
            mRecorderDialogLabel.setText(R.string.tooshort);
        }

    }

    /**
     * dialog消失
     */
    public void diMisss() {


        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;

        }
    }

    /**
     * 通过level去更新mRecorderDialogVoice的图片
     */
    public void upToLevel(int level) {

        if (mDialog != null && mDialog.isShowing()) {
            mRecorderDialogIcon.setVisibility(View.VISIBLE);
            mRecorderDialogVoice.setVisibility(View.VISIBLE);
            mRecorderDialogLabel.setVisibility(View.VISIBLE);

            int resId = mContext.getResources().getIdentifier("v" + level, "drawable", mContext.getPackageName());
            mRecorderDialogVoice.setImageResource(resId);
            mRecorderDialogLabel.setText(R.string.uptocancel);
        }
    }


}

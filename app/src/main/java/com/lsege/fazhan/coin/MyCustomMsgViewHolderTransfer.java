package com.lsege.fazhan.coin;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lsege.fazhan.coin.activity.Red.RedDetailActivity;
import com.lsege.fazhan.coin.activity.assets.BillsDetailsActivity;
import com.lsege.fazhan.coin.activity.chat.GeRenMingPianDetailActivity;
import com.lsege.fazhan.coin.constract.StatusRedContract;
import com.lsege.fazhan.coin.fragment.chat.AlertDialogFragmentCopy;
import com.lsege.fazhan.coin.model.InfoRed;
import com.lsege.fazhan.coin.model.InfoRedModel;
import com.lsege.fazhan.coin.presenter.StatusRedPresenter;
import com.netease.nim.uikit.MyAttachment;
import com.netease.nim.uikit.MyAttachmentTransfer;
import com.netease.nim.uikit.MyConstant;
import com.netease.nim.uikit.business.session.viewholder.MsgViewHolderBase;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseMultiItemFetchLoadAdapter;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.NIMSDK;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import com.netease.nimlib.sdk.uinfo.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/8.
 */

class MyCustomMsgViewHolderTransfer extends MsgViewHolderBase{
    private RelativeLayout sendView1, sendView2, sendView3, sendView4;
    private TextView title1, content1, title2, title3, title4, content2, content3, content4, message_item_time;
    private TextView type1, type2, type3, type4, viewById1, viewById2, viewById3, viewById4;  // 红包描述
    // View viewById;
    MyAttachmentTransfer attachment;
    boolean me = false;
    private ImageView brackground1, brackground2, brackground3, brackground4, head1, head2, head3, head4;    // 红包名称
    private InfoRedModel mData;

    public MyCustomMsgViewHolderTransfer(BaseMultiItemFetchLoadAdapter adapter) {
        super(adapter);


    }

    @Override
    protected int getContentResId() {
        return R.layout.red_package_item_view2;
    }

    @Override
    protected void inflateContentView() {
        sendView2 = findViewById(R.id.bri_send2);
        title2 = findViewById(R.id.title2);
        brackground2 = findViewById(R.id.brackground2);
        type2 = findViewById(R.id.type2);
        content2 = findViewById(R.id.content2);
        head2 = findViewById(R.id.head2);
    }

    @Override
    protected void bindContentView() {
        attachment = (MyAttachmentTransfer) message.getAttachment();
        if (!isReceivedMessage()) {// 消息方向，自己发送的
            if (title2 != null || content2 != null || type2 != null || brackground2 != null || head2 != null) {
                title2.setText(attachment.getTitle());
                content2.setText("¥" + attachment.getAmount());
                type2.setText("金红包转账");
                Glide.with(context).load(R.mipmap.zhuanz).into(head2);
            }
        } else {
            if (title2 != null || content2 != null || type2 != null || brackground2 != null || head2 != null) {
                title2.setText("转账给你");
                content2.setText("¥" + attachment.getAmount());
                type2.setText("金红包转账");
                Glide.with(context).load(R.mipmap.zhuanz).into(head2);
            }

        }

    }


    @Override
    protected int leftBackground() {
        return R.color.transparent;
    }

    @Override
    protected int rightBackground() {
        return R.color.transparent;
    }
    @Override
    protected boolean shouldDisplayReceipt() {
        return false;
    }

    @Override
    protected boolean isShowHeadImage() {
        attachment = (MyAttachmentTransfer) message.getAttachment();
        if (attachment.getType() == 4) {
            return false;
        } else {
            return super.isShowHeadImage();
        }


    }
    @Override
    protected boolean isShowBubble() {
        attachment = (MyAttachmentTransfer) message.getAttachment();
        if (attachment.getType() == 4) {
            return false;
        } else {
            return super.isShowBubble();
        }
    }

    @Override
    protected void onItemClick() {
        attachment = (MyAttachmentTransfer) message.getAttachment();
        if (!isReceivedMessage()) {// 消息方向，自己发送的
                Intent intent = new Intent(context, BillsDetailsActivity.class);
                intent.putExtra("id", attachment.getPayId());
                context.startActivity(intent);
        } else {
                Intent intent = new Intent(context, BillsDetailsActivity.class);
                intent.putExtra("id", attachment.getReceiveId());
                context.startActivity(intent);
        }
    }



}

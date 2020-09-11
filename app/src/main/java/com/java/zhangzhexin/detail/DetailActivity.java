package com.java.zhangzhexin.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

import com.java.zhangzhexin.MainActivity;
import com.java.zhangzhexin.R;
import com.java.zhangzhexin.detail.entitydetail.EntityDetailFragment;
import com.java.zhangzhexin.detail.newsdetail.NewsDetailFragment;
import com.java.zhangzhexin.detail.scholardetail.ScholarDetailFragment;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.common.UiError;
import com.sina.weibo.sdk.openapi.IWBAPI;
import com.sina.weibo.sdk.openapi.WBAPIFactory;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;



/*
FIXME:
感觉详情页不需要fragment的切换
可以根据不同type 展开不同的type_detail.xml?
可能参数有些不同 具体处理
可以定义
一个abstract的参数提取
一个abstract的参数设置函数

先写完News的详情页 之后再决定如何扩展到其他的详情页
*/
public class DetailActivity extends AppCompatActivity implements WbShareCallback {

    private NewsDetailFragment newsDetailFragment;
    private EntityDetailFragment entityDetailFragment;
    private ScholarDetailFragment scholarDetailFragment;

    private Fragment currentFragment;
    private String type;

    private ImageView weiboButton;
    private ImageView weixinButton;

    //微信
    private static final String APP_ID = "wx88888888";
    //IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;

    //微博
    private static final String APP_KY = "2651280216";
    //在微博开发平台为应用申请的App Key
    private static final String REDIRECT_URL = "http://www.sina.com";
    //在微博开放平台为应用申请的高级权限
    private static final String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";
    private IWBAPI mWBAPI;
//    private CheckBox mShareText;
//    private CheckBox mShareImage;
//    private CheckBox mShareUrl;
//    private CheckBox mShareMultiImage;
//    private CheckBox mShareVideo;
//    private RadioButton mShareClientOnly;
//    private RadioButton mShareClientH5;

    public void switchFragment(Fragment target) {
        if (currentFragment != target) {
            //System.out.println("当前fragment为 " + currentFragment + ", 切换到fragment = " + target);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(currentFragment).show(target);
            transaction.setMaxLifecycle(currentFragment, Lifecycle.State.STARTED).setMaxLifecycle(target, Lifecycle.State.RESUMED);
            transaction.commit();
            currentFragment = target;
        }
    }

    public void initFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(newsDetailFragment==null){
            newsDetailFragment = NewsDetailFragment.newInstance();
            transaction.add(R.id.frameLayout, newsDetailFragment).setMaxLifecycle(newsDetailFragment, Lifecycle.State.RESUMED); //默认显示newsDetail
            //System.out.println("finish construct newsDetailFragment");
        }
        if(entityDetailFragment==null){
            entityDetailFragment = EntityDetailFragment.newInstance();
            transaction.add(R.id.frameLayout,entityDetailFragment).hide(entityDetailFragment).setMaxLifecycle(entityDetailFragment, Lifecycle.State.STARTED);
            //System.out.println("finish construct entityDetailFragment");
        }
        if(scholarDetailFragment==null){
            scholarDetailFragment = ScholarDetailFragment.newInstance();
            transaction.add(R.id.frameLayout,scholarDetailFragment).hide(scholarDetailFragment).setMaxLifecycle(scholarDetailFragment, Lifecycle.State.STARTED);
            //System.out.println("finish construct scholarDetailFragment");
        }
        currentFragment = newsDetailFragment;
        transaction.commit();
    }


    private void initWeibo(){
        AuthInfo authInfo = new AuthInfo(this, APP_KY, REDIRECT_URL, SCOPE);
        mWBAPI = WBAPIFactory.createWBAPI(this);
        mWBAPI.registerApp(this, authInfo);
    }

    private void initWX(){
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        // 将应用的appId注册到微信
        api.registerApp(APP_ID);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
//        System.out.println("详情页onCreate");
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_detail);
        initFragment();

        initWeibo();
        initWX();

        weixinButton = findViewById(R.id.weixinShareButton);
        weixinButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(currentFragment == newsDetailFragment) {
                    //System.out.println("检测到微信分享点击");
                    doWeixinShare();
                }
            }
        });

        weiboButton = findViewById(R.id.weiboShareButton);
        weiboButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentFragment == newsDetailFragment) {
                    //System.out.println("检测到微博分享点击");
                    doWeiboShare();
                }
            }
        });
        //System.out.println("详情页离开onCreate");
    }

    //返回MainActivity
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        type = getIntent().getStringExtra("type");
        if(type.equals("news"))
        {
            String news_id = getIntent().getStringExtra("news_id");
            switchFragment(newsDetailFragment);
            newsDetailFragment.setId(news_id);
        }
        else if(type.equals("entity")){ //实体
            int entity_id = getIntent().getIntExtra("entity_id",-1);
            //System.out.println("得到实体id = "+entity_id);
            switchFragment(entityDetailFragment);
            //System.out.println("entityDetailFragment = "+entityDetailFragment);
            entityDetailFragment.setId(entity_id);
        }
        else if(type.equals("scholar")){ //学者
            int scholar_id = getIntent().getIntExtra("scholar_id",-1);
            //System.out.println("得到学者id = "+scholar_id);
            switchFragment(scholarDetailFragment);
            //System.out.println("scholarDetailFragment = "+scholarDetailFragment);
            scholarDetailFragment.setId(scholar_id);
        }
    }

    //需要在resume里getIntent
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mWBAPI.doResultIntent(data, this);
    }

    private void doWeixinShare(){
        String content = newsDetailFragment.current_news.content;
        if(content.length()>20){
            content = content.substring(0,20)+"...";
        }
        WXTextObject textObj = new WXTextObject();
        textObj.text = "["+newsDetailFragment.current_news.title+"]\n"+content;

        //用 WXTextObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());  //transaction字段用与唯一标示一个请求
        req.message = msg;

        //调用api接口，发送数据到微信
        api.sendReq(req);
    }


    private void doWeiboShare() {
//        System.out.println("get into weiboshare");
        WeiboMultiMessage message = new WeiboMultiMessage();

        TextObject textObject = new TextObject();


        String content = newsDetailFragment.current_news.content;
        if(content.length()>20){
            content = content.substring(0,20)+"...";
        }

        textObject.text = "["+newsDetailFragment.current_news.title+"]\n"+content;
        message.textObject = textObject;
//        System.out.println(Thread.currentThread().getStackTrace()[2].getLineNumber());

        mWBAPI.shareMessage(message, true);
    }

    @Override
    public void onComplete() {
        Toast.makeText(DetailActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(UiError error) {
        Toast.makeText(DetailActivity.this, "分享失败:" + error.errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel() {
        Toast.makeText(DetailActivity.this, "分享取消", Toast.LENGTH_SHORT).show();
    }
}

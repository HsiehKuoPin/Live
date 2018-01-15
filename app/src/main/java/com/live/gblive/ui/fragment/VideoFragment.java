package com.live.gblive.ui.fragment;

import android.os.Bundle;

import com.live.gblive.R;
import com.live.gblive.base.MvpFragment;
import com.live.gblive.utils.LogUtil;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.pili.pldroid.player.widget.PLVideoView;

import butterknife.BindView;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/15 15:39
 * description:
 */
public class VideoFragment extends MvpFragment {
    @BindView(R.id.vtv)
    PLVideoTextureView vtv;
    private int mRotation;

    private String url;

    private boolean isFull;

    public static VideoFragment newInstance(String url,boolean isFull) {

        Bundle args = new Bundle();

        VideoFragment fragment = new VideoFragment();
        fragment.url = url;
        fragment.isFull = isFull;
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getRootViewId() {
        return R.layout.fragment_video;
    }

    @Override
    public void initView() {
        LogUtil.d("url:" + url);
        vtv.setVideoPath(url);
        if(isFull){
            vtv.setDisplayOrientation(PLVideoView.ASPECT_RATIO_PAVED_PARENT);
        }else{
            vtv.setDisplayOrientation(PLVideoView.ASPECT_RATIO_16_9);
        }
        vtv.setOnPreparedListener(new PLMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(PLMediaPlayer plMediaPlayer, int i) {
                LogUtil.d("onPrepared:" + url);
                start();
            }

        });
        vtv.setOnBufferingUpdateListener(new PLMediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(PLMediaPlayer plMediaPlayer, int i) {
                if(i>0) LogUtil.d("onBufferingUpdate|" + i);
            }
        });
        vtv.setOnCompletionListener(new PLMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(PLMediaPlayer plMediaPlayer) {
                LogUtil.d("onCompletion");
            }
        });
        vtv.setOnInfoListener(new PLMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(PLMediaPlayer plMediaPlayer, int i, int i1) {
                LogUtil.d("onInfo|i:" + i + "--i1:" + i1);
                return false;
            }
        });

        vtv.setOnErrorListener(new PLMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(PLMediaPlayer plMediaPlayer, int i) {
                LogUtil.w("onError:i:" + i);
                return false;
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        start();
    }

    @Override
    public void onPause() {
        super.onPause();
        pause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopPlayback();
    }
    private void start(){
        if(vtv!=null) vtv.start();
    }
    public void pause(){
        if(vtv!=null) vtv.pause();
    }

    public void stopPlayback(){
        if(vtv!=null) vtv.stopPlayback();

    }

    public void seekTo(long i){
        vtv.seekTo(i);
    }
    public boolean isPlaying(){
        return vtv.isPlaying();
    }

    public void onClickRotate() {
        mRotation = (vtv.getDisplayAspectRatio() + 90) % 360;
        setDisplayAspectRatio(mRotation);
    }
    /**
     *
     * @param ratio
     *      PLVideoView.ASPECT_RATIO_ORIGIN
     *      PLVideoView.ASPECT_RATIO_FIT_PARENT
     *      PLVideoView.ASPECT_RATIO_PAVED_PARENT
     *      PLVideoView.ASPECT_RATIO_16_9
     *      PLVideoView.ASPECT_RATIO_4_3
     *
     */
    public void setDisplayAspectRatio(int ratio){
        vtv.setDisplayAspectRatio(ratio);
    }
    /**
     *
     * @param orientation
     *      0/90/180/270
     */
    public void setDisplayOrientation(int orientation){
        vtv.setDisplayOrientation(orientation);
    }

    public void play(String url){
        this.url = url;
        LogUtil.d("url:" + url);
        vtv.setVideoPath(url);
        vtv.start();
    }
}

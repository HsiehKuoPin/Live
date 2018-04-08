package com.live.gblive.ui.service;

import android.hardware.Camera;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import java.io.IOException;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/3/29 15:05
 * description:
 */
public class CameraLiveWallpaper extends WallpaperService {
    @Override
    public Engine onCreateEngine() {
        // 返回自定义的CameraEngine
        return new CameraEngine();
    }

    class CameraEngine extends Engine implements Camera.PreviewCallback {
        private Camera camera;

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);

            startPreview();
            // 设置处理触摸事件
            setTouchEventsEnabled(true);

        }

        @Override
        public void onTouchEvent(MotionEvent event) {
            super.onTouchEvent(event);
            // 时间处理:点击拍照,长按拍照
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            stopPreview();
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            if (visible) {
                startPreview();
            } else {
                stopPreview();
            }
        }

        /**
         * 开始预览
         */
        public void startPreview() {
            camera = Camera.open();
            camera.setDisplayOrientation(90);

            try {
                camera.setPreviewDisplay(getSurfaceHolder());
            } catch (IOException e) {
                e.printStackTrace();
            }
            camera.startPreview();

        }

        /**
         * 停止预览
         */
        public void stopPreview() {
            if (camera != null) {
                try {
                    camera.stopPreview();
                    camera.setPreviewCallback(null);
                    // camera.lock();
                    camera.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                camera = null;
            }
        }

        @Override
        public void onPreviewFrame(byte[] bytes, Camera camera) {
            camera.addCallbackBuffer(bytes);
        }
    }

}

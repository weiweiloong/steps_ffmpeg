//
// Created by yangw on 2018-5-14.
//

#ifndef MYMUSIC_WLVIDEO_H
#define MYMUSIC_WLVIDEO_H


#include "WlQueue.h"
#include "WlCallJava.h"

extern "C"
{
#include <libswscale/swscale.h>
#include <libavutil/imgutils.h>
#include <libavutil/time.h>
#include <libavcodec/avcodec.h>
};

class WlVideo {

public:
    int streamIndex = -1;
    AVCodecContext *avCodecContext = NULL;
    AVCodecParameters *codecpar = NULL;
    WlQueue *queue = NULL;
    WlPlaystatus *playstatus = NULL;
    WlCallJava *wlCallJava = NULL;
    AVRational time_base;


    pthread_t thread_play;



public:
    WlVideo(WlPlaystatus *playstatus, WlCallJava *wlCallJava);
    ~WlVideo();

    void play();

    void release();





};


#endif //MYMUSIC_WLVIDEO_H

#include <jni.h>
#include <string>
#include <android/log.h>
#include "pthread.h"
#include "WlCallJava.h"
#include "WlFFmpeg.h"

pthread_t thread;

extern "C"
{
#include <libavformat/avformat.h>
}


_JavaVM *javaVM = NULL;
WlCallJava *callJava = NULL;
WlFFmpeg *fFmpeg = NULL;


extern "C"
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved)
{
    jint result = -1;
    javaVM = vm;
    JNIEnv *env;
    if(vm->GetEnv((void **) &env, JNI_VERSION_1_4) != JNI_OK)
    {
        return result;
    }

    return JNI_VERSION_1_4;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_huoshan_myplayer_player_WlPlayer_n_1parpared(JNIEnv *env, jobject instance, jstring source_) {
    const char *source = env->GetStringUTFChars(source_, 0);

    if(fFmpeg == NULL)
    {
        if(callJava == NULL)
        {
            callJava = new WlCallJava(javaVM, env, &instance);
        }
        fFmpeg = new WlFFmpeg(callJava, source);
        fFmpeg->parpared();
    }
}

extern "C"
JNIEXPORT void JNICALL
Java_com_huoshan_myplayer_player_WlPlayer_n_1start(JNIEnv *env, jobject thiz) {
    if (fFmpeg != NULL) {
        fFmpeg->start();
    }
}
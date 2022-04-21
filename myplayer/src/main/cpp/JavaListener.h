//
// Created by loong on 2022/4/20.
//

#ifndef MYMUSIC_JAVALISTENER_H
#define MYMUSIC_JAVALISTENER_H

#include "jni.h"

class JavaListener {

public:
    JavaVM *jvm;
    _JNIEnv *jniEnv;
    jobject jobj;

    jmethodID jmid;

public:
    JavaListener(JavaVM *vm, _JNIEnv *env, jobject obj);
    ~JavaListener();

    /**
     * 1； 主线程
     * 0： 子线程
     * @param threadType
     * @param code
     * @param msg
     */
    void onError(int threadType, int code, const char *msg);
};


#endif //MYMUSIC_JAVALISTENER_H

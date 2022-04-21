//
// Created by loong on 2022/4/20.
//

#include "JavaListener.h"


JavaListener::JavaListener(JavaVM *vm, _JNIEnv *env, jobject obj){
    jvm = vm;
    jniEnv = env;
    jobj = obj;

//    if (!env){
//        return;
//    }
    jclass clz = env->GetObjectClass(jobj);
    jmid = env->GetMethodID(clz, "OnError", "(ILjava/lang/String;)V");
}

/**
 *
 * @param threadType
 * @param code
 * @param msg
 */
void JavaListener::onError(int threadType, int code, const char *msg)
{
    if( threadType == 0){
        JNIEnv *env;
        jvm->AttachCurrentThread(&env, 0);
        jstring jmsg = env->NewStringUTF(msg);
        env->CallVoidMethod(jobj, jmid, code, jmsg);
        env->DeleteLocalRef(jmsg);

        jvm->DetachCurrentThread();
    }
    else if( threadType == 1){
        jstring jmsg = jniEnv->NewStringUTF(msg);
        jniEnv->CallVoidMethod(jobj, jmid, code, jmsg);
        jniEnv->DeleteLocalRef(jmsg);
    }
}
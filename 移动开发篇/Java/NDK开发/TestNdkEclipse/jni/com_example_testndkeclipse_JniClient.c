#include "com_example_testndkeclipse_JniClient.h"
#include <stdlib.h>
#include <stdio.h>

// ����logͷ�ļ�
#include  <android/log.h>
// log��ǩ
#define  TAG    "=====CSY====="
// ����info��Ϣ
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
// ����debug��Ϣ
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
// ����error��Ϣ
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)

#ifdef __cplusplus
extern "C"
{
#endif
/*
 * Class:     com_example_testndkeclipse_JniClient
 * Method:    AddStr
 * Signature: (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_testndkeclipse_JniClient_AddStr
  (JNIEnv *env, jclass arg, jstring instringA, jstring instringB)
{
    //jstring str = (*env)->NewStringUTF(env, instringA+"=="+instringB+"are from JNI");
    jstring str = (*env)->NewStringUTF(env,"I am from JNI");
	LOGI("%d",len1);//c�������  ������eclipse�д�ӡ����  �÷���printf()

	//��jstringת����const char*ָ�룬ʹ��const���η���ʾ�����ݲ��ɱ��޸�
	const char* str1 = (*env)->NewStringUTFChars(instringA,null);
    return str;
}

/*
* Class:     com_example_testndkeclipse_JniClient
* Method:    AddInt
* Signature: (II)I
*/
JNIEXPORT jint JNICALL Java_com_example_testndkeclipse_JniClient_AddInt
  (JNIEnv *env, jclass arg, jint a, jint b)
{
    return a + b;
}

#ifdef __cplusplus
}
#endif

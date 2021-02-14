//
// Created by Ama on 12/7/2020.
//

#include <jni.h>

extern "C" jint Java_id_ac_ui_cs_mobileprogramming_bungaamalia_sutaato_FirstFragment_nativePlus(
        JNIEnv* env,
        jobject,
        int currValue
        ) {
    return currValue + 1;
}

extern "C" jint Java_id_ac_ui_cs_mobileprogramming_bungaamalia_sutaato_FirstFragment_nativeMinus(
        JNIEnv* env,
        jobject,
        int currValue
) {
    return currValue - 1;
}

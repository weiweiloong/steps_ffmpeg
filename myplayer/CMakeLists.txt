# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.18.1)

include_directories(src/main/cpp/include)
include_directories(src/main/cpp/soundtouch/include)
include_directories(src/main/cpp/soundtouch/SoundTouch)

#-------------111 添加动态库--------------------------------------------------------------------------
add_library( avcodec SHARED IMPORTED)
# 设置动态库路径
set_target_properties( avcodec
                       PROPERTIES IMPORTED_LOCATION
                       ${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}/libavcodec.so)
#------------222 添加动态库---------------------------------------------------------------------------
add_library( avfilter SHARED IMPORTED)
# 设置动态库路径
set_target_properties( avfilter
                       PROPERTIES IMPORTED_LOCATION
                       ${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}/libavfilter.so)
#------------333 添加动态库---------------------------------------------------------------------------
add_library( avformat SHARED IMPORTED)
# 设置动态库路径
set_target_properties( avformat
                       PROPERTIES IMPORTED_LOCATION
                       ${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}/libavformat.so)
#------------444 添加动态库---------------------------------------------------------------------------
add_library( avutil SHARED IMPORTED)
# 设置动态库路径
set_target_properties( avutil
                       PROPERTIES IMPORTED_LOCATION
                       ${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}/libavutil.so)
#------------555 添加动态库---------------------------------------------------------------------------
add_library( postproc SHARED IMPORTED)
# 设置动态库路径
set_target_properties( postproc
                       PROPERTIES IMPORTED_LOCATION
                       ${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}/libpostproc.so)
#------------666 添加动态库---------------------------------------------------------------------------
add_library( swresample SHARED IMPORTED)
# 设置动态库路径
set_target_properties( swresample
                       PROPERTIES IMPORTED_LOCATION
                       ${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}/libswresample.so)
#-----------777 添加动态库---------------------------------------------------------------------------
add_library( swscale SHARED IMPORTED)
# 设置动态库路径
set_target_properties( swscale
                       PROPERTIES IMPORTED_LOCATION
                       ${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}/libswscale.so)

project("mymusic")


add_library( # Sets the name of the library.
        mymusic

        SHARED

        src/main/cpp/native-lib.cpp
        src/main/cpp/WlAudio.cpp
        src/main/cpp/WlCallJava.cpp
        src/main/cpp/WlFFmpeg.cpp
	    src/main/cpp/WlQueue.cpp
	    src/main/cpp/WlPlaystatus.cpp

		src/main/cpp/soundtouch/SoundTouch/AAFilter.cpp
		src/main/cpp/soundtouch/SoundTouch/FIFOSampleBuffer.cpp
		src/main/cpp/soundtouch/SoundTouch/FIRFilter.cpp
		src/main/cpp/soundtouch/SoundTouch/cpu_detect_x86.cpp
		src/main/cpp/soundtouch/SoundTouch/sse_optimized.cpp
		src/main/cpp/soundtouch/SoundTouch/RateTransposer.cpp
		src/main/cpp/soundtouch/SoundTouch/SoundTouch.cpp
		src/main/cpp/soundtouch/SoundTouch/InterpolateCubic.cpp
		src/main/cpp/soundtouch/SoundTouch/InterpolateLinear.cpp
		src/main/cpp/soundtouch/SoundTouch/InterpolateShannon.cpp
		src/main/cpp/soundtouch/SoundTouch/TDStretch.cpp
		src/main/cpp/soundtouch/SoundTouch/PeakFinder.cpp

		)

find_library( # Sets the name of the path variable.
        log-lib

        log)


target_link_libraries( # Specifies the target library.
        mymusic

        avcodec
        avfilter
        avformat
        avutil
        postproc
        swresample
        swscale

        OpenSLES
        ${log-lib})
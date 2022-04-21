//
// Created by yangw on 2018-2-28.
//

#include "WlAudio.h"

WlAudio::WlAudio(WlPlaystatus *playstatus) {
    this->playstatus = playstatus;
    queue = new WlQueue(playstatus);
}

WlAudio::~WlAudio() {

}

package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Slice;

import java.util.LinkedList;
import java.util.List;

class RO_lpush extends RO_push {
    RO_lpush(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    void pusher(LinkedList<Slice> list, Slice slice) {
        list.addFirst(slice);
    }

}

package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Slice;

import java.util.LinkedList;
import java.util.List;

class RO_lpop extends RO_pop {
    RO_lpop(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    Slice popper(LinkedList<Slice> list) {
        return list.removeFirst();
    }
}

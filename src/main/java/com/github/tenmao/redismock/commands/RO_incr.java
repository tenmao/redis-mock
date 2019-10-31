package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Slice;

import java.util.List;

class RO_incr extends RO_incrby {
    RO_incr(RedisBase base, List<Slice> params) {
        super(base, params, 1);
    }

    @Override
    long incrementOrDecrementValue(List<Slice> params) {
        return 1L;
    }
}

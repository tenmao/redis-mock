package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.List;

class RO_flushall extends AbstractRedisOperation {
    RO_flushall(RedisBase base, List<Slice> params) {
        super(base, params, 0, null, null);
    }

    @Override
    Slice response() {
        base().clear();
        return Response.OK;
    }
}

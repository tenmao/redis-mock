package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.List;

class RO_exists extends AbstractRedisOperation {
    RO_exists(RedisBase base, List<Slice> params) {
        super(base, params, 1, null, null);
    }

    @Override
    Slice response() {
        if (base().rawGet(params().get(0)) != null) {
            return Response.integer(1);
        }
        return Response.integer(0);
    }
}

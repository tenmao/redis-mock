package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.List;

class RO_strlen extends AbstractRedisOperation {
    RO_strlen(RedisBase base, List<Slice> params) {
        super(base, params, 1, null, null);
    }

    @Override
    Slice response() {
        Slice value = base().rawGet(params().get(0));
        if (value == null) {
            return Response.integer(0);
        }
        return Response.integer(value.length());
    }
}

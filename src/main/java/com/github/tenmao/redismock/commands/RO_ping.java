package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.List;

class RO_ping extends AbstractRedisOperation {
    RO_ping(RedisBase base, List<Slice> params) {
        super(base, params, 0, null, null);
    }

    @Override
    Slice response() {
        if (params().isEmpty()) {
            return Response.bulkString(new Slice("PONG"));
        }

        return Response.bulkString(params().get(0));
    }
}

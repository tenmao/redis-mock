package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.List;

class RO_getset extends AbstractRedisOperation {
    RO_getset(RedisBase base, List<Slice> params) {
        super(base, params, 2, null, null);
    }

    @Override
    Slice response() {
        Slice value = base().rawGet(params().get(0));
        base().rawPut(params().get(0), params().get(1), -1L);
        return Response.bulkString(value);
    }
}

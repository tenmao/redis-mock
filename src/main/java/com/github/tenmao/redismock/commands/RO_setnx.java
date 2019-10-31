package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.List;

class RO_setnx extends AbstractRedisOperation {
    RO_setnx(RedisBase base, List<Slice> params) {
        super(base, params, 2, null, null);
    }

    @Override
    Slice response() {
        if (base().rawGet(params().get(0)) == null) {
            base().rawPut(params().get(0), params().get(1), -1L);
            return Response.integer(1);
        }
        return Response.integer(0);
    }
}

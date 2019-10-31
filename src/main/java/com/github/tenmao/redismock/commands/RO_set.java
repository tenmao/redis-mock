package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.List;

class RO_set extends AbstractRedisOperation {
    RO_set(RedisBase base, List<Slice> params) {
        super(base, params, null, null, null);
    }

    public RO_set(RedisBase base, List<Slice> params, Integer i) {
        super(base, params, i, null, null);
    }

    long valueToSet(List<Slice> params) {
        return -1L;
    }

    @Override
    Slice response() {
        //TODO will support set multiple parameter like NX PX|EX
        base().rawPut(params().get(0), params().get(1), valueToSet(params()));
        return Response.OK;
    }
}

package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.List;

import static com.github.tenmao.redismock.Utils.convertToLong;

class RO_expireat extends AbstractRedisOperation {
    RO_expireat(RedisBase base, List<Slice> params) {
        super(base, params, 2, null, null);
    }

    @Override
    Slice response() {
        long deadline = convertToLong(new String(params().get(1).data())) * 1000;
        return Response.integer(base().setDeadline(params().get(0), deadline));
    }
}

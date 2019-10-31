package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.List;

class RO_ttl extends AbstractRedisOperation {
    RO_ttl(RedisBase base, List<Slice> params) {
        super(base, params, 1, null, null);
    }

    Slice finalReturn(Long pttl) {
        return Response.integer((pttl + 999) / 1000);
    }

    @Override
    Slice response() {
        Long pttl = base().getTTL(params().get(0));
        if (pttl == null) {
            return Response.integer(-2L);
        }
        if (pttl == -1) {
            return Response.integer(-1L);
        }
        return finalReturn(pttl);
    }
}

package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.List;

class RO_pttl extends RO_ttl {
    RO_pttl(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    Slice finalReturn(Long pttl) {
        return Response.integer(pttl);
    }
}

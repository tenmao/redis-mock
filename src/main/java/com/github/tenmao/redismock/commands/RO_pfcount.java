package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

import static com.github.tenmao.redismock.Utils.deserializeObject;

class RO_pfcount extends AbstractRedisOperation {
    RO_pfcount(RedisBase base, List<Slice> params) {
        super(base, params, null, 0, null);
    }

    @Override
    Slice response() {
        Set<Slice> set = Sets.newHashSet();
        for (Slice key : params()) {
            Slice data = base().rawGet(key);
            if (data == null) {
                continue;
            }

            Set<Slice> s = deserializeObject(data);
            set.addAll(s);
        }
        return Response.integer((long) set.size());
    }
}

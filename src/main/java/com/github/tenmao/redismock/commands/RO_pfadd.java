package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

import static com.github.tenmao.redismock.Utils.deserializeObject;
import static com.github.tenmao.redismock.Utils.serializeObject;

class RO_pfadd extends AbstractRedisOperation {
    RO_pfadd(RedisBase base, List<Slice> params) {
        super(base, params, null, 1, null);
    }

    @Override
    Slice response() {
        Slice key = params().get(0);
        Slice data = base().rawGet(key);
        boolean first;

        Set<Slice> set;
        int prev;
        if (data == null) {
            set = Sets.newHashSet();
            first = true;
            prev = 0;
        } else {
            set = deserializeObject(data);
            first = false;
            prev = set.size();
        }

        for (Slice v : params().subList(1, params().size())) {
            set.add(v);
        }

        Slice out = serializeObject(set);
        if (first) {
            base().rawPut(key, out, -1L);
        } else {
            base().rawPut(key, out, null);
        }

        if (prev != set.size()) {
            return Response.integer(1L);
        }
        return Response.integer(0L);
    }
}

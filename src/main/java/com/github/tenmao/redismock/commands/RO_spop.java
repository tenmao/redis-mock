package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.github.tenmao.redismock.Utils.deserializeObject;
import static com.github.tenmao.redismock.Utils.serializeObject;

class RO_spop extends AbstractRedisOperation {
    RO_spop(RedisBase base, List<Slice> params) {
        super(base, params, 1, null, null);
    }

    @Override
    Slice response() {
        Slice key = params().get(0);
        Slice data = base().rawGet(key);
        Set<Slice> set;
        if (data != null) {
            set = deserializeObject(data);
        } else {
            return Response.NULL;
        }

        if (set.isEmpty()) {
            return Response.NULL;
        }
        Iterator<Slice> it = set.iterator();
        Slice v = it.next();
        it.remove();
        base().rawPut(key, serializeObject(set), -1L);
        return Response.bulkString(v);
    }
}

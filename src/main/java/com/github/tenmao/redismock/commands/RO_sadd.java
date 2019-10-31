package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;
import com.github.tenmao.redismock.exception.InternalException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.github.tenmao.redismock.Utils.deserializeObject;
import static com.github.tenmao.redismock.Utils.serializeObject;

class RO_sadd extends AbstractRedisOperation {
    RO_sadd(RedisBase base, List<Slice> params) {
        super(base, params, null, 1, null);
    }

    @Override
    Slice response() {
        Slice key = params().get(0);
        Slice data = base().rawGet(key);
        Set<Slice> set;

        if (data != null) {
            set = deserializeObject(data);
        } else {
            set = new HashSet<>();
        }

        for (int i = 1; i < params().size(); i++) {
            set.add(params().get(i));
        }
        try {
            base().rawPut(key, serializeObject(set), -1L);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
        return Response.integer(set.size());
    }
}

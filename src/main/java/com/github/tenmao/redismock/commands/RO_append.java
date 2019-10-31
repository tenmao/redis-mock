package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.List;

class RO_append extends AbstractRedisOperation {
    RO_append(RedisBase base, List<Slice> params) {
        super(base, params, 2, null, null);
    }

    @Override
    Slice response() {
        Slice key = params().get(0);
        Slice value = params().get(1);
        Slice s = base().rawGet(key);
        if (s == null) {
            base().rawPut(key, value, -1L);
            return Response.integer(value.length());
        }
        byte[] b = new byte[s.length() + value.length()];
        for (int i = 0; i < s.length(); i++) {
            b[i] = s.data()[i];
        }
        for (int i = s.length(); i < s.length() + value.length(); i++) {
            b[i] = value.data()[i - s.length()];
        }
        base().rawPut(key, new Slice(b), -1L);
        return Response.integer(b.length);
    }
}

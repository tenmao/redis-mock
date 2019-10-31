package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Slice;

import java.util.List;

import static com.github.tenmao.redismock.Utils.*;

abstract class AbstractRedisOperation implements RedisOperation {
    private final RedisBase base;
    private final List<Slice> params;

    AbstractRedisOperation(RedisBase base, List<Slice> params, Integer expectedParams, Integer minParams, Integer factorParams) {
        this.base = base;
        this.params = params;
        precheck(expectedParams, minParams, factorParams);
    }

    void doOptionalWork() {
    }

    abstract Slice response();

    RedisBase base() {
        return base;
    }

    List<Slice> params() {
        return params;
    }

    @Override
    public Slice execute() {
        doOptionalWork();

        synchronized (base) {
            return response();
        }
    }

    /**
     * Runs a default precheck to make sure the parameters are as expected
     */
    private void precheck(Integer expectedParams, Integer minParams, Integer factorParams) {
        if (expectedParams != null) {
            checkArgumentsNumberEquals(params, expectedParams);
        }
        if (minParams != null) {
            checkArgumentsNumberGreater(params, minParams);
        }
        if (factorParams != null) {
            checkArgumentsNumberFactor(params, factorParams);
        }
    }
}

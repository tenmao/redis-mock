package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.Slice;

/**
 * Represents a Redis Operation which can be executed against {@link com.github.tenmao.redismock.RedisBase}
 */
public interface RedisOperation {
    Slice execute();
}

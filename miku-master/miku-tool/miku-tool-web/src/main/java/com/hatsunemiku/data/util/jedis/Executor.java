package com.hatsunemiku.data.util.jedis;

import redis.clients.jedis.ShardedJedis;

public interface Executor<K> {
	public K execute(ShardedJedis jedis);
}

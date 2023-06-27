package org.infinispan.multimap.api;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

import org.infinispan.commons.util.Experimental;
import org.infinispan.container.entries.CacheEntry;
import org.infinispan.util.function.SerializablePredicate;

/**
 * MultimapCache provides the common building block for the two different types of multimap caches that Infinispan
 * provides: embedded and remote. <p> Please see the <a href="http://infinispan.org/documentation/">Infinispan
 * documentation</a> and/or the <a href="http://infinispan.org/docs/dev/getting_started/getting_started.html">5 Minute
 * Usage Tutorial</a> for more details on Infinispan.
 * <p/>
 * <p>
 * MutimapCache is a type of Infinispan Cache that maps keys to values, similar to {@link
 * org.infinispan.commons.api.AsyncCache} in which each key can contain multiple values.
 * <pre>
 *    foo -> 1
 *    bar -> 3, 4, 5
 * </pre>
 * <p>
 * <h2>Example</h2>
 * <pre>
 *
 *    multimapCache.put("k", "v1").join();
 *    multimapCache.put("k", "v2").join();
 *    multimapCache.put("k", "v3").join();
 *
 *    Collection<String> results = multimapCache.get("k").join();
 *
 * </pre>
 * <p>
 * <h2>Eviction</h2> <p> Eviction works per key. This means all the values associated on a key will be evicted. </p>
 * <p>
 * <h2>Views</h2>
 * <p>
 * The returned collections when calling "get" are views of the values on the key. Any change on these collections won't
 * affect the cache values on the key.
 * <p>
 * <h2>Null values</h2> Null values are not supported. The multimap cache won't have a null key or any null value.
 * <p>
 * Example
 * <pre>
 *     multimapCache.put(null, "v1").join() -> fails
 *     multimapCache.put("k", null).join() -> fails
 *     multimapCache.put("k", "v1").join() -> works and add's v1
 *     multimapCache.containsKey("k").join() -> true
 *     multimapCache.remove("k", "v1").join() -> works, removes v1 and as the remaining collection is empty, the key is
 * removed
 *     multimapCache.containsKey("k").join() -> false
 *  </pre>
 * <p>
 * <p>
 * <h2>Duplicates</h2> The current implementation does not support duplicate values on keys. {@link Object#equals(Object)}
 * method is used to check if a value is already present in the key. This means that the following code
 * <pre>
 *    multimapCache.put("k", "v1").join();
 *    multimapCache.put("k", "v2").join();
 *    multimapCache.put("k", "v2").join();
 *    multimapCache.put("k", "v2").join();
 *
 *    multimapCache.get("k").thenAccept(values -> System.out.println(values.size()));
 * </pre>
 * prints the value 2. "k" -> ["v1", "v"]
 * <p>
 * Future implementations might evolve to support duplicated values.
 * <p>
 * <h2>Transactions</h2> MultimapCache supports implicit transactions without blocking. The following methods block when
 * they are called in a explicit transaction context. This limitation could be improved in the following versions if
 * technically possible.
 * <p>
 * <ul>
 * <li>{@link MultimapCache#size()}
 * <li>{@link MultimapCache#containsEntry(Object, Object)}
 * <li>{@link MultimapCache#remove(Predicate)}
 * </ul>
 * <p>
 * More about transactions in : <a
 * href="http://infinispan.org/docs/dev/user_guide/user_guide.html#transactions">the Infinispan Documentation</a>.
 *
 * @author Katia Aresti, karesti@redhat.com
 * @see <a href="http://infinispan.org/documentation/">Infinispan documentation</a>
 * @since 9.2
 */
@Experimental
public interface MultimapCache<K, V> {

   /**
    * Asynchronous method. Puts a key-value pair in this multimap cache.
    * <ul>
    * <li>If this multimap cache supports duplicates, the value will be always added.</li>
    * <li>If this multimap cache does <i>not support</i> duplicates and the value exists on the key,
    * nothing will be done.</li>
    * </ul>
    *
    * @param key   the key to be put
    * @param value the value to added
    * @return {@link CompletableFuture} containing a {@link Void}
    * @since 9.2
    */
   CompletableFuture<Void> put(K key, V value);

   /***
    * Asynchronous method. Returns a <i>view collection</i> of the values associated with key in this multimap cache,
    * if any. Any changes to the retrieved collection won't change the values in this multimap cache.
    * <b>When this method returns an empty collection, it means the key was not found.</b>
    *
    * @param key to be retrieved
    * @return a {@link CompletableFuture} containing {@link Collection <V>} which is a view of the underlying values.
    * @since 9.2
    */
   CompletableFuture<Collection<V>> get(K key);

   /**
    * Retrieves a CacheEntry corresponding to a specific key in this multimap cache.
    *
    * @param key the key whose associated cache entry is to be returned
    * @return the cache entry to which the specified key is mapped, or {@link Optional#empty()} if this multimap
    * contains no mapping for the key
    * @since 9.2
    */
   CompletableFuture<Optional<CacheEntry<K, Collection<V>>>> getEntry(K key);

   /**
    * Asynchronous method. Removes the entry associated with the key from this multimap cache, if such exists.
    *
    * @param key to be removed
    * @return a {@link CompletableFuture} containing {@link Boolean#TRUE} if the entry was removed, and {@link
    * Boolean#FALSE} when the entry was not removed
    * @since 9.2
    */
   CompletableFuture<Boolean> remove(K key);

   /**
    * Asynchronous method. Removes a key-value pair from this multimap cache, if such exists.
    * Returns true when the key-value pair has been removed from the key.
    * <p>
    * <ul>
    * <li>In the case where duplicates are <b>not supported</b>, <b>only one</b> the key-value
    * pair will be removed, if such exists.</li>
    * <li>In the case where duplicates are supported, <b>all the key-value pairs</b> will be removed.</li>
    * <li>If the values remaining after the remove call are empty, the whole entry will be removed.</li>
    * </ul>
    *
    * @param key   key to be removed
    * @param value value to be removed
    * @return {@link CompletableFuture} containing {@link Boolean#TRUE} if the key-value pair was removed, and {@link
    * Boolean#FALSE} when the key-value pair was not removed
    * @since 9.2
    */
   CompletableFuture<Boolean> remove(K key, V value);

   /**
    * Asynchronous method. Removes every value that match the {@link Predicate}.
    * <p>
    * This method <b>is blocking</b> used in a explicit transaction context.
    *
    * @param p the predicate to be tested on every value in this multimap cache
    * @return {@link CompletableFuture} containing a {@link Void}
    * @since 9.2
    */
   CompletableFuture<Void> remove(Predicate<? super V> p);

   /**
    * Overloaded method of {@link MultimapCache#remove(Predicate)} with {@link SerializablePredicate}. The compiler
    * will pick up this method and make the given predicate {@link java.io.Serializable}.
    *
    * @param p the predicate to be tested on every value in this multimap cache
    * @return {@link CompletableFuture} containing a {@link Void}
    * @since 9.2
    */
   default CompletableFuture<Void> remove(SerializablePredicate<? super V> p) {
      return this.remove((Predicate) p);
   }

   /**
    * Asynchronous method. Returns {@link Boolean#TRUE} if this multimap cache contains the key.
    *
    * @param key the key tht might exists in this multimap cache
    * @return {@link CompletableFuture} containing a {@link Boolean}
    * @since 9.2
    */
   CompletableFuture<Boolean> containsKey(K key);

   /**
    * Asynchronous method that returns {@link Boolean#TRUE} if this multimap cache contains the value at any key.
    *
    * @param value the value that might exists in any entry
    * @return {@link CompletableFuture} containing a {@link Boolean}
    * @since 9.2
    */
   CompletableFuture<Boolean> containsValue(V value);

   /**
    * Asynchronous method that returns {@link Boolean#TRUE} if this multimap cache contains the key-value pair.
    *
    * @param key   the key of the key-value pair
    * @param value the value of the key-value pair
    * @return {@link CompletableFuture} containing a {@link Boolean}
    * @since 9.2
    */
   CompletableFuture<Boolean> containsEntry(K key, V value);

   /**
    * Returns the number of key-value pairs in this multimap cache.
    * It doesn't return the distinct number of keys.
    * <p>
    * This method <b>is blocking</b> in a explicit transaction context.
    * <p>
    * The {@link CompletableFuture} is a
    *
    * @return {@link CompletableFuture} containing the size as {@link Long}
    * @since 9.2
    */
   CompletableFuture<Long> size();

   /**
    * Multimap can support duplicates on the same key k -> ['a', 'a', 'b'] or not k -> ['a', 'b']
    * depending on configuration.
    * <p>
    * Returns duplicates are supported or not in this multimap cache.
    *
    * @return {@code true} if this multicache supports duplicate values for a given key.
    * @since 9.2
    */
   boolean supportsDuplicates();

}

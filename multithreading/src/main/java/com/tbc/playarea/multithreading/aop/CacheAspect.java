/**
 * 
 */
package com.tbc.playarea.multithreading.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author parthp
 *
 */
@Aspect
public class CacheAspect {
	private Map<String, Object> cache;

	public CacheAspect() {
		super();
		cache = new HashMap<String, Object>();
	}
	
	@Pointcut("execution(@Cacheable * *.*(..))")
	private void cache() {
		
	}
	
	@Around("cache()")
	public Object aroundCacheMethods(ProceedingJoinPoint joinPoint) {
		System.out.println("Execution of Cacheable method caught!");
		String key = buildCacheKey(joinPoint);
		System.out.println("Generated Key: " + key);
		Object result = cache.get(key);
		if(null == result) {
			System.out.println("Result not cached yet. Must be calculated.");
			try {
				result = joinPoint.proceed();
				System.out.println("Caching result!");
				cache.put(key, result);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Result: " + result + " found in cache");
		}
		
		return result;
	}

	private String buildCacheKey(ProceedingJoinPoint joinPoint) {
		StringBuilder keyBuf = new StringBuilder();
		keyBuf.append(joinPoint.getTarget().getClass().getName());
		keyBuf.append(".").append(joinPoint.getSignature().getName());
		keyBuf.append("(");
		for(final Object arg : joinPoint.getArgs()) {
			keyBuf.append(arg.getClass().getSimpleName() + "=" + arg + ";");
		}
		keyBuf.append(")");
		return keyBuf.toString();
	}
}

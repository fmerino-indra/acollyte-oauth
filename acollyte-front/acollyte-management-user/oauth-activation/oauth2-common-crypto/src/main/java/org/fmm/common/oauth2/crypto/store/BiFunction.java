package org.fmm.common.oauth2.crypto.store;

@FunctionalInterface
public interface BiFunction<S1, S2, T> {
	T apply(S1 s1, S2 s2);
}

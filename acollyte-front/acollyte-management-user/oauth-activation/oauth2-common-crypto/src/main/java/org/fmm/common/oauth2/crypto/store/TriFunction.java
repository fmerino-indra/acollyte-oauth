package org.fmm.common.oauth2.crypto.store;

@FunctionalInterface
public interface TriFunction<S1, S2, S3, T> {
	T apply(S1 s1, S2 s2, S3 s3);
}

package org.jdw.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "profilesblog", ignoreUnknownFields = false)
public class ProfilesBlogProperties {

	private final Async async = new Async();

	public Async getAsync() {
		return async;
	}

	public static class Async {

		private int corePoolSize = 2;

		private int maxPoolSize = 50;

		private int queueCapacity = 10000;

		public int getCorePoolSize() {
			return corePoolSize;
		}

		public void setCorePoolSize(int corePoolSize) {
			this.corePoolSize = corePoolSize;
		}

		public int getMaxPoolSize() {
			return maxPoolSize;
		}

		public void setMaxPoolSize(int maxPoolSize) {
			this.maxPoolSize = maxPoolSize;
		}

		public int getQueueCapacity() {
			return queueCapacity;
		}

		public void setQueueCapacity(int queueCapacity) {
			this.queueCapacity = queueCapacity;
		}
	}
}

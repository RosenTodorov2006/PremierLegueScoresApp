package bg.soft_uni.premierlegueapp.services.sheduling;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheScheduler {
    private final CacheManager cacheManager;

    public CacheScheduler(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Scheduled(fixedRate = 49000)
    public void clearAndUpdateCaches() {
        clearCaches();
    }
    private void clearCaches() {
        Cache standingCache = cacheManager.getCache("standing");
        if (standingCache != null) {
            standingCache.clear();
        }

        Cache matchesCache = cacheManager.getCache("matches");
        if (matchesCache != null) {
            matchesCache.clear();
        }
    }
}

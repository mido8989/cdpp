package com.hyundaimotors.hmb.cdppapp.output;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hyundaimotors.hmb.cdppapp.util.AccountRequestCaches;

@EnableScheduling
@Component
public class DuplicateRequestCacheCleanBatch {
    @Scheduled(cron = "0 0/20 * * * *")
    public void cleanAccountRequestCache() {
        AccountRequestCaches.getInstance().clearExpiredCache();
    }
}

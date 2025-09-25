package com.goormthon.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class RandomNumberPicker {

    //랜덤한 N개의 숫자 산출
    public List<Long> pickRandom(long totalCount, long pageSize) {
        if (pageSize > totalCount) {
            throw new IllegalArgumentException("pageSize cannot be greater than totalCount");
        }

        Random random = new Random();
        Set<Long> picked = new HashSet<>();

        while (picked.size() < pageSize) {
            long num = random.nextLong(totalCount) + 1; // 1~totalCount
            picked.add(num); // Set이라 중복 자동 제거
        }

        return new ArrayList<>(picked);
    }

    //제외하고 뽑기
    public List<Long> pickRandomExcluding(long totalCount, long size, List<Long> excludedIds) {
        if (size > totalCount - excludedIds.size()) {
            throw new IllegalArgumentException("size is too large for the available numbers");
        }

        Set<Long> excludedSet = new HashSet<>(excludedIds); // 빠른 탐색용
        Set<Long> picked = new HashSet<>();
        Random random = new Random();

        while (picked.size() < size) {
            long num = random.nextInt((int) totalCount) + 1; // 1~totalCount
            if (!excludedSet.contains(num)) {
                picked.add(num);
            }
        }

        return new ArrayList<>(picked);
    }

}

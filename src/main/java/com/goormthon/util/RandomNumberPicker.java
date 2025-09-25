package com.goormthon.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RandomNumberPicker {

    public List<Long> pickRandomNumbers(List<Long> numbers, long n) {
        if (n > numbers.size()) {
            throw new IllegalArgumentException("n은 numbers의 크기보다 클 수 없습니다.");
        }

        // 원본 리스트를 바꾸지 않기 위해 복사
        List<Long> copy = new ArrayList<>(numbers);

        // 섞기
        Collections.shuffle(copy);

        // 앞에서 n개 추출
        return new ArrayList<>(copy.subList(0, (int) n));
    }

    public List<Long> pickRandomNumbers(List<Long> numbers, long n, List<Long> excludes) {
        // numbers 복사 후 excludes 제거
        List<Long> filtered = new ArrayList<>(numbers);
        filtered.removeAll(excludes);

        if (n > filtered.size()) {
            throw new IllegalArgumentException("n은 (numbers - excludes)의 크기보다 클 수 없습니다.");
        }

        // 섞기
        Collections.shuffle(filtered);

        // 앞에서 n개 추출
        return new ArrayList<>(filtered.subList(0, (int) n));
    }

}

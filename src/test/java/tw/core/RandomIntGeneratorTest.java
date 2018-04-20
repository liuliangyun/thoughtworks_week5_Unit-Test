package tw.core;


import org.junit.Test;
import tw.core.generator.RandomIntGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {
    @Test
    public void should_get_string_length__is_4_when_numbersOfNeed_is_4() {
        // Given
        RandomIntGenerator randomIntGenerator = new RandomIntGenerator();

        // When
        String result = randomIntGenerator.generateNums(9, 4);
        List<String> resultList = Arrays.stream(result.split(" ")).collect(Collectors.toList());

        // Then
        assertEquals(4, resultList.size());
    }
}
package ru.vsu.cs.okshina_v_a;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class TestOfExtractingQueue {
    private final MySimpleQueue<Integer> inputArrQueue1;
    private final MySimpleQueue<Integer> inputArrQueue2;
    private final int[] expectedResult;

    public TestOfExtractingQueue(MySimpleQueue<Integer> inputArrQueue1, MySimpleQueue<Integer> inputArrQueue2, int[] expectedResult) {
        this.inputArrQueue1 = inputArrQueue1;
        this.inputArrQueue2 = inputArrQueue2;
        this.expectedResult = expectedResult;
    }

    static int[] arr1 = new int[]{1,2,3};
    static int[] arr2 = new int[]{4,5,6};

    static int[] arr3 = new int[]{2, 1, -8, -35, 7};
    static int[] arr4 = new int[]{1, 7, -6, 3, 32, 2, 65, 34};

    static int[] arr5 = new int[]{0,1};
    static int[] arr6 = new int[]{2,3};

    @Parameterized.Parameters
    public static List<Object[]> cases() {
        return Arrays.asList(new Object[][]{
                {MySimpleQueue.arrayToMyQueue(arr1), MySimpleQueue.arrayToMyQueue(arr2), new int[]{3}},
                {MySimpleQueue.arrayToMyQueue(arr3), MySimpleQueue.arrayToMyQueue(arr4), new int[]{12}},
                {MySimpleQueue.arrayToMyQueue(arr5), MySimpleQueue.arrayToMyQueue(arr6), new int[]{2}},
        });
    }

    @Test
    public void TestOfQueues() throws Exception {
        int[] actualResult = ExtractingQueue.transformMyQueue(inputArrQueue1, inputArrQueue2);
        Assert.assertEquals(Arrays.toString(expectedResult), Arrays.toString(actualResult));
    }
}
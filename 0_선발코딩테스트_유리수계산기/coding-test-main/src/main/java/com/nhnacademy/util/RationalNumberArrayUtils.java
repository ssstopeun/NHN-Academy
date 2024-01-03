/*
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright 2023. NHN Academy Corp. All rights reserved.
 * + * While every precaution has been taken in the preparation of this resource,  assumes no
 * + responsibility for errors or omissions, or for damages resulting from the use of the information
 * + contained herein
 * + No part of this resource may be reproduced, stored in a retrieval system, or transmitted, in any
 * + form or by any means, electronic, mechanical, photocopying, recording, or otherwise, without the
 * + prior written permission.
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */

package com.nhnacademy.util;

import com.nhnacademy.number.MyNumber;
import com.nhnacademy.number.impl.RationalNumber;

import javax.naming.OperationNotSupportedException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RationalNumberArrayUtils {

    private RationalNumberArrayUtils(){
        throw new IllegalStateException("Utility class!");
    }

    /**
     * 오름차순 정렬
     * @param myNumbers
     * @return 오름차순으로 정렬된 List<MyNumber>반환
     */
    public static List<MyNumber> sort(List<MyNumber> myNumbers){
        return myNumbers.stream()
                .sorted((a,b)-> {
                    if (a.toDouble() - b.toDouble()<0) {
                        return -1;
                    }
                    return 1;
                }).collect(Collectors.toList());
    }

    /**
     * 합계
     * @param myNumbers
     * @return List<MyNumber>의 합을 반환
     */
    public static MyNumber sum(List<MyNumber> myNumbers){
        return myNumbers.stream()
                .reduce(new RationalNumber(0),(a,b)-> {
                    try {
                        return a.plus(b);
                    } catch (OperationNotSupportedException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    /**
     *
     * @param myNumbers
     * @return List<MyNumber>의 양수 합을 반환
     */
    public static MyNumber sumOfPositive(List<MyNumber> myNumbers){
        return myNumbers.stream()
                .filter(myNumber -> myNumber.toDouble()>0)
                .reduce(new RationalNumber(0),(a,b)-> {
                    try {
                        return a.plus(b);
                    } catch (OperationNotSupportedException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

}

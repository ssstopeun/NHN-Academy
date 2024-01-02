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

package com.nhnacademy.number;

import javax.naming.OperationNotSupportedException;

public interface MyNumber {
    MyNumber plus(MyNumber operand) throws OperationNotSupportedException;
    MyNumber minus(MyNumber operand) throws OperationNotSupportedException;
    MyNumber multipliedBy(MyNumber operand) throws OperationNotSupportedException;
    MyNumber divideBy(MyNumber operand) throws OperationNotSupportedException;
    abstract Double toDouble();
}
package com.tang.hwplib.objects.docinfo.tabdef

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 탭의 속성을 나타내는 객체
 * UINT32 - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 탭의 속성값을 가진 데이터
 */
class HWPTabDefProperty {
    var value: Long = 0

    /**
     * 문단 왼쪽 끝 자동 탭 유무를 반환하는 함수
     * 내어 쓰기용 자동 탭
     * bit 0
     *
     * @return [Boolean] 문단 왼쪽 끝 자동 탭 유무 반환
     */
    fun isAutoTabAtParagraphLeftEnd() : Boolean = get(value, 0)

    /**
     * 문단 왼쪽 끝 자동 탭 유무를 설정하는 함수
     * 내어 쓰기용 자동 탭
     * bit 0
     *
     * @param [autoTabAtParagraphLeftEnd] [Boolean], 문단 왼쪽 끝 자동 탭 유무의 참/거짓값을 가진 데이터
     */
    fun setAutoTabAtParagraphLeftEnd(autoTabAtParagraphLeftEnd: Boolean) {
        value = set(value, 0, autoTabAtParagraphLeftEnd)
    }

    /**
     * 문단 오른쪽 끝 자동 탭 유무를 반환하는 함수
     * bit 1
     *
     * @return [Boolean] 문단 오른쪽 끝 자동 탭 유무 반환
     */
    fun isAutoTabAtParagraphRightEnd() : Boolean = get(value, 1)

    /**
     * 문단 오른쪽 끝 자동 탭 유무를 설정하는 함수
     * bit 1
     *
     * @param [autoTabAtParagraphRightEnd] [Boolean], 문단 오른쪽 끝 자동 탭 유무의 참/거짓값을 가진 데이터
     */
    fun setAutoTabAtParagraphRightEnd(autoTabAtParagraphRightEnd: Boolean) {
        value = set(value, 1, autoTabAtParagraphRightEnd)
    }
}
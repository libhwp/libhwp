package com.tang.hwplib.objects.bodytext.control.sectiondefine

import com.tang.hwplib.annotation.ID
import com.tang.hwplib.annotation.IDTypes
import com.tang.hwplib.annotation.LinkID
import com.tang.hwplib.objects.docinfo.HWPBorderFill
import com.tang.hwplib.objects.etc.*
import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 위치 기준
 *
 * @author accforaus
 *
 * @property [value] [Byte], 위치 기준 값
 */
enum class HWPPositionCriterion(v: Byte) {
    /**
     * 본문 기준
     */
    MainText(0.toByte()),
    /**
     * 종이 기준
     */
    Paper(1.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPPositionCriterion] enum 값
         */
        fun valueOf(v: Byte) : HWPPositionCriterion {
            for (pc in values())
                if (pc.value == v)
                    return pc
            return MainText
        }
    }
}

/**
 * 채울 영억
 *
 * @author accforaus
 *
 * @property [value] [Byte], 채울 영역 값
 */
enum class HWPFillArea(v: Byte) {
    /**
     * 종이
     */
    Paper(0.toByte()),
    /**
     * 쪽
     */
    Page(1.toByte()),
    /**
     * 테두리
     */
    Border(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPFillArea] enum 값
         */
        fun valueOf(v: Byte) : HWPFillArea {
            for (fa in values())
                if (fa.value == v)
                    return fa
            return Paper
        }
    }
}

/**
 * 쪽 테두리/배경 속성을 나타내는 객체
 * UINT32 - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 쪽 테두리/배경 속성값
 */
class HWPPageBorderFillProperty {
    var value: Long = 0
        set(newValue) {
            field = newValue
            _positionCriterion = getPositionCriterion()
            _isIncludeHeader = isIncludeHeader()
            _isIncludeFooter = isIncludeFooter()
            _fillArea = getFillArea()
        }
    private var _positionCriterion: HWPPositionCriterion = HWPPositionCriterion.MainText
    private var _isIncludeHeader: Boolean = false
    private var _isIncludeFooter: Boolean = false
    private var _fillArea: HWPFillArea = HWPFillArea.Paper

    /**
     * 위치 기준을 반환하는 함수
     * bit 0
     *
     * @return [HWPPositionCriterion] 위치 기준 반환
     */
    fun getPositionCriterion() : HWPPositionCriterion = if (get(value, 0)) HWPPositionCriterion.Paper else HWPPositionCriterion.MainText

    /**
     * 위치 기준을 설정하는 함수
     * bit 0
     *
     * @param [positionCriterion] [HWPPositionCriterion] 위치 기준값
     */
    fun setPositionCriterion(positionCriterion: HWPPositionCriterion) {
        if (positionCriterion == HWPPositionCriterion.MainText)
            value = set(value, 0, false)
        else (positionCriterion == HWPPositionCriterion.Paper)
            value = set(value, 0, true)
    }

    /**
     * 머리말 포함 여부를 반환하는 함수
     * bit 1
     *
     * @return [Boolean] 머리말 포함 여부 반환
     */
    fun isIncludeHeader() : Boolean = get(value, 1)

    /**
     * 머리말 포함 여부를 설정하는 함수
     * bit 1
     *
     * @param [includeHeader] [Boolean] 머리말 포함 여부값
     */
    fun setIncludeHeader(includeHeader: Boolean) {
        value = set(value, 1, includeHeader)
    }

    /**
     * 꼬리말 포함 여부를 반환하는 함수
     * bit 2
     *
     * @return [Boolean] 꼬리말 포함 여부 반환
     */
    fun isIncludeFooter() : Boolean = get(value, 2)

    /**
     * 꼬리말 포함 여부를 설정하는 함수
     * bit 2
     *
     * @param [includeFooter] [Boolean] 꼬리말 포함 여부값
     */
    fun setIncludeFooter(includeFooter: Boolean) {
        value = set(value, 2, includeFooter)
    }

    /**
     * 채울 영역을 반환하는 함수
     * bit 3-4
     *
     * @return [HWPFillArea] 채울 영역 반환
     */
    fun getFillArea() : HWPFillArea = HWPFillArea.valueOf(get(value, 3,4).toByte())

    /**
     * 채울 영역을 설정하는 함수
     * bit 3-4
     *
     * @param [fillArea] [HWPFillArea] 채울 영역값
     */
    fun setFillArea(fillArea: HWPFillArea) {
        value = set(value, 3, 4, fillArea.value.toInt())
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPPageBorderFillProperty] 생성된 객체 반환
         */
        fun build(positionCriterion: HWPPositionCriterion = HWPPositionCriterion.MainText,
                  includeHeader: Boolean = false,
                  includeFooter: Boolean = false,
                  fillArea: HWPFillArea = HWPFillArea.Paper)
                : HWPPageBorderFillProperty = HWPPageBorderFillProperty().apply {
            setPositionCriterion(positionCriterion)
            setIncludeHeader(includeHeader)
            setIncludeFooter(includeFooter)
            setFillArea(fillArea)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPPageBorderFillProperty] 생성된 객체 반환
         */
        fun build(value: Long = 0) : HWPPageBorderFillProperty = HWPPageBorderFillProperty().apply {
            this.value = value
        }
    }
}

/**
 * 쪽 테두리/배경을 나타내는 객체
 * Tag ID: HWPTAG_PAGE_BORDER_FILL [PAGE_BORDER_FILL]
 * 12 bytes
 *
 * @author accforaus
 *
 * @property [property] [HWPPageBorderFillProperty], 속성 (UINT32 - unsigned 4 bytes)
 * @property [leftGap] [Int], 테두리/배걍 위치 왼쪽 긴격 (HWPUNIT16 - unsigned 2 bytes)
 * @property [rightGap] [Int], 테두리/배걍 위치 오른쪽 긴격 (HWPUNIT16 - unsigned 2 bytes)
 * @property [topGap] [Int], 테두리/배걍 위치 위쪽 긴격 (HWPUNIT16 - unsigned 2 bytes)
 * @property [bottomGap] [Int], 테두리/배걍 위치 아래쪽 긴격 (HWPUNIT16 - unsigned 2 bytes)
 * @property [borderFillId] [Int], 테두리/배경[HWPBorderFill] ID (UINT16 - unsigned 2 bytes)
 */
@LinkID class HWPPageBorderFill {
    var property: HWPPageBorderFillProperty = HWPPageBorderFillProperty()
    var leftGap: Int = 0
    var rightGap: Int = 0
    var topGap: Int = 0
    var bottomGap: Int = 0
    @ID(IDTypes.BorderFill)
    var borderFillId: Int = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPPageBorderFill] 복사된 객체 반환
     */
    fun copy() : HWPPageBorderFill = HWPPageBorderFill().also {
        it.property.value = this.property.value
        it.leftGap = this.leftGap
        it.rightGap = this.rightGap
        it.topGap = this.topGap
        it.bottomGap = this.bottomGap
        it.borderFillId = this.borderFillId
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPPageBorderFill] 생성된 객체 반환
         */
        fun build(property: HWPPageBorderFillProperty = HWPPageBorderFillProperty.build(),
                  leftGap: Int = 0, rightGap: Int = 0, topGap: Int = 0, bottomGap: Int = 0,
                  borderFillId: Int = 0)
                : HWPPageBorderFill = HWPPageBorderFill().apply {
            this.property = property
            this.leftGap = leftGap
            this.rightGap = rightGap
            this.topGap = topGap
            this.bottomGap = bottomGap
            this.borderFillId = borderFillId
        }
    }
}

/**
 * 용지 방향
 *
 * @author accforaus
 *
 * @property [value] [Byte], 용지 방향 값
 */
enum class HWPPaperDirection(v: Byte) {
    /**
     * 좁게
     */
    Portrait(0.toByte()),
    /**
     * 넓게
     */
    Landscape(1.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPPaperDirection] enum 값
         */
        fun valueOf(v: Byte) : HWPPaperDirection {
            for (pd in values())
                if (pd.value == v)
                    return pd
            return Portrait
        }
    }
}

/**
 * 제책 방법
 *
 * @author accforaus
 *
 * @property [value] [Byte], 제책 방법 값
 */
enum class HWPMakingBookMethod(v: Byte) {
    /**
     * 한쪽 편집
     */
    OneSideEditing(0.toByte()),
    /**
     * 맞쪽 편집
     */
    BothSideEditing(1.toByte()),
    /**
     * 위로 넘기기
     */
    BackFlip(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPMakingBookMethod] enum 값
         */
        fun valueOf(v: Byte) : HWPMakingBookMethod {
            for (mbm in values())
                if (mbm.value == v)
                    return mbm
            return OneSideEditing
        }
    }
}

/**
 * 용지 설정 속성을 나타내는 객체
 * UINT32 - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 용지 설정 속성값
 */
class HWPPageDefProperty {
    var value: Long = 0
        set(newValue) {
            field = newValue
            _paperDirection = getPaperDirection()
            _makingBookMethod = getMakingBookMethod()
        }
    private var _paperDirection: HWPPaperDirection = HWPPaperDirection.Portrait
    private var _makingBookMethod: HWPMakingBookMethod = HWPMakingBookMethod.OneSideEditing
    /**
     * 용지 방향을 반환하는 함수
     * bit 0
     *
     * @return [HWPPaperDirection] 용지 방향 반환
     */
    fun getPaperDirection() : HWPPaperDirection = when (get(value, 0)) {
        true -> HWPPaperDirection.Portrait
        false -> HWPPaperDirection.Landscape
    }

    /**
     * 용지 방향을 설정하는 함수
     * bit 0
     *
     * @param [paperDirection] [HWPPaperDirection] 용지 방향값
     */
    fun setPaperDirection(paperDirection: HWPPaperDirection) {
        if (paperDirection == HWPPaperDirection.Portrait)
            value = set(value, 0, false)
        else
            value = set(value, 0, true)
    }

    /**
     * 제책 방법을 반환하는 함수
     * bit 1-2
     *
     * @return [HWPMakingBookMethod] 제책 방법 반환
     */
    fun getMakingBookMethod() : HWPMakingBookMethod = HWPMakingBookMethod.valueOf(get(value,1,2).toByte())

    /**
     * 제책 방법을 설정하는 함수
     * bit 1-2
     *
     * @param [makingBookMethod] [HWPMakingBookMethod] 제책 방법값
     */
    fun setMakingBookMethod(makingBookMethod: HWPMakingBookMethod) {
        value = set(value, 1, 2, makingBookMethod.value.toInt())
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPPageDefProperty] 생성된 객체 반환
         */
        fun build(paperDirection: HWPPaperDirection = HWPPaperDirection.Portrait,
                  makingBookMethod: HWPMakingBookMethod = HWPMakingBookMethod.OneSideEditing)
                : HWPPageDefProperty = HWPPageDefProperty().apply {
            setPaperDirection(paperDirection)
            setMakingBookMethod(makingBookMethod)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPPageDefProperty] 생성된 객체 반환
         */
        fun build(value: Long = 0) : HWPPageDefProperty = HWPPageDefProperty().apply {
            this.value = value
        }
    }
}

/**
 * 용지 설정을 나타내는 객체
 * Tag ID: HWPTAG_PAGE_DEF [PAGE_DEF]
 * 40 bytes
 *
 * @author accforaus
 *
 * @property [paperWidth] [Long], 용지 가로 크기 (HWPUNIT - unsigned 4 bytes)
 * @property [paperHeight] [Long], 용지 세로 크기 (HWPUNIT - unsigned 4 bytes)
 * @property [leftMargin] [Long], 왼쪽 여백 (HWPUNIT - unsigned 4 bytes)
 * @property [topMargin] [Long], 위쪽 여백 (HWPUNIT - unsigned 4 bytes)
 * @property [bottomMargin] [Long], 아래쪽 여백 (HWPUNIT - unsigned 4 bytes)
 * @property [rightMargin] [Long], 오른쪽 여백 (HWPUNIT - unsigned 4 bytes)
 * @property [headerMargin] [Long], 머리말 여백 (HWPUNIT - unsigned 4 bytes)
 * @property [footerMargin] [Long], 꼬리말 여백 (HWPUNIT - unsigned 4 bytes)
 * @property [gutterMargin] [Long], 제본 여백 (HWPUNIT - unsigned 4 bytes)
 * @property [property] [Long], 속성 (UINT32 - unsigned 4 bytes)
 */
class HWPPageDef {
    var paperWidth: Long = 0
    var paperHeight: Long = 0
    var leftMargin: Long = 0
    var rightMargin: Long = 0
    var topMargin: Long = 0
    var bottomMargin: Long = 0
    var headerMargin: Long = 0
    var footerMargin: Long = 0
    var gutterMargin: Long = 0
    var property: HWPPageDefProperty = HWPPageDefProperty()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPPageDef] 복사된 객체 반환
     */
    fun copy() : HWPPageDef = HWPPageDef().also {
        it.paperWidth = this.paperWidth
        it.paperHeight = this.paperHeight
        it.leftMargin = this.leftMargin
        it.rightMargin = this.rightMargin
        it.topMargin = this.topMargin
        it.bottomMargin = this.bottomMargin
        it.headerMargin = this.headerMargin
        it.footerMargin = this.footerMargin
        it.gutterMargin = this.gutterMargin
        it.property.value = this.property.value
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPPageDef] 생성된 객체 반환
         */
        fun build(paperWidth: Long = 0, paperHeight: Long = 0,
                  leftMargin: Long = 0, rightMargin: Long = 0,
                  topMargin: Long = 0, bottomMargin: Long = 0,
                  headerMargin: Long = 0, footerMargin: Long = 0,
                  gutterMargin: Long = 0,
                  property: HWPPageDefProperty = HWPPageDefProperty.build())
                : HWPPageDef = HWPPageDef().apply {
            this.paperWidth = paperWidth
            this.paperHeight = paperHeight
            this.leftMargin = leftMargin
            this.rightMargin = rightMargin
            this.topMargin = topMargin
            this.bottomMargin = bottomMargin
            this.headerMargin = headerMargin
            this.footerMargin = footerMargin
            this.gutterMargin = gutterMargin
            this.property = property
        }
    }
}
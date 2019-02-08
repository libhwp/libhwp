package com.tang.hwplib.objects.docinfo

import com.tang.hwplib.objects.docinfo.charshape.*
import com.tang.hwplib.objects.etc.Color4Byte
import com.tang.hwplib.objects.etc.CHAR_SHAPE
/**
 * 글자 모양을 나타내는 객체
 * Tag ID: HWPTAG_CHAR_SHAPE [CHAR_SHAPE]
 * 72 byte
 *
 * @author accforaus
 *
 * @property [faceNameIds] [HWPFaceNameIds], 언어별 글꼴 ID(FaceID) 참조값 (WORD array[7] - unsigned 14 bytes)
 * @property [ratios] [HWPRatios], 언어별 장평 50% - 200% (UINT8 array[7] - unsigned 7 bytes)
 * @property [charSpaces] [HWPCharSpaces], 언어별 자간 -50% ~ 50% (INT8 array[7] - signed 7 bytes)
 * @property [relativeSizes] [HWPRelativeSizes], 언어별 상대 크기 10% ~ 250% (UINT8 array[7] - unsigned 7 bytes)
 * @property [charOffsets] [HWPCharOffsets], 언어별 글자 위치 -100% ~ 100% (INT8 array[7] - signed 7 bytes)
 * @property [baseSize] [Int], 기준 크기 0pt ~ 4096pt (INT32 - signed 4 bytes)
 * @property [property] [HWPCharShapeProperty], 속성 (UINT32 - unsigned 4 bytes)
 * @property [shadowGap1] [Byte], 그림자 간격1 -100% ~ 100% (INT8 - signed 1 byte)
 * @property [shadowGap2] [Byte], 그림자 간격2 -100% ~ 100% (INT8 - signed 1 byte)
 * @property [charColor] [Color4Byte], 글자 색 (COLORREF - unsigned 4 bytes)
 * @property [underLineColor] [Color4Byte], 밑줄 색 (COLORREF - unsigned 4 bytes)
 * @property [shadeColor] [Color4Byte], 음영 색 (COLORREF - unsigned 4 bytes)
 * @property [shadowColor] [Color4Byte], 그림자 색 (COLORREF - unsigned 4 bytes)
 * @property [borderFillId] [Int], 글자 테두리/배경 ID(CharShapeBorderFill) 참조 값 [>=5.0.2.1] (UINT16 - unsigned 2 bytes)
 * @property [strikeLineColor] [Color4Byte], 취소선 색 [>=5.0.3.0] (COLORREF - unsigned 4 bytes)
 */
class HWPCharShape {
    var faceNameIds: HWPFaceNameIds = HWPFaceNameIds()
    var ratios: HWPRatios = HWPRatios()
    var charSpaces: HWPCharSpaces = HWPCharSpaces()
    var relativeSizes: HWPRelativeSizes = HWPRelativeSizes()
    var charOffsets: HWPCharOffsets = HWPCharOffsets()
    var baseSize: Int = 0
    var property: HWPCharShapeProperty = HWPCharShapeProperty()
    var shadowGap1: Byte = 0
    var shadowGap2: Byte = 0
    var charColor: Color4Byte = Color4Byte()
    var underLineColor: Color4Byte = Color4Byte()
    var shadeColor: Color4Byte = Color4Byte()
    var shadowColor: Color4Byte = Color4Byte()
    var borderFillId: Int = 0
    var strikeLineColor: Color4Byte = Color4Byte()
}
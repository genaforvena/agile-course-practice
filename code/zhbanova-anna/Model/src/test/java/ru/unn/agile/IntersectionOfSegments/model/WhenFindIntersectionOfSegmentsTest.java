package ru.unn.agile.IntersectionOfSegments.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class WhenFindIntersectionOfSegmentsTest {
    private enum TypeOfTestSegments {
        not_intersect, parallel, not_intersect_and_lie_on_same_line,
        have_common_end, intersect_at_one_point,
        same,
        second_segment_contained_in_first, first_segment_contained_in_second,
        have_common_part_end_first_segment, have_common_part_end_second_segment,
        not_intersect_when_one_segment_x_const, parallel_when_segments_x_const,
        have_common_end_when_one_segment_x_const,
        intersect_at_one_point_when_second_segment_x_const,
        intersect_at_one_point_when_first_segment_x_const,
        same_when_segments_x_const, one_segment_contained_in_another_when_segments_x_const,
        have_common_part_when_segments_x_const,
        parallel_have_common_end
    }

    public  WhenFindIntersectionOfSegmentsTest(final TypeOfTestSegments typeOfSegments) {
        this.typeOfSegments = typeOfSegments;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testTypeOfSegments() {
        return Arrays.asList(new Object[][]{
                {TypeOfTestSegments.not_intersect}, {TypeOfTestSegments.parallel_have_common_end},
                {TypeOfTestSegments.not_intersect_and_lie_on_same_line},
                {TypeOfTestSegments.have_common_end}, {TypeOfTestSegments.same},
                {TypeOfTestSegments.intersect_at_one_point}, {TypeOfTestSegments.parallel},
                {TypeOfTestSegments.second_segment_contained_in_first},
                {TypeOfTestSegments.first_segment_contained_in_second},
                {TypeOfTestSegments.have_common_part_end_first_segment},
                {TypeOfTestSegments.have_common_part_end_second_segment},
                {TypeOfTestSegments.not_intersect_when_one_segment_x_const},
                {TypeOfTestSegments.parallel_when_segments_x_const},
                {TypeOfTestSegments.have_common_end_when_one_segment_x_const},
                {TypeOfTestSegments.intersect_at_one_point_when_second_segment_x_const},
                {TypeOfTestSegments.intersect_at_one_point_when_first_segment_x_const},
                {TypeOfTestSegments.same_when_segments_x_const},
                {TypeOfTestSegments.one_segment_contained_in_another_when_segments_x_const},
                {TypeOfTestSegments.have_common_part_when_segments_x_const}
        });
    }

    @Test
    public void canReturnCorrectTypeOfIntersection() {
        initializeTestData(typeOfSegments);

        assertEquals(correctResult.getTypeOfIntersection(),
                resultIsIntersectedWith.getTypeOfIntersection());
    }

    @Test
    public void canReturnCorrectStartPointOfCommonPart() {
        initializeTestData(typeOfSegments);

        assertEquals(correctResult.getSegment().getStart(),
                resultIsIntersectedWith.getSegment().getStart());
    }

    @Test
    public void canReturnCorrectFinishPointOfCommonPart() {
        initializeTestData(typeOfSegments);

        assertEquals(correctResult.getSegment().getFinish(),
                resultIsIntersectedWith.getSegment().getFinish());
    }

    @Test
    public void canReturnCorrectLengthCommonPart() {
        initializeTestData(typeOfSegments);

        assertEquals(correctLengthOfCommonPart,
                resultIsIntersectedWith.getSegment().getLengthSegment(), 0.0);
    }

    private void initializeTestData(final TypeOfTestSegments typeOfSegments) {
        switch (typeOfSegments) {
            case not_intersect: case parallel:
            case not_intersect_and_lie_on_same_line:
            case not_intersect_when_one_segment_x_const:
            case parallel_when_segments_x_const:
                setUpTestDataWhenSegmentsNotIntersect(typeOfSegments);
                break;
            case have_common_end: case intersect_at_one_point:
            case have_common_end_when_one_segment_x_const:
            case intersect_at_one_point_when_second_segment_x_const:
            case intersect_at_one_point_when_first_segment_x_const:
                setUpTestDataWhenSegmentsIntersectInOnePoint(typeOfSegments);
                break;
            default:
                setUpTestDataWhenSegmentsHaveCommonPart(typeOfSegments);
                break;
        }
        resultIsIntersectedWith = testSegment1.isIntersectedWith(testSegment2);
    }

    private void setUpTestDataWhenSegmentsNotIntersect(
            final TypeOfTestSegments typeOfSegments) {
        switch (typeOfSegments) {
            case not_intersect:
                setUpSegmentsWhichNotIntersect();
                break;
            case not_intersect_and_lie_on_same_line:
                setUpSegmentsWhichNotIntersectAndLieOnSameLine();
                break;
            case parallel:
                setUpParallelSegments();
                break;
            case not_intersect_when_one_segment_x_const:
                setUpSegmentsWhichNotIntersectWhenOneSegmentXconst();
                break;
            default: //parallel_when_segments_x_const
                setUpParallelSegmentsWhenSegmentXconst();
                break;
        }
    }

    private void setUpTestDataWhenSegmentsIntersectInOnePoint(
            final TypeOfTestSegments typeOfSegments) {
        switch (typeOfSegments) {
            case have_common_end:
                setUpSegmentsWhichHaveCommonEnd();
                break;
            case intersect_at_one_point:
                setUpSegmentsWhichIntersectAtOnePoint();
                break;
            case have_common_end_when_one_segment_x_const:
                setUpSegmentsWhichHaveCommonEndWhenOneSegmentXconst();
                break;
            case intersect_at_one_point_when_second_segment_x_const:
                setUpSegmentsWhichIntersectAtOnePointWhenSecondSegmentXconst();
                break;
            default: //intersect_at_one_point_when_first_segment_x_const
                setUpSegmentsWhichIntersectAtOnePointWhenFirstSegmentXconst();
                break;
        }
    }

    private void setUpTestDataWhenSegmentsHaveCommonPartAndXconst(
            final TypeOfTestSegments typeOfSegments) {
        switch (typeOfSegments) {
            case same_when_segments_x_const:
                setUpTheSameSegmentsWhenSegmentsXconst();
                break;
            case one_segment_contained_in_another_when_segments_x_const:
                setUpSegmentsWhichOneContainedInAnotherWhenSegmentsXconst();
                break;
            case have_common_part_when_segments_x_const:
                setUpSegmentsWithCommonPartWhenSegmentsXconst();
                break;
            default:
                break;
        }
    }

    private void setUpTestDataWhenSegmentsHaveCommonPart(
            final TypeOfTestSegments typeOfSegments) {
        switch (typeOfSegments) {
            case same:
                setUpTheSameSegments(); break;
            case second_segment_contained_in_first:
                setUpSegmentsWhichSecondSegmentContainedInFirst(); break;
            case first_segment_contained_in_second:
                setUpSegmentsWhichFirstSegmentContainedInSecond(); break;
            case have_common_part_end_first_segment:
                setUpSegmentsWithCommonPartEndFirstSegment(); break;
            case have_common_part_end_second_segment:
                setUpSegmentsWithCommonPartEndSecondSegment(); break;
            case parallel_have_common_end:
                setUpSegmentsParallelWithCommonEnd(); break;
            default:
                setUpTestDataWhenSegmentsHaveCommonPartAndXconst(typeOfSegments);
                break;
        }
    }

    private void setUpSegmentsParallelWithCommonEnd() {
        testSegment1 = new Segment(new Point(1.0, 1.0), new Point(2.0, 2.0));
        testSegment2 = new Segment(new Point(2.0, 2.0), new Point(3.0, 3.0));
        correctResult = new Intersection(TypeOfIntersection.SegmentsHaveCommonPart,
                new Segment(testSegment1.getFinish(), testSegment2.getStart()));
        correctLengthOfCommonPart = 0;
    }

    private void setUpSegmentsWhichNotIntersect() {
        testSegment1 = new Segment(new Point(1.0, 2.0), new Point(5.0, 4.0));
        testSegment2 = new Segment(new Point(3.0, 1.0), new Point(6.0, 1.0));
        correctResult = new Intersection(TypeOfIntersection.NotIntersection,
                new Segment(null, null));
        correctLengthOfCommonPart = -1;
    }

    private void setUpSegmentsWhichNotIntersectAndLieOnSameLine() {
        testSegment1 = new Segment(new Point(1.0, 1.0), new Point(5.0, 5.0));
        testSegment2 = new Segment(new Point(6.0, 6.0), new Point(7.0, 7.0));
        correctResult = new Intersection(TypeOfIntersection.NotIntersection,
                new Segment(null, null));
        correctLengthOfCommonPart = -1;
    }
    private void setUpParallelSegments() {
        testSegment1 = new Segment(new Point(0.0, 0.0), new Point(5.0, 5.0));
        testSegment2 = new Segment(new Point(0.0, 1.0), new Point(5.0, 6.0));
        correctResult = new Intersection(TypeOfIntersection.NotIntersection,
                new Segment(null, null));
        correctLengthOfCommonPart = -1;
    }

    private void setUpSegmentsWhichHaveCommonEnd() {
        testSegment1 = new Segment(new Point(5.2, 3.0), new Point(5.0, 5.0));
        testSegment2 = new Segment(new Point(0.0, 1.0), new Point(5.2, 3.0));
        correctResult = new Intersection(TypeOfIntersection.IntersectionInOnePoint,
                new Segment(testSegment1.getStart(), testSegment1.getStart()));
        correctLengthOfCommonPart = 0;
    }

    private void setUpSegmentsWhichIntersectAtOnePoint() {
        testSegment1 = new Segment(new Point(0.0, 0.0), new Point(4.0, 2.0));
        testSegment2 = new Segment(new Point(0.0, 2.0), new Point(4.0, 0.0));
        correctResult = new Intersection(TypeOfIntersection.IntersectionInOnePoint,
                new Segment(new Point(2.0, 1.0), new Point(2.0, 1.0)));
        correctLengthOfCommonPart = 0;
    }

    private void setUpTheSameSegments() {
        testSegment1 = new Segment(new Point(10.0, 5.0), new Point(5.0, 5.0));
        testSegment2 = new Segment(new Point(10.0, 5.0), new Point(5.0, 5.0));
        correctResult = new Intersection(TypeOfIntersection.OnePartOfOther,
                testSegment1);
        correctLengthOfCommonPart = 5;
    }

    private void setUpSegmentsWhichSecondSegmentContainedInFirst() {
        testSegment1 = new Segment(new Point(0.0, 0.0), new Point(10.0, 10.0));
        testSegment2 = new Segment(new Point(2.0, 2.0), new Point(5.0, 5.0));
        correctResult = new Intersection(TypeOfIntersection.OnePartOfOther, testSegment2);
        correctLengthOfCommonPart = Math.sqrt(18);
    }

    private void setUpSegmentsWhichFirstSegmentContainedInSecond() {
        testSegment1 = new Segment(new Point(1.0, 1.0), new Point(10.0, 10.0));
        testSegment2 = new Segment(new Point(0.0, 0.0), new Point(20.0, 20.0));
        correctResult = new Intersection(TypeOfIntersection.OnePartOfOther, testSegment1);
        correctLengthOfCommonPart = Math.sqrt(162);
    }

    private void setUpSegmentsWithCommonPartEndFirstSegment() {
        testSegment1 = new Segment(new Point(0.0, 0.0), new Point(5.0, 5.0));
        testSegment2 = new Segment(new Point(2.0, 2.0), new Point(8.0, 8.0));
        correctResult = new Intersection(TypeOfIntersection.SegmentsHaveCommonPart,
                new Segment(testSegment2.getStart(), testSegment1.getFinish()));
        correctLengthOfCommonPart = Math.sqrt(18);
    }

    private void setUpSegmentsWithCommonPartEndSecondSegment() {
        testSegment1 = new Segment(new Point(2.0, 2.0), new Point(8.0, 8.0));
        testSegment2 = new Segment(new Point(0.0, 0.0), new Point(5.0, 5.0));
        correctResult = new Intersection(TypeOfIntersection.SegmentsHaveCommonPart,
                new Segment(testSegment1.getStart(), testSegment2.getFinish()));
        correctLengthOfCommonPart = Math.sqrt(18);
    }

    private void setUpSegmentsWhichNotIntersectWhenOneSegmentXconst() {
        testSegment1 = new Segment(new Point(0.0, 0.0), new Point(5.0, 5.0));
        testSegment2 = new Segment(new Point(6.0, 2.0), new Point(6.0, 8.0));
        correctResult = new Intersection(TypeOfIntersection.NotIntersection,
                new Segment(null, null));
        correctLengthOfCommonPart = -1;
    }

    private void setUpParallelSegmentsWhenSegmentXconst() {
        testSegment1 = new Segment(new Point(2.0, 0.0), new Point(2.0, 5.0));
        testSegment2 = new Segment(new Point(6.0, 2.0), new Point(6.0, 8.0));
        correctResult = new Intersection(TypeOfIntersection.NotIntersection,
                new Segment(null, null));
        correctLengthOfCommonPart = -1;
    }

    private void setUpSegmentsWhichHaveCommonEndWhenOneSegmentXconst() {
        testSegment1 = new Segment(new Point(5.2, 3.0), new Point(5.0, 5.0));
        testSegment2 = new Segment(new Point(5.2, 3.0), new Point(5.2, 2.0));
        correctResult = new Intersection(TypeOfIntersection.IntersectionInOnePoint,
                new Segment(testSegment1.getStart(), testSegment1.getStart()));
        correctLengthOfCommonPart = 0;
    }

    private void setUpSegmentsWhichIntersectAtOnePointWhenSecondSegmentXconst() {
        testSegment1 = new Segment(new Point(0.0, 0.0), new Point(4.0, 2.0));
        testSegment2 = new Segment(new Point(2.0, 5.0), new Point(2.0, 0.0));
        correctResult = new Intersection(TypeOfIntersection.IntersectionInOnePoint,
                new Segment(new Point(2.0, 1.0), new Point(2.0, 1.0)));
        correctLengthOfCommonPart = 0;
    }

    private void setUpSegmentsWhichIntersectAtOnePointWhenFirstSegmentXconst() {
        testSegment1 = new Segment(new Point(2.0, 5.0), new Point(2.0, 0.0));
        testSegment2 = new Segment(new Point(0.0, 0.0), new Point(4.0, 2.0));
        correctResult = new Intersection(TypeOfIntersection.IntersectionInOnePoint,
                new Segment(new Point(2.0, 1.0), new Point(2.0, 1.0)));
        correctLengthOfCommonPart = 0;
    }

    private void setUpTheSameSegmentsWhenSegmentsXconst() {
        testSegment1 = new Segment(new Point(10.0, 5.0), new Point(10.0, 2.0));
        testSegment2 = new Segment(new Point(10.0, 5.0), new Point(10.0, 2.0));
        correctResult = new Intersection(TypeOfIntersection.OnePartOfOther,
                new Segment(new Point(10.0, 2.0), new Point(10.0, 5.0)));
        correctLengthOfCommonPart = 3;
    }

    private void setUpSegmentsWhichOneContainedInAnotherWhenSegmentsXconst() {
        testSegment1 = new Segment(new Point(2.0, 0.0), new Point(2.0, 10.0));
        testSegment2 = new Segment(new Point(2.0, 2.0), new Point(2.0, 5.0));
        correctResult = new Intersection(TypeOfIntersection.OnePartOfOther, testSegment2);
        correctLengthOfCommonPart = 3;
    }

    private void setUpSegmentsWithCommonPartWhenSegmentsXconst() {
        testSegment1 = new Segment(new Point(1.0, 0.0), new Point(1.0, 5.0));
        testSegment2 = new Segment(new Point(1.0, 2.0), new Point(1.0, 8.0));
        correctResult = new Intersection(TypeOfIntersection.SegmentsHaveCommonPart,
                new Segment(testSegment2.getStart(), testSegment1.getFinish()));
        correctLengthOfCommonPart = 3;
    }

    private TypeOfTestSegments typeOfSegments;
    private Segment testSegment1;
    private Segment testSegment2;
    private Intersection resultIsIntersectedWith;
    private Intersection correctResult;
    private double correctLengthOfCommonPart;
}

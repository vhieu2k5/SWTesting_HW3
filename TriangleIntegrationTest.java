import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

public class TriangleIntegrationTest {

    @Test
    public void testRealEquilateral() {
        Triangle t = new Triangle(3, 3, 3);
        List<String> flags = t.getTypeFlags();
        assertEquals(1, flags.size());
        assertTrue(flags.contains("equilateral"));
    }

    @Test
    public void testRealIsosceles() {
        Triangle t = new Triangle(3, 3, 4);
        List<String> flags = t.getTypeFlags();
        assertEquals(1, flags.size());
        assertTrue(flags.contains("isosceles"));
    }

    @Test
    public void testRealRightAngledScalene() {
        Triangle t = new Triangle(3, 4, 5);
        List<String> flags = t.getTypeFlags();
        assertEquals(2, flags.size());
        assertTrue(flags.contains("scalene"));
        assertTrue(flags.contains("right-angled"));
    }

    @Test
    public void testIntegrationImpossibleTriangles() {
        Triangle negativeSide = new Triangle(-1, 5, 5);
        assertTrue(negativeSide.getTypeFlags().isEmpty());
    }
}
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.After;  // For cleaning up the Mockito session
import org.junit.Before; // For starting the Mockito session
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

// NOTICE: The @RunWith line is completely REMOVED. This bypasses the initialization bug!
public class TriangleUnitTest {
    
    @Mock
    private Triangle mockTriangle;
    
    @InjectMocks
    private TriangleService triangleService;

    // This handles the Mockito 5.x session lifecycles
    private AutoCloseable closeableSession;

    @Before
    public void setUp() {
        // This explicitly kicks off the 5.18.0 MockMaker engine safely
        closeableSession = MockitoAnnotations.openMocks(this); 
    }

    @After
    public void tearDown() throws Exception {
        // This releases the memory hooks cleanly after each test case
        if (closeableSession != null) {
            closeableSession.close();
        }
    }

    @Test   
    public void testEquilateralTriangle(){
        when(mockTriangle.getTypeFlags()).thenReturn(Collections.singletonList("equilateral"));

        List<String> result = triangleService.processTriangle(mockTriangle);

        assertTrue(result.contains("equilateral"));
        assertEquals(1, result.size());
    }
    
    @Test
    public void testIsoscelesTriangle() {
        when(mockTriangle.getTypeFlags()).thenReturn(Collections.singletonList("isosceles"));

        List<String> result = triangleService.processTriangle(mockTriangle);
        
        assertTrue(result.contains("isosceles"));
        assertEquals(1, result.size());
    }

    @Test
    public void testRightAngledAndScaleneTriangle() {
        when(mockTriangle.getTypeFlags()).thenReturn(Arrays.asList("scalene", "right-angled"));

        List<String> result = triangleService.processTriangle(mockTriangle);
        
        assertTrue(result.contains("right-angled"));
        assertTrue(result.contains("scalene"));
        assertEquals(2, result.size());
    }

    @Test
    public void testBoundaryZeroSide() {
        when(mockTriangle.getTypeFlags()).thenReturn(Collections.<String>emptyList());

        List<String> result = triangleService.processTriangle(mockTriangle);
        
        assertTrue(result.isEmpty());
    }

    @Test
    public void testBoundaryLineTriangle() {
        when(mockTriangle.getTypeFlags()).thenReturn(Collections.<String>emptyList());

        List<String> result = triangleService.processTriangle(mockTriangle);
        
        assertTrue(result.isEmpty());
    }
}
package java.util.lab4;
import static org.junit.Assert.*;
import org.junit.*;

import java.util.*;

public class IteratorTest {
    private List<String> list;
    private Iterator<String> itr;


    //set up a list before the tests run
    @Before
    public void InitialSetUp() {
        list = new ArrayList<String>();
        list.add("apple");
        list.add("orange");
        list.add("blueberry");
        list.add("banana");
        itr = list.iterator();
    }


    //-------------------------HAS NEXT------------------------------------------
    //Testing the hasNext(): function, there will be three tests TT,FT, TF


    // Test 1 -- Base Choice Coverage should be a happy path, so we choose C1-T, C5-T
    @Test
    public void testHasNext_BaseCase() {
        assertTrue(itr.hasNext());
    }


    // Test 2-- Case of C1-F, C5-T
    @Test
    public void testHasNext_FT() {
        itr.next();
        itr.next();
        itr.next();
        itr.next();
        assertFalse(itr.hasNext());
    }

    // Test 3 -- testHasNext_C5(): C1-T, C5-F
    @Test(expected= ConcurrentModificationException.class)
    public void testHasNext_TF()
    {
        list.add ("strawberry");
        itr.hasNext();
    }


    //------------------------TESTING NEXT()---------------------------------
    // Testing the next() function, there will be 4 tests TTT,FTT, TFT, TTF


    // Test 1-- Choose a base choice coverage with a happy path, so TTT
    @Test public void testNext_BaseCase()
    {
        assertEquals ("apple", itr.next());
    }


    // Test 2 --  C1-F, C2-F, C5-T
    @Test(expected= NoSuchElementException.class)
    public void testNext_FFT()
    {
        //consume all the elements
        itr.next();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
    }


    // Test 3-- TFT
    @Test public void testNext_TFT()
    {
        list = new ArrayList<String>();
        list.add (null);
        itr = list.iterator();
        assertNull (itr.next());
    }


    // Test 4 -- TFF
    @Test(expected=ConcurrentModificationException.class)
    public void testNext_TFF()
    {
        list.add ("strawberry");
        itr.next();
    }

    //------------------------TESTS FOR REMOVE ------------------------
    // There will be 6 Tests for remove(), TTTTT,FTTTT,TFTTT, TTFTT, TTTFT,TTTTF


    // Test 1 --  Base choice coverage, choose a happy path, so we pick TTTTT
    @Test public void testRemove_BaseCase()
    {
        itr.next();
        itr.remove();
        assertFalse (list.contains ("apple"));
    }


    // Test 2 - FTTTT
    @Test public void testRemove_FTTTT()
    {
        itr.next();
        itr.next();
        itr.next();
        itr.next();
        itr.remove();
        assertFalse (list.contains ("orange"));
    }

    // Test 3 -- TFTTT
    @Test public void testRemove_TFTTT()
    {
        list.add (null);
        list.add ("strawberry");
        itr = list.iterator();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
        itr.remove();
        assertFalse (list.contains (null));
    }

    // Test 4 - TTFTT
    @Test(expected=UnsupportedOperationException.class)
    public void testRemove_TTFTT()
    {
        list = Collections.unmodifiableList (list);
        itr = list.iterator();
        itr.next();
        itr.remove();
    }


    // Test 5-- TTTFT
    @Test (expected=IllegalStateException.class)
    public void testRemove_TTTFT()
    {
        itr.remove();
    }


    // Test 6 --  TTTTF
    @Test (expected= ConcurrentModificationException.class)
    public void testRemove_TTTTF()
    {
        itr.next();
        list.add ("strawberry");
        itr.remove();
    }
}






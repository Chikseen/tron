/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.github.mrpaulblack.tron;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {
    @Test public void serverHasAGreeting() {
        Server classUnderTest = new Server();
        assertNotNull("server should have a greeting", classUnderTest.getGreeting());
    }
}

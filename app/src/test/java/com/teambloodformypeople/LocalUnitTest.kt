package com.teambloodformypeople

import android.app.Activity
import android.app.Application
import android.bluetooth.BluetoothAdapter
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.hamcrest.CoreMatchers.`is` as matchIs
import org.mockito.Mockito.`when` as mockWhen

class LocalUnitTest {

    @Test fun mockFinalMethod() {
        val activity = mock(Activity::class.java)
        val app = mock(Application::class.java)
        mockWhen(activity.application).thenReturn(app)
        assertEquals(app, activity.application)
        verify(activity).application
        verifyNoMoreInteractions(activity)
    }

    @Test fun mockFinalClass() {
        val adapter = mock(BluetoothAdapter::class.java)
        mockWhen(adapter.isEnabled).thenReturn(true)
        assertTrue(adapter.isEnabled)
        verify(adapter).isEnabled
        verifyNoMoreInteractions(adapter)
    }
}
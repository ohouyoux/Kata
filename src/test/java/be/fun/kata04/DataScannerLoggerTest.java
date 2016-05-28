package be.fun.kata04;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { LoggerFactory.class })
public class DataScannerLoggerTest {

    private DataScannerLogger<String, RuntimeException> systemUnderTest;

    private Logger loggerMock;

    private DataScanner<String, RuntimeException> scannerMock;

    @Before
    public void setUp() {
        loggerMock = mock(Logger.class);
        mockStatic(LoggerFactory.class);
        when(LoggerFactory.getLogger(DataScannerLogger.class)).thenReturn(loggerMock);

        scannerMock = mock(DataScanner.class);
        when(scannerMock.scan()).thenReturn("This is a test");
        systemUnderTest = new DataScannerLogger(scannerMock);
    }

    @Test
    public void shouldLogScannedMessage() {
        String result = systemUnderTest.scan();

        assertThat(result).isEqualTo("This is a test");
        verify(loggerMock).info("This is a test");
    }
}
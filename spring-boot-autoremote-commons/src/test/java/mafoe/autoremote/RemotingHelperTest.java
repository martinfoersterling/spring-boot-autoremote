package mafoe.autoremote;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemotingHelperTest {

    @Test
    public void serviceInterfaceToEndpoint() {
        String name = RemotingHelper.serviceInterfaceToEndpoint(TestService.class);
        assertEquals("/TestService", name);
    }

    private static class TestService {

    }
}